package Window;

import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Controller.Controller;

enum Direction {
    UP, DOWN, LEFT, RIGHT
}

public class BoardPanel extends JPanel implements KeyListener {

    private Controller controller = new Controller();
    private Direction nextDirection;
    private boolean isValidInput = false;

    private static int score = 0;
    private static int bestScore = 0;

    private static boolean endGame=false;

    public BoardPanel() {
        this.setLayout(null);
        bestScore = 0;
        initGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                nextDirection = Direction.UP;
                isValidInput = true;
                break;
            case KeyEvent.VK_DOWN:
                nextDirection = Direction.DOWN;
                isValidInput = true;
                break;
            case KeyEvent.VK_LEFT:
                nextDirection = Direction.LEFT;
                isValidInput = true;
                break;
            case KeyEvent.VK_RIGHT:
                nextDirection = Direction.RIGHT;
                isValidInput = true;
                break;
            case KeyEvent.VK_ESCAPE:
                initGame();
                break;
            default:
                isValidInput = false;
                break;
        }
        if (isValidInput && !GameOver() && !Win()) {
            controller.Move(nextDirection.ordinal());
            controller.Merge();
            score = controller.GetScore();
            bestScore = controller.GetBestScore();
        }
        isValidInput = false;
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    public void initGame() {
        controller.Init();
        score = 0;
        isValidInput = false;
        addKeyListener(this);
        repaint();
    }

    private boolean GameOver() {
        return controller.isGameOver();
    }

    private boolean Win() {
        return controller.isWin();
    }

    private void drawBoard(Graphics g) {
        int[][] board = controller.GetBoard();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(ColorFontSet.boardLineColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), ColorFontSet.BOARD_ARC, ColorFontSet.BOARD_ARC);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                drawTile(g2d, board[i][j], i, j);
            }
        }
    }

    private void drawTile(Graphics2D g2d, int value, int i, int j) {

        g2d.setColor(getTileColor(value));
        g2d.fillRoundRect(ColorFontSet.TILE_GAP + (ColorFontSet.TILE_GAP + ColorFontSet.TILE_SIZE) * j,
                ColorFontSet.TILE_GAP + (ColorFontSet.TILE_GAP + ColorFontSet.TILE_SIZE) * i, ColorFontSet.TILE_SIZE,
                ColorFontSet.TILE_SIZE, ColorFontSet.TILE_ARC, ColorFontSet.TILE_ARC);
        g2d.setColor(getForeColor(value));
        g2d.setFont(getForeFont(value));
        FontMetrics fm = g2d.getFontMetrics(getForeFont(value));
        int x = ColorFontSet.TILE_GAP + (ColorFontSet.TILE_GAP + ColorFontSet.TILE_SIZE) * j
                + (ColorFontSet.TILE_SIZE - fm.stringWidth(String.valueOf(value))) / 2;
        int y = ColorFontSet.TILE_GAP + (ColorFontSet.TILE_GAP + ColorFontSet.TILE_SIZE) * i
                + (ColorFontSet.TILE_SIZE - fm.getHeight()) / 2 + fm.getAscent();
        g2d.drawString(String.valueOf(value), x, y);
    }

    private Color getTileColor(int value) {
        switch (value) {
            case 0:
                return ColorFontSet.boardBackColor;
            case 2:
                return ColorFontSet.tile2Color;
            case 4:
                return ColorFontSet.tile4Color;
            case 8:
                return ColorFontSet.tile8Color;
            case 16:
                return ColorFontSet.tile16Color;
            case 32:
                return ColorFontSet.tile32Color;
            case 64:
                return ColorFontSet.tile64Color;
            case 128:
                return ColorFontSet.tile128Color;
            case 256:
                return ColorFontSet.tile256Color;
            case 512:
                return ColorFontSet.tile512Color;
            case 1024:
                return ColorFontSet.tile1024Color;
            case 2048:
                return ColorFontSet.tile2048Color;
            default:
                return ColorFontSet.boardBackColor;
        }
    }

    private Color getForeColor(int value) {
        if (value == 0) {
            return ColorFontSet.boardBackColor;
        }
        if (value <= 4) {
            return ColorFontSet.textColor_1;
        }
        return Color.WHITE;
    }

    private Font getForeFont(int value) {
        if (value <= 64) {
            return ColorFontSet.tileFont_1;
        }
        return ColorFontSet.tileFont_2;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (isValidInput) {
            controller.Move(nextDirection.ordinal());
            isValidInput = false;
        }
        drawBoard(g);
        if (GameOver()||endGame) {
            g.setColor(new Color(104, 104, 104, 150));
            g.fillRoundRect(0, 0, getWidth(), getHeight(), ColorFontSet.BOARD_ARC, ColorFontSet.BOARD_ARC);
            g.setColor(Color.WHITE);
            g.setFont(ColorFontSet.tileFont_1);
            String str = "Game Over!";
            FontMetrics fm = g.getFontMetrics(ColorFontSet.titleFont);
            g.drawString(str, (getWidth() - fm.stringWidth(str)+100) / 2, getHeight() / 2);
        }
        if (Win()) {
            g.setColor(new Color(104, 104, 104, 150));
            g.fillRoundRect(0, 0, getWidth(), getHeight(), ColorFontSet.BOARD_ARC, ColorFontSet.BOARD_ARC);
            g.setColor(Color.WHITE);
            g.setFont(ColorFontSet.tileFont_1);
            String str = "You Win!";
            FontMetrics fm = g.getFontMetrics(ColorFontSet.tileFont_1);
            g.drawString(str, (getWidth() - fm.stringWidth(str)+100) / 2, getHeight() / 2);
        }
    }

    public int getScore() {
        return score;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void endGame() {
        this.endGame = true;
        repaint();
    }
}
