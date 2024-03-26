import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class OrdinaryBackpack {
    public static void main(String[] args) throws Exception {
        // BufferedReader를 사용하여 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫 번째 줄에서 n과 k를 읽어들임
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]); // 물건의 개수
        int k = Integer.parseInt(input[1]); // 배낭의 무게 한도

        // 물건의 무게와 가치를 담을 배열 생성
        int[] w = new int[n]; // 물건의 무게
        int[] v = new int[n]; // 물건의 가치
        // n개의 줄에서 각 물건의 무게와 가치를 읽어들임
        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            w[i] = Integer.parseInt(input[0]); // 물건의 무게 입력
            v[i] = Integer.parseInt(input[1]); // 물건의 가치 입력
        }

        // 동적 프로그래밍 배열 초기화
        int[][] dp = new int[n+1][k+1]; // dp 배열: [물건의 개수+1][배낭의 무게 한도+1]

        // dp 배열 채우기
        for (int i = 1; i <= n; i++) { // 각 물건에 대해 반복
            for (int j = 1; j <= k; j++) { // 배낭의 무게 한도에 대해 반복
                if (w[i-1] <= j) {
                    // 현재 물건을 넣을 수 있는 경우
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i-1]] + v[i-1]);
                    for (int[] warr: dp) {
                        System.out.println(Arrays.toString(warr));
                    }
                    System.out.println("----------------------------");
                } else {
                    // 현재 물건을 넣을 수 없는 경우
                    dp[i][j] = dp[i-1][j]; // 이전 상태 그대로 가져옴
                }
            }
        }

        // 결과 출력
        System.out.println(dp[n][k]); // 최대 가치 출력
    }
}
