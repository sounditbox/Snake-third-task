package views.fieldObjects;

import fieldObjects.SnakePart;
import views.fieldObjects.FieldObjectWrapper;
import views.fieldObjects.SnakeHeadWrapper;

public class SnakePartWrapper extends FieldObjectWrapper {
    public SnakePartWrapper(SnakePart snakePart) {
        super(snakePart);
    }

    public SnakeHeadWrapper getHead() {
        SnakePart part = (SnakePart) source;
        return (SnakeHeadWrapper)part.getHead().getWrapper();
    }
}
