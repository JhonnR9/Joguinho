public class Test {
    public int position[][] = new int[4][4];
    public int[] build = new int[16];

    public int result[] = new int[16];

    void update() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                position[x][y] = build[x * 3 + y];
            }
        }

        System.out.println(position[1]);
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.update();
    }
}
