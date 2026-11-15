package lab7;
import java.util.*;
public class task7_18 {
    static class Cell {
        int x, y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return x == cell.x && y == cell.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        Set<Cell> colored = new HashSet<>();

        colored.add(new Cell(0, 0));
        colored.add(new Cell(0, 1));
        colored.add(new Cell(1, 0));
        colored.add(new Cell(1, 1));

        colored.add(new Cell(5, 5));
        colored.add(new Cell(5, 6));
        colored.add(new Cell(6, 5));
        colored.add(new Cell(6, 6));

        colored.add(new Cell(10, 10));
        colored.add(new Cell(10, 11));
        colored.add(new Cell(11, 10));

        Set<String> uniqueShapes = new HashSet<>();
        Set<Cell> visited = new HashSet<>();

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (Cell start : colored) {
            if (!visited.contains(start)) {
                List<Cell> shape = new ArrayList<>();
                Queue<Cell> queue = new LinkedList<>();

                queue.add(start);
                visited.add(start);

                while (!queue.isEmpty()) {
                    Cell c = queue.poll();
                    shape.add(c);

                    for (int i = 0; i < 4; i++) {
                        Cell neighbor = new Cell(c.x + dx[i], c.y + dy[i]);
                        if (colored.contains(neighbor) && !visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.add(neighbor);
                        }
                    }
                }

                String shapeSignature = getNormalizedSignature(shape);
                uniqueShapes.add(shapeSignature);
            }
        }

        System.out.println("Кількість унікальних фігур: " + uniqueShapes.size());
    }

    private static String getNormalizedSignature(List<Cell> shape) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (Cell c : shape) {
            if (c.x < minX) minX = c.x;
            if (c.y < minY) minY = c.y;
        }

        List<String> coords = new ArrayList<>();
        for (Cell c : shape) {
            coords.add((c.x - minX) + "," + (c.y - minY));
        }

        Collections.sort(coords);
        return String.join(";", coords);
    }
}
