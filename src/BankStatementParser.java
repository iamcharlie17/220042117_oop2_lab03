import java.util.List;

public interface BankStatementParser {
    List<Transaction> parse(String fileContent);
}
