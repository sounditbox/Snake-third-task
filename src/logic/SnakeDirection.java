package logic;

public enum SnakeDirection {
    Up,
    Down,
    Left,
    Right;

    public boolean isOppositeDirection(SnakeDirection second_dir) {
        return (this == SnakeDirection.Down && second_dir == SnakeDirection.Up) ||
                (this == SnakeDirection.Up && second_dir == SnakeDirection.Down)||
                (this == SnakeDirection.Right && second_dir == SnakeDirection.Left)||
                (this == SnakeDirection.Left && second_dir == SnakeDirection.Right);
    }
}
