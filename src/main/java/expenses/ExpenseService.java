package expenses;

import java.time.LocalDateTime;

public class ExpenseService  {
    private final ExpenseStorage storage;

    public ExpenseService(ExpenseStorage storage) {
        this.storage = storage;
    }

    public ExpenseService(){
        this.storage = new ExpenseStorage();
    }

    /**
     * adds an expense entry
     * @param description Users description of the entry
     * @param amount of money the user spent
     */
    public void add(String description, int amount){
        //user cannot enter empty or invalid description/amount
        if (description.isEmpty() || amount <= 0){ throw new IllegalArgumentException("Description and amount must be positive");}
        ExpenseData data = new ExpenseData();
        data.setId(storage.getNextId());
        data.setDescription(description);
        data.setAmount(amount);
        data.setDate(LocalDateTime.now());
        storage.add(data);
    }

    // is this really the safe implementation?
    public void list(){
        ExpenseStorage.findAll().forEach(System.out::println);
    }

    //TODO: implement summary() method

    //TODO: implement delete() method

    //TODO: implement summaryWithMonth() method

    @Override
    public String toString(){
        return "ExpenseService{entries=" + ExpenseStorage.findAll().size() + "}";
    }
}

