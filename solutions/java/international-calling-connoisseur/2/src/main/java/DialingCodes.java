import java.util.Map;
import java.util.HashMap;

public class DialingCodes {

    private Map<Integer,String> dialingCodeMap = new HashMap<>();

    public Map<Integer, String> getCodes() {
        return dialingCodeMap;        
    }

    public void setDialingCode(Integer code, String country) {
        dialingCodeMap.put(code, country);    
    }

    public String getCountry(Integer code) {
        return dialingCodeMap.get(code);     
    }

    public void addNewDialingCode(Integer code, String country) {
        // Only add entry if both the code and the country don't exist 
        if (!dialingCodeMap.containsKey(code)
         && !dialingCodeMap.containsValue(country)) {
            dialingCodeMap.put(code,country);
        }
    }

    public Integer findDialingCode(String country) {
        var entries = dialingCodeMap.entrySet();
        for (var entry : entries) {
            if (entry.getValue().equals(country)){
                return entry.getKey();
            }
        }
        return null;
    }

    public void updateCountryDialingCode(Integer code, String country) {
        // Only update if country exists in the map
        var oldCode = findDialingCode(country); 
        if (oldCode != null) {
            dialingCodeMap.remove(oldCode);            
            dialingCodeMap.put(code, country); 
        }
    }
}
