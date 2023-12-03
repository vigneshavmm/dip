import java.util.*;
public class huffman {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a text: ");
        String text = input.nextLine();
        int[] counts = new int[256];
        for (char c : text.toCharArray()) counts[(int)c]++;
        PriorityQueue<Tree> heap = new PriorityQueue<>();
        for (int i = 0; i < counts.length; i++) if (counts[i] > 0) heap.add(new Tree(counts[i], (char)i));
        while (heap.size() > 1) heap.add(new Tree(heap.poll(), heap.poll()));
        String[] codes = new String[256];
        assignCode(heap.peek().root, "", codes);
        System.out.printf("%-15s%-15s%-15s%-15s\n", "ASCII Code", "Character", "Frequency", "Code");
        for (int i = 0; i < codes.length; i++)
            if (counts[i] != 0)
                System.out.printf("%-15d%-15s%-15d%-15s\n", i, (char)i + "", counts[i], codes[i]);
    }
    public static void assignCode(Tree.Node root, String code, String[] codes) {
        if (root.left != null) {
            assignCode(root.left, code + "0", codes);
            assignCode(root.right, code + "1", codes);
        } else codes[(int)root.element] = root.code = code;
    }
    public static class Tree implements Comparable<Tree> {
        Node root;
        public Tree(Tree t1, Tree t2) {
            root = new Node();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }
        public Tree(int weight, char element) {
            root = new Node(weight, element);
        }
        public int compareTo(Tree t) {
            return Integer.compare(root.weight, t.root.weight);
        }
        public class Node {
            char element;
            int weight;
            Node left;
            Node right;
            String code = "";
            public Node() {}
            public Node(int weight, char element) {
                this.weight = weight;
                this.element = element;
            }
        }
    }
}