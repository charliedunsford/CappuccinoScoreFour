package scorefour.view;

import javax.swing.*;
import java.awt.*;

public class WidgetsView extends JMenuBar
{


        private int numberPlayerWins = 0;
        private int numberAiWins = 0;
        private int numberGamesPlayed = 0;
        JMenu info = new JMenu("Info");
        JLabel playerWinCounter = new JLabel("Number of Player Wins: "+numberPlayerWins);
        JLabel aiWinCounter = new JLabel("Number of AI Wins: "+numberAiWins);
        JLabel gamesPlayed = new JLabel("Number of Games Played: "+numberGamesPlayed);
        public WidgetsView()
        {
            this.setSize(100,100);
            this.setForeground(Color.WHITE);
            this.setOpaque(false);
            this.setBorderPainted(false);


            this.add(info);
            info.setMenuLocation(230,0);
            info.setForeground(Color.WHITE);
            info.add(playerWinCounter);
            info.add(aiWinCounter);
            info.add(gamesPlayed);
            info.setOpaque(false);
            info.setBorderPainted(false);
        }
        public int addWin()
        {
            numberPlayerWins++;
            return numberPlayerWins;
        }

        public int addAiWin()
        {
            numberAiWins++;
            return numberAiWins;
        }

        public int addGamesPlayed()
        {
            numberGamesPlayed++;
            return numberGamesPlayed;
        }
}
