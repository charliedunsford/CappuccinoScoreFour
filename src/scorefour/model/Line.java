package scorefour.model;

import scorefour.common.BeadColour;

public class Line {

    private final Bead[] beads;
    private int lineNumber; //was thinking could maybe override tostring with this

    public Line(Bead bead1, Bead bead2, Bead bead3, Bead bead4)
    {
            beads = new Bead[]{bead1, bead2, bead3, bead4};
    }

    public boolean isWin() // returns true if the line has 4 beads of the same colour
    {

        for(int i = 0; i <= 3; i++)
        {
            if (beads[i] == null) {
                return false;
            }
        }

        if(beads[0].getColour() == beads[1].getColour() && beads[1].getColour() == beads[2].getColour() && beads[2].getColour() == beads[3].getColour())
        {
            return true;
        }
        return false;
    }

    public Boolean isInPlay(){  //returns false if there are beads of different colours on this line, meaning no win can occur here. Might be useful for AI?
        BeadColour lineColour = null;

        for (Bead bead : beads) {
            if (bead != null) {
                if (lineColour != null && bead.getColour() != lineColour) {
                    return false;
                }
                lineColour = bead.getColour();
            }
        }
        return true;
    }

    public int beadAmount() //returns the amount of the beads currently on the line. Also might be useful for AI
    {
        int amount = 0;
        for (Bead bead : beads)
        {
            if (bead != null)
            {
                amount++;
            }
        }
        return amount;
    }
}
