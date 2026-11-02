import java.math.BigDecimal;
import java.util.Objects;

public record TransactionCustomer(String nameOrig, BigDecimal oldbalanceOrg, BigDecimal newbalanceOrig) {
    public TransactionCustomer {
        Objects.requireNonNull(nameOrig);
        Objects.requireNonNull(oldbalanceOrg);
        Objects.requireNonNull(newbalanceOrig);
        if (oldbalanceOrg.signum() < 0) throw  new IllegalArgumentException("O valor deve ser positivo:"+oldbalanceOrg);
        if (newbalanceOrig.signum() < 0) throw  new IllegalArgumentException("O valor deve ser positivo:"+newbalanceOrig);
        if (nameOrig.trim().isEmpty()) throw  new IllegalArgumentException("Nao pode ser vazio:");
    }
}
