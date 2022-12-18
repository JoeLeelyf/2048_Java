package BaseFunc;

import java.io.BufferedReader;
import java.io.File;
import java.io.OutputStreamWriter;
import java.util.Vector;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

public class RankingProcessor {
    private static String fileName = "./src/Data/ranking.txt";
    private static Vector<Ranking> ranking = new Vector<Ranking>();

    public RankingProcessor() {
        readRanking();
    }

    private static void writeRanking() {
        try {
            File file = new File(fileName);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("");
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file))) {
                int rank = 1;
                for (Ranking r : ranking) {
                    writer.write(rank + "," + r.getName() + "," + r.getScore() + "," + r.getBoard() + "\n");
                    rank++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readRanking() {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                ranking.clear();
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    int rank = Integer.parseInt(data[0]);
                    String name = data[1];
                    int score = Integer.parseInt(data[2]);
                    int[][] board = new int[4][4];
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            board[i][j] = Integer.parseInt(data[3 + i * 4 + j]);
                        }
                    }
                    ranking.add(new Ranking(rank, score, name, board));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRanking(int score, String name, int[][] board) {
        int rank = 1;
        for (Ranking r : ranking) {
            if (r.getScore() <= score) {
                break;
            }
            rank++;
        }
        ranking.add(rank - 1, new Ranking(rank, score, name, board));
        writeRanking();
    }

    public Vector<Ranking> getRanking() {
        readRanking();
        return ranking;
    }

}
