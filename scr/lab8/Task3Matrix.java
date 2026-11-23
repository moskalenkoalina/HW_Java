import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Task3Matrix {
    private record Cell(int row, int column, double value) {}

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: java Task3Matrix <file.bin> <k>");
        }
        Path file = Path.of(args[0]);
        double scalar = parseFiniteDouble(args[1], "k");
        double[][] matrix = readMatrix(file);




        System.out.println("Sum: " + statistics.getSum());
        System.out.println("Mean: " + statistics.getAverage());
        System.out.println("Positive values: " + positive + ", negative values: " + negative
                + ", zero values: " + zero);
        System.out.println("Row sums: " + Arrays.toString(rowSums));
        System.out.println("Element with the greatest absolute value: " + largest.value()
                + " at position (" + largest.row() + ", " + largest.column() + ")");
        System.out.println("Matrix multiplied by " + scalar + ":");
        Arrays.stream(scaled).forEach(row -> System.out.println(Arrays.toString(row)));
        System.out.println("Elements in ascending order: "
                + Arrays.toString(Arrays.stream(values).sorted().toArray()));
    }

    private static double[][] readMatrix(Path file) throws IOException {
        try (DataInputStream input = new DataInputStream(
                new BufferedInputStream(Files.newInputStream(file)))) {
            int rows;
            int columns;
            try {
                rows = input.readInt();
                columns = input.readInt();
            } catch (EOFException exception) {
                throw new IOException("Invalid matrix file: dimensions are missing: " + file, exception);
            }
            if (rows <= 0 || columns <= 0) {
                throw new IOException("The row and column counts must be positive: " + file);
            }
            long expectedSize;
            try {
                long elementCount = Math.multiplyExact((long) rows, columns);
                long dataSize = Math.multiplyExact(elementCount, Double.BYTES);
                expectedSize = Math.addExact(2L * Integer.BYTES, dataSize);
            } catch (ArithmeticException exception) {
                throw new IOException("Matrix dimensions are too large: " + file, exception);
            }
            if (Files.size(file) != expectedSize) {
                throw new IOException("File size does not match the declared matrix dimensions: " + file);
            }

            double[][] matrix = new double[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    double value = input.readDouble();
                    if (!Double.isFinite(value)) {
                        throw new IOException("Non-finite value at position ("
                                + row + ", " + column + ") in file: " + file);
                    }
                    matrix[row][column] = value;
                }
            }
            return matrix;
        } catch (EOFException exception) {
            throw new IOException("Unexpected end of matrix file: " + file, exception);
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
