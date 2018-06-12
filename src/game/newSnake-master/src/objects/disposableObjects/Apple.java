package objects.disposableObjects;

import game.Coordinate;
import game.Field;
import game.Snake;

public class Apple extends DisposableObject {
    public Apple(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void toInteractWithSnake(Snake snake, Field field) {
        super.toInteractWithSnake(snake, field);
    }

    @Override
    public void effectOfObject(Snake snake, Field field) {
        snake.pushBack(field);
    }

    @Override
    public int getChanceOfOccurrence() {
        return 3;
    }

    @Override
    public String nameOfTheObject() {
        return "Apple";
    }
}
