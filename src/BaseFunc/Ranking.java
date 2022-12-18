package BaseFunc;

public class Ranking {
    private int rank;
    private int score;
    private String name;
    private int[][] board = new int[4][4];

    public Ranking(int rank, int score, String name, int[][] board) {
        this.rank = rank;
        this.score = score;
        this.name = name;
        this.board = board;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBoard() {
        String retBoard = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                retBoard += String.valueOf(board[i][j]) + ",";
            }
        }
        return retBoard;
    }

    public int[][] getBoardArray() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public String toString() {
        return rank + "   " + score + "   " + name;
    }

}
