package scorefour.view;

import javax.swing.*;

public class WinLoseIndicator
{
    private String win = ("You Won the Game!!!");
    private String lose = ("You Lost the game... Give Up.");
    public boolean Won;

    public void WinLoseIndicator()
    {
        JOptionPane.showMessageDialog(null,message(),"Win/Lose",JOptionPane.PLAIN_MESSAGE);
    }


    private String message()
    {
        if (Won==true)
        {
            return win;
        }
        else
        {
            return lose;
        }
    }

}
