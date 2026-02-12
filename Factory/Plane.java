package Factory;

public class Plane implements Vehicle {
    @Override
    public void create() {
        System.out.println("Plane created.");
    }

    @Override
    public int getNumOfWheels() {
        return 3;
    }
}
