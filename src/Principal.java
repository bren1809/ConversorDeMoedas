import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        try {
            Map<String, Double> taxas = new ApiCotacao().getTaxas();

            //Inicia os componentes
            Conversor conversor = new Conversor(taxas);
            MenuMoeda menu = new MenuMoeda();
            DecimalFormat df = new DecimalFormat("#, ##0.00");

            //Fluxo principal
            int opcaoBase = menu.exibirMenu("SELECIONE A MOEDA BASE: ");
            String moedaBase = menu.getCodigoMoeda(opcaoBase);

            int opcaoDestino = menu.exibirMenu("SELECIONE A MOEDA DE DESTINO: ");
            String moedaDestino = menu.getCodigoMoeda(opcaoDestino);

            double valor = menu.lerValor();
            double valorConvertido = conversor.converter(valor, moedaBase, moedaDestino);

            //Resultado final
            System.out.println("\n******************************************");
            System.out.println("RESULTADO DA CONVERSÃO:");
            System.out.println(df.format(valor) + " " + moedaBase + " = " +
                    df.format(valorConvertido) + " " + moedaDestino);
            System.out.println("******************************************");


        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }
}
