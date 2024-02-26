package assignment1;
public class AngryBee extends HoneyBee {
    private int attackDmg;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public AngryBee(Tile position, int attackDmg) {
        super(position, BASE_HEALTH, BASE_COST);
        this.attackDmg = attackDmg;
    }

    public boolean takeAction() {
        if (!getPosition().isOnThePath()) {
            return false;
        }
        SwarmOfHornets swarm = getPosition().getHornets();
        if (swarm.sizeOfSwarm() == 0) {
            Tile nextTile = getPosition().towardTheNest();
            if (nextTile != null) {
                swarm = nextTile.getHornets();
            }
        }

        if (swarm.sizeOfSwarm() > 0) {
            Hornet hornet = swarm.getFirstHornet();
            if (hornet != null && !hornet.getPosition().isNest()) {
                hornet.takeDamage(attackDmg);
                return true;
            }
        }

        return false;
    }



}
