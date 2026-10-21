import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards);
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        // Do I have cards they don't have 
        var myCount = myCollection.stream()
                .filter(card -> !theirCollection.contains(card))
                .count();
        // Do they have cards I don't have 
        var theirCount = theirCollection.stream()
                .filter(card -> !myCollection.contains(card))
                .count();
        return myCount > 0 && theirCount > 0;
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        // This is an intersection operation
        Set<String> commonCards = new HashSet<>();
        if (collections.isEmpty()) {
            return commonCards;
        }
        commonCards = collections.getFirst();
        for (int i = 1; i < collections.size(); i++) {
            commonCards = commonCards.stream()
                    .filter(collections.get(i)::contains)
                    .collect(Collectors.toSet());
        }
        return commonCards;
    }

    static Set<String> allCards(List<Set<String>> collections) {
        // This is a union operation
        Set<String> allCards = new HashSet<>();
        if (collections.isEmpty()) {
            return allCards;
        }
        allCards = collections.getFirst();
        for (int i = 1; i < collections.size(); i++) {
            allCards = Stream.concat(allCards.stream(), collections.get(i).stream())
                    .collect(Collectors.toSet());
        }
        return allCards;
    }
}
