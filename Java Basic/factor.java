import java.util.Scanner;

public class factor {
    public static void main(String[] args) {
        System.out.println("enter the number:");
        Scanner s=new Scanner(System.in);
        int u=s.nextInt();
        for(int i=1;i<=u;i++){
            if(u%i==0)
            {
                System.out.println(i+" ");
            }
        }

    }
}
