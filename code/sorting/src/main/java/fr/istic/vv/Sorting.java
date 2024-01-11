package fr.istic.vv;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {

        int n = array.length;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (comparator.compare(array[i - 1], array[i]) > 0) {
                    // Swap array[i-1] and array[i]
                    T temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        return array;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        quicksortHelper(array, 0, array.length - 1, comparator);
        return array;
    }

    private static <T> void quicksortHelper(T[] array, int low, int high, Comparator<T> comparator) {

        if (low < high) {
            int partitionIndex = partition(array, low, high, comparator);
            quicksortHelper(array, low, partitionIndex - 1, comparator);
            quicksortHelper(array, partitionIndex + 1, high, comparator);
        }
    }

    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {

        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                // Swap array[i] and array[j]
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap array[i+1] and array[high] (pivot)
        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {

        int n = array.length;

        if (n > 1) {
            int mid = n / 2;
            T[] left = java.util.Arrays.copyOfRange(array, 0, mid);
            T[] right = java.util.Arrays.copyOfRange(array, mid, n);

            mergesort(left, comparator);
            mergesort(right, comparator);
            merge(array, left, right, comparator);
        }
        return array;
    }

    private static <T> void merge(T[] array, T[] left, T[] right, Comparator<T> comparator) {

        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }
    }
}
