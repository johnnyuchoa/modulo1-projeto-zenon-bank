import org.jetbrains.annotations.Contract;

import java.math.BigDecimal;
import java.util.Objects;

public record Transaction(int step, TransactionType type, BigDecimal amount, BigDecimal amount1, TransactionCustomer origin, TransactionCustomer recipient, boolean isFraud, boolean isFlaggedFraud){
    public Transaction {
        if(step <=0 ) throw  new IllegalArgumentException("O valor de step deve ser positivo"+ step);
        if(amount.signum() <= 0 ) throw new IllegalArgumentException("O valor deve ser positivo"+amount);
    }
}