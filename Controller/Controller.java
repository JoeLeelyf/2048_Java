package Controller;

import java.util.Random;
import java.util.Vector;

enum Direction {
    UP, DOWN, LEFT, RIGHT
}

public class Controller {

    private Direction myDirection;
    private int[][] board = new int[4][4];
    private int[][] retBoard = new int[4][4];
    private int score;
    private int bestScore;

    private void MoveUp() {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                int y = i;
                while (--y >= 0) {
                    if (board[y][j] == 0) {
                        board[y][j] = board[y + 1][j];
                        board[y + 1][j] = 0;
                    }
                }
            }
        }
    }

    private void MoveDown() {
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 0; i--) {
                int y = i;
                while (++y <= 3) {
                    if (board[y][j] == 0) {
                        board[y][j] = board[y - 1][j];
                        board[y - 1][j] = 0;
                    }
                }
            }
        }
    }

    private void MoveRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >= 0; j--) {
                int x = j;
                while (++x <= 3) {
                    if (board[i][x] == 0) {
                        board[i][x] = board[i][x - 1];
                        board[i][x - 1] = 0;
                    }
                }
            }
        }
    }

    private void MoveLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int x = j;
                while (--x >= 0) {
                    if (board[i][x] == 0) {
                        board[i][x] = board[i][x + 1];
                        board[i][x + 1] = 0;
                    }
                }
            }
        }
    }

    private void MergeUp() {
        for (int j = 0; j < 4; j++) {
            for (int i = 1; i < 4; i++) {
                if (board[i][j] == board[i - 1][j] && board[i][j] != 0) {
                    board[i - 1][j] += 1;
                    this.score += Math.pow(2, board[i][j]);
                    if (this.score > this.bestScore) {
                        bestScore = this.score;
                    }
                    board[i][j] = 0;
                    this.Move(myDirection.ordinal());
                }
            }
        }
    }

    private void MergeDown() {
        for (int j = 0; j < 4; j++) {
            for (int i = 2; i >= 0; i--) {
                if (board[i][j] == board[i + 1][j] && board[i][j] != 0) {
                    board[i + 1][j] += 1;
                    this.score += Math.pow(2, board[i][j]);
                    if (this.score > this.bestScore) {
                        bestScore = this.score;
                    }
                    board[i][j] = 0;
                    this.Move(myDirection.ordinal());
                }
            }
        }
    }

    private void MergeRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j >= 0; j--) {
                if (board[i][j] == board[i][j + 1] && board[i][j] != 0) {
                    board[i][j + 1] += 1;
                    this.score += Math.pow(2, board[i][j]);
                    if (this.score > this.bestScore) {
                        bestScore = this.score;
                    }
                    board[i][j] = 0;
                    this.Move(myDirection.ordinal());
                }
            }
        }
    }

    private void MergeLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (board[i][j] == board[i][j - 1] && board[i][j] != 0) {
                    board[i][j - 1] += 1;
                    this.score += Math.pow(2, board[i][j]);
                    if (this.score > this.bestScore) {
                        bestScore = this.score;
                    }
                    board[i][j] = 0;
                    this.Move(myDirection.ordinal());
                }
            }
        }
    }

    public Controller() {
        this.score = 0;
        this.bestScore = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = 0;
            }
        }
    }

    public int GetScore() {
        return this.score;
    }

    public int GetBestScore() {
        return this.bestScore;
    }

    public int[][] GetBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    this.retBoard[i][j] = (int) Math.pow(2, board[i][j]);
                } else {
                    this.retBoard[i][j] = 0;
                }
            }
        }
        return this.retBoard;
    }

    public int[][] Init() {
        this.score = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = 0;
            }
        }
        Random random = new Random();
        int x_1 = random.nextInt(4);
        int y_1 = random.nextInt(4);
        int x_2, y_2;
        do {
            x_2 = random.nextInt(4);
            y_2 = random.nextInt(4);
        } while (x_2 == x_1 && y_2 == y_1);
        board[x_1][y_1] = 1;
        board[x_2][y_2] = 2;
        return board;
    }

    public int[][] Move(int indexDirection) {
        Direction direction = Direction.values()[indexDirection];
        myDirection = direction;
        switch (direction) {
            case UP:
                this.MoveUp();
                break;
            case DOWN:
                this.MoveDown();
                break;
            case LEFT:
                this.MoveLeft();
                break;
            case RIGHT:
                this.MoveRight();
                break;
        }
        return board;
    }

    public int[][] Merge() {
        switch (myDirection) {
            case UP:
                this.MergeUp();
                break;
            case DOWN:
                this.MergeDown();
                break;
            case LEFT:
                this.MergeLeft();
                break;
            case RIGHT:
                this.MergeRight();
                break;
        }

        if (!isGameOver()) {
            Vector<Integer> blankpoint_x = new Vector<Integer>();
            Vector<Integer> blankpoint_y = new Vector<Integer>();
            Random random = new Random();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (board[i][j] == 0) {
                        blankpoint_x.add(i);
                        blankpoint_y.add(j);
                    }
                }
            }
            int index = random.nextInt(blankpoint_x.size());
            board[blankpoint_x.elementAt(index)][blankpoint_y.elementAt(index)] = random.nextInt(2) + 1;
        }
        return board;
    }

    public boolean isGameOver() {
        boolean isGameOver = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    isGameOver = false;
                }
            }
        }
        return isGameOver;
    }

    public boolean isWin() {
        boolean isWin = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 11) {
                    isWin = true;
                }
            }
        }
        return isWin;
    }

}
