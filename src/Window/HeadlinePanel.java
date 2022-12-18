package Window;

import java.awt.*;
import javax.swing.*;

public class HeadlinePanel extends JPanel {
    public HeadlinePanel() {
        this.setLayout(null);
        this.setBackground(ColorFontSet.backColor);

        JLabel Title = new JLabel("2048");
        Title.setFont(ColorFontSet.titleFont);
        Title.setForeground(ColorFontSet.textColor_1);
        Title.setHorizontalAlignment(JLabel.LEFT);
        Title.setVerticalAlignment(JLabel.CENTER);
        Title.setBounds(50, 0, 200, 60);

        JLabel Score = new JLabel("score");
        Score.setFont(ColorFontSet.scoreFont);
        Score.setForeground(ColorFontSet.textColor_2);
        Score.setHorizontalAlignment(JLabel.CENTER);
        Score.setVerticalAlignment(JLabel.CENTER);
        Score.setOpaque(true);
        Score.setBackground(ColorFontSet.buttonScore);
        Score.setBounds(250, 20, 100, 16);

        JLabel ScoreNum = new JLabel("0");
        ScoreNum.setFont(ColorFontSet.scoreNumFont);
        ScoreNum.setForeground(Color.WHITE);
        ScoreNum.setHorizontalAlignment(JLabel.CENTER);
        ScoreNum.setVerticalAlignment(JLabel.CENTER);
        ScoreNum.setOpaque(true);
        ScoreNum.setBackground(ColorFontSet.buttonScore);
        ScoreNum.setBounds(250, 36, 100, 35);

        JLabel Best = new JLabel("best");
        Best.setFont(ColorFontSet.bestFont);
        Best.setForeground(ColorFontSet.textColor_2);
        Best.setHorizontalAlignment(JLabel.CENTER);
        Best.setVerticalAlignment(JLabel.CENTER);
        Best.setOpaque(true);
        Best.setBackground(ColorFontSet.buttonBestScore);
        Best.setBounds(370, 20, 100, 16);

        JLabel BestNum = new JLabel("0");
        BestNum.setFont(ColorFontSet.bestNumFont);
        BestNum.setForeground(Color.WHITE);
        BestNum.setHorizontalAlignment(JLabel.CENTER);
        BestNum.setVerticalAlignment(JLabel.CENTER);
        BestNum.setOpaque(true);
        BestNum.setBackground(ColorFontSet.buttonBestScore);
        BestNum.setBounds(370, 36, 100, 35);

        JLabel TimeLeft = new JLabel();
        TimeLeft.setFont(ColorFontSet.timeFont);
        TimeLeft.setForeground(ColorFontSet.textColor_3);
        TimeLeft.setHorizontalAlignment(JLabel.CENTER);
        TimeLeft.setVerticalAlignment(JLabel.CENTER);
        TimeLeft.setBounds(0, 70, 200, 30);

        add(Title);
        add(Score);
        add(ScoreNum);
        add(Best);
        add(BestNum);
        add(TimeLeft);
    }

    public void renewScore(int score) {
        JLabel ScoreNum = (JLabel) this.getComponent(2);
        ScoreNum.setText(String.valueOf(score));
    }

    public void renewBestScore(int best) {
        JLabel BestNum = (JLabel) this.getComponent(4);
        BestNum.setText(String.valueOf(best));
    }

    public void renewTime(int time) {
        JLabel TimeLeft = (JLabel) this.getComponent(5);
        if (time >= 2) {
            TimeLeft.setText("Time Left: " + String.valueOf(time) + " min");
        } else if (time == 1) {
            TimeLeft.setForeground(Color.RED);
            TimeLeft.setText("Time Left: " + String.valueOf(time) + " min");
        } else {
            TimeLeft.setForeground(Color.RED);
            TimeLeft.setFont(new Font("Arial", Font.BOLD, 25));
            TimeLeft.setText("Time Out!");
        }
    }
}
