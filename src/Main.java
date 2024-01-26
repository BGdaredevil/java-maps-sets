import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("input");

//        countRealNums(sc);
        getStudentAverage(sc);

    }

    public static void getStudentAverage(Scanner sc) {
        int size = Integer.parseInt(sc.nextLine());
        Map<String, ArrayList<Double>> studentsList = new TreeMap<>();

        while (size > 0) {
            size--;
            String[] input = sc.nextLine().split(" ");
            String personName = input[0];
            double personGrade = Double.parseDouble(input[1]);
            if (!studentsList.containsKey(personName)) {
                studentsList.put(personName, new ArrayList<>(1));
            }

            studentsList.get(personName).add(personGrade);
        }

        studentsList.forEach((studentName, gradesList) -> {
            Optional<Double> avgGradeOpt = gradesList.stream().reduce(Double::sum);
            if (avgGradeOpt.isPresent()) {
                double averageGrade = avgGradeOpt.get() / gradesList.size();
                String grades = gradesList.stream().map(e -> String.format("%.2f", e)).collect(Collectors.joining(" "));

                System.out.printf("%s -> %s (avg: %.2f)\n", studentName, grades, averageGrade);
            }
        });

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