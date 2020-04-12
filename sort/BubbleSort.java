package algorithm.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[]{7, 2, 1, 8, 10, 6, 8, 5};
        new BubbleSort().bubbleSort(arr);
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private void bubbleSort(int[] arr) {

        for (int i=0; i<arr.length-1; i++) {
            for (int j=0; j<arr.length-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    private void swap(int[] arr, int ia, int ib) {
        int temp = arr[ia];
        arr[ia] = arr[ib];
        arr[ib] = temp;
    }

}
