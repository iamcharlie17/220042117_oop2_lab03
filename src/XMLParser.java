import java.util.List;
import java.util.ArrayList;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class XMLParser implements BankStatementParser {
    @Override
    public List<Transaction> parse(String fileContent) {
        List<Transaction> transactions = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(fileContent)));
            NodeList nodeList = doc.getElementsByTagName("transaction");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    LocalDate date = LocalDate.parse(element.getElementsByTagName("date").item(0).getTextContent(), formatter);
                    double amount = Double.parseDouble(element.getElementsByTagName("amount").item(0).getTextContent());
                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    transactions.add(new Transaction(date, amount, description));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
