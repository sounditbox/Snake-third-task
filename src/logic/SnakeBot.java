package logic;

import views.FieldView;
import views.fieldObjects.AppleWrapper;

import java.util.ArrayList;
import java.util.Random;

public class SnakeBot implements ISnakeBot{

    private Snake snake;

    public void SetSnake(Snake snake) {
        if (this.snake == null) { // some senseless
            this.snake = snake;
        }
    }

    public void getNextDirection(FieldView fieldView){
        //Change for situation: if apple's count will be more then 1
        //It's very simple snake's direction choicer, and TODO Snake need BFS
        ArrayList<AppleWrapper> apples = fieldView.getApples();
        //May be do choice more best apple
        Point applePoint = apples.get(0).getPosition(); //here will be choice
        SnakeDirection nextDirection;
        if (snake.getHeadPosition().x > applePoint.x)
            nextDirection = SnakeDirection.Left;
        else if (snake.getHeadPosition().x < applePoint.x)
            nextDirection = SnakeDirection.Right;
        else if (snake.getHeadPosition().y > applePoint.y)
            nextDirection = SnakeDirection.Up;
        else
            nextDirection = SnakeDirection.Down;
        snake.tryChangeSnakeDirection(nextDirection);
    }

    private SnakeDirection getRandomDirection(){
        Random random = new Random();
        int i = random.nextInt(SnakeDirection.values().length);
        return SnakeDirection.values()[i];
    }
}
