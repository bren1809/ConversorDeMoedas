import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ApiCotacao {
    private static final String API_KEY = Config.get("API_KEY");
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public Map<String, Double> getTaxas() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(API_URL))
                .build();

        String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        JsonObject json = new Gson().fromJson(response, JsonObject.class);

        return new MoedaServico().filtrarMoedas(json.getAsJsonObject("conversion_rates"),
                new String[]{"USD", "BRL", "BOB", "ARS", "CLP", "COP"});
    }
}
