import java.util.Scanner;
public class getting_inputs {
        public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        //int a,b,c;
        float a,b,c;
        System.out.println("enter 2 numbers:");
        a=s.nextInt();
        b=s.nextInt();
        c=(a*a)+(b*b)+(2*a*b);
        System.out.println("Result : "+c);
    } 
}