import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String get(String chave) {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
           return prop.getProperty(chave);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar o config.properties: " + e.getMessage());
        }
    }
}
