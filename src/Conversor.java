import java.util.Map;

public class Conversor {
    private final Map<String, Double> taxas;

    public Conversor(Map<String, Double> taxas) {
        this.taxas = taxas;
    }

    public double converter(double valor, String moedaOrigem, String moedaDestino) {
        validarMoeda(moedaOrigem);
        validarMoeda(moedaDestino);

        double taxaOrigem = taxas.get(moedaOrigem);
        double taxaDestino = taxas.get(moedaDestino);
        return valor * (taxaDestino / taxaOrigem);
    }

    private void validarMoeda(String codigoMoeda) {
        if (!taxas.containsKey(codigoMoeda)) {
            throw new IllegalArgumentException("Moeda não suportada: " + codigoMoeda);
        }
    }
}
