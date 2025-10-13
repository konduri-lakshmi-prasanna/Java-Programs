/**
 * Program Title: Iterative Binary Search
 * Author: iceybubble
 * Date: 2025-10-13
 *
 * Description: Implements the iterative version of the binary search algorithm to find efficiently 
 * a target element in a sorted array.
 *
 * Language: Java
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */

public class BinarySearch {
    // Performs binary search on a sorted array
    public static int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) {
                return mid; // key found
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // key not found
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 7, 11, 15, 19, 23, 28};
        int key = 15;
        int result = binarySearch(arr, key);

        if (result == -1) {
            System.out.println(key + " not found in the array.");
        } else {
            System.out.println(key + " found at index: " + result);
        }
    }
}
