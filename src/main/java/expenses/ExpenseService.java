package expenses;

import java.io.IOException;
import java.time.LocalDateTime;

public class ExpenseService {
    private final ExpenseStorage storage;

    public ExpenseService() {
        this.storage = new ExpenseStorage();
    }

    /**
     * Adds an expense entry.
     *
     * @param description user description of the entry
     * @param amount money the user spent
     * @return the newly created expense entry
     */
    public ExpenseData add(String description, int amount) {
        String cleanDescription = "";
        if (description != null) {
            cleanDescription = description.trim();
        }

        if (cleanDescription.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        ExpenseData data = new ExpenseData();
        data.setId(storage.getNextId());
        data.setDescription(cleanDescription);
        data.setAmount(amount);
        data.setDate(LocalDateTime.now());
        storage.add(data);

        try {
            ExpenseStorage.writer();
        } catch (IOException e) {
            throw new RuntimeException("Failed to persist expense data.", e);
        }

        return data;
    }

    public void list() {
        ExpenseStorage.findAll().forEach(System.out::println);
    }

    // TODO: implement summary() method
    // TODO: implement delete() method
    // TODO: implement summaryWithMonth() method

    @Override
    public String toString() {
        return "ExpenseService{entries=" + ExpenseStorage.findAll().size() + "}";
    }
}

