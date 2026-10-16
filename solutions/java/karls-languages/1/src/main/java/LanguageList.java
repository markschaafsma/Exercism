import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LanguageList {
    private final List<String> languages = new ArrayList<>();

    public boolean isEmpty() {
        return languages.isEmpty();
    }

    public void addLanguage(String language) {
        languages.add(language);
    }

    public void removeLanguage(String language) {
        languages.remove(language);
    }

    public String firstLanguage() {
        return languages.get(0);
    }

    public int count() {
        return (int) languages.stream().distinct().count(); 
    }

    public boolean containsLanguage(String language) {
        return languages.contains(language);
    }

    public boolean isExciting() {
        Set<String> set = Set.of("java", "kotlin");
        return languages.stream()
                .map(String::toLowerCase)
                .anyMatch(set::contains);        
    }
}
