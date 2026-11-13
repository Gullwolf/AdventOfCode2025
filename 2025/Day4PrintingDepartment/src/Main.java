import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * Part one method
 */
public class Main {
    private static String[][] shelf;

    public static void main(String[] args) throws IOException {
        int total = 0;
        int bigTotal = 0;
        String filename = "input.txt";

        readFile(filename);

        checkShelfs();

        total = getNumberRolls();

        bigTotal += total;
        removeX();

        //Part two to loop it around.
        while(total > 0){
            checkShelfs();
            total = getNumberRolls();
            bigTotal += total;
            removeX();
        }

        System.out.println("Big Total: " + bigTotal);

    }

    public static void removeX(){
        for (int i = 0; i < shelf.length; i++) {
            for (int j = 0; j < shelf[i].length; j++) {
                if (shelf[i][j].equals("X")) {
                    shelf[i][j] = ".";
                }
            }
        }
    }

    public static int getNumberRolls(){
        int count = 0;
        for (int i = 0; i < shelf.length; i++) {
            for (int j = 0; j < shelf[i].length; j++) {
                if (shelf[i][j].equals("X")) {
                    count += 1;
                }
            }
        }

        return count;
    }

    public static void checkShelfs() {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < shelf.length; i++) {
            for (int j = 0; j < shelf[i].length; j++) {
                int surrounded = 0;
                if (Objects.equals(shelf[i][j], "@")) {

                    for (int k = 0; k < 8; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];

                        if (ni >= 0 && ni < shelf.length &&
                                nj >= 0 && nj < shelf[i].length &&
                                (Objects.equals(shelf[ni][nj], "@") || Objects.equals(shelf[ni][nj], "X"))) {
                            surrounded++;
                        }
                    }

                }
                if (surrounded < 4 && Objects.equals(shelf[i][j], "@")){
                    shelf[i][j] = "X";
                }
            }

        }
    }


    public static void readFile(String filename) throws IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(filename));

        List<String[]> rows = new ArrayList<>();
        String line;


        while ((line = buffer.readLine()) != null) {
            rows.add(line.trim().split(""));
        }
        buffer.close();


        int rowCount = rows.size();
        int colCount = rows.get(0).length;

        shelf = new String[rowCount][colCount];


        for (int r = 0; r < rowCount; r++) {
            if (rows.get(r).length != colCount) {
                throw new IllegalArgumentException("Inconsistent column count at row " + r);
            }
            for (int c = 0; c < colCount; c++) {
                shelf[r][c] = rows.get(r)[c];
            }
        }
    }

}