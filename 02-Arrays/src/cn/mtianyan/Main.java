package cn.mtianyan;

public class Main {

    public static void main(String[] args) {
        // 必须传入长度
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        System.out.println("=========");

        int[] scores = new int[]{100, 99, 96};
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
        }

        System.out.println("=========");

        for (int score : scores) {
            System.out.println(score);
        }

        System.out.println("=========");
        scores[0] = 92;

        for (int score : scores) {
            System.out.println(score);
        }
    }
}
