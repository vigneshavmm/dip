import java.util.Scanner;
public class one_array {
    public static void main(String[] args) {
        int a[]={1,2,3,4,5,6,7};
        //for(int i=0;i<a.length;i++){
        //System.out.println(a[i]);}
        for(int e:a){
            System.out.println(e);
        }
        int b[];//declaring an array
        b=new int[5];//allocating memory to array
        int []c=new int[5];//combining both statement
        for(int f:b){
            System.out.println(f);
        }
        for(int i=0;i<5;i++){
            System.out.println("enter a integer");
            Scanner s=new Scanner(System.in);
            c[i]=s.nextInt();

        }
        for (int h:c){
            System.out.println(h);
        }
    }
}
