import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Part one method
 */
public class Main {
    public static void main(String[] args) {
        int total = 0;

        File myObj = new File("input.txt");

        // try-with-resources: Scanner will be closed automatically
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int index = 0;
                String high = String.valueOf(data.charAt(index));

                for (int i = 0; i < data.length() - 1; i++) {
                    String temp = String.valueOf(data.charAt(i));
                    if (Integer.parseInt(high) < Integer.parseInt(temp)) {
                        high = temp;
                        index = i;
                    }
                }
                String firstNum = high;

                high = String.valueOf(data.charAt(index + 1));
                for (int j = index + 1; j < data.length(); j++) {
                    String temp = String.valueOf(data.charAt(j));
                    if (Integer.parseInt(high) < Integer.parseInt(temp)) {
                        high = temp;
                        index = j;
                    }
                }
                String secondNum = high;

                String number = firstNum + secondNum;

                total = total + Integer.parseInt(number);


            }
            System.out.println("Total: " + total);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}