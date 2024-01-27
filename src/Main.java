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
//        phonebook(sc);
//        cardHands(sc);
//        populationCounter(sc);
//        wordSynonyms(sc);
//        oddOccurances(sc);
//        wordFilter(sc);
//        cities(sc);
//        largestThree(sc);
        charCounter(sc);

    }

    private static void charCounter(Scanner sc) {
        String[] tokens = sc.nextLine().split(" ");
        Map<String, Integer> map = new LinkedHashMap<>(tokens.length);

        for (String word : tokens) {
            for (String character : word.split("")) {
                if (!map.containsKey(character)) {
                    map.put(character, 0);
                }

                map.put(character, map.get(character) + 1);
            }
        }

        map.forEach((character, count) -> System.out.printf("%s -> %d\n", character, count));
    }

    public static void largestThree(Scanner sc) {
        String input =
                Arrays.stream(sc.nextLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .sorted((a, b) -> b - a)
                        .limit(3)
                        .mapToInt(Integer::intValue)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(" "));

        System.out.println(input);
    }

    public static void cities(Scanner sc) {
        int count = Integer.parseInt(sc.nextLine());
        Map<String, Map<String, Set<String>>> store = new LinkedHashMap<>();

        while (count > 0) {
            String[] line = sc.nextLine().split(" ");
            String continent = line[0];
            String country = line[1];
            String city = line[2];
            if (!store.containsKey(continent)) {
                store.put(continent, new LinkedHashMap<>());
            }

            Map<String, Set<String>> countries = store.get(continent);
            if (!countries.containsKey(country)) {
                countries.put(country, new LinkedHashSet<>());
            }

            countries.get(country).add(city);

            count--;
        }

        store.forEach((contitnent, countries) -> {
            System.out.printf("%s:\n", contitnent);
            countries.forEach((country, cities) -> System.out.printf("  %s -> %s\n", country, String.join(", ", cities)));
        });

    }

    public static void wordFilter(Scanner sc) {
//        one-liner without any mentions of maps
//        System.out.println(
//                Arrays.stream(sc.nextLine().split(" "))
//                        .filter(e -> e.length() % 2 == 0)
//                        .collect(Collectors.joining("\n"))
//        );

        String[] input = sc.nextLine().split(" ");
        Map<String, Integer> words = new LinkedHashMap<>();

        for (String word : input) {
            if (word.length() % 2 == 0) {
                words.put(word, 1);
            }
        }

        System.out.println(String.join("\n", words.keySet()));
    }

    public static void oddOccurances(Scanner sc) {
        String[] input = sc.nextLine().toLowerCase().split(" ");
        Map<String, Integer> occurances = new LinkedHashMap<>();

        for (String word : input) {
            if (!occurances.containsKey(word)) {
                occurances.put(word, 0);
            }

            occurances.put(word, occurances.get(word) + 1);
        }

        String result = occurances.entrySet().stream().filter((entry) -> entry.getValue() % 2 == 1).map(Map.Entry::getKey).collect(Collectors.joining(", "));
        System.out.println(result);
    }

    public static void wordSynonyms(Scanner sc) {
        int count = Integer.parseInt(sc.nextLine());
        Map<String, ArrayList<String>> dictionary = new HashMap<>(count);
        Map<String, String> dictionaryKeys = new HashMap<>(count);

        while (count > 0) {
            String word = sc.nextLine();
            String synonym = sc.nextLine();
            if (!dictionary.containsKey(word)) {
                dictionary.put(word, new ArrayList<>());
            }

            if (!dictionaryKeys.containsKey(synonym)) {
                dictionaryKeys.put(synonym, word);
                dictionary.get(word).add(synonym);
            }

            count--;
        }

        dictionary.forEach((key, value) -> System.out.printf("%s - %s\n", key, String.join(", ", value)));
    }

    public static void populationCounter(Scanner sc) {
        String command = sc.nextLine();
        Map<String, Map<String, Integer>> countries = new LinkedHashMap<>();

        while (!command.equals("report")) {
            String[] comandProps = command.split("\\|");
            String country = comandProps[1];
            String city = comandProps[0];
            int population = Integer.parseInt(comandProps[2]);

            if (!countries.containsKey(country)) {
                countries.put(country, new LinkedHashMap<>());
            }

            Map<String, Integer> countryItem = countries.get(country);
            if (!countryItem.containsKey(city)) {
                countryItem.put(city, 0);
            }

            countryItem.put(city, countryItem.get(city) + population);

            command = sc.nextLine();
        }

        countries.entrySet()
                .stream()
                .sorted((a, b) -> Integer.compare(
                        b.getValue().values().stream().mapToInt(Integer::intValue).sum(),
                        a.getValue().values().stream().mapToInt(Integer::intValue).sum()
                ))
                .toList()
                .forEach(element -> {
                    String countryName = element.getKey();
                    int totalPop = element.getValue().values().stream().mapToInt(Integer::intValue).sum();
                    System.out.printf("%s (total population: %d)\n", countryName, totalPop);

                    element.getValue()
                            .entrySet()
                            .stream()
                            .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                            .forEach(entry -> System.out.printf("  => %s: %d\n", entry.getKey(), entry.getValue()));
                });

    }

    public static void cardHands(Scanner sc) {
        String command = sc.nextLine();
        Map<String, Integer> cardColor = new HashMap<>(Map.ofEntries(
                Map.entry("S", 4),
                Map.entry("H", 3),
                Map.entry("D", 2),
                Map.entry("C", 1)
        ));
        Map<String, Integer> cardPower = new HashMap<>(Map.ofEntries(
                Map.entry("2", 2),
                Map.entry("3", 3),
                Map.entry("4", 4),
                Map.entry("5", 5),
                Map.entry("6", 6),
                Map.entry("7", 7),
                Map.entry("8", 8),
                Map.entry("9", 9),
                Map.entry("10", 10),
                Map.entry("J", 11),
                Map.entry("Q", 12),
                Map.entry("K", 13),
                Map.entry("A", 14)
        ));

        Map<String, Map<String, Integer>> peoplesDecks = new LinkedHashMap<>();

        while (!command.equals("JOKER")) {
            String[] input = command.split(": ");
            String personName = input[0];
            String[] personDrawDeck = input[1].split(", ");

            if (!peoplesDecks.containsKey(personName)) {
                peoplesDecks.put(personName, new HashMap<>());
            }

            Map<String, Integer> personsDeck = peoplesDecks.get(personName);

            for (String card : personDrawDeck) {
                if (!personsDeck.containsKey(card)) {
                    String[] cardProps = card.split("(?=[C,H,S,D]{1})");
                    int cardPow = cardPower.get(cardProps[0]);
                    int cardColorMult = cardColor.get(cardProps[1]);

                    personsDeck.put(card, cardPow * cardColorMult);
                }
            }

            command = sc.nextLine();
        }

        peoplesDecks.forEach((person, deck) -> {
            int deckPower = deck.values().stream().reduce(0, Integer::sum);
            System.out.printf("%s: %d\n", person, deckPower);
        });

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