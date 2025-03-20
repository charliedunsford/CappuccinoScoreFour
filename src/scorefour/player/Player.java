package scorefour.player;
import java.util.Random;
import scorefour.common.BeadColour;


public class Player {

    /**
     * Each player starts with 32 beads and an enum
     * relating to a colour
     */

    private final BeadColour beadcolour;
    private int beads;

    /**
     * Constructor for creating an initial player
     * should only be used for player 1
     */

    public Player() {
        this.beadcolour = setcolour();
        this.beads = 32;
    }

    /**
     * Constructor for all other players (ai and human)
     * takes in first player and assigns opposite colour
     * @param p is the initial player
     *
     */

    public Player(Player p) {
        this.beads = p.beads;
        if (BeadColour.WHITE == p.beadcolour) {
            this.beadcolour = BeadColour.BLACK;
        }
        else if (BeadColour.BLACK == p.beadcolour) {
            this.beadcolour = BeadColour.WHITE;
        }
        else {this.beadcolour = BeadColour.WHITE;}
    }

    /**
     * Only used by first constuctor to assign a random ENUM value
     * @return a random ENUM colour
     */

    public BeadColour setcolour(){
        Random rand = new Random();
        return BeadColour.values()[rand.nextInt(BeadColour.values().length)];
    }

    public BeadColour getBeadcolour() {
        return beadcolour;
    }

    public void Usebead(){
        this.beads--;
    }


    public int getBeads() {
        return beads;
    }
}
