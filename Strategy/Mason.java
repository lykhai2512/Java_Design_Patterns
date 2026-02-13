package Strategy;

public class Mason implements Villager {
    @Override
    public String buy(int tokens){
        return "Bought " + tokens/10 + " bricks";
    }
}
