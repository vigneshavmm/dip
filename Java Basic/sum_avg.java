import java.util.Scanner;
public class sum_avg {
    public static void main(String[] args) {
        System.out.println("enter the integer:");
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int sum=0;
        for(int i=1;i<=n;i++){
            System.out.println("enter the number " +i+ ":");
            int a=s.nextInt();
            sum+=a;
        }
        System.out.println(sum);
        System.out.println(sum/n);
    }
}