package Factory;

public abstract class VehicleFactory {
    public Vehicle create() {
        Vehicle vehicle = createVehicle();
        vehicle.create();
        return vehicle;
    }
    protected abstract Vehicle createVehicle();
}
