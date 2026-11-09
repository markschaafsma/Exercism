import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        if (scores == null) return 0;
        return Collections.max(scores);
    }

    List<Integer> personalTopThree() {
        if (scores == null) return new ArrayList<Integer>();
        return scores.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();
    }
}
