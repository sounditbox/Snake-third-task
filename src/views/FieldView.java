package views;

import fieldObjects.*;
import logic.Field;
import views.fieldObjects.*;

import java.util.ArrayList;

public class FieldView {
    private Field field;

    public FieldView(Field field) {
        this.field = field;
    }

    public int getWidth() {
        return field.width;
    }

    public int getHeight() {
        return field.height;
    }

    public ArrayList<FieldObjectWrapper> getAllObjects() {
        ArrayList<FieldObjectWrapper> wrappers = new ArrayList<>();
        field.getAllObjects().forEach(fieldObject -> {
            wrappers.add(fieldObject.getWrapper());
        });
        return wrappers;
    }

    public ArrayList<WallWrapper> getWalls() {
        ArrayList<WallWrapper> walls = new ArrayList<>();
        field.getAllObjects().forEach(fieldObject -> {
            if (fieldObject instanceof Wall) walls.add((WallWrapper) fieldObject.getWrapper());
        });
        return walls;
    }

    public ArrayList<AppleWrapper> getApples() {
        ArrayList<AppleWrapper> apples = new ArrayList<>();
        field.getAllObjects().forEach(fieldObject -> {
            if (fieldObject instanceof Apple) apples.add((AppleWrapper) fieldObject.getWrapper());
        });
        return apples;
    }

    public ArrayList<SnakePartWrapper> getSnakeParts() {
        ArrayList<SnakePartWrapper> snakeParts = new ArrayList<>();
        field.getAllObjects().forEach(fieldObject -> {
            if (fieldObject instanceof SnakePart) snakeParts.add((SnakePartWrapper) fieldObject.getWrapper());
        });
        return snakeParts;
    }

    public ArrayList<SnakeHeadWrapper> getSnakeHeads() {
        ArrayList<SnakeHeadWrapper> snakeHeads = new ArrayList<>();
        field.getAllObjects().forEach(fieldObject -> {
            if (fieldObject instanceof SnakeHead) snakeHeads.add((SnakeHeadWrapper) fieldObject.getWrapper());
        });
        return snakeHeads;
    }
}