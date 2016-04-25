package pl.com.bottega.life;

import java.io.IOException;

/**
 * Created by Slawek on 24/04/16.
 */
public class GameApp {

    public static void main(String[] args) throws InterruptedException, IOException {
        GameEngine engine = new GameEngine(45, 150, 0.25);

        while(true){
            print(engine);
            Thread.sleep(30);
            clearScreen();
            engine.nextStep();
        }
    }

    private static void clearScreen() throws IOException, InterruptedException {
        System.out.print("\033[2J");
        //System.out.print("\033\143");
    }

    private static void print(GameEngine engine) {
        System.out.println("step: " + engine.getStepNumber()  + "  live: " + engine.getLifeRatio() * 100 + "%");

        for(boolean[] row : engine.getCurrentState()){
            for (boolean cell : row){
                System.out.print(cell ? "X" : " ");
            }
            System.out.println();
        }
    }
}
