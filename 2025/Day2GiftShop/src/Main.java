import java.io.File;                  // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        BigInteger total = BigInteger.valueOf(0);
        System.out.println(total);

        File myObj = new File("input.txt");

        // try-with-resources: Scanner will be closed automatically
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArr = data.split(",");

                for (String elem : dataArr) {
                    String[] parts = elem.split("-");
                    String part1 = parts[0]; // 004
                    String part2 = parts[1]; // 034556
                    BigInteger lowerLimit = new BigInteger(part1);
                    BigInteger upperLimit = new BigInteger(part2);

                    //Loop from bigInt1 to bigInt2
                    for (BigInteger bigInt = lowerLimit; bigInt.compareTo(upperLimit) <= 0; bigInt = bigInt.add(BigInteger.ONE)) {
                        //boolean result = splitInHalf(bigInt.toString());
                        boolean result = containsOnlySubstrings(bigInt.toString());
                        if (result) {
                            System.out.println("Int: " + bigInt);
                            //System.out.println("Result: " + result);
                            //System.out.println("Total Before: " + total);
                            total = total.add(bigInt);
                            //System.out.println("Total After: " + total);
                        }

                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Total: " + total);
    }


    /**
     * Part one method
     * @param string
     * @return
     */
    public static boolean splitInHalf(String string) {
        if (string.length() < 2) {
            return false;
        }
        if ((string.length() % 2) != 0) {
            return false;
        }

        String firstHalf = string.substring(0, (string.length() / 2));
        String secondHalf = string.substring((string.length() / 2));
        return firstHalf.equals(secondHalf);
    }

    /**
     * Part two method
     * @param string
     * @return
     */
    public static boolean containsOnlySubstrings(String string) {
        if (string.length() < 2) {
            return false;
        }
        StringBuilder substr = new StringBuilder();
        for (int i = 0; i < string.length() / 2; i++) {
            substr.append(string.charAt(i));
            String clearedFromSubstrings = string.replaceAll(substr.toString(), "");

            if (clearedFromSubstrings.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}