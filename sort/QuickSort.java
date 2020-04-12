package algorithm.sort;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{7, 2, 1, 8, 10, 6, 8, 5};
        new QuickSort().quickSort(arr, 0, arr.length-1);
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private void quickSort(int[] arr, int from, int to) {
        if (from >= to) {
            return;
        }

        int p = partition2(arr, from, to);
        quickSort(arr, from, p-1);
        quickSort(arr, p+1, to);
    }

    /**
     * index
     * @param arr
     * @param from
     * @param to
     * @return
     */
    private int partition(int[] arr, int from, int to) {
        int p = from;
        int index = from + 1;
        for (int i=index; i<=to; i++) {
            if (arr[i] < arr[p]) {
                swap(arr, i, index);
                index++; // index左边的都是小于arr[p]的
            }
        }
        swap(arr, index-1, p);
        return index-1;
    }

    private int partition2(int[] arr, int from, int to) {
        if (from >= to) {
            return from;
        }
        int key = arr[from];
        int left = from;
        int right = to;
        while (left < right) {
            while (left < right && arr[left] <= key) { // 防止left超过right，数组越界
                left++;
            }
            while (arr[right] > key) { // right不需要判断是否小于left，因为right最后的位置要到小于key的那一部分，这样就可以在最后swap(key, right)
                right--;
            }
            if (left < right) { // 如果right<left意味着这一趟比较结束了，不需要再交换了
                swap(arr, left, right);
            }
        }
        swap(arr, from, right);
        return right;
    }

    private void swap(int[] arr, int ia, int ib) {
        int temp = arr[ia];
        arr[ia] = arr[ib];
        arr[ib] = temp;
    }

}
