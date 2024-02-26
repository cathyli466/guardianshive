package assignment1;

public class SwarmOfHornets {
    private Hornet[] hornets;
    private int size;
    public static double QUEEN_BOOST;
    public SwarmOfHornets() {
        this.hornets = new Hornet[20];
        this.size = 0;
    }

    public int sizeOfSwarm() {
        return size;
    }

    public Hornet[] getHornets() {
        Hornet[] hornetInSwarm = new Hornet[size];
        for (int i = 0; i < size; i++) {
            hornetInSwarm[i] = hornets[i];
        }
        return hornetInSwarm;
    }

    public Hornet getFirstHornet() {
        if (size > 0){
            return hornets[0];
        }
        return null;
    }

    public void addHornet(Hornet addedHornet) {
        if (hornets.length == size) {
            resize ();
        }
        hornets[size] = addedHornet;
        size++;
        if (addedHornet.isTheQueen()){
            for (Hornet horn: hornets){
                if (horn != null && !(horn.isTheQueen())){
                    horn.regenerateHealth(QUEEN_BOOST);
                }
            }
        }
    }

    private void resize() {
        int newCapacity = hornets.length * 2;
        Hornet[] newHornetsArray = new Hornet[newCapacity];


        System.arraycopy(hornets, 0, newHornetsArray, 0, size);

        hornets = newHornetsArray;
    }


    public boolean removeHornet(Hornet removeHornet) {
        for (int i = 0; i < size; i++) {
            if (hornets[i] == removeHornet) {
                for (int j = i; j < size - 1; j++) {
                    hornets[j] = hornets[j + 1];
                }
                hornets[size - 1] = null;
                size--;
                return true;
            }
        }
        // No such hornets exist
        return false;
    }
}
