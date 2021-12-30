import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Solution {
    private final ArrayList<Line> lines;

    public Solution(String filename) { this.lines = parse(filename); }

    private ArrayList<Line> parse(String filename) {
        ArrayList<Line> lines = new ArrayList<>();

        try {
            FileInputStream stream = new FileInputStream(filename);
            Scanner         sc     = new Scanner(stream);

            while (sc.hasNext()) {
                String[] p1  = sc.next().split(",");
                String[] p2;

                if ((sc.hasNext()) && (sc.next().equals("->"))) {
                    if (sc.hasNext()) {
                        p2 = sc.next().split(",");

                        lines.add(new Line(Integer.parseInt(p1[0]), Integer.parseInt(p1[1]),
                                           Integer.parseInt(p2[0]), Integer.parseInt(p2[1])));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    private void dumpSol(int[][] matrix) {
        int total = 0;

        for (int i=0; i < 1000; ++i)
            for (int j = 0; j < 1000; ++j)
                if (matrix[i][j] > 1) total++;

        System.out.println("Total points with 2 or more crossings: " + total);
    }

    public void solveP1() {
        int [][] matrix = new int[1000][1000];

        for (Line line : this.lines) {
            int yP = line.y2 - line.y1;
            int xP = line.x2 - line.x1;

            if (yP == 0) {
                if (line.x1 > line.x2) {
                    for (int x = line.x2; x <= line.x1; ++x) { matrix[line.y1][x]++; }
                } else {
                    for (int x = line.x1; x <= line.x2; ++x) { matrix[line.y1][x]++; }
                }
            } else if (xP == 0) {
                if (line.y1 > line.y2) {
                    for (int y = line.y2; y <= line.y1; ++y) { matrix[y][line.x1]++; }
                } else {
                    for (int y = line.y1; y <= line.y2; ++y) { matrix[y][line.x1]++; }
                }
            }
        }

        dumpSol(matrix);
    }

    public void solveP2() {
        int[][] matrix = new int[1000][1000];

        for (Line line : this.lines) {
            int yP = line.y2 - line.y1;
            int xP = line.x2 - line.x1;

            if (yP == 0) {
                if (line.x1 > line.x2) {
                    for (int x = line.x2; x <= line.x1; ++x) { matrix[line.y1][x]++; }
                } else {
                    for (int x = line.x1; x <= line.x2; ++x) { matrix[line.y1][x]++; }
                }
            } else if (xP == 0) {
                if (line.y1 > line.y2) {
                    for (int y = line.y2; y <= line.y1; ++y) { matrix[y][line.x1]++; }
                } else {
                    for (int y = line.y1; y <= line.y2; ++y) { matrix[y][line.x1]++; }
                }
            } else if (Math.abs(yP / xP) == 1) {
                if ((line.x1 < line.x2) && (line.y1 < line.y2)) {
                    for (int i = 0; i <= (line.y2 - line.y1); ++i) { matrix[line.y1 + i][line.x1 + i]++; }
                } else if ((line.x1 <= line.x2) && (line.y1 > line.y2)) {
                    for (int i = 0; i <= (line.y1 - line.y2); ++i) { matrix[line.y1 - i][line.x1 + i]++; }
                } else if ((line.x1 > line.x2) && (line.y1 < line.y2)) {
                    for (int i = 0; i <= (line.y2 - line.y1); ++i) { matrix[line.y1 + i][line.x1 - i]++; }
                } else if ((line.x1 > line.x2) && (line.y1 > line.y2)) {
                    for (int i = 0; i <= line.y1 - line.y2; ++i) { matrix[line.y1 - i][line.x1 - i]++; }
                }
            }
        }

        dumpSol(matrix);
    }

    private static class Line {
        int x1;
        int x2;
        int y1;
        int y2;

        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }
}