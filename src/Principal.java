import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.image.ColorConvertOp;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);

        System.out.print("Digite a moeda de origem (ex: USD): ");
        var moedaOrigem = leitura.nextLine().toUpperCase();

        System.out.print("Digite a moeda de destino (ex: BRL): ");
        var moedaDestino = leitura.nextLine().toUpperCase();


        String keyApi = "41238e740e020e03d7715cdc";
        String endereco = "https://v6.exchangerate-api.com/v6/" + keyApi + "/pair/" + moedaOrigem + "/" + moedaDestino;


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
        Moeda converte = gson.fromJson(json, Moeda.class);

        System.out.println("Conversão de " + converte);

    }
}
