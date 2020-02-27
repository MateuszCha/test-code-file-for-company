public class MinandMax {
    public static Pair<Integer, Integer> getMinimumAndMaximum(int[] array) {
        if (array == null || array.length == 0) {
            return new Pair<Integer, Integer>(null, null);
        }
        int min = array[0];
        int maks = array[0];
        for(int i = 1; i<array.length; i++)
        {
            int pom = array[i];
            if(min > pom)
                min = pom;
            if(maks < pom)
                maks = pom;
        }
        return new Pair<Integer, Integer>(min, maks);
    }
    public static class Pair<X, Y> {
        public X x;
        public Y y;

        public Pair(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws Exception {
        int[] data = new int[]{1, 2, 3, 5, -2, -8, 0, 77, 5, 5};

        Pair<Integer, Integer> result = getMinimumAndMaximum(data);

        System.out.println("The minimum is " + result.x);
        System.out.println("The maximum is " + result.y);
    }
}
