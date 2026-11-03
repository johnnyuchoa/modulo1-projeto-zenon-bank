import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionListRepository {

    private final List<Transaction> transactions;

    public TransactionListRepository(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);
        this.transactions = transactions;
    }

    public void printFindByOrginName(String originName){
        transactions.forEach(transaction -> System.out.println(transaction.origin().nameOrig()));
    }
    public Optional<Transaction> findByOrginName(String originName){
        return transactions.stream().
                filter(transaction -> transaction.origin().nameOrig().equals(originName)).
                findFirst();
    }
}
