package views.fieldObjects;

import fieldObjects.FieldObject;
import logic.Point;

public abstract class FieldObjectWrapper {
    protected FieldObject source;

    public FieldObjectWrapper(FieldObject obj) {
        source = obj;
    }

    public boolean isWalkable() {
        return source.isWalkable();
    }

    public boolean isActive() {
        return source.isActive();
    }

    public Point getPosition() {
        return source.getPosition();
    }
}