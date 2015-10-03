import java.util.List;

/**
 * Created by wessel on 03/10/2015.
 */
public class ExampleMain {
    public static void main(String[] args) {
        int[] array = {10, 11, 12, 13, 1, 2, 3};
        List<Integer> testList = new MyLowMemoryArrayList<>();
        for(int item : array)
            testList.add(item);

        System.out.println("the first testlist is " + testList);

        List<Integer> testList2 = new MyLowMemoryArrayList<>();
        System.out.println("the second testlist is " + testList2);

        testList2.addAll(testList);
        testList2.addAll(testList);
        System.out.println("the result of adding all of the first testlist to the second testlist twice is:");
        System.out.println(testList2);

        if (testList2.containsAll(testList))
            System.out.println("the second testlist contains al elements that exist in first testlist");

        System.out.println("after conferting the second list to a stream then filtering on item > 10 the resulting array is");
        testList2.stream().filter(x -> x > 10).forEach(x -> System.out.print(x + ", "));
    }
}
