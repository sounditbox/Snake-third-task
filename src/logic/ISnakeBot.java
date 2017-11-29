package logic;

import views.FieldView;

public interface ISnakeBot {
    void SetSnake(Snake snake);
    void getNextDirection(FieldView fieldView);
}
