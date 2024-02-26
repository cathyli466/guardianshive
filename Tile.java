package assignment1;

public class Tile {
    private int food;
    private boolean builtHive;
    private boolean builtNest;
    private boolean onPath;
    private boolean onFire;
    private Tile nextTileToHive;
    private Tile nextTileToNest;
    private HoneyBee bee;
    private SwarmOfHornets hornets;


    public Tile() {
        this.builtHive = false;
        this.builtNest = false;
        this.onPath = false;
        this.nextTileToHive = null;
        this.nextTileToNest = null;
        this.food = 0;
        this.bee = null;
        this.hornets = new SwarmOfHornets();
        this.onFire = false;
    }

    public Tile(int food, boolean builtHive, boolean builtNest, boolean onPath, Tile nextTileToHive,
                Tile nextTileToNest, HoneyBee bee, SwarmOfHornets hornets) {
        this.builtHive = builtHive;
        this.builtNest = builtNest;
        this.onPath = onPath;
        this.nextTileToHive = nextTileToHive;
        this.nextTileToNest = nextTileToNest;
        this.food = food;
        this.bee = bee;
        this.hornets = hornets;
    }
    public void setOnFire() {
        this.onFire = true;
    }

    public boolean isOnFire() {
        return this.onFire;
    }
    public boolean isHive() {
        return builtHive;
    }

    public boolean isNest() {
        return builtNest;
    }

    public void buildHive() {
        builtHive = true;
    }

    public void buildNest() {
        builtNest = true;
    }

    public boolean isOnThePath() {
        return onPath;
    }

    public Tile towardTheHive() {
        if (onPath && nextTileToHive != null && !builtHive){
            return nextTileToHive;
        }
        return null;
    }

    public Tile towardTheNest() {
        if (onPath && nextTileToNest != null && !builtNest) {
            return nextTileToNest;
        }
        return null;
    }

    public void createPath(Tile nextTileToHive, Tile nextTileToNest) {
        if ((builtHive && nextTileToHive != null) || (builtNest && nextTileToNest != null)
                || (nextTileToHive == null && nextTileToNest == null)) {
            throw new IllegalArgumentException("The arguments are invalid.");
        }
        this.nextTileToHive = nextTileToHive;
        this.nextTileToNest = nextTileToNest;
        this.onPath = true;
    }

    public int collectFood() {
        int foodCollected = food;
        food = 0;
        return foodCollected;
    }

    public void storeFood(int amountFoodReceived) {
        food += amountFoodReceived;
    }

    public int getNumOfHornets() {
        return hornets.sizeOfSwarm();
    }

    public HoneyBee getBee() {
        return bee;
    }

    public Hornet getHornet() {
        return hornets.getFirstHornet();
    }

    public SwarmOfHornets getHornets() {
        return hornets;
    }
    public boolean addInsect(Insect insect) {
        if (insect instanceof HoneyBee) {
            if (bee == null && !builtNest) {
                bee = (HoneyBee) insect;
                bee.setPosition(this); // Set the bee's position to this tile
                return true;
            }
            return false;
        } else if (insect instanceof Hornet) {
            if (onPath || builtNest || builtHive) {
                hornets.addHornet((Hornet) insect);
                ((Hornet) insect).setPosition(this); // Set the hornet's position to this tile
                return true;
            }
            return false;
        }
        return false;
    }


    public boolean removeInsect(Insect insect) {
        if (insect instanceof HoneyBee && bee == insect) {
            bee = null;
            return true;
        }
        else if (insect instanceof Hornet) {
            return hornets.removeHornet((Hornet) insect);
        }
        return false;
    }

}