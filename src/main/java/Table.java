
public interface Table {
    // Set the cell at (x, y) to value.
    void set(int x, int y, int value);

    // Compute the sum of values from (0, 0) to (x, y), inclusive.
    int sum(int x, int y);
}

class FastSetTable implements Table {

    private int rowSize = 0;
    private int colSize = 0;
    private int grid[][] = new int[1][1];

    private void resizeGrid() {
        int newGrid[][] = new int[colSize][rowSize];
        for(int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, newGrid[y], 0, grid[0].length);
        }
        grid = newGrid;
    }

    public void set(int x, int y, int value) {
        boolean boundsUpdated = false;
        if (x >= rowSize) {
            rowSize = x + 1;
            boundsUpdated = true;
        }
        if (y >= colSize) {
            colSize = y + 1;
            boundsUpdated = true;
        }

        if (boundsUpdated) {
            resizeGrid();
        }
        grid[y][x] = value;
    }

    public int sum(int x, int y) {
        if (x < 0 || x > rowSize || y < 0 || y > colSize) {
            throw new IllegalArgumentException("Grid position does not exist");
        }
        int sum = 0;
        for(int yPos = 0; yPos <= y; yPos++) {
            for(int xPos = 0; xPos <= x; xPos++) {
                sum += grid[yPos][xPos];
            }
        }
        return sum;
    }

}

class FastSumTable implements Table {

    private int rowSize = 0;
    private int colSize = 0;
    private int grid[][] = new int[1][1];
    private int sumGrid[][] = new int[1][1];

    private void resizeGrid() {
        int newGrid[][] = new int[colSize][rowSize];
        for(int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, newGrid[y], 0, grid[0].length);
        }
        grid = newGrid;
    }

    private void updateSumGrid() {
        sumGrid = new int[colSize][rowSize];
        for (int y = 0; y < colSize; y++) {
            for (int x = 0; x < rowSize; x++) {
                int sum = 0;
                for(int sumY = 0; sumY <= y; sumY++) {
                    for (int sumX = 0; sumX <= x; sumX++) {
                        sum += grid[sumY][sumX];
                    }
                }
                sumGrid[y][x] = sum;
            }
        }
    }

    public void set(int x, int y, int value) {
        boolean boundsUpdated = false;
        if (x >= rowSize) {
            rowSize = x + 1;
            boundsUpdated = true;
        }
        if (y >= colSize) {
            colSize = y + 1;
            boundsUpdated = true;
        }

        if (boundsUpdated) {
            resizeGrid();
        }
        grid[y][x] = value;
        updateSumGrid();
    }

    public int sum(int x, int y) {
        if (x < 0 || x > rowSize || y < 0 || y > colSize) {
            throw new IllegalArgumentException("Grid position does not exist");
        }
        return sumGrid[y][x];
    }

}

class Tester {
    private static void runTests(Table table) {
        table.set(0,0,1);
        table.set(1,0,2);
        table.set(2,0,3);
        table.set(0,1,4);
        table.set(1,1,5);
        table.set(2,1,6);
        table.set(0,2,7);
        table.set(1,2,8);
        table.set(2,2,9);
        System.out.println(table.sum(0,0));
        System.out.println(table.sum(1,1));
        System.out.println(table.sum(2,1));
    }

    public static void main(String[] args) {
        final Table table = new FastSetTable();
        final Table table2 = new FastSumTable();
        runTests(table);
        runTests(table2);
    }

}

