
// Java implementation of the approach
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class MergeSort {

    private static long specialCount(Integer arr[], int l, int r, int x) {
        long count = 0;
        int mid = (l + r) / 2;
        if (l < r) {
            if (arr[mid] == x) {
                count += (mid - l + 1) + specialCount(arr, mid + 1, r, x);
            } else {
                count += specialCount(arr, l, mid, x);
            }
            // } else {
            // count = 1;
        } else {
            if (arr[mid] == x) {
                count = 1;
            }
        }
        return count;
    }

    // Function to count the number of inversions
    // during the merge process
    private static long mergeAndCount(Integer[] arr, int l, int m, int r) {

        // Left subarray
        Integer[] left = Arrays.copyOfRange(arr, l, m + 1);

        // Right subarray
        Integer[] right = Arrays.copyOfRange(arr, m + 1, r + 1);
        int i = 0, j = 0, k = l;
        long swaps = 0;
        long countSpecial = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                if (left[i] == right[j] + 1) {
                    countSpecial = specialCount(left, i, left.length - 1, left[i]);
                    swaps = swaps + (countSpecial * 2);
                }

                // swaps += specialCount(left, 0, arr.length - 1);
                // int i_new = i;
                // while ((i_new < left.length && j < right.length) && (left[i_new] == right[j]
                // + 1)) {
                // if

                // swaps += 2;
                // System.out.println("SHOWME!!!" + (right[j] + 1));
                // i_new = i_new + (left.length - i_new) / 2;
                // }

                arr[k++] = right[j++];

                swaps += ((m + 1) - (l + i) - countSpecial) * 3;
            }
        }
        while (i < left.length)
            arr[k++] = left[i++];
        while (j < right.length)
            arr[k++] = right[j++];
        return swaps;
    }

    // Merge sort function
    private static long mergeSortAndCount(Integer[] arr, int l, int r) {

        // Keeps track of the inversion count at a
        // particular node of the recursion tree
        long count = 0;

        if (l < r) {
            int m = (l + r) / 2;

            // Total inversion count = left subarray count
            // + right subarray count + merge count

            // Left subarray count
            count += mergeSortAndCount(arr, l, m);

            // Right subarray count
            count += mergeSortAndCount(arr, m + 1, r);

            // Merge count
            count += mergeAndCount(arr, l, m, r);
        }

        return count;
    }

    // Driver code
    public static void main(String[] args) throws FileNotFoundException {
        // if (args.length > 0) {
        // File file = new File(args[0]);
        // ArrayList<Integer> list = new ArrayList<Integer>();

        // Work with your 'file' object here

        Scanner scanner = new Scanner(new File("src/1000.txt"));
        List<Integer> al = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            al.add(scanner.nextInt());
        }
        Integer[] arr = new Integer[al.size()];
        arr = al.toArray(arr);
        System.out.println("Total cost: " + mergeSortAndCount(arr, 0, arr.length - 1));
        // 1000 --->751784
        // 10000 --->74921043
        // 100000 -->7474765798
        // }
    }
}

// This code is contributed by Pradip Basak
