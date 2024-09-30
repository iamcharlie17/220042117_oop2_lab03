import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private double amount;
    private String description;

    public Transaction(LocalDate date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
