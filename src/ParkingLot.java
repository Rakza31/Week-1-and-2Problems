//Parking Lot Management with Open Addressing

class Spot {
    String plate;
    boolean occupied;
}

public class ParkingLot {

    Spot[] table = new Spot[10];

    int hash(String plate) {
        return Math.abs(plate.hashCode()) % table.length;
    }

    public int parkVehicle(String plate) {

        int index = hash(plate);

        while (table[index] != null && table[index].occupied) {
            index = (index + 1) % table.length;
        }

        table[index] = new Spot();
        table[index].plate = plate;
        table[index].occupied = true;

        return index;
    }

    public static void main(String[] args) {

        ParkingLot p = new ParkingLot();

        System.out.println("Spot: " + p.parkVehicle("ABC123"));
        System.out.println("Spot: " + p.parkVehicle("XYZ999"));
    }
}
