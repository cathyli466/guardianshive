package assignment1;

public class Hornet extends Insect {
    public static int BASE_FIRE_DMG;
    private int attackDmg;
    private boolean isQueen;
    private static int queenCount;

    public Hornet(Tile position, int health, int attackDmg) {
        super(position, health);
        this.attackDmg = attackDmg;
        this.isQueen = false;
        this.queenCount = 0;
    }

    public boolean isTheQueen() {
        return isQueen;
    }

    public void promote() {
        if (queenCount == 0) {
            isQueen = true;
            queenCount++;
        }
    }

    public boolean takeAction() {
        boolean result = takeAction2();
        System.out.println(result);
        if (isQueen && result) {
            result = takeAction2();

        }
        return result;
    }

    private boolean takeAction2() {
        if (getPosition().isOnFire()) {
            takeDamage(BASE_FIRE_DMG);
            if (getPosition()==null) {
                return false;
            }
        }
        if (getPosition().getBee() != null) {
            getPosition().getBee().takeDamage(attackDmg);
            return true;
        } else if (getPosition().getBee() == null && !(getPosition().isHive())) {
            Tile nextTile = getPosition().towardTheHive();
            setPosition(nextTile);
            nextTile.addInsect(this);
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Hornet)) {
            return false;
        }

        Hornet another = (Hornet) obj;
        return super.equals(obj) && this.attackDmg == another.attackDmg;
    }

}
