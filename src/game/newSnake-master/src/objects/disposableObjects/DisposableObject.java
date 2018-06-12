package objects.disposableObjects;

import game.Coordinate;
import game.Field;
import game.Snake;
import objects.PenetrableObject;

public abstract class DisposableObject extends PenetrableObject {
    public DisposableObject(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void toInteractWithSnake(Snake snake, Field field) {
        super.toInteractWithSnake(snake, field);
        this.effectOfObject(snake, field);
    }

    public abstract void effectOfObject(Snake snake, Field field);
    public abstract int getChanceOfOccurrence();
}
