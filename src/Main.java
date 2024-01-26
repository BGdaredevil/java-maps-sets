import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("input");

//        countRealNums(sc);
//        getStudentAverage(sc);
//        countSymbols(sc);
        phonebook(sc);

    }

    public static void phonebook(Scanner sc) {
        String command = sc.nextLine();
        boolean isSearchMode = false;
        Map<String, String> phones = new HashMap<>();
        System.out.println();
        while (!command.equals("stop")) {
            if (command.equals("search")) {
                isSearchMode = true;
                command = sc.nextLine();
            }

            String[] comandParams = command.split("-");
            if (isSearchMode) {
                String item = phones.get(comandParams[0]);

                if (item != null) {
                    System.out.printf("%s -> %s\n", comandParams[0], item);
                    command = sc.nextLine();
                    continue;
                }

                System.out.printf("Contact %s not found.\n", comandParams[0]);
                command = sc.nextLine();
                continue;
            }

            phones.put(comandParams[0], comandParams[1]);
            command = sc.nextLine();
        }
    }

    public static void countSymbols(Scanner sc) {
        String[] input = sc.nextLine().split("");

        Map<String, Integer> results = new TreeMap<>();

        for (String ch : input) {
            if (!results.containsKey(ch)) {
                results.put(ch, 0);
            }

            results.put(ch, results.get(ch) + 1);
        }

        for (Map.Entry<String, Integer> item : results.entrySet()) {
            System.out.printf("%s: %d\n", item.getKey(), item.getValue());
        }

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