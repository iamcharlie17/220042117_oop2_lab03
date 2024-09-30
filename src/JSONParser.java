import java.util.List;
import java.util.ArrayList;
import org.json.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JSONParser implements BankStatementParser {
    @Override
    public List<Transaction> parse(String fileContent) {
        List<Transaction> transactions = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(fileContent);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            LocalDate date = LocalDate.parse(jsonObject.getString("date"), formatter);
            double amount = jsonObject.getDouble("amount");
            String description = jsonObject.getString("description");
            transactions.add(new Transaction(date, amount, description));
        }
        return transactions;
    }
}
