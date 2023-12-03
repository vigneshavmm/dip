import java.util.Scanner;

public class perfect {
    public static void main(String[] args) {
        System.out.println("enter the number");
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        int sum=0;
        for(int i=1;i<n;i++){
            if(n%i==0){
                sum+=i;
            }
        }
        if (sum==n){
            System.out.println(n+"perfect");
        }else{
            System.out.println(n+"is not perfect");
        }
    }
}
