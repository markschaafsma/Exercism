import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class HighScores {

    private List<Integer> scores;
    

    public HighScores(List<Integer> highScores) {
        this.scores = highScores;
    }

    List<Integer> scores() {
        return scores;
    }

    Integer latest() {
        return scores.getLast();
    }

    Integer personalBest() {
        if (scores == null) {
            return 0;
        }
        else {
            return scores.stream()
                    .filter(Objects::nonNull)
                    .max(Integer::compareTo)
                    .orElse(0);
        }
    }

    List<Integer> personalTopThree() {
        if (scores == null) return new ArrayList<Integer>();
        List<Integer> sortedList = scores.stream()
                .sorted(Integer::compareTo)
                .toList()
                .reversed();
        return switch (sortedList.size()) {
            case 0 -> sortedList.subList(0, 0);
            case 1 -> sortedList.subList(0, 1);
            case 2 -> sortedList.subList(0, 2);
            default -> sortedList.subList(0, 3);
        };
    }
}
