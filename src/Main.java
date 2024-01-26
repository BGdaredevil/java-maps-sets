import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("input");
        countRealNums(sc);

    }

    public static void countRealNums(Scanner sc) {
        double[] input = Arrays.stream(sc.nextLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        Map<Double, Integer> result = new LinkedHashMap<>(input.length);

        for (double element : input) {
            if (!result.containsKey(element)) {
                result.put(element, 0);
            }

            result.put(element, result.get(element) + 1);
        }

        result.forEach((key, val) -> System.out.printf("%.1f -> %d\n", key, val));
    }
}