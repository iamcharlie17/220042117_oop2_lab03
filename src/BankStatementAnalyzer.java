import java.util.List;

public class BankStatementAnalyzer {
    private BankStatementParser parser;

    public BankStatementAnalyzer(BankStatementParser parser) {
        this.parser = parser;
    }

    public List<Transaction> analyze(String fileContent) {
        return parser.parse(fileContent);
    }

    public double calculateTotal(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public long getTransactionsInMonth(List<Transaction> transactions, int month, int year) {
        return transactions.stream()
                .filter(t -> t.getDate().getMonthValue() == month && t.getDate().getYear() == year)
                .count();
    }

    public List<Transaction> getTopExpenses(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .limit(10)
                .toList();
    }

    public String getMostSpentCategory(List<Transaction> transactions) {
        return "Most spent category placeholder";
    }
}
