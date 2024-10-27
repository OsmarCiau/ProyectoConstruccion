package Logistics;

import Inventory.Dimension;
import Inventory.Furniture;
import StoreKeeper.Order;
import StoreKeeper.Rack;
import StoreKeeper.StoreKeeper;
import StoreKeeper.Platform;
import StoreKeeper.Cell;
import Container.ContainerList;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main (String[] args){
        //DeliveryTruck deliveryTruck = new DeliveryTruck(10, 10.0f, 10.0f);
        //TruckDriver truckDriver = new TruckDriver(" ", 200);
        //Route route = new Route(200, "Los pinos", "Altabrisa", 300.0f, LocalTime.of(0,0));

        //System.out.println(deliveryTruck);
        //System.out.println(truckDriver);
        //System.out.println(route);

        /*
        DeliveryTruckAdmin deliveryTruckAdmin = new DeliveryTruckAdmin();

        deliveryTruckAdmin.registerDeliveryTruck(123, 10.0f, 100.0f);
        deliveryTruckAdmin.registerDeliveryTruck(456, 10.0f, 100.0f);
        System.out.println(deliveryTruckAdmin.getAvailableTrucks());

        deliveryTruckAdmin.registerTruckDriver("Robert", 100);
        deliveryTruckAdmin.registerTruckDriver("Sandra", 200 );
        System.out.println(deliveryTruckAdmin.getAvailableDrivers());

        deliveryTruckAdmin.assignDriverToTruck(123, "Sandra");
        System.out.println(deliveryTruckAdmin.getDeliveryTruckTruckDriverMap());

         */

        ContainerList<Rack> rackList = new ContainerList<Rack>();
        rackList.add(new Rack(1));
        rackList.add(new Rack(2));
        rackList.add(new Rack(3));

        Dimension dimensionMueble = new Dimension(0.5F, 0.5F, 0.5F);
        Furniture mueble = new Furniture(1,"Mesa","Mabe", "Blanco", dimensionMueble, 4, 20);
        ArrayList<Furniture> furnitures = new ArrayList<>();
        furnitures.add(mueble);

        Order order = new Order(24, "Mi casa", new Date(), furnitures);
        Dimension dimensionPlatform5 = new Dimension(3,4,3);
        StoreKeeper storeKeeper = new StoreKeeper(23, rackList);
        Platform platform = new Platform(order,2,dimensionPlatform5);

        System.out.println("\n");
        System.out.println("Inicio de pruebas");
        //imprimiendo la cantidad de celdas
        System.out.println("Cantidad de celdas en el rack " +  rackList.getByNumber(1).getNumber() + ": " + rackList.getByNumber(1).getCellCounter()+"\n");



        Cell cell = rackList.getByNumber(1).getRackCells().getByNumber(1);

        storeKeeper.placePlatformInCell(platform);

        storeKeeper.retirePlatformInCell(platform);



    }
}
