import java.util.Scanner;

public class reverse_no {
    public static void main(String[] args) {
     System.out.println("enter the number:");
     Scanner s=new Scanner(System.in);
     int n=s.nextInt();
     int rev=0,rem;
     while(n!=0) {
        rem=n%10;
        rev=(rev*10)+rem;
        n/=10;
     }  
     System.out.println(rev);
    } 
}