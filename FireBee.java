package assignment1;

public class FireBee extends HoneyBee {
    private int maxAttackRange;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public FireBee(Tile position, int maxAttackRange) {
        super(position, BASE_HEALTH, BASE_COST);
        this.maxAttackRange = maxAttackRange;
    }

    public boolean takeAction() {
        Tile currentPosition = getPosition();

        if (!currentPosition.isOnThePath() || currentPosition.isNest()) {
            return false;
        }

        Tile nextTile = currentPosition.towardTheNest();
        int distance = 1;

        while (nextTile != null && !nextTile.isNest()) {

            if (distance <= maxAttackRange && nextTile.getNumOfHornets() > 0 && !nextTile.isOnFire()) {
                nextTile.setOnFire();
                return true;
            }
            nextTile = nextTile.towardTheNest();
            distance++;
        }

        return false;
    }
}