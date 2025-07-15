import java.util.List;
import java.util.TreeSet;
import java.util.HashSet;

public class Prova {
    public static void main(String[] args) {
        System.out.println(new HashSet<>(List.of(1, 2)).equals(new TreeSet<>(List.of(2, 1))));
        // You can add test cases or other logic here if needed.
    }
}
