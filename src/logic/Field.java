package logic;

import fieldObjects.Apple;
import fieldObjects.FieldObject;
import fieldObjects.SnakeHead;
import fieldObjects.SnakePart;
import fieldObjects.Wall;

import java.util.ArrayList;
import java.util.Random;

public class Field {
    private ArrayList<FieldObject> allObjects;
    public final int height;
    public final int width;

    public Field(int height, int width) {
        this.height = height;
        this.width = width;
    }
    
    public ArrayList<FieldObject> getAllObjects() {
        return allObjects;
    }

    public void setAllObjects(ArrayList<FieldObject> allObjects) {
        this.allObjects = allObjects;
    }
    
    public Point getRandomFreePosition() {
        ArrayList<Point> allPoints = getAllPoints();
        ArrayList<Point> freePoints = getFreePoints(allPoints);

        Random rnd = new Random();
        int index = rnd.nextInt(freePoints.size());
        return freePoints.get(index);
    }

    public ArrayList<Point> getAllPoints() {
        ArrayList<Point> allPoints = new ArrayList<Point>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                allPoints.add(new Point(x,y));
            }
        }
        return allPoints;
    }
    
    public ArrayList<Point> getFreePoints(ArrayList<Point> allPoints) {
        ArrayList<Point> freePoints = new ArrayList<Point>();
        for (int i = 0; i < allPoints.size(); i++) {
            Point point = allPoints.get(i);
            boolean free = true;
            for(int j = 0; j < allObjects.size(); j++) {
                FieldObject obj = allObjects.get(j);
                if (obj.getPosition().equals(point)) {
                    free = false;
                    break;
                }
            }
            if (free)
                freePoints.add(point);
        }
        return freePoints;
    }

    public Point getPositionAfterMovement(SnakeDirection direction, Point from) {
        int newX = from.x;
        int newY = from.y;
        if (direction == SnakeDirection.Left)
            newX = (newX - 1 + width) % width;
        if (direction == SnakeDirection.Right)
            newX = (newX + 1) % width;
        if (direction == SnakeDirection.Up)
            newY = (newY - 1 + height) % height;
        if (direction == SnakeDirection.Down)
            newY = (newY + 1) % height;
        return new Point(newX, newY);
    }
}
