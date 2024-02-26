package assignment1;

public class SniperBee extends HoneyBee {
    private int attackDamage;
    private int piercingPower;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public SniperBee(Tile position, int attackDamage, int piercingPower) {
        super(position, BASE_HEALTH, BASE_COST);
        this.attackDamage = attackDamage;
        this.piercingPower = piercingPower;
    }

    private boolean isAiming = true;

    public boolean takeAction() {
        if (!getPosition().isOnThePath()) {
            return false;
        }

        if (isAiming) {
            isAiming = false;
            return false;
        } else {
            isAiming = true;
            Tile currentTile = getPosition();
            SwarmOfHornets swarm;
            do {
                swarm = currentTile.getHornets();
                if (swarm.sizeOfSwarm() == 0) {
                    currentTile = currentTile.towardTheNest();
                }
            } while (swarm.sizeOfSwarm() == 0 && currentTile != null);

            if (swarm.sizeOfSwarm() > 0) {
                int numHornetsToShoot = Math.min(piercingPower, swarm.sizeOfSwarm());
                Hornet[] hornets = swarm.getHornets();
                for (int i = 0; i < numHornetsToShoot; i++) {
                    Hornet hornet = hornets[i];
                    if (hornet != null && !hornet.getPosition().isNest()) {
                        hornet.takeDamage(attackDamage);
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

}
