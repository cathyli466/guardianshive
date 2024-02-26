package assignment1;

public abstract class HoneyBee extends Insect{

    private int foodCost;
    public static double HIVE_DMG_REDUCTION;

    public HoneyBee(Tile position, int health, int cost) {
        super(position, health);
        this.foodCost = cost;
    }
    public int getCost() {
        return foodCost;
    }

    public void takeDamage(int damage) {
        if (getPosition() != null && getPosition().isHive()) {
            double reduceDmg = damage * (1 - HIVE_DMG_REDUCTION);
            super.takeDamage((int) Math.floor(reduceDmg));
        }
        else {
            super.takeDamage(damage);
        }
    }
}
