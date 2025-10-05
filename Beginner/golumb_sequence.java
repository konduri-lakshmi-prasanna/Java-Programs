public class GolombSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        System.out.println("Golomb sequence up to " + n + ":");
        for (int i = 1; i <= n; i++) {
            System.out.print(golomb(i) + " ");
        }
        scanner.close();
    }

    public static int golomb(int n) {
        if (n == 1) {
            return 1;
        }
        return 1 + golomb(n - golomb(golomb(n - 1)));
    }
}