package Window;

import javax.swing.*;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


enum Direction {
    UP, DOWN, LEFT, RIGHT
}

public class MainWindow extends JFrame implements KeyListener {
    HeadlinePanel headlinePanel = new HeadlinePanel();
    BoardPanel boardPanel = new BoardPanel();
    BottomPanel bottomPanel = new BottomPanel();
    private int TimeLimit = 0;
    private boolean isTimeLimit = false;

    public MainWindow() {
        super("2048 Game");
        int window_w = 500;
        int window_h = 700;
        this.setSize(window_w, window_h);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.SetMenuBar();
        this.addKeyListener(this);

        Container contentPane = this.getContentPane();
        contentPane.setBackground(ColorFontSet.backColor);

        int width_h = 500;
        int height_h = 100;
        headlinePanel.setBounds(0, 20, width_h, height_h);
        contentPane.add(headlinePanel);

        int width_b = 470;
        int height_b = 470;
        boardPanel.setBounds((window_w - width_b) / 2, 130, width_b, height_b);
        contentPane.add(boardPanel);
        this.addKeyListener(boardPanel);

        int width_btm = 500;
        int height_btm = 50;
        bottomPanel.setBounds(0, 620, width_btm, height_btm);
        this.add(bottomPanel);
    }

    public void SetMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu_1 = new JMenu("Start");
        JMenuItem menuItem_1 = new JMenuItem("Start a New Game");
        JMenuItem menuItem_2 = new JMenuItem("Restart a Former Game");
        menuItem_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardPanel.initGame();
            }
        });
        menu_1.add(menuItem_1);
        menu_1.add(menuItem_2);
        menuBar.add(menu_1);

        JMenu menu_2 = new JMenu("Setting");
        JMenuItem menuItem_3 = new JMenuItem("Timing Mode");
        menuItem_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = new Object[] { "4 min", "6 min", "8 min", "10 min", "12 min" };
                TimeLimit = JOptionPane.showOptionDialog(null, "Please choose the time limit:", "Timing Mode",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                TimeLimit = (TimeLimit + 1) * 4;
                isTimeLimit = true;
                CountDownThread countDownThread = new CountDownThread();
                countDownThread.start();
            }
        });

        menu_2.add(menuItem_3);
        menuBar.add(menu_2);

        JMenu menu_3 = new JMenu("Help");
        JMenuItem menuItem_4 = new JMenuItem("How to Play");
        JMenuItem menuItem_5 = new JMenuItem("About");
        menuItem_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpWindow();
            }
        });
        menu_3.add(menuItem_4);
        menu_3.add(menuItem_5);
        menuBar.add(menu_3);

        this.setJMenuBar(menuBar);
    }

    private void renew() {
        headlinePanel.renewScore(boardPanel.getScore());
        headlinePanel.renewBestScore(boardPanel.getBestScore());
    }

    class CountDownThread extends Thread {
        public void run() {
            try {
                int timeGap = 6000;
                int TimeLeft=TimeLimit;
                boolean isCountSec=false;
                while (TimeLeft > 0) {
                    if(TimeLeft>=2 && !isCountSec){
                        headlinePanel.renewTime(TimeLeft);
                        Thread.sleep(timeGap);
                        TimeLeft--;
                    }
                    else{
                        isCountSec=true;
                        TimeLeft=60;
                        headlinePanel.renewTime(TimeLeft);
                        Thread.sleep(timeGap/60);
                        TimeLeft--;
                    }
                }
                if (TimeLeft == 0) {
                    headlinePanel.renewTime(0);
                    boardPanel.endGame();
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        renew();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        renew();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
