package Proyect.Logistics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class GeoDataProvider {
    private static final String API_KEY = "AIzaSyAprtokl7TAwkM6QkbCT25sBlRt-LkweuU";

    // Método principal para obtener las coordenadas a partir de la dirección
    public double[] getCoordinatesFromAddress(String address) throws Exception {
        String encodedAddress = normalizeAddress(address);
        String urlString = generateRequestURLCoordinates("geocode", encodedAddress);
        JSONObject jsonResponse = getApiResponse(urlString);
        verifyResult(jsonResponse);
        
        return extractCoordinates(jsonResponse);
    }

    public double getDistance(double[] origin, double[] destination) throws Exception {
        JSONObject routeInfo = getRouteInformation(origin, destination);
        return routeInfo.getJSONArray("routes").getJSONObject(0)
                .getJSONArray("legs").getJSONObject(0)
                .getJSONObject("distance").getDouble("value") / 1000;
    }

    public double getDuration(double[] origin, double[] destination) throws Exception {
        JSONObject routeInfo = getRouteInformation(origin, destination);
        return routeInfo.getJSONArray("routes").getJSONObject(0)
                .getJSONArray("legs").getJSONObject(0)
                .getJSONObject("duration").getDouble("value") / 60;
    }

    // Método principal para obtener la información de la ruta entre dos puntos
    private JSONObject getRouteInformation(double[] origin, double[] destination) throws Exception {
        String originStr = formatCoordinates(origin);
        String destinationStr = formatCoordinates(destination);
        
        String urlString = generateRequestURLRoute("directions", originStr, destinationStr);
        JSONObject jsonResponse = getApiResponse(urlString);
        validateRouteResponse(jsonResponse);
        
        return jsonResponse;
    }

    // Método común para obtener la respuesta de la API
    private static JSONObject getApiResponse(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Error HTTP: " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return new JSONObject(response.toString());
    }

    // Método común para verificar si la respuesta es válida
    private static void verifyResult(JSONObject jsonResponse) {
        if (!"OK".equals(jsonResponse.getString("status"))) {
            System.out.println("Error en la API: " + jsonResponse.getString("status"));
            throw new RuntimeException("Error en la API: " + jsonResponse.getString("status"));
        }
    }

    // Para obtener las coordenadas de la respuesta de Geocoding
    private static double[] extractCoordinates(JSONObject jsonResponse) {
        JSONObject location = jsonResponse.getJSONArray("results").getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location");
        double lat = location.getDouble("lat");
        double lng = location.getDouble("lng");

        return new double[]{lat, lng};
    }

    // Método para formatear las coordenadas
    private static String formatCoordinates(double[] coordinates) {
        return coordinates[0] + "," + coordinates[1];
    }

    // Método para generar la URL de la solicitud para GeoCode
    private static String generateRequestURLCoordinates(String type, String address) {
        return "https://maps.googleapis.com/maps/api/" + type + "/json?address=" + address + "&key=" + API_KEY;
    }


    // Método para generar la URL de la solicitud para Directions
    private static String generateRequestURLRoute(String type, String origin, String destination) {
        return "https://maps.googleapis.com/maps/api/" + type + "/json?origin=" + origin + "&destination=" + destination + "&key=" + API_KEY;
    }

    // Normalizar la dirección para la URL
    private static String normalizeAddress(String address) {
        return URLEncoder.encode(address, StandardCharsets.UTF_8);
    }

    // Validar la respuesta de la API de Direcciones
    private static void validateRouteResponse(JSONObject jsonResponse) {
        if (!"OK".equals(jsonResponse.getString("status"))) {
            System.out.println("Error en Directions API: " + jsonResponse.getString("status"));
            throw new RuntimeException("Error en Directions API: " + jsonResponse.getString("status"));
        }
    }


    public static void main(String[] args) {
        GeoDataProvider geoDataProvider = new GeoDataProvider();
        try {
            double[] coordinates = geoDataProvider.getCoordinatesFromAddress("Cedis Manuel Delgado, Tamanché, Yucatán, México");
            double[] destination = geoDataProvider.getCoordinatesFromAddress("Facultad de Matemáticas, Mérida, Yucatán, México");
            
            System.out.println("Coordinates: " + coordinates[0] + ", " + coordinates[1]);
            System.out.println("Coordinates: " + destination[0] + ", " + destination[1]);

            double distance = geoDataProvider.getDistance(coordinates, destination);
            double duration = geoDataProvider.getDuration(coordinates, destination);

            System.out.println("Distance: " + distance + " km");
            System.out.println("Duration: " + duration + " minutes");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
