import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    public Conversor convertirMoneda (String monedaBase, String monedaTarget, double monto){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/a8903b0bb1bcde62077154c6/pair/"+monedaBase+"/"+monedaTarget);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir la respuesta para verificar
            System.out.println(response.body());

            // Deserializar la respuesta en un objeto ApiResponse
            ApiResponse apiResponse = new Gson().fromJson(response.body(), ApiResponse.class);

            // Crear y retornar un objeto Conversor
            double montoConvertido = apiResponse.getConversion_rate() * monto;
            return new Conversor(apiResponse.getBase_code(), apiResponse.getTarget_code(), monto, apiResponse.getConversion_rate(), montoConvertido);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en la operación: " + e.getMessage());
        }
    }
}
