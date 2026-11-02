import java.util.List;
import java.util.Objects;

public class FraudAnalyzer {
    private final List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions){
        Objects.requireNonNull(transactions);
        this.transactions = transactions;
    }
    public long countFrauds(){
        return transactions.stream().filter(Transaction::isFraud).count();
    }
    //Precisava fazer a fraude de maior valor
    //Precisa fazer o limite suspeito

}
