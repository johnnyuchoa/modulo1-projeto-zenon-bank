import java.util.List;

public record Transaction(String step, String type, String amount, String nameOrig, String oldbalanceOrg, String newbalanceOrig, String nameDest, String oldbalanceDest, String newbalanceDest, String isFraud, String isFlaggedFraud){
    public Transaction(List<String> values) {

        this(
                values.get(0),
                values.get(1),
                values.get(2),
                values.get(3),
                values.get(4),
                values.get(5),
                values.get(6),
                values.get(7),
                values.get(8),
                values.get(9),
                values.get(10)
        );
        validaStep(values);
    }
    private static void validaStep(List<String> valores){
        int i = Integer.parseInt(valores.get(0));
        if(i <= 0)
            System.out.println("Valor do campo Step incomum para linha."+valores.toString());
    }
}