import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * Part two method
 */
public class Main2 {
    public static void main(String[] args) {
        long total = 0;

        File myObj = new File("input.txt");

        // try-with-resources: Scanner will be closed automatically
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Deque<Character> stack = new ArrayDeque<>();
                int removals = data.length() - 12;

                for(char c: data.toCharArray()){
                    while (!stack.isEmpty() && removals > 0 && stack.peekLast() < c){
                        stack.pollLast();
                        removals --;
                    }
                    stack.addLast(c);
                }

                long result = 0;
                int count = 0;
                for (char c : stack) {
                    if (count == 12) break;
                    result = result * 10 + (c - '0');
                    count++;
                }

                long number =  result;

                total = total + number;


            }
            System.out.println("Total: " + total);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}