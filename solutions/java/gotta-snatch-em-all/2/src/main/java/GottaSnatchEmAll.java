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
        // Do I have cards they don't have?
        var myCount = myCollection.stream()
                .filter(card -> !theirCollection.contains(card))
                .count();
        // Do they have cards I don't have? 
        var theirCount = theirCollection.stream()
                .filter(card -> !myCollection.contains(card))
                .count();
        return myCount > 0 && theirCount > 0;
    }

    static Set<String> commonCards(List<Set<String>> collections) {
        // This is an intersection operation
        return collections.stream()
                //Since Set is immutable, map to mutable HashSet, so retainAll will work.
                //Note that new HashSet<>(innerCollection) copies all items from the original
                // inner collection into the brand-new Hashset instance.
                .map(HashSet::new)
                .reduce((common, collection) -> {
                    //Copy to keep "common" pure - mutating stream arguments during reduction is an antipattern.
                    //Further, it will break parallelStream() processing.
                    HashSet<String> result = new HashSet<>(common);
                    result.retainAll(collection);
                    return result;
                })
                .orElse(new HashSet<>());
    }

    static Set<String> allCards(List<Set<String>> collections) {
        // This is a union operation
        return collections.stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }
}
