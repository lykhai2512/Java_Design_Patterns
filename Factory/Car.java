package Factory;

public class Car implements Vehicle {
    @Override
    public void create() {
        System.out.println("Car created.");
    }

    @Override
    public int getNumOfWheels() {
        return 4;
    }
}
