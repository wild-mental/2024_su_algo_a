import java.util.Scanner;

public class RealNumberFinder {
    public static void main(String[] args) {
        // read 1 line from standard input
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt() - 1;
        // find maximum k value satisfying k(k+1)/2 <= n
        int k = (int) (Math.sqrt(2 * (long) n + 0.25) - 0.5);
        int i = n - k * (k + 1) / 2;
        if (k % 2 == 0) {
            System.out.println((k - i + 1) + "/" + (i + 1));
        } else {
            System.out.println((i + 1) + "/" + (k - i + 1));
        }
    }
}
