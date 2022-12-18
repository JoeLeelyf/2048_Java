package Window;

import javax.swing.*;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BaseFunc.RankingProcessor;
import BaseFunc.SearchByName;
import BaseFunc.Ranking;

enum Direction {
    UP, DOWN, LEFT, RIGHT
}

public class MainWindow extends JFrame implements KeyListener {
    HeadlinePanel headlinePanel = new HeadlinePanel();
    BoardPanel boardPanel = new BoardPanel();
    BottomPanel bottomPanel = new BottomPanel();
    RankingProcessor rankingProcessor = new RankingProcessor();
    Vector<Ranking> rankingList = new Vector<Ranking>();

    private int TimeLimit = 0;

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
        rankingList = rankingProcessor.getRanking();

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
        menuItem_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Please input your name:");
                Vector<Ranking> result = SearchByName.searchByName(name, rankingList);
                if (result.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No such player!");
                } else {
                    Object[] options = new Object[result.size()];
                    for (int i = 0; i < result.size(); i++) {
                        options[i] = result.get(i).getScore();
                    }
                    int index = JOptionPane.showOptionDialog(null, "Please choose the score:", "Restart a Former Game",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
                    boardPanel.initGame(result.get(index).getBoardArray(), result.get(index).getScore());
                    headlinePanel.renewScore(result.get(index).getScore());
                    headlinePanel.renewBestScore(result.get(index).getScore());
                    boardPanel.repaint();
                }
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
                CountDownThread countDownThread = new CountDownThread();
                countDownThread.start();
            }
        });

        menu_2.add(menuItem_3);
        menuBar.add(menu_2);

        JMenu menu_3 = new JMenu("Rank");
        JMenuItem menuItem_7 = new JMenuItem("Show Ranking List");
        menuItem_7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RankingWindow(rankingList);
            }
        });
        menu_3.add(menuItem_7);
        menuBar.add(menu_3);

        JMenu menu_4 = new JMenu("Save");
        JMenuItem menuItem_4 = new JMenuItem("Save the Game");
        menuItem_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Please input your name:");
                rankingProcessor.addRanking(boardPanel.getBestScore(), name, boardPanel.getBoard());
                rankingList = rankingProcessor.getRanking();
            }
        });
        menu_4.add(menuItem_4);
        menuBar.add(menu_4);

        JMenu menu_5 = new JMenu("Help");
        JMenuItem menuItem_5 = new JMenuItem("How to Play");
        JMenuItem menuItem_6 = new JMenuItem("About");
        menuItem_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpWindow();
            }
        });
        menuItem_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutWindow();
            }
        });
        menu_5.add(menuItem_5);
        menu_5.add(menuItem_6);
        menuBar.add(menu_5);

        this.setJMenuBar(menuBar);
    }

    private void renew() {
        headlinePanel.renewScore(boardPanel.getScore());
        headlinePanel.renewBestScore(boardPanel.getBestScore());
    }

    class CountDownThread extends Thread {
        public void run() {
            try {
                int timeGap = 60000;
                int TimeLeft = TimeLimit;
                while (TimeLeft >= 0) {
                    if (boardPanel.Win()) {
                        break;
                    }
                    headlinePanel.renewTime(TimeLeft);
                    Thread.sleep(timeGap);
                    --TimeLeft;
                    if (TimeLeft == 0) {
                        headlinePanel.renewTime(0);
                        boardPanel.endGame();
                    }
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
        renew();
    }

}
