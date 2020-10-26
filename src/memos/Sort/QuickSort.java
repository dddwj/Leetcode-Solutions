package memos.Sort;

import java.util.Arrays;

public class QuickSort {

    /*
        Good Reference:
            https://juejin.im/post/5d507199e51d4561cc25f00c
            https://youtu.be/gl_XQHTJ5hY

        注意：
            Collections.sort() 使用MergeSort / TimSort
            Arrays.sort() 使用QuickSort
     */

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        qs.quickSort(new int[] {4, 10, 14, 11, 17, 8, 13, 15});
    }

    public void quickSort(int[] a) {
        /*
        Shuffle:
            List list = Arrays.asList(a);
            Collections.shuffle(list);
            list.toArray(a);
         */
        System.out.println(Arrays.toString(a));
        sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    private void sort(int[] a, int l, int r) {
        if (l < r) {
            int index = partition(a, l, r);
            sort(a, l, index - 1);
            sort(a, index + 1, r);
        }
    }

    private int partition(int[] a, int l, int r) {
        int p = a[l];
        int i = l, j = r;
        while (i < j) {
            while (a[j] >= p && i < j) {
                j--;
            }
            while (a[i] <= p && i < j) {
                i++;
            }
            swap(a, i, j);
        }
        swap(a, l, i);
        return i;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
