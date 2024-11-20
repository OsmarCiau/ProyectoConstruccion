package Proyect.Logistics;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class Route {
    private int routeId;
    private String originLocation;
    private List<String> destinations;  // Lista de destinos
    private List<Duration> travelTimes;  // Lista de tiempos de viaje
    private float distance;  // Distancia total recorrida
    private LocalTime estimatedTime;  // Tiempo estimado para completar la ruta

    public Route(int p_routeId, String p_originLocation, List<String> p_destinations,
                 List<Duration> p_travelTimes, float p_distance, LocalTime p_estimatedTime) {
        setRouteId(p_routeId);
        setOriginLocation(p_originLocation);
        setDestinations(p_destinations);
        setTravelTimes(p_travelTimes);
        setDistance(p_distance);
        setEstimatedTime(p_estimatedTime);
    }

    // Setters y validaciones
    private void setRouteId(int p_routeId) {
        if (p_routeId <= 0) throw new IllegalArgumentException("Route ID debe ser mayor que cero");
        this.routeId = p_routeId;
    }

    private void setOriginLocation(String p_originLocation) {
        if (p_originLocation == null || p_originLocation.isEmpty()) throw new IllegalArgumentException("Origin Location no puede ser nulo o vacío");
        this.originLocation = p_originLocation;
    }

    private void setDestinations(List<String> p_destinations) {
        if (p_destinations == null || p_destinations.isEmpty()) throw new IllegalArgumentException("Destinations no puede ser nulo o vacío");
        this.destinations = p_destinations;
    }

    private void setTravelTimes(List<Duration> p_travelTimes) {
        if (p_travelTimes == null || p_travelTimes.isEmpty()) throw new IllegalArgumentException("Travel Times no puede ser nulo o vacío");
        this.travelTimes = p_travelTimes;
    }

    private void setDistance(float p_distance) {
        if (p_distance <= 0) throw new IllegalArgumentException("Distance debe ser mayor que cero");
        this.distance = p_distance;
    }

    private void setEstimatedTime(LocalTime p_estimatedTime) {
        if (p_estimatedTime == null) throw new IllegalArgumentException("Estimated Time no puede ser nulo");
        this.estimatedTime = p_estimatedTime;
    }

    // Getters
    public int getIdRoute() {
        return routeId;
    }

    public String getOriginLocation() {
        return originLocation;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public List<Duration> getTravelTimes() {
        return travelTimes;
    }

    public float getDistance() {
        return distance;
    }

    public LocalTime getEstimatedTime() {
        return estimatedTime;
    }

    // Método para mostrar la ruta
    @Override
    public String toString() {
        StringBuilder routeDetails = new StringBuilder();
        routeDetails.append("Route: ")
                    .append("Route ID: ").append(getIdRoute())
                    .append(", Origin Location: '").append(getOriginLocation()).append("'")
                    .append(", Destinations: ").append(getDestinations())
                    .append(", Distances: ").append(getDistance())
                    .append(", Estimated Hour for Arriving: ").append(getEstimatedTime())
                    .append("\nDetails by Destination:\n");

        for (int i = 0; i < destinations.size(); i++) {
            routeDetails.append("  - ").append(destinations.get(i))
                        .append(" Travel Time (including assembly): ").append(travelTimes.get(i))
                        .append("\n");
        }

        return routeDetails.toString();
    }

    // Método para obtener la duración total de la ruta
    public Duration getTotalTravelTime() {
        Duration totalTravelTime = Duration.ZERO;
        for (Duration travelTime : travelTimes) {
            totalTravelTime = totalTravelTime.plus(travelTime);
        }
        return totalTravelTime;
    }
}
