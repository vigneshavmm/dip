import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;

class HuffmanNode implements Comparable<HuffmanNode> {
    int data;
    char c;
    HuffmanNode left, right;

    public HuffmanNode(int data, char c) {
        this.data = data;
        this.c = c;
        left = null;
        right = null;
    }
    public int compareTo(HuffmanNode node) {
        return data - node.data;
    }
}
class HuffmanTree {
    public static HashMap<Character, String> huffmanCodes = new HashMap<>();

    public static void buildHuffmanTree(HashMap<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        for (char c : freqMap.keySet()) {
            queue.add(new HuffmanNode(freqMap.get(c), c));
        }
        while (queue.size() > 1) {
            HuffmanNode x = queue.poll();
            HuffmanNode y = queue.poll();
            HuffmanNode f = new HuffmanNode(x.data + y.data, '-');
            f.left = x;
            f.right = y;
            queue.add(f);
        }

        HuffmanNode root = queue.poll();
        generateHuffmanCodes(root, "");
    }

    public static void generateHuffmanCodes(HuffmanNode root, String code) {
        if (root == null)
            return;

        if (root.left == null && root.right == null)
            huffmanCodes.put(root.c, code);

        generateHuffmanCodes(root.left, code + "0");
        generateHuffmanCodes(root.right, code + "1");
    }
}
public class HuffmanImageCompression {
    public static void main(String[] args) throws IOException {
        String inputImagePath = "input_image.png";
        String outputCompressedPath = "compressed_image.bin";
        String outputDecompressedPath = "decompressed_image.png";

        BufferedImage image = ImageIO.read(new File(inputImagePath));
        byte[] imageBytes = getBytesFromImage(image);

        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (byte b : imageBytes) {
            char c = (char) (b & 0xFF);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        HuffmanTree.buildHuffmanTree(freqMap);
        String compressedData = compressImage(imageBytes);
        writeCompressedData(outputCompressedPath, compressedData);

        String decompressedData = decompressImage(compressedData);
        byte[] decompressedBytes = getBytesFromString(decompressedData);
        saveDecompressedImage(decompressedBytes, outputDecompressedPath);
    }

    public static byte[] getBytesFromImage(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }

    public static String compressImage(byte[] imageBytes) {
        StringBuilder compressedData = new StringBuilder();
        for (byte b : imageBytes) {
            char c = (char) (b & 0xFF);
            compressedData.append(HuffmanTree.huffmanCodes.get(c));
        }
        return compressedData.toString();
    }

    public static void writeCompressedData(String outputPath, String compressedData) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(outputPath))) {
            int padding = 8 - compressedData.length() % 8;
            compressedData = String.format("%" + (compressedData.length() + padding) + "s", compressedData).replace(' ', '0');
            for (int i = 0; i < compressedData.length(); i += 8) {
                String byteStr = compressedData.substring(i, i + 8);
                byte b = (byte) Integer.parseInt(byteStr, 2);
                dos.write(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String decompressImage(String compressedData) {
        StringBuilder decompressedData = new StringBuilder();
        HuffmanNode root = new HuffmanNode(0, '-');
        HuffmanNode current = root;
        for (char bit : compressedData.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }
            if (current.left == null && current.right == null) {
                decompressedData.append(current.c);
                current = root;
            }
        }
        return decompressedData.toString();
    }

    public static byte[] getBytesFromString(String data) {
        byte[] result = new byte[data.length()];
        for (int i = 0; i < data.length(); i++) {
            result[i] = (byte) data.charAt(i);
        }
        return result;
    }

    public static void saveDecompressedImage(byte[] imageBytes, String outputPath) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        BufferedImage decompressedImage = ImageIO.read(bais);
        ImageIO.write(decompressedImage, "png", new File(outputPath));
    }
}