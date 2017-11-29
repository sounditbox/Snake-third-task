package logic;

import views.FieldView;

import java.util.ArrayList;

public class GameController {
    private FieldView wrapper;
    public final ArrayList<Snake> snakes;
    private GameModel gameModel;
    private int SEED = 0;

    public GameController(int playersCount) {
        //playersCount -> SEED;
        FieldGenerator generator = new FieldGenerator();
        Field field = generator.generate(SEED);
        wrapper = new FieldView(field);
        gameModel = new GameModel(field);
        snakes = gameModel.getSnakes();
    }

    public void tick() {
        gameModel.tick();
    }

    public boolean isGameOver() {
        return gameModel.getSnakes().get(0).isDead();
    }

    public FieldView getFieldView() {
        return wrapper;
    }
}