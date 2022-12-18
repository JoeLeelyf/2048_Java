package Window;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutWindow extends JFrame {
    public AboutWindow() {
        this.setTitle("About");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(320, 140);
        this.setResizable(false);

        Container container = this.getContentPane();
        container.setBackground(ColorFontSet.backColor);
        container.add(new AboutPanel());
    }

    class AboutPanel extends JPanel {
        public AboutPanel() {
            setLayout(null);
            setBounds(0, 0, 320, 100);
            setBackground(ColorFontSet.backColor);

            JLabel label = new JLabel("Version 1.0");
            label.setBounds(120, 20, 100, 20);
            label.setFont(new Font("Arial", Font.BOLD, 15));
            label.setForeground(Color.BLACK);
            this.add(label);

            JLabel label2 = new JLabel("Author: Joe Lee");
            label2.setBounds(100, 40, 200, 20);
            label2.setFont(new Font("Arial", Font.BOLD, 15));
            label2.setForeground(Color.BLACK);
            this.add(label2);

            JLabel label3 = new JLabel("Email: l-yf21@mails.tsinghua.edu.cn");
            label3.setBounds(20, 60, 300, 20);
            label3.setFont(new Font("Arial", Font.BOLD, 15));
            label3.setForeground(Color.BLACK);
            this.add(label3);

            JLabel label4 = new JLabel("Github:https://github.com/JoeLeelyf/2048_Java");
            label4.setBounds(10, 80, 320, 20);
            label4.setFont(new Font("Arial", Font.BOLD, 13));
            label4.setForeground(Color.BLACK);
            this.add(label4);

        }
    }
}
