
import java.util.*;

public class volumeFunctionOverloading {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter radius of sphere: ");
            int r = sc.nextInt();

            System.out.print("Enter side of cube: ");
            float s = sc.nextFloat();

            System.out.print("Enter length, breadth, and height of cuboid: ");
            int l = sc.nextInt();
            int b = sc.nextInt();
            int h = sc.nextInt();

            calculate(r);
            calculate(s);
            calculate(l, b, h);
        }
    }

    static void calculate(int radius) {
        double volume = (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
        System.out.printf("The Volume of Sphere = %.2f%n", volume);
    }

    static void calculate(float side) {
        double volume = Math.pow(side, 3);
        System.out.printf("The Volume of Cube = %.2f%n", volume);
    }

    static void calculate(int length, int breadth, int height) {
        double volume = length * breadth * height;
        System.out.printf("The Volume of Cuboid = %.2f%n", volume);
    }
}
