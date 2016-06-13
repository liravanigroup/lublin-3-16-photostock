package pl.com.bottega.life;

/**
 * Created by maciuch on 11.06.16.
 */
public class GameEngine {

    private boolean[][] currentState;
    private boolean[][] newState;
    private int width, height;
    private double liveRatio;

    public GameEngine(int width, int height, double liveRatio) {
        currentState = new boolean[height][width];
        newState = new boolean[height][width];
        this.width = width;
        this.height = height;
        this.liveRatio = liveRatio;
        initCurrentState();
    }

    private void initCurrentState() {
        for (int y = 0; y < height; y++) {
            boolean[] row = currentState[y];
            for (int x = 0; x < width; x++) {
                double random = Math.random();
                row[x] = random <= liveRatio;
            }
        }
    }

    public void nextStep() {
        for (int y = 0; y < height; y++) {
            boolean[] row = currentState[y];
            boolean[] newRow = newState[y];
            for (int x = 0; x < width; x++) {
                int liveNeighbours = liveNeighbours(x, y);
                newRow[x] = newCellState(row[x], liveNeighbours);
            }
        }
        swapState();
    }

    private void swapState() {
        boolean[][] tmp = this.currentState;
        this.currentState = newState;
        this.newState = tmp;
    }

    private int liveNeighbours(int x, int y) {
        int ys = Math.max(0, y - 1);
        int yk = Math.min(y + 1, height - 1);
        int xs = Math.max(0, x - 1);
        int xk = Math.min(x + 1, width - 1);
        int counter = 0;
        for (int i = ys; i <= yk; i++) {
            for (int j = xs; j <= xk; j++) {
                if (currentState[i][j])
                    counter += 1;
            }
        }
        if (currentState[y][x])
            counter -= 1;
        return counter;
    }

    private boolean newCellState(boolean oldState, int liveNeighbours) {
        return (oldState && (liveNeighbours == 2 || liveNeighbours == 3))
                || (!oldState && liveNeighbours == 3);
    }

    public boolean isAlive(int x, int y) {
        return currentState[y][x];
    }

    public void tenCellsRow() {
        int y = height / 2;
        int x = width / 2;
        for (int i = x - 5; i < x + 5; i++)
            currentState[y][i] = true;
    }
}
