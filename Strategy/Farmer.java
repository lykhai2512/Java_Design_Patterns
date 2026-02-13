package Strategy;

public class Farmer implements Villager {
    @Override
    public String buy(int tokens){
        return "Bought " + tokens/21 + " wheat";
    }
}
