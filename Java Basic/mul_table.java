import java.util.Scanner;

public class mul_table {
    public static void main(String[] args) {
        System.out.println("enter the number:");
        Scanner s=new Scanner(System.in);
        int x=s.nextInt();
        System.out.println("enter the limit:");
        Scanner r=new Scanner(System.in);
        int y=r.nextInt();
        for(int i=1;i<=y;i++){
            System.out.println(x+"x"+i+"="+x*y);
        }
    }
}
