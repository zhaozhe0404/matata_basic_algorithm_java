package algorithm.sort;

/**
 * 归并排序
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] arr = new int[]{7, 2, 1, 8, 10, 6, 8, 5, 12, 3, 4};
        new MergeSort().mergeSort(arr, 0, arr.length-1);
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private void mergeSort(int[] arr, int from, int to) {
        if (from == to) return;
        int mid = (from + to) / 2;
        mergeSort(arr, from, mid);
        mergeSort(arr, mid+1, to);
        this.merge(arr, from, mid, to);
    }

    private void merge(int[] arr, int from, int mid, int to) {
        int i = from;
        int j = mid+1;
        while (i<=mid && j<=to) {
            if (arr[i] <= arr[j]) {
                i++;
            }
            if (arr[i] > arr[j]) { // mid左边的元素都要向后移动一位，给较小的树arr[j]让出来，
                int temp = arr[j];
                mid++;
                int k = mid;
                while (k>i) {
                    arr[k] = arr[k-1];
                    k--;
                }
                arr[i] = temp;
                i++;
                j++;
            }
        }

    }
}
