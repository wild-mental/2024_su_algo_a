import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BridgingWithoutCrossing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력 처리
        int cases = Integer.parseInt(br.readLine());
        for (int i=0; i<cases; i++) {
            int[] w_e = Arrays.stream(br.readLine().strip().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(calculateCombination(w_e[0], w_e[1]));
        }
    }
    // 조합 계산 메서드
    public static int calculateCombination(int m, int n) {
        // 초기화
        int result = 1;
        // m이 n-m 보다 큰 경우, m을 n-m으로 바꿈
        if (m > n - m) {
            m = n - m;
        }
        // 분자와 분모 계산
        for (int i = 0; i < m; i++) {
            result *= (n - i);
            result /= (i + 1);
        }
        return result;
    }
}
