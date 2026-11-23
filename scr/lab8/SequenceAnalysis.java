package lab8;


import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SequenceAnalysis {
    public record Extremum(int index, double value) {}

    public record Fragment(int startIndex, int endIndex, double[] values) {
        public int length() {
            return endIndex - startIndex + 1;
        }
    }

    public static void main(String[] args) {
        double[] sequence = args.length > 0
                ? Arrays.stream(args).mapToDouble(Double::parseDouble).toArray()
                : new double[]{1, 3, 5, 4, 2, 6, 8, 7, 9, 11, 10};

        analyze(sequence);
    }

    public static void analyze(double[] sequence) {
        validateSequence(sequence);

        int n = sequence.length;

        // 1 і 2. Локальні максимуми та мінімуми (якщо n >= 3)
        List<Extremum> localMaxima = n < 3
                ? List.of()
                : IntStream.range(1, n - 1)
                .filter(i -> sequence[i] > sequence[i - 1] && sequence[i] > sequence[i + 1])
                .mapToObj(i -> new Extremum(i, sequence[i]))
                .toList();

        List<Extremum> localMinima = n < 3
                ? List.of()
                : IntStream.range(1, n - 1)
                .filter(i -> sequence[i] < sequence[i - 1] && sequence[i] < sequence[i + 1])
                .mapToObj(i -> new Extremum(i, sequence[i]))
                .toList();

        // 3. Кількість переходів: зростання -> спадання та спадання -> зростання
        long upToDownCount = localMaxima.size();
        long downToUpCount = localMinima.size();

        // 4, 5 і 6. Найдовший неперервний строго зростаючий фрагмент
        Fragment longestIncreasing = findLongestStrictlyIncreasing(sequence);

        System.out.println("Sequence: " + Arrays.toString(sequence));
        System.out.println("Local maxima: " + formatExtrema(localMaxima));
        System.out.println("Local minima: " + formatExtrema(localMinima));
        System.out.println("Transitions (increasing -> decreasing): " + upToDownCount);
        System.out.println("Transitions (decreasing -> increasing): " + downToUpCount);
        System.out.println("Longest strictly increasing fragment: "
                + Arrays.toString(longestIncreasing.values())
                + " | startIndex=" + longestIncreasing.startIndex()
                + ", endIndex=" + longestIncreasing.endIndex()
                + ", length=" + longestIncreasing.length());
    }

    public static void validateSequence(double[] sequence) {
        if (sequence == null || sequence.length == 0) {
            throw new IllegalArgumentException("Sequence must not be empty.");
        }
        for (double value : sequence) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Sequence must contain only finite numbers.");
            }
        }
    }

    public static Fragment findLongestStrictlyIncreasing(double[] sequence) {
        validateSequence(sequence);

        int bestStart = 0;
        int bestEnd = 0;
        int bestLen = 1;

        int currentStart = 0;
        for (int i = 1; i < sequence.length; i++) {
            if (sequence[i] > sequence[i - 1]) {
                int currentLen = i - currentStart + 1;
                if (currentLen > bestLen) {
                    bestLen = currentLen;
                    bestStart = currentStart;
                    bestEnd = i;
                }
            } else {
                currentStart = i;
            }
        }

        double[] slice = Arrays.copyOfRange(sequence, bestStart, bestEnd + 1);
        return new Fragment(bestStart, bestEnd, slice);
    }

    private static String formatExtrema(List<Extremum> extrema) {
        if (extrema.isEmpty()) {
            return "none";
        }
        return extrema.stream()
                .map(e -> e.value() + " (index " + e.index() + ")")
                .toList()
                .toString();
    }
}