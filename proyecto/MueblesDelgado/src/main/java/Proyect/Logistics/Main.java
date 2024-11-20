package Proyect.Logistics;

import Proyect.StoreKeeper.Order;
import Proyect.Inventory.Furniture;
import Proyect.Inventory.Dimension;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // Crear muebles de ejemplo para los pedidos
        ArrayList<Furniture> furnitureList1 = new ArrayList<>();
        furnitureList1.add(new Furniture(1, "Sofa", "BrandA", "Red", new Dimension(2.0f, 1.0f, 1.0f), 2, 30)); // 30 minutos de ensamblaje
        furnitureList1.add(new Furniture(2, "Mesa", "BrandB", "Black", new Dimension(1.5f, 1.0f, 1.0f), 1, 15)); // 15 minutos de ensamblaje

        ArrayList<Furniture> furnitureList2 = new ArrayList<>();
        furnitureList2.add(new Furniture(3, "Silla", "BrandC", "Blue", new Dimension(1.0f, 0.5f, 0.5f), 1, 20)); // 20 minutos de ensamblaje

        ArrayList<Furniture> furnitureList3 = new ArrayList<>();
        furnitureList3.add(new Furniture(4, "Mesita", "BrandD", "White", new Dimension(1.0f, 1.0f, 0.5f), 1, 20)); // 20 minutos de ensamblaje
        furnitureList3.add(new Furniture(5, "Lámpara", "BrandE", "Silver", new Dimension(0.3f, 0.3f, 1.2f), 1, 10)); // 10 minutos de ensamblaje

        ArrayList<Furniture> furnitureList4 = new ArrayList<>();
        furnitureList4.add(new Furniture(6, "Cama", "BrandF", "Green", new Dimension(2.0f, 1.5f, 0.5f), 1, 40)); // 40 minutos de ensamblaje

        ArrayList<Furniture> furnitureList5 = new ArrayList<>();
        furnitureList5.add(new Furniture(7, "Escritorio", "BrandG", "Brown", new Dimension(1.5f, 0.8f, 1.0f), 1, 25)); // 25 minutos de ensamblaje

        // Crear pedidos con ubicaciones de plazas comerciales en Mérida
        Order order1 = new Order(1, "Plaza Altabrisa, Mérida, Yucatán", LocalDate.now(), furnitureList1);
        Order order2 = new Order(2, "Plaza Galerías, Mérida, Yucatán", LocalDate.now(), furnitureList2);
        Order order3 = new Order(3, "Plaza La Isla, Mérida, Yucatán", LocalDate.now(), furnitureList3);
        Order order4 = new Order(4, "Plaza City Center, Mérida, Yucatán", LocalDate.now(), furnitureList4);
        Order order5 = new Order(5, "Cancún, Quintana Roo", LocalDate.now(), furnitureList5);

        // Crear la lista de pedidos
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);

        // Crear el planificador de rutas
        RoutePlanner routePlanner = new RoutePlanner("Cedis Manuel Delgado, Tamanché, Yucatán", LocalTime.of(8, 0));

        // Obtener las rutas óptimas
        List<List<Route>> routes = routePlanner.planOptimalRoutes(orders);

        // Imprimir las rutas
        for (int i = 0; i < routes.size(); i++) {
            System.out.println("Rutas para el día " + (i + 1) + ":");
            for (Route route : routes.get(i)) {
                System.out.println("Ruta " + route.getIdRoute() + ":");
                System.out.println("Origen: " + route.getOriginLocation());
                System.out.println("Destinos:");
                for (int j = 0; j < route.getDestinations().size(); j++) {
                    System.out.println(route.getDestinations().get(j) + " - Tiempo de viaje: " + route.getTravelTimes().get(j));
                }
                System.out.println("Distancia total: " + route.getDistance() + " km");
                System.out.println("Hora estimada de llegada: " + route.getEstimatedTime());
                System.out.println();
            }
        }

    }
}
