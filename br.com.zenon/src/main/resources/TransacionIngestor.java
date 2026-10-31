import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
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
        System.out.println("SOLUÇAO OFICIAL");
        List<Transaction> transactions = new ArrayList<>(read(caminhoRedundante));
        System.out.println(transactions.size());
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
            }
        }catch (Exception ex) {
            throw new RuntimeException("Erro ao ler o arquivo?" + filename, ex);
        }
        return transactions;
    }

    @org.jetbrains.annotations.NotNull
    @org.jetbrains.annotations.Contract("_ -> new")
    public static List<String> readLineBuffers(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
