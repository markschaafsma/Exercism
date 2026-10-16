class SqueakyClean {

    static final char KEBAB_CHAR = '-';

    static String clean(String identifier) {
        boolean capitaliseNext = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < identifier.length(); i++) {
            char c = identifier.charAt(i);
            if (c != KEBAB_CHAR ) {
                if (capitaliseNext) {
                    c = Character.toUpperCase(c);
                }
                c = switch (c) {
                    case ' ' -> '_';
                    case '4' -> 'a';
                    case '3' -> 'e';
                    case '0' -> 'o';
                    case '1' -> 'l';
                    case '7' -> 't';
                    default -> c;
                };
                if (Character.isLetter(c) || c == '_' || Character.isISOControl(c)) {
                    sb.append(c);
                }
            }
            capitaliseNext = capitaliseNext(c);
        }
        return sb.toString().replaceAll("(?U)\\p{Cntrl}|\\p{Gc=Cf}", "CTRL");
    }

    public static boolean capitaliseNext (char character) {
        return character == KEBAB_CHAR;
    }
}
