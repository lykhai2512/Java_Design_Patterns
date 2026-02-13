package Decorator;

public class CakeLayering extends CakeDecorator {
    public CakeLayering(Cake cake) {
        super(cake);
    }

    @Override
    public String prepare() {
        return super.prepare() + " with 1 more layers";
    }

    @Override
    public String bake() {
        return super.bake() + " Layering the cake.";
    }
    
}
