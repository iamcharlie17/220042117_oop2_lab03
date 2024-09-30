import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CSVParser implements BankStatementParser {
    @Override
    public List<Transaction> parse(String fileContent) {
        List<Transaction> transactions = new ArrayList<>();
        String[] lines = fileContent.split("\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (String line : lines) {
            String[] columns = line.split(",");
            LocalDate date = LocalDate.parse(columns[0], formatter);
            double amount = Double.parseDouble(columns[1]);
            String description = columns[2];
            transactions.add(new Transaction(date, amount, description));
        }
        return transactions;
    }
}
