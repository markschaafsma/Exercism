import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Etl {
    Map<String, Integer> transform(Map<Integer, List<String>> old) {
        Map<String, Integer> transformedMap = new HashMap<>();
        old.forEach((points, letters) -> {
            letters.forEach(letter -> {
                transformedMap.put(letter.toLowerCase(), points);
            });
        });
        return  transformedMap;
    }
}
