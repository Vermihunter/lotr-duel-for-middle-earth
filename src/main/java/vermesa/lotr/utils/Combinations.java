package vermesa.lotr.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combinations {
    /**
     * Generates all combinations of the specified size from the given list.
     *
     * @param <T>   the element type
     * @param input the list of elements to choose from
     * @param k     the size of each combination
     * @return a list of combinations, each of which is a list of elements of size k
     */
    public static <T> List<List<T>> combinations(List<T> input, int k) {
        if (k < 0 || k > input.size()) {
            return Collections.emptyList();
        }
        List<List<T>> result = new ArrayList<>();
        combineRecursive(input, k, 0, new ArrayList<>(), result);
        return result;
    }

    private static <T> void combineRecursive(List<T> input, int k, int start,
                                             List<T> current, List<List<T>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i <= input.size() - (k - current.size()); i++) {
            current.add(input.get(i));
            combineRecursive(input, k, i + 1, current, result);
            current.removeLast();
        }
    }

    /**
     * Generates all multiset combinations of a specified size given repetition limits per element.
     * That is, repeats[i] is the max count of input.get(i) in a combination.
     * Only combinations of total size k are returned.
     *
     * @param <T>     the element type
     * @param input   the list of distinct elements
     * @param repeats array of repetition limits; repeats.length must match input.size()
     * @param k       the total size of each combination
     * @return list of all size-k combinations respecting the repetition limits
     */
    public static <T> List<List<T>> combinationsWithRepeats(List<T> input, int[] repeats, int k) {
        if (repeats == null || repeats.length != input.size() || k < 0) {
            return Collections.emptyList();
        }
        List<List<T>> result = new ArrayList<>();
        combineRepeatRecursive(input, repeats, 0, k, new ArrayList<>(), result);
        return result;
    }

    private static <T> void combineRepeatRecursive(List<T> input, int[] repeats,
                                                   int index, int k,
                                                   List<T> current,
                                                   List<List<T>> result) {
        if (current.size() > k) {
            return; // exceed desired size
        }

        if (index == input.size()) {
            if (current.size() == k) {
                result.add(new ArrayList<>(current));
            }
            return;
        }

        T element = input.get(index);
        int max = repeats[index];
        for (int count = 0; count <= max; count++) {
            if (current.size() + count > k) break;
            for (int i = 0; i < count; i++) {
                current.add(element);
            }
            combineRepeatRecursive(input, repeats, index + 1, k, current, result);
            for (int i = 0; i < count; i++) {
                current.removeLast();
            }
        }
    }

    /**
     * Generates the Cartesian product of a list of lists.
     * The returned combinations each have size equal to the outer list's size,
     * with the i-th element drawn from regionNeighboringRegions.get(i).
     *
     * @param <T>                      the element type
     * @param regionNeighboringRegions list of lists of possible values per position
     * @return list of all Cartesian-product combinations
     */
    public static <T> List<List<T>> cartesianProduct(List<List<T>> regionNeighboringRegions) {
        if (regionNeighboringRegions == null || regionNeighboringRegions.isEmpty()) {
            return Collections.emptyList();
        }
        List<List<T>> result = new ArrayList<>();
        cartesianRecursive(regionNeighboringRegions, 0, new ArrayList<>(), result);
        return result;
    }

    private static <T> void cartesianRecursive(List<List<T>> lists,
                                               int depth,
                                               List<T> current,
                                               List<List<T>> result) {
        if (depth == lists.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        List<T> choices = lists.get(depth);
        if (choices == null || choices.isEmpty()) {
            // If no choices at this position, skip generating any combination
            return;
        }
        for (T item : choices) {
            current.add(item);
            cartesianRecursive(lists, depth + 1, current, result);
            current.removeLast();
        }
    }

}
