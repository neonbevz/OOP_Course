import java.util.Arrays;
import java.util.Scanner;


public class Main {

    private static int sum(int[] arr) {
        int out = 0;
        for (int num : arr) {
            out += num;
        }
        return out;
    }

    private class Numbers {
        String str;

        public Numbers(String str) {
            this.str = str;
        }

        public String iterate(int pos) {
            int cur = 0;
            String out = "";
            for (int i=0; i<this.str.length(); i++) {
                if (this.str.charAt(i) == ' ') {
                    cur += 1;
                } else {
                    if (cur == pos) {
                        out += this.str.charAt(i);
                    }
                }
            }
            return out;
        }
    }

    public static void main(String[] args) {
        String in = "1 22 333 4444";
        Numbers nums = new Numbers(in);

//        Scanner in = new Scanner(System.in);
//        System.out.println("Amount: ");
//        int amount = in.nextInt();
//        int numbers[] = new int[amount];
//        for (int i=0; i<amount; i++) {
//            System.out.println("Number " + Integer.toString(i+1) + ":");
//            numbers[i] = in.nextInt();
//        }
//        Arrays.sort(numbers);
//        for (int i=0; i<amount; i++) {
//            System.out.print(numbers[i]);
//        }
//        System.out.println("Biggest: " + Integer.toString(numbers[numbers.length -1]));
//        System.out.println("Smallest: " + Integer.toString(numbers[0]));
//        System.out.println("Sum: " + Integer.toString(sum(numbers)));
//        System.out.println("Average: " + Float.toString(sum(numbers) / numbers.length));

    }
}
