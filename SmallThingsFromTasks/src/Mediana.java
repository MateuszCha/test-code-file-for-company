import java.util.Arrays;

public class Mediana {
    public static Integer[] sort(Integer[] array) {
        if (array == null || array.length == 0)
            return null;
        int median;
        int arraySize = array.length;
        Arrays.sort(array);
        if (arraySize % 2 == 0) {
            arraySize = (int) arraySize / 2 - 1;
            median = (int) Math.round(((double) array[arraySize] + array[arraySize + 1]) / 2);
        } else {
            arraySize = (int) ((arraySize / 2));
            median = array[arraySize];
        }
        Integer[] neArr = new Integer[array.length];
        int i = 0;
        int left = arraySize;
        int right = arraySize + 1;
        while (i < neArr.length) {
            int value = 0;
            if (right > array.length || (left != -1 && (-(array[left] - median) <= (array[right]) - median))) {
                value = array[left];
                left--;
            } else {
                value = array[right];
                right++;
            }
            neArr[i] = value;
            i++;
        }
        return neArr;
    }

    public static void main(String[] args) {
     /*  Integer[] tabInt = new Integer[]{3,4,5,5,6,7,8,9,0,11,22,33,76};
        tabInt=sort(tabInt);
       /* for(int tab : tabInt)
            System.out.print(tab+" ");
       */
    }
}