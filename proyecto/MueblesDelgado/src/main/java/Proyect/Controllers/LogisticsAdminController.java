package Proyect.Controllers;

import Proyect.Logistics.LogisticsAdminService;
import Proyect.Logistics.OrderTruckAssignment;
import Proyect.Logistics.Route;
import Proyect.StoreKeeper.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logistics")
public class LogisticsAdminController {

    private final LogisticsAdminService logisticsAdminService;

    @Autowired
    public LogisticsAdminController(LogisticsAdminService logisticsAdminService) {
        this.logisticsAdminService = logisticsAdminService;
    }

    // Endpoint para planificar rutas
    @PostMapping("/planRoutes")
    public ResponseEntity<List<Route>> planRoutes(@RequestBody List<Order> orders) {
        try {
            // Planificar rutas para las órdenes recibidas
            List<Route> plannedRoutes = logisticsAdminService.planRoutes(orders);
            return ResponseEntity.ok(plannedRoutes);  // Responde con las rutas planificadas
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);  // Responde con un error 400 si ocurre una excepción
        }
    }

    // Endpoint para asignar rutas a los camiones
    @PostMapping("/assignRoutes")
    public ResponseEntity<Void> assignRoutes(@RequestBody List<Route> plannedRoutes, @RequestBody List<Order> orders) {
        try {
            // Asignar las rutas planificadas a los camiones y órdenes correspondientes
            logisticsAdminService.assignRoutesToTrucks(plannedRoutes, orders);
            return ResponseEntity.ok().build();  // Responde con un 200 OK si la asignación fue exitosa
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();  // Responde con un error 400 si ocurre una excepción
        }
    }

    // Endpoint para obtener todas las asignaciones de camiones a rutas
    @GetMapping("/assignmentsRoutesTrucks")
    public ResponseEntity<List<OrderTruckAssignment>> getAssignments() {
        // Obtener todas las asignaciones de camiones a rutas
        List<OrderTruckAssignment> assignments = logisticsAdminService.getOrderTruckAssignments();
        return ResponseEntity.ok(assignments);  // Responde con la lista de asignaciones
    }
}
