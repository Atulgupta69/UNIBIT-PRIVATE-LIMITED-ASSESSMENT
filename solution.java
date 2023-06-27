Given an array of integers and a target value, you must determine which two integers' sum
equals the target and return a 2D array. Then merge the array into a single array with sorting (
ascending ) order, in the next step double the target value and find again the combination of
digits (can be multiple digits ) that are equal to the double targeted value and returned into a 2D
array.
Sample Input : [1, 3, 2, 2, -4, -6, -2, 8];
Target Value = 4,
Output: First Combination For “4” : [ [1,3],[2,2],[-4,8],[-6,2] ];
Merge Into a single Array : [-6,-4,1,2,2,2,3,8];
Second Combination For “8” : [ [ 1,3,2,2], [8,-4,2,2],....,[n,n,n,n] ]


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationFinder {
    public static int[][] findCombinations(int[] arr, int target) {
        Map<Integer, Integer> complements = new HashMap<>();
        List<int[]> firstCombination = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (complements.containsKey(complement)) {
                int[] pair = {arr[i], complement};
                firstCombination.add(pair);
            }
            complements.put(arr[i], i);
        }

        int[] mergedArray = Arrays.stream(arr).sorted().toArray();

        target *= 2;
        List<int[]> secondCombination = new ArrayList<>();

        for (int num : mergedArray) {
            int complement = target - num;
            if (complements.containsKey(complement)) {
                int[] pair = new int[mergedArray.length + 2];
                pair[0] = num;
                pair[1] = complement;
                System.arraycopy(mergedArray, 0, pair, 2, mergedArray.length);
                secondCombination.add(pair);
            }
        }

        int[][] result = new int[3][];
        result[0] = firstCombination.toArray(new int[0][]);
        result[1] = mergedArray;
        result[2] = secondCombination.toArray(new int[0][]);
        return result;
    }

    public static void main(String[] args) {
        int[] inputArray = {1, 3, 2, 2, -4, -6, -2, 8};
        int targetValue = 4;

        int[][] output = findCombinations(inputArray, targetValue);

        System.out.println("First Combination for " + targetValue + ":");
        for (int[] pair : output[0]) {
            System.out.println(Arrays.toString(pair));
        }

        System.out.println("Merge into a single Array:");
        System.out.println(Arrays.toString(output[1]));

        System.out.println("Second Combination for " + (targetValue * 2) + ":");
        for (int[] pair : output[2]) {
            System.out.println(Arrays.toString(pair));
        }
    }
}
