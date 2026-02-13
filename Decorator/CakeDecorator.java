package Decorator;

public abstract class CakeDecorator implements Cake {
    protected Cake cake;

    public CakeDecorator(Cake cake) {
        this.cake = cake;
    }

    @Override
    public String prepare() {
        return cake.prepare();
    }

    @Override
    public String bake() {
        return cake.bake();
    }
    
}
