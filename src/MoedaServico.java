import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;

public class MoedaServico {

    public Map<String, Double> filtrarMoedas(JsonObject rates, String[] currencyCodes) {
        Map<String, Double> moedasSelecionadas = new HashMap<>();

        for (String code : currencyCodes) {
            if (rates.has(code)) {
                Double value = rates.get(code).getAsDouble();
                moedasSelecionadas.put(code, value);
            }
        }
        return moedasSelecionadas;
    }

    public double converterMoeda(double valor, String origem, String destino, Map<String, Double> taxas) {
        if (!taxas.containsKey(origem) || !taxas.containsKey(destino)) {
            throw new IllegalArgumentException("Código de moeda inválido: " + origem + "ou" + destino);
        }

        double taxaOrigem = taxas.get(origem);
        double taxaDestino = taxas.get(destino);

        return valor * (taxaDestino / taxaOrigem);
    }
}