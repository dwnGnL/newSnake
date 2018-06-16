package objects;

import game.Coordinate;
import game.Field;
import game.Snake;

public abstract class PenetrableObject extends FieldObject {
    public PenetrableObject(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void toInteractWithSnake(Snake snake, Field field) {
        snake.pushFront(field);
        snake.popBack(field);
    }
}
