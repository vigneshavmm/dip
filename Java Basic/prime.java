import java.util.Scanner;

public class prime {
    public static void main(String[] args) {
        System.out.println("enter the number:");
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int f=0;
        for (int i=1;i<=n;i++){
            if(n%i==0){
                f++;
            }    
        }
        if(f==2){
                System.out.println(n+"is prime");
            }else{
                System.out.println(n+"is not prime");
         }
    }
}