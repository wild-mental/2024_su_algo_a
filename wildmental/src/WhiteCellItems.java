import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WhiteCellItems {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String row;
        int whiteFs = 0;
        for (int i=0; i<8; i++) {
            row = br.readLine().strip();
//            System.out.printf("%d / %s / %d\n", i, row, whiteFs);
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == 'F' && (i%2 == j%2)) {
//                    System.out.printf("found at (%d, %d)", i, j);
                    whiteFs++;
                }
            }
        }
        System.out.println(whiteFs);
    }
}
