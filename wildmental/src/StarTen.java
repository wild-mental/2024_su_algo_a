import java.io.*;

public class StarTen {
    static int[][] unit = {
            {1,1,1},
            {1,0,1},
            {1,1,1}
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
            {0,0},{0,1},{0,2},
            {1,0},      {1,2},
            {2,0},{2,1},{2,2},
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
    static void printResult(int[][] canvas) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int[] row: canvas) {
            for (int num: row){
                if (num == 1) bw.write("*");
                else bw.write(" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}
