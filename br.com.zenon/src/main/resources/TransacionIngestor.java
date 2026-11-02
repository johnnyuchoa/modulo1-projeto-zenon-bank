import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransacionIngestor {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:/Users/Johnny/Projetos/UNIPDS/data/paysim_with_bad_data.csv");
        String caminhoRedundante = "C:/Users/Johnny/Projetos/UNIPDS/data/paysim_with_bad_data.csv";

        System.out.println("Teste primario com leitura de buffer");
        BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
        while (br.readLine() != null);
        br.close();

        System.out.println("Imprimindo linhas lidas CSV");

        try {
            if(!path.equals(null))
                readLineBuffers(path).stream().limit(100).forEach(linha -> System.out.println(linha));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Carregando no Record");
        /*
        System.out.println("Teste primario com leitura de buffer");
        readLineBuffers(path).stream().limit(100).forEach(
                linha -> transactions.add(new Transaction(List.of(linha.split(",")))));
        System.out.println("Conteudo do transaction:");
        if(!transactions.isEmpty())
            transactions.forEach(t -> System.out.println(t.toString()));
        */
        try {
            System.out.println("SOLUÇAO OFICIAL");
            List<Transaction> transactions = new ArrayList<>(read(caminhoRedundante));
            System.out.println(transactions.size());
            transactions.forEach(System.out::println);
//            var fraudAnalyser = new FraudAnalyzer(transactions);
//            System.out.println("Total de fraudes"+fraudAnalyser.countFrauds());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Transaction> read(String filename){

        List<Transaction> transactions = new ArrayList<>();

        try (FileInputStream fis     = new FileInputStream(filename);
             Scanner scanner = new Scanner(fis)){

            int lineCount = 0;

            while(scanner.hasNextLine()){

                String line = scanner.nextLine();
                lineCount++;

                if(lineCount ==1000)
                    break;
                else{
                    if(!line.isEmpty())
                        transactions.add(parseTransection(line));
                    else
                        throw new IllegalArgumentException( "O campo Step não pode ser nulo ou vazio.");
                }
            }
        }catch (Exception ex) {
            throw new RuntimeException("Erro ao ler o arquivo?" + filename, ex);
        }
        return transactions;
    }

    private static Transaction parseTransection(String line) {
        try {
            String[] chunks = line.split(",");
            int step = Integer.parseInt(chunks[0]);
            TransactionType type = TransactionType.valueOf(chunks[1]);
            BigDecimal amount = new BigDecimal(chunks[2]);
            var origin = new TransactionCustomer(chunks[3], new BigDecimal(chunks[4]), new BigDecimal(chunks[5]));
            var recipient = new TransactionCustomer(chunks[6], new BigDecimal(chunks[7]), new BigDecimal(chunks[8]));
            boolean isFraud = "1".equals(chunks[9])?true:false;
            boolean isFlaggedFraud = "1".equals(chunks[10])?true:false;
            return new Transaction(step,type,amount, amount, origin,recipient, isFraud,isFlaggedFraud);
        } catch (IllegalArgumentException e) {
            System.out.println("Ao fazer o parse "+ line+ "|" + e);
            e.printStackTrace();
            return null;
        }
    }

    @org.jetbrains.annotations.NotNull
    @org.jetbrains.annotations.Contract("_ -> new")
    public static List<String> readLineBuffers(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
