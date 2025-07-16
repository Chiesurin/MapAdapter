import java.util.List;
import myAdapter.*;

public class Prova {
    public static void main(String[] args) {
        HMap map = new MapAdapter(100);
        for (int i = 0; i < 100; i++) {
            map.put("" + i, "" + i);
        }

        System.out.println(map.hashCode());
    }
}
