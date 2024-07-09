package com.alura.challenge.conversorMonedas;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConversorMoneda {
    private Map<String, String> countryToCurrencyMap;

    public ConversorMoneda(Map<String, String> countryToCurrencyMap) {
        this.countryToCurrencyMap = countryToCurrencyMap;
    }

    //Método para buscar la tasa de cambio y realizar la conversión
    public double buscadorMonedas(String paisOrigen, String paisDestino, double valor) throws Exception {
        //Validar monedas de cada país
        String monedaOrigen = countryToCurrencyMap.get(paisOrigen);
        String monedaDestino = countryToCurrencyMap.get(paisDestino);

        //Validar que los países sean validos
        if (monedaOrigen == null|| monedaDestino == null){
            throw new Exception("Pais de origen o destino no válido");
        }

        //Construiir la api
        String apiKey = "3965da2e8ce60738beabfaab"; // Reemplaza esto con tu clave API real.
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + monedaOrigen + "/";
        URI direccion = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        try {
            // Enviar la solicitud HTTP.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar que la respuesta sea exitosa.
            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

                // Verificar que la respuesta contenga la tasa de cambio para la moneda destino.
                if (jsonObject.has("conversion_rates")) {
                    JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
                    if (conversionRates.has(monedaDestino)) {
                        double tipoCambio = conversionRates.get(monedaDestino).getAsDouble();
                        // Realizar la conversión.
                        double resultado= valor * tipoCambio;
                        resultado = redondear(resultado,2);
                        return resultado;
                    } else {
                        throw new Exception("La respuesta de la API no contiene el tipo de cambio para " + monedaDestino);
                    }
                } else {
                    throw new Exception("La respuesta de la API no contiene tasas de cambio.");
                }
            } else {
                throw new Exception("Error en la solicitud a la API: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al realizar la conversión: " + e.getMessage());
        }


    }

    // Método auxiliar para redondear un número a una cantidad específica de decimales
    private double redondear(double valor, int decimales) {
        if (decimales < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(valor);
        bd = bd.setScale(decimales, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }




}
