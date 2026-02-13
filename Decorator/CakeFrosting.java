package Decorator;

public class CakeFrosting extends CakeDecorator {
    public CakeFrosting(Cake cake) {
        super(cake);
    }

    @Override
    public String prepare() {
        return super.prepare() + " Adding frosting.";
    }

    @Override
    public String bake() {
        return super.bake() + " Frosting the cake.";
    }
    
}
