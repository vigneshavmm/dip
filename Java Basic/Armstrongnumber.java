import java.util.Scanner;
public class Armstrongnumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();
        int s = 0, t = n, d = (int)Math.log10(n) + 1;
        while (t > 0) {
            int r = t % 10;
            s += Math.pow(r, d);
            t /= 10;
        }
        System.out.println(n + (s == n ? " is an Armstrong number." : " is not an Armstrong number."));
    }
}