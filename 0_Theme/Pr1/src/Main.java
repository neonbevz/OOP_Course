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

    private static String get_num(int pos, String str) {
        int cur = 0;
        String out = "";
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) == ' ') {
                cur += 1;
            } else {
                if (cur == pos) {
                    out += str.charAt(i);
                }
            }
        }
        return out;
    }

    private static String biggest(String nums, int amount) {
        if (amount == 1) {
            return get_num(0, nums);
        } else {
            int out = Integer.parseInt(get_num(0, nums));
            for (int i=1; i<amount; i++) {
                if (Integer.parseInt(get_num(i, nums)) > out) {
                    out = Integer.parseInt(get_num(i, nums));
                }
            }
            return Integer.toString(out);
        }
    }

    private static String smallest(String nums, int amount) {
        if (amount == 1) {
            return get_num(0, nums);
        } else {
            int out = Integer.parseInt(get_num(0, nums));
            for (int i=1; i<amount; i++) {
                if (Integer.parseInt(get_num(i, nums)) < out) {
                    out = Integer.parseInt(get_num(i, nums));
                }
            }
            return Integer.toString(out);
        }
    }

    private static String sum(String nums, int amount) {
        int out = 0;
        for (int i=0; i<amount; i++) {
            out += Integer.parseInt(get_num(i, nums));
        }
        return Integer.toString(out);
    }

    private static String product(String nums, int amount) {
        int out = Integer.parseInt(get_num(0, nums));
        for (int i=1; i<amount; i++) {
            out *= Integer.parseInt(get_num(i, nums));
        }
        return Integer.toString(out);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int amount = in.nextInt();
        String numbers = in.nextLine();
//        System.out.println("Biggest: " + biggest(numbers, amount));
//        System.out.println("Smallest: " + smallest(numbers, amount));
//        System.out.println("Sum: " + sum(numbers, amount));
//        System.out.println("Average: " + Float.toString(Integer.parseInt(sum(numbers, amount)) / amount));
//        System.out.println("Product: " + product(numbers, amount));

    }
}
