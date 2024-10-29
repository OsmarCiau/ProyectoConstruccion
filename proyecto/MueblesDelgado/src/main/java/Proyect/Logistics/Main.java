package Proyect.Logistics;

public class Main {
    public static void main (String[] args){
        //DeliveryTruck deliveryTruck = new DeliveryTruck(10, 10.0f, 10.0f);
        //TruckDriver truckDriver = new TruckDriver(" ", 200);
        //Route route = new Route(200, "Los pinos", "Altabrisa", 300.0f, LocalTime.of(0,0));

        //System.out.println(deliveryTruck);
        //System.out.println(truckDriver);
        //System.out.println(route);

        DeliveryTruckAdmin deliveryTruckAdmin = new DeliveryTruckAdmin();

        deliveryTruckAdmin.registerDeliveryTruck(123, 10.0f, 100.0f);
        deliveryTruckAdmin.registerDeliveryTruck(456, 10.0f, 100.0f);
        System.out.println(deliveryTruckAdmin.getAvailableTrucks());

        deliveryTruckAdmin.registerTruckDriver("Robert", 100);
        deliveryTruckAdmin.registerTruckDriver("Sandra", 200 );
        System.out.println(deliveryTruckAdmin.getAvailableDrivers());

        deliveryTruckAdmin.assignDriverToTruck(123, "Sandra");
        System.out.println(deliveryTruckAdmin.getDeliveryTruckDriverMap());


    }
}
