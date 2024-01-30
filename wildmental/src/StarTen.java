import java.io.*;

public class StarTen {
    static int[][] unit = {  // 메모리 절약을 위해서 byte 타입으로 바꾼다.
            {1,1,1},
            {1,0,1},
            {1,1,1}
    };
    static int unitLv = 1;
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int threePower = Integer.parseInt(br.readLine().strip());  // N 값을 그대로 입력 받는다.
        // ShortCut
        if (threePower == 3) { printResult(unit); return;}  // 기본 유닛 그대로 출력할 수 있는 숏컷
        // 거듭 제곱 계산
        int power = 0;  // k값을 계산
        do {  // Math 의 로그 연산에서 밑을 3으로 바꾸어 적용해도 된다.
            threePower /= 3;
            power++;  // 2부터 들어옴
        } while (threePower != 1);
        // 유닛 증폭 (8배로 특정 패턴의 증폭 수행)
        for (int i=1; i<power; i++) {  // 재귀 효과를 얻을 수 있는 반복문 컨트롤을 적용
            unit = fillFractalWithUnit(unit);  // unit 데이터를 재귀시킴
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
        for (int i=0; i<unit.length; i++){  // String 으로 다루면서, 복사 로직을 행 단위로 개선할 수도 있다.
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
