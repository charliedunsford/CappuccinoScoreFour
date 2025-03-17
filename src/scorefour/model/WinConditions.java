package scorefour.model;

public class WinConditions {

    private  Line[] lines = new Line[76];
    private int currentLine;
    private Peg[][] pegs;

    public WinConditions(Board board) {
        currentLine = 0;
        pegs = board.getPegs();
    }

    public boolean isGameWon(){

        addLines();

        for (Line line : lines)
        {
            if (line.isWin())
            {
                System.out.println("Win on line " + line.toString()); // this method has not yet been overridden
                return true;
            }
        }
        return false;

    }

    private void addLines()
    {
        for(int row = 0; row < 4; row ++)// Adds 16 lines that exist along a single peg
        {
            for(int col = 0; col < 4; col ++)
            {
                this.lines[currentLine] = new Line(pegs[row][col].getBeads()[0],
                        pegs[row][col].getBeads()[1],
                        pegs[row][col].getBeads()[2],
                        pegs[row][col].getBeads()[3]);
                currentLine++;
            }
        }


        for (int col = 0; col < 4; col ++) // Adds 16 lines along the rows
        {
            for (int height = 0; height < 4; height++)
            {
                this.lines[currentLine] = new Line(pegs[0][col].getBeads()[height],
                        pegs[1][col].getBeads()[height],
                        pegs[2][col].getBeads()[height],
                        pegs[3][col].getBeads()[height]);
                currentLine++;
            }
        }


        for (int row = 1; row < 5; row ++) //Adds 16 lines along the Columns
        {
            for (int height = 0; height < 4; height++)
            {
                lines[currentLine] = new Line(pegs[row][0].getBeads()[height],
                        pegs[row][1].getBeads()[height],
                        pegs[row][2].getBeads()[height],
                        pegs[row][3].getBeads()[height]);
                currentLine++;
            }
        }




        //Adds 8 lines along the Row-Column diagonal

        for (int height = 0; height < 4; height ++)  // 4 lines starting from (0, 0) to (3, 3)
        {
            lines[currentLine] = new Line(pegs[0][0].getBeads()[height],
                    pegs[1][1].getBeads()[height],
                    pegs[2][2].getBeads()[height],
                    pegs[3][3].getBeads()[height]);
            currentLine++;
        }

        for (int height = 0; height < 4; height ++)  // 4 lines starting from (3, 0) to (0, 3)
        {
            lines[currentLine] = new Line(pegs[3][0].getBeads()[height],
                    pegs[2][1].getBeads()[height],
                    pegs[1][2].getBeads()[height],
                    pegs[0][3].getBeads()[height]);
            currentLine++;
        }



        //Adds 8 lines along the Row-Height diagonal

        for (int col = 0; col < 4; col ++)
        {
            lines[currentLine] = new Line(pegs[0][col].getBeads()[0],
                    pegs[1][col].getBeads()[1],
                    pegs[2][col].getBeads()[2],
                    pegs[3][col].getBeads()[3]);
            currentLine++;
        }
        for (int col = 0; col < 4; col ++)
        {
            lines[currentLine] = new Line(pegs[0][col].getBeads()[3],
                    pegs[1][col].getBeads()[2],
                    pegs[2][col].getBeads()[1],
                    pegs[3][col].getBeads()[0]);
            currentLine++;
        }




        //Adds 8 lines along the column-height diagonal
        for (int row = 0; row < 4; row ++)
        {
            lines[currentLine] = new Line(pegs[row][0].getBeads()[0],
                    pegs[row][1].getBeads()[1],
                    pegs[row][2].getBeads()[2],
                    pegs[row][3].getBeads()[3]);
            currentLine++;
        }
        for (int row = 0; row < 4; row ++)
        {
            lines[currentLine] = new Line(pegs[row][3].getBeads()[0],
                    pegs[row][2].getBeads()[1],
                    pegs[row][1].getBeads()[2],
                    pegs[row][0].getBeads()[3]);
            currentLine++;
        }

        //Adds the 4 remaining lines that span all three dimensions

        lines[currentLine] = new Line(pegs[0][0].getBeads()[0],
                pegs[1][1].getBeads()[1],
                pegs[2][2].getBeads()[2],
                pegs[3][3].getBeads()[3]);
        currentLine++;

        lines[currentLine] = new Line(pegs[3][0].getBeads()[0],
                pegs[2][1].getBeads()[1],
                pegs[1][2].getBeads()[2],
                pegs[0][3].getBeads()[3]);
        currentLine++;

        lines[currentLine] = new Line(pegs[0][3].getBeads()[0],
                pegs[1][2].getBeads()[1],
                pegs[2][1].getBeads()[2],
                pegs[3][0].getBeads()[3]);
        currentLine++;

        lines[currentLine] = new Line(pegs[0][0].getBeads()[3],
                pegs[1][1].getBeads()[2],
                pegs[2][2].getBeads()[1],
                pegs[3][3].getBeads()[0]);
        currentLine++;
    }

}