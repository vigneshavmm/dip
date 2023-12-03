import java.util.Arrays;
public class w{
    public static void main(String[] args){
        String t="kitu";
        int[] signal= text2signal(t);
        int[] dwtR=dwt(signal);
        System.out.println("dwt:"+Arrays.toString(dwtR));
    }
    public static int[] text2signal(String t){
        int[] signal=new int[t.length()];
        for(int i=0;i<t.length();i++){
            signal[i]=(int)t.charAt(i);
        }
        return signal;
    }
    public static int[] dwt(int[] signal){
        int[] dwtR=Arrays.copyOf(signal,signal.length);
        return dwtR;
    }
}