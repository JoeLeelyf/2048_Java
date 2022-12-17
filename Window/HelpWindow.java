package Window;

import javax.swing.*;

public class HelpWindow extends JFrame{
    public HelpWindow() {
        this.setTitle("How to play");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
