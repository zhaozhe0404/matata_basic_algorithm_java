package algorithm.sort;

public class CountingSort {

    public static void main(String[] args) {
        int[] arr = new int[]{7, 2, 1, 8, 10, 6, 8, 5};
        new CountingSort().countingSort(arr, 10);
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private void countingSort(int[] arr, int maxValue) {
        int[] bucket = new int[maxValue+1];

        for (int i=0; i<arr.length; i++) {
            bucket[arr[i]]++;
        }
        int index = 0;
        for (int i=0; i<maxValue+1; i++) {
            while (bucket[i] > 0) {
                arr[index++] = i;
                bucket[i]--;
            }
        }
    }

}
