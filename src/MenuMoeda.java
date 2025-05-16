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
        while (true) {
            try {
                System.out.println("\n" + mensagem);
                for (int i = 0; i < CODIGOS.length; i++) {
                    System.out.printf("(%d) %s (%s)\n", i + 1, NOMES[i], CODIGOS[i]);
                }
                System.out.printf("(%d) Sair\n", CODIGOS.length + 1);

                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao < 1 || opcao > CODIGOS.length + 1) {
                    System.out.println("⚠️ Opção inválida. Tente novamente.");
                    continue;
                }

                return opcao;
            } catch (Exception e) {
                System.out.println("⚠️ Entrada inválida. Digite apenas números.");
                scanner.nextLine();
            }
        }
    }

    public double lerValor() {
        while (true) {
            try {
                System.out.print("\nDigite o valor a converter: ");
                double valor = scanner.nextDouble();
                scanner.nextLine();
                if (valor < 0) {
                    System.out.println("⚠️ Digite um valor positivo.");
                    continue;
                }
                return valor;
            } catch (Exception e) {
                System.out.println("⚠️ Entrada inválida. Digite apenas números (use ponto para decimais).");
                scanner.nextLine();
            }
        }
    }

    public String getCodigoMoeda(int opcao) {
        return CODIGOS[opcao - 1];
    }

    public void close() {
        scanner.close();
    }
}
