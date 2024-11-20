package Proyect.Logistics;

import Proyect.StoreKeeper.Order;
import Proyect.Validations.ValidationUtils;
import Proyect.Inventory.Dimension;
import Proyect.Inventory.Furniture;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        // Crear algunos muebles de ejemplo para los pedidos
        ArrayList<Furniture> furnitureList1 = new ArrayList<>();
        furnitureList1.add(new Furniture(1, "Sofa", "BrandA", "Red", new Dimension(2.0f, 1.0f, 1.0f), 2, 30)); // 30 minutos de ensamblaje
        furnitureList1.add(new Furniture(2, "Mesa", "BrandB", "Black", new Dimension(1.5f, 1.0f, 1.0f), 1, 15)); // 15 minutos de ensamblaje

        ArrayList<Furniture> furnitureList2 = new ArrayList<>();
        furnitureList2.add(new Furniture(3, "Silla", "BrandC", "Blue", new Dimension(0.5f, 1.0f, 0.5f), 4, 10)); // 10 minutos de ensamblaje
        furnitureList2.add(new Furniture(4, "Estante", "BrandD", "White", new Dimension(1.0f, 1.5f, 0.3f), 1, 20));  // 20 minutos de ensamblaje

        ArrayList<Furniture> furnitureList3 = new ArrayList<>();
        furnitureList3.add(new Furniture(5, "Cama", "BrandE", "Brown", new Dimension(2.0f, 1.5f, 2.0f), 1, 40)); // 40 minutos de ensamblaje

        ArrayList<Furniture> furnitureList4 = new ArrayList<>();
        furnitureList4.add(new Furniture(6, "Mesa de noche", "BrandF", "Gray", new Dimension(0.5f, 0.5f, 0.5f), 1, 10)); // 10 minutos de ensamblaje

        // Crear las fechas de entrega para los pedidos
        Date deliveryDate1 = new Date(); // Fecha actual como ejemplo
        Date deliveryDate2 = new Date();
        Date deliveryDate3 = new Date();
        Date deliveryDate4 = new Date();

        // Crear los pedidos con su respectiva información
        Order order1 = new Order(1, "Calle 60, Centro, Mérida, Yucatán, México", deliveryDate1, furnitureList1);
        Order order2 = new Order(2, "Avenida Prolongación Montejo, Mérida, Yucatán, México", deliveryDate2, furnitureList2);
        Order order3 = new Order(3, "Avenida 2000, Mérida, Yucatán, México", deliveryDate3, furnitureList3);
        Order order4 = new Order(4, "Calle 39, Chuburná, Mérida, Yucatán, México", deliveryDate4, furnitureList4);

        // Crear una lista de pedidos
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        // Crear el planificador de rutas
        RoutePlanner routePlanner = new RoutePlanner("Cedis Manuel Delgado, Tamanché, Yucatán, México", LocalTime.of(8, 0));

        // Planificar las rutas para los pedidos
        List<Route> routes = routePlanner.planDailyRoutes(orders);

        // Imprimir las rutas planificadas
        for (Route route : routes) {
            System.out.println(route);
        }
    }
}
