import java.util.Scanner;
public class strong_number {
    public static void main(String[] args) {
        int n,org,r,f=1,i,sum;
        System.out.println("enter the number");
        Scanner s=new Scanner(System.in);
        n=s.nextInt();
        org=n;
        while(n>0){
            r=n%10;
            System.out.println(r);
             for(i=1;i<=r;i++){
                f*=i;
            }
            System.out.println(f);n/=10;
        }
    }
}