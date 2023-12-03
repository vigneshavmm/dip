import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        System.out.print("Enter the integer");
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int f=1;
        for(int i=1;i<=n;i++){
            f*=i;
        }
        System.out.println("factorial of " +n+ " is " +f);
    }
}
