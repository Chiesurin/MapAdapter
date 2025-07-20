import myAdapter.*;
import myTest.DummyTestMap;
import java.util.HashMap;

public class Prova {
    

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();

        hashMap.put("key1", "value1");

        hashMap.entrySet().remove("key1");
    }
}
