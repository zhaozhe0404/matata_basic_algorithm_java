package algorithm.sort;

/**
 * 大顶堆排序
 */
public class HeapSort {

    private static int length = 0;

    public static void main(String[] args) {
        int[] arr = new int[]{7, 2, 1, 8, 10, 6, 8, 5};
        length = arr.length;
        new HeapSort().heapSort(arr);
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 大顶堆完成的是从小到大排序
     * @param arr
     */
    private void heapSort(int[] arr) {
        buildMaxHeap(arr);
        for (int i=length-1; i>0; i--) {
            swap(arr, 0, i);
            length--;
            heapify(arr, 0);
        }
    }

    private void buildMaxHeap(int[] arr) {
        int mid = (int) Math.floor(arr.length/2);
        for (int i=mid; i>=0; i--) {
            heapify(arr, i);
        }
    }

    private void heapify(int[] arr, int i) {
        int left = 2*i+1;
        int right = 2*(i+1);
        int largest = i;
        if (left < length && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < length && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, largest, i);
            heapify(arr, largest);
        }
    }

    private void swap(int[] arr, int ia, int ib) {
        int temp = arr[ia];
        arr[ia] = arr[ib];
        arr[ib] = temp;
    }
}
