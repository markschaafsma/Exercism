import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

class ListOps {

    static <T> List<T> append(List<T> list1, List<T> list2) {
        // Given two lists, add all items in the second list to the end of the first list
        List<List<T>> listOfLists = new ArrayList<>(ListOps.size(list1) + ListOps.size(list2));
        listOfLists.add(list1);
        listOfLists.add(list2);
        return ListOps.concat(listOfLists);
    }

    static <T> List<T> concat(List<List<T>> listOfLists) {
        // Given a series of lists, combine all items in all lists into one flattened list
        int capacity = 0;
        for (List<T> list : listOfLists) {
            capacity += ListOps.size(list);
        }
        List<T> result = new ArrayList<>(capacity);
        listOfLists.forEach(result::addAll);
        return result;
    }

    static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        // Given a predicate and a list, return the list of all items for which predicate(item) is True
        List<T> result = new ArrayList<>();
        for (T element : list) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        return result;
    }

    static <T> int size(List<T> list) {
        // Given a list, return the total number of items within it
        int count = 0;
        for (T element : list) {
            count++;
        }
        return count;
    }

    static <T, U> List<U> map(List<T> list, Function<T, U> transform) {
        // Given a function and a list, return the list of the results of applying function(item) on all items
        List<U> result = new ArrayList<>();
        for (T element : list) {
            transform.apply(element);
            result.add(transform.apply(element));
        }
        return result;
    }

    static <T> List<T> reverse(List<T> list) {
        // Given a list, return a list with all the original items, but in reversed order
        int capacity = ListOps.size(list);
        List<T> result = new ArrayList<>(capacity);
        for (int i = 0, j = capacity - 1 ; i < ListOps.size(list); i++, j--) {
            result.add(list.get(j));
        }
        return result;
    }

    static <T, U> U foldLeft(List<T> list, U initial, BiFunction<U, T, U> f) {
        // Given a function, a list, and initial accumulator, fold (reduce) each item into the accumulator from the left)
        // That is the list element are processed left to right
        U accumulator = initial;
        for (T element : list) {
            accumulator = f.apply(accumulator, element);
        }
        return accumulator;
    }

    static <T, U> U foldRight(List<T> list, U initial, BiFunction<T, U, U> f) {
        // Given a function, a list, and an initial accumulator, fold (reduce) each item into the accumulator from the right
        // That is the list element are processed right to left
        U accumulator = initial;
        for (T element : ListOps.reverse(list)) {
            accumulator = f.apply(element, accumulator);
        }
        return accumulator;
    }

    private ListOps() {
        // No instances.
    }

}
