package expenses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExpenseData {
    private int id;
    private LocalDateTime date;
    private String description;
    private double amount;

    public ExpenseData(int id, String description, LocalDateTime date, double amount) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    public ExpenseData() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDateTime getDate() {return date;}
    public String getDescription() {return description;}
    public double getAmount() {return amount;}

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String dateString = (date == null) ? "-" : date.format(dtf);
        String desc = (description == null) ? "" : description;
        return String.format("Expense{id=%d, date=%s, description='%s', amount=%.2f}", id, dateString, desc, amount);
    }

}

