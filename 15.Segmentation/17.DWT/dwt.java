import java.util.Arrays;
public class dwt {
    public static void main(String[] args) {
        String text = "Hello, World!";
        int[] signal = textToNumericSignal(text);
        int[] dwtResult = discreteWaveletTransform(signal);
        System.out.println("DWT Result: " + Arrays.toString(dwtResult));
    }
    public static int[] textToNumericSignal(String text) {
        int[] signal = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            signal[i] = (int) text.charAt(i);
        }   
        return signal;
    }
    public static int[] discreteWaveletTransform(int[] signal) {
        int[] dwtResult = Arrays.copyOf(signal, signal.length);
        return dwtResult;
    }
}