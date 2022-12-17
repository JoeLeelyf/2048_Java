package Window;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BottomPanel extends JPanel {
    public BottomPanel() {
        this.setBackground(ColorFontSet.backColor);
        JLabel tipsLabel = new JLabel();
        tipsLabel.setHorizontalAlignment(JLabel.CENTER);
        tipsLabel.setVerticalAlignment(JLabel.CENTER);
        tipsLabel.setFont(ColorFontSet.tipFont);
        tipsLabel.setForeground(ColorFontSet.textColor_1);
        tipsLabel.setText("Tips: Use arrow keys to move the tiles and get 2048!");
        tipsLabel.setBounds(0, 0, 500, 40);
        this.add(tipsLabel);
    }
}
