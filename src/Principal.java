import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);

        try {
            Map<String, Double> taxas = new ApiCotacao().getTaxas();

            //Inicia os componentes
            Conversor conversor = new Conversor(taxas);
            MenuMoeda menu = new MenuMoeda();
            DecimalFormat df = new DecimalFormat("#, ##0.00");

            while (true) {
                try {
                    // Fluxo principal
                    int opcaoBase = menu.exibirMenu("SELECIONE A MOEDA BASE: ");
                    if (opcaoBase == 7) break; // para sair

                    String moedaBase = menu.getCodigoMoeda(opcaoBase);

                    int opcaoDestino = menu.exibirMenu("SELECIONE A MOEDA DE DESTINO: ");
                    if (opcaoDestino == 7) break; // para sair

                    String moedaDestino = menu.getCodigoMoeda(opcaoDestino);

                    double valor = menu.lerValor();
                    double valorConvertido = conversor.converter(valor, moedaBase, moedaDestino);

                    //Resultado final
                    System.out.println("\n******************************************");
                    System.out.println("RESULTADO DA CONVERSÃO:");
                    System.out.println(df.format(valor) + " " + moedaBase + " = " +
                            df.format(valorConvertido) + " " + moedaDestino);
                    System.out.println("******************************************");

                    System.out.println("Deseja convertar outra moeda? (s/n): ");
                    String continuar = leitura.nextLine();
                    if(!continuar.equalsIgnoreCase("s")) break;

                } catch (Exception e) {
                    System.out.println("❌ Erro na conversão: " + e.getMessage());
                    System.out.println("Tente novamente...\n");
                }
            }

            System.out.println("\n✅ Programa encerrado. Obrigado por usar o conversor!");
            menu.close();

        } catch (Exception e) {
            System.out.println("❌ Erro ao carregar as taxas da API: " + e.getMessage());
        }

        leitura.close();

    }
}
