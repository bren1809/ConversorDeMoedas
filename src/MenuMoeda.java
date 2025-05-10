import java.sql.SQLOutput;
import java.util.Scanner;

public class MenuMoeda {
    private static final String[] CODIGOS = {"USD", "BRL", "BOB", "ARS", "CLP", "COP"};
    private static final String[] NOMES = {"Dólar Americano", "Real Brasileiro", "Boliviano",
            "Peso Argentino", "Peso Chileno", "Peso Colombiano"};

    private final Scanner scanner;

    public MenuMoeda() {
        this.scanner = new Scanner(System.in);
    }

    public int exibirMenu(String mensagem) {
        System.out.println("\n" + mensagem);
        for (int i = 0; i < CODIGOS.length; i++) {
            System.out.printf("(%d) %s (%s)\n", i + 1, NOMES[i], CODIGOS[i]);
        }
        System.out.printf("(%d) Sair\n", CODIGOS.length + 1);
        return scanner.nextInt();
    }

    public double lerValor() {
        System.out.print("\nDigite o valor a converter: ");
        return scanner.nextDouble();
    }

    public String getCodigoMoeda(int opcao) {
        return CODIGOS[opcao - 1];
    }

    public void close() {
        scanner.close();
    }
}
