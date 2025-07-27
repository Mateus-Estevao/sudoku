package br.com.dio;

import br.com.dio.ui.custom.screen.MainScreen;

import java.util.Map;
import java.util.stream.Stream;

import static java.util.Map.entry;
import static java.util.stream.Collectors.toMap;

public class UIMain {

    public static void main(String[] args) {
        if (args.length == 0) {
            args = generateDefaultArgs();
        }

        final var gameConfig = Stream.of(args)
                .collect(toMap(
                        k -> k.split(";")[0],
                        v -> v.split(";")[1]
                ));

        var mainScreen = new MainScreen(gameConfig);
        mainScreen.buildMainScreen();
    }

    private static String[] generateDefaultArgs() {
        String[] result = new String[81];
        int index = 0;

        Map<String, Integer> fixedValues = Map.ofEntries(
                entry("0,0", 5), entry("0,1", 3), entry("0,4", 7),
                entry("1,0", 6), entry("1,3", 1), entry("1,4", 9), entry("1,5", 5),
                entry("2,1", 9), entry("2,2", 8), entry("2,7", 6),
                entry("3,0", 8), entry("3,4", 6), entry("3,8", 3),
                entry("4,0", 4), entry("4,3", 8), entry("4,5", 3), entry("4,8", 1),
                entry("5,0", 7), entry("5,4", 2), entry("5,8", 6),
                entry("6,1", 6), entry("6,6", 2), entry("6,7", 8),
                entry("7,3", 4), entry("7,4", 1), entry("7,5", 9), entry("7,8", 5),
                entry("8,4", 8), entry("8,7", 7), entry("8,8", 9)
        );

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String key = i + "," + j;
                if (fixedValues.containsKey(key)) {
                    result[index++] = key + ";" + fixedValues.get(key) + ",true";
                } else {
                    result[index++] = key + ";0,false";
                }
            }
        }

        return result;
    }
}
