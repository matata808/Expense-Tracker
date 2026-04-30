package expenses;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        System.out.print(this);
    }

    // TODO: implement summary() method
    public void summary(){
        double total = 0.0;
        for (ExpenseData data : ExpenseStorage.expenses) {
            total += data.getAmount();
        }
        System.out.println("Total expenses: " + total);
    }
    public void remove(int id){
        ExpenseStorage.expenses.removeIf(e -> e.getId() == id);
        try {
            ExpenseStorage.writer();
        } catch (IOException e) {
            throw new RuntimeException("Failed to persist expense data.", e);
        }
    }
    // TODO: implement summaryWithMonth() method

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("# ID  Date       Description  Amount ").append(System.lineSeparator());
        sb.append("# -----------------------------------").append(System.lineSeparator());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (ExpenseData data : ExpenseStorage.expenses) {
            String date = data.getDate() == null ? "-" : data.getDate().format(dtf);
            sb.append("# ")
                    .append(data.getId()).append(' ')
                    .append(date).append(' ')
                    .append(data.getDescription()).append(' ')
                    .append(data.getAmount())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}

