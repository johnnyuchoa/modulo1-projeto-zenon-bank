import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionIngestor {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:/Users/Johnny/Projetos/UNIPDS/data/cargaII.csv");

        BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
        while (br.readLine() != null);
        br.close();

        System.out.println("Imprimindo linhas lidas CSV");
        readLineBuffers(path).stream().limit(100).forEach(
                linha -> System.out.println(linha));
        System.out.println("Carregando no Record");
        List<Transaction> transactions = new ArrayList<>();
        readLineBuffers(path).stream().limit(100).forEach(
                linha -> transactions.add(new Transaction(List.of(linha.split(",")))));
        System.out.println("Conteudo do transaction:");
        transactions.forEach(t -> System.out.println(t.toString()));
    }
    public static List<String> readLineBuffers(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
