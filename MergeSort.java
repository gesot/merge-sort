// Sotiriou Giorgos
//AEM : 2414

// Java implementation of the approach
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class MergeSort {

    static int count(Integer arr[], int first, int x, int n) {
        // index of first occurrence of x in arr[0..n-1]

        // index of last occurrence of x in arr[0..n-1]
        int j;

        /* get the index of first occurrence of x */

        /* If x doesn't exist in arr[] then return -1 */
        /*
         * Else get the index of last occurrence of x. Note that we are only looking in
         * the subarray after first occurrence
         */
        j = last(arr, first, n - 1, x, n);

        /* return count */
        return j - first + 1;
    }

    static int last(Integer arr[], int low, int high, int x, int n) {
        if (high >= low) {
            /* low + (high - low)/2; */
            int mid = (low + high) / 2;
            if ((mid == n - 1 || x < arr[mid + 1]) && arr[mid] == x)
                return mid;
            else if (x < arr[mid])
                return last(arr, low, (mid - 1), x, n);
            else
                return last(arr, (mid + 1), high, x, n);
        }
        return -1;
    }

    // Function to count the number of inversions
    // during the merge process
    private static long mergeAndCount(Integer[] arr, int l, int m, int r) {
        // System.out.println("left" + l);
        // System.out.println("middle" + m);
        // System.out.println("right" + r);

        // Left subarray
        Integer[] left = Arrays.copyOfRange(arr, l, m + 1);

        // Right subarray
        Integer[] right = Arrays.copyOfRange(arr, m + 1, r + 1);
        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));
        // System.out.println(Arrays.toString(arr));
        int i = 0, j = 0, k = l;
        long swaps = 0;
        long countSpecial = 0;

        while (i < left.length && j < right.length) {
            countSpecial = 0;
            if (left[i] <= right[j])
                arr[k++] = left[i++];
            else {
                if (left[i] == right[j] + 1) {
                    countSpecial = count(left, i, left[i], left.length);
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

                swaps += ((m + 1) - (l + i)) * 3 - countSpecial;
                arr[k++] = right[j++];

                // System.out.println("m:" + m);
                // System.out.println("l:" + l);
                // System.out.println("i:" + i);
                // System.out.println((m + 1) - (l + i));
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
        if (args.length > 0) {
            // File file = new File(args[0]);
            // ArrayList<Integer> list = new ArrayList<Integer>();

            // Work with your 'file' object here

            Scanner scanner = new Scanner(new File(args[0]));
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
        }
    }
}

// This code is contributed by Pradip Basak
