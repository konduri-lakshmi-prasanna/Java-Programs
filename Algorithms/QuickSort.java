package Algorithms;

/**
 * QuickSort Algorithm Implementation.
 * 
 * Quick Sort is a divide-and-conquer sorting algorithm.
 * 
 * Average Time Complexity: O(n log n)
 * Worst-Case Time Complexity: O(n^2) (occurs when pivot selection is poor, e.g., already sorted array)
 * Space Complexity: O(log n) due to recursive stack calls
 * 
 * @author Manish
 */
public class QuickSort {

    /**
     * Sorts an array using Quick Sort algorithm.
     * 
     * @param arr The array to be sorted
     * @param low Starting index
     * @param high Ending index
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the array around a pivot element.
     * 
     * @param arr The array to partition
     * @param low Starting index
     * @param high Ending index
     * @return The index of the pivot after partition
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choosing the last element as pivot
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and pivot (arr[high])
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        int[] sampleArray = { 10, 7, 8, 9, 1, 5 };

        System.out.println("Original Array:");
        for (int num : sampleArray) {
            System.out.print(num + " ");
        }

        quickSort(sampleArray, 0, sampleArray.length - 1);

        System.out.println("\n\nSorted Array:");
        for (int num : sampleArray) {
            System.out.print(num + " ");
        }
    }
}
