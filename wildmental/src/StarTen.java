import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class StarTen {
    static int[][] unit = {
            new int[] {1,1,1},
            new int[] {1,0,1},
            new int[] {1,1,1}
    };
    static int unitLv = 1;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int threePower = Integer.parseInt(br.readLine().strip());
        // ShortCut
        if (threePower == 3) { printResult(unit); return;}
        // 거듭 제곱 계산
        int power = 0;
        do {
            threePower /= 3;
            power++;
        } while (threePower != 1);
        // 유닛 증폭
        for (int i=1; i<power; i++) {
            unit = fillFractalWithUnit(unit);
            unitLv++;
        }
        // 프린트
        printResult(unit);
    }
    static int[][] fillFractalWithUnit(int[][] unit) {
        int shift = (int) Math.pow(3, unitLv);
        int newSize = (int) Math.pow(3, unitLv+1);
//        System.out.printf("shift:%d newSize:%d \n", shift, newSize);
        int[][] newUnit = new int[newSize][newSize];
        int[][] indexShifter = {
            new int[] {0,0}, new int[] {0,1}, new int[] {0,2},
            new int[] {1,0},                  new int[] {1,2},
            new int[] {2,0}, new int[] {2,1}, new int[] {2,2},
        };
        // 기존 유닛 내부의 모든 좌표를 순회 하면서
        for (int i=0; i<unit.length; i++){
            for (int j=0; j<unit.length; j++){
                // 1값을 만날 때마다
                if (unit[i][j]==1){
                    // 새로운 유닛에 8배 복사
                    for (int[] xy: indexShifter){
                        int shiftingX = (xy[0]*shift);
                        int shiftingY = (xy[1]*shift);
//                        System.out.printf("i,j (%d,%d)\n", i, j);
//                        System.out.printf("x,y (%d,%d)\n", shiftingX, shiftingY);
                        newUnit[i+shiftingX][j+shiftingY] = 1;
                    }
                }
            }
        }
        return newUnit;
    }
    static void printResult(int[][] canvas) {
        for (int[] row: canvas) {
            System.out.println(
                    Arrays.stream(row)
                          .mapToObj(num-> num == 1? "*": " ")
                          .collect(Collectors.joining())
            );
        }
    }
}
