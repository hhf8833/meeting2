package test1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Test3 {
    public static void main(String[] args) {

        Map<String, Object> sampleMap = new LinkedHashMap<>();
        sampleMap.put("asd", "value1");
        sampleMap.put("dgfg", "value2");
        sampleMap.put("b", "value3");
        sampleMap.put("a", "value4");
        System.out.println(sampleMap);
        String getKeyFromValue = getSingleKeyFromValue(sampleMap, "value2");
        System.out.println(getKeyFromValue);
    }

    public static <K, V> K getSingleKeyFromValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        for (K key : map.keySet()) {

        }
        return null;
    }
}
