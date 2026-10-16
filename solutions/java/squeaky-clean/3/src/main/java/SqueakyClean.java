class SqueakyClean {

    static final char KEBAB_CHAR = '-';

    enum TransitionState {
        NORMAL,
        UPPERCASE_NEXT
    }

    record ProcessingState(StringBuilder buffer, TransitionState nextAction) {

        public static ProcessingState empty() {
            return new ProcessingState(new StringBuilder(), TransitionState.NORMAL);
        }

        public ProcessingState accumulate(Character ch) {
            StringBuilder newBuffer = new StringBuilder(this.buffer);
            TransitionState nextState;

            // Trigger condition: Hyphen matches, discard it, and flag the next char
            if (ch == KEBAB_CHAR) {
                nextState = TransitionState.UPPERCASE_NEXT;
            } else {
                // Apply transformation based on state carried over from the previous step
                char characterToAppend = (this.nextAction == TransitionState.UPPERCASE_NEXT)
                        ? Character.toUpperCase(ch)
                        : ch;

                newBuffer.append(characterToAppend);
                nextState = TransitionState.NORMAL; // Reset state
            }

            return new ProcessingState(newBuffer, nextState);
        }

        public ProcessingState combine(ProcessingState other) {
            StringBuilder mergedBuffer = new StringBuilder(this.buffer).append(other.buffer);
            return new ProcessingState(mergedBuffer, other.nextAction);
        }

        @Override
        public String toString() {
            return buffer.toString();
        }
    }

    // Use Stream processing
    static String clean(String identifier) {
        return identifier.chars()
                .mapToObj(c -> (char) c)
                // Kebab case conversion logic with kebab character removal
                .reduce(
                        ProcessingState.empty(),
                        ProcessingState::accumulate,
                        ProcessingState::combine
                )
                .toString()
                .chars()
                .mapToObj(c -> (char) c)
                // Whitespace and leetSpeak replacement logic
                .map(c -> switch (c) {
                    case ' ' -> '_';
                    case '4' -> 'a';
                    case '3' -> 'e';
                    case '0' -> 'o';
                    case '1' -> 'l';
                    case '7' -> 't';
                    default -> c;
                })
                .filter(c -> Character.isLetter(c) || c == '_' || Character.isISOControl(c))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .replaceAll("(?U)\\p{Cntrl}|\\p{Gc=Cf}", "CTRL");
    }
}
