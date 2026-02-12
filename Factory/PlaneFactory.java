package Factory;

public class PlaneFactory extends VehicleFactory {
    @Override
    protected Vehicle createVehicle() {
        return new Plane();
    }
}
