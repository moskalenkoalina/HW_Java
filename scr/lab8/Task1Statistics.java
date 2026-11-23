import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

public class Task1Statistics {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: java Task1Statistics <directory> <delta>");
        }

        Path root = Path.of(args[0]);
        double delta = parseFiniteDouble(args[1], "delta");
        if (delta < 0) {
            throw new IllegalArgumentException("delta must be non-negative.");
        }
        if (!Files.isDirectory(root)) {
            throw new IllegalArgumentException("Directory does not exist or is not a directory: " + root);
        }


        
    }

    private static boolean isMeasurementFile(Path path) {
        String name = path.getFileName().toString().toLowerCase(Locale.ROOT);
        return name.startsWith("log") && name.endsWith(".txt");
    }

    private static void readMeasurements(Path file, List<Double> destination) throws IOException {
        try (Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8)) {
            // var iterator = lines.iterator();
            // int lineNumber = 0;
            // while (iterator.hasNext()) {
            //     String line = iterator.next();
            //     lineNumber++;
            //     if (line.isBlank()) {
            //         continue;
            //     }
            //     try {
            //         destination.add(parseFiniteDouble(line.trim(), "value"));
            //     } catch (IllegalArgumentException exception) {
            //         throw new IOException(file + ":" + lineNumber + ": " + exception.getMessage(), exception);
            //     }
            // }
            lines.filter(line -> !line.isBlank())
                 .forEachOrdered(line -> destination.add(parseFiniteDouble(line.trim(), "value"))); 
        }
    }

    private static double parseFiniteDouble(String text, String name) {
        try {
            double value = Double.parseDouble(text);
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(name + " must be a finite number: " + text);
            }
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(name + " must be a number: " + text, exception);
        }
    }
}
