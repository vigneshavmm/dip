import java.util.Scanner;

public class loop {
    public static void main(String[] args) {
        System.out.println("enter the integer:");
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        for(int i=1;i<=n;i++){                //for loop
            System.out.println(i);
        } 
        int num[]={1,2,3,4,5,6,7,8};         //enhanced for loop
        for(int o:num){
            System.out.println(o);
        }
        for(int x=1;x<=9;x++){              // nested for loop
            for(int y=1;y<x;y++){
                System.out.println("&");
            }
            System.out.println("");
        }
    }
    
}
