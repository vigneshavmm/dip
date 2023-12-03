import java.util.Scanner;
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println("enter the number");
        Scanner s = new Scanner(System.in);
        int n=s.nextInt();
        int a=-1,b=1,c;
        for(int i=1;i<n;i++){
            c=a+b;
            System.out.println(c);
            a=b;
            b=c;
        }
    }
}