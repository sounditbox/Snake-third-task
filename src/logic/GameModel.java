package logic;

import java.util.ArrayList;
import java.util.Random;

import fieldObjects.*;

public class GameModel {
    private Field field;
    private ArrayList<Snake> snakes;
    private ArrayList<FieldObject> addedNewObj;

    public GameModel(Field field) {
        this.field = field;
        snakes = new ArrayList<Snake>();
        field.getAllObjects().forEach(obj -> {
            if (obj instanceof SnakeHead)
                snakes.add(new Snake((SnakeHead)obj));
        });
        addedNewObj = new ArrayList<>();
        generateApple();
        field.getAllObjects().addAll(addedNewObj);
        addedNewObj.clear();
    }

    public Field getField() {
        return field;
    }
    
    public ArrayList<Snake> getSnakes() {
        return snakes;
    }
    
    public void tick() {
        field.getAllObjects().forEach(FieldObject::tick);
        field.getAllObjects().forEach(fieldObject -> {
            //fieldObject.tick();
            if (fieldObject instanceof SnakeHead) {
                SnakeHead head = (SnakeHead)fieldObject;
                field.getAllObjects().forEach(obj -> {
                    if (head != obj && head.getPosition().equals(obj.getPosition()))
                        obj.interactWithSnake(head, this);
                });
            }
        });
        field.getAllObjects().removeIf(obj -> !obj.isActive());
        field.getAllObjects().addAll(addedNewObj);
        addedNewObj.clear();
    }

    public void killSnake(SnakeHead snakeHead) {
        killAllSnakePartsFromHead(snakeHead);
    }

    public void generateApple() {
        Point position = field.getRandomFreePosition();
        addedNewObj.add(new Apple(position));
    }
    
    public void increaseSnake(SnakeHead snakeHead) {
        SnakePart tail = getLastSnakePart(snakeHead);
        SnakePart newPart = new SnakePart(tail.getPosition(), tail);
        tail.setNext(newPart);
        addedNewObj.add(newPart);
    }
    
    private SnakePart getLastSnakePart(SnakeHead snakeHead) {
        SnakePart current = snakeHead;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        return current;
    }
    
    private void killAllSnakePartsFromHead(SnakeHead snakeHead) {
        snakeHead.die();
        for(SnakePart current = snakeHead.getNext(); current != null; current = current.getNext()){
            current.tick();
            current.die();
        }
    }
}
