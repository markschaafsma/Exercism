import java.util.Arrays;

class SqueakyClean {

    static final char KEBAB_CHAR = '-';

    // Using Java SE 8 Streams
    static String clean(String identifier) {
        char[] prev = {'\0'}; // Use a char array to hold mutable state
        return identifier.chars()
                .mapToObj(c -> (char) c)
                // Kebab case conversion logic
                .map(c -> {
                    char ch = (prev[0] == KEBAB_CHAR) ? Character.toUpperCase(c) : c;
                    prev[0] = ch; // Update the state for the next item
                    return ch;
                })
                .filter(c -> c != KEBAB_CHAR)
                .map(c -> {
                    if (Character.isWhitespace(c)) c = '_';
                    if (c == '4') c = 'a';
                    if (c == '3') c = 'e';
                    if (c == '0') c = 'o';
                    if (c == '1') c = 'l';
                    if (c == '7') c = 't';
                    return c;
                 })
                 .filter(c -> Character.isLetter(c) || c == '_')
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
