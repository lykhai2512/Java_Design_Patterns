package Decorator;

public class CakeImp implements Cake {
    @Override
    public String prepare() {
        return "Preparing a basic cake.";
    }

    @Override
    public String bake() {
        return "Baking the basic cake.";
    }
    
}
