/**
 * @Description
 * @Author 天空
 * @Date 2024/5/17 16:49
 **/
public class mergeSortTest {
    /**
     * 左右部分进行合并，先逐个排序再合并
     * @param originArray 原始数组，需要从其中取值作比较、排序，最后将排好序的元素重新赋值给原始数组对应的位置
     * @param tempArray 临时数组，用于存储排序后的结果
     * @param left 左半区第一个元素的位置
     * @param mid 左右区中间位置，其实就是左半区最后一个元素的位置
     * @param right 右半区最后一个元素的位置
     */
    public static void merge(int[] originArray, int[] tempArray, int left, int mid, int right) {
        // 标记左半区第一个未排序的元素
        int l_pos = left;
        // 标记右半区第一个未排序的元素
        int r_pos = mid + 1;

        // 临时数组元素的下标
        int temp_pos = left;
        // 注意此时tempArray已拥有的左右部分元素在前面的递归中是已排好序的
        while (l_pos <= mid && r_pos <= right) {
            // 左右部分一个一个来作比较，哪边小就把那边对应的值塞进去，然后移动这部分的下标，去比较后面的
            // 直至其中一部分的元素完全被比较完，跳出循环
            if (originArray[l_pos] < originArray[r_pos]) {
                // 当左半区第一个剩余元素更小
                tempArray[temp_pos++] = originArray[l_pos++];
            } else {
                // 当右半区第一个剩余元素更小
                tempArray[temp_pos++] = originArray[r_pos++];
            }
        }

        // 左边和右边至少有一个应是所有元素都比较完的
        // 剩余的那一边一定是比另一部分已排好序的元素值都大
        if (l_pos <= mid) {
            // 合并左半区剩余部分
            while (l_pos <= mid) {
                tempArray[temp_pos++] = originArray[l_pos++];
            }
        } else if (r_pos <= right) {
            // 合并右半区剩余部分
            while (r_pos <= right) {
                tempArray[temp_pos++] = originArray[r_pos++];
            }
        }

        // 把临时数组中合并后的元素复制回原来的数组
        while(left<=right){
            originArray[left] = tempArray[left];
            left++;
        }
    }

    /**
     * 归并排序实现
     * @param originArray 原始数组，需要从其中取值作比较、排序，最后将排好序的元素重新赋值给原始数组对应的位置
     * @param tempArray 临时数组，用于存储排序后的结果
     * @param left 左半区第一个元素的位置
     * @param right 右半区最后一个元素的位置
     */
    public static void merge_sort(int[] originArray, int[] tempArray, int left, int right) {
        // 如果元素只有一个，则无需再做任何操作
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        // 递归划分左半区
        merge_sort(originArray, tempArray, left, mid);
        // 递归划分右半区
        merge_sort(originArray, tempArray, mid + 1, right);
        // 合并已经排序的部分
        merge(originArray, tempArray, left, mid, right);
    }

    public static void main(String[] args) {
        int[] originArray = {9, 3, 5, 7, 2, 10, 4, 1, 6, 8};

        int sum = originArray.length;
        int[] tempArray = new int[sum];
        int left = 0;
        int right = sum - 1;
        merge_sort(originArray, tempArray, left, right);
        for (int i = 0; i < originArray.length; i++) {
            System.out.print(originArray[i] + " ");
        }
    }
}
