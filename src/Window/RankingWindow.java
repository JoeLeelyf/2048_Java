package Window;

import java.awt.*;
import javax.swing.*;
import BaseFunc.Ranking;
import java.util.Vector;

public class RankingWindow extends JFrame {
    class RankingPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private JLabel rankLabel = new JLabel();
        private JLabel nameLabel = new JLabel();
        private JLabel scoreLabel = new JLabel();

        public RankingPanel(Ranking ranking) {
            this.setBackground(ColorFontSet.backColor);
            this.setLayout(new GridLayout(1, 3));

            rankLabel.setText("    "+ranking.getRank() + "");
            rankLabel.setFont(ColorFontSet.rankFont);
            rankLabel.setForeground(ColorFontSet.textColor_1);
            rankLabel.setVerticalAlignment(SwingConstants.CENTER);
            this.add(rankLabel);

            nameLabel.setText(ranking.getName());
            nameLabel.setFont(ColorFontSet.rankFont);
            nameLabel.setForeground(ColorFontSet.textColor_1);
            nameLabel.setVerticalAlignment(SwingConstants.CENTER);
            this.add(nameLabel);

            scoreLabel.setText("     "+ranking.getScore() + "");
            scoreLabel.setFont(ColorFontSet.rankFont);
            scoreLabel.setForeground(ColorFontSet.textColor_1);
            scoreLabel.setVerticalAlignment(SwingConstants.CENTER);
            this.add(scoreLabel);
        }
    }

    public RankingWindow(Vector<Ranking> rankingList) {
        super("Ranking");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(new GridLayout(5, 1));

        Container contentPane = this.getContentPane();
        contentPane.setBackground(ColorFontSet.backColor);

        for(int i=0;i<Math.min(rankingList.size(),5);i++) {
            RankingPanel rankingPanel = new RankingPanel(rankingList.get(i));
            this.add(rankingPanel);
        }
        this.pack();
    }
}
