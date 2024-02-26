package assignment1;

public abstract class Insect {

    private Tile position;
    private int healthPoints;

    public Insect(Tile position, int health) {
        this.position = position;
        this.healthPoints = health;

        boolean add = position.addInsect(this);
        if (!add) {
            throw new IllegalArgumentException("Insect cannot be added to tile.");
        }
    }
    public final Tile getPosition() {
        return position;
    }

    public void regenerateHealth(double percentage) {
        this.healthPoints = this.healthPoints + (int) (this.healthPoints *  percentage);
    }
    public final int getHealth() {
        return healthPoints;
    }
    public void setPosition(Tile updatedPosition) {
        this.position = updatedPosition;
    }

    public void takeDamage(int damage) {
        this.healthPoints -= damage;
        if (healthPoints <= 0) {
            healthPoints = 0;
            position.removeInsect(this);
            position = null;
        }
    }

    public abstract boolean takeAction();

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Insect)) {
            return false;
        }

        Insect another = (Insect) object;
        return this.getClass() == another.getClass() &&
                this.position == another.position &&
                this.healthPoints == another.healthPoints;
    }
}
