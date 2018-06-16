package objects;

import game.Coordinate;
import game.Field;
import game.Snake;

public abstract class FieldObject {
    public Coordinate coordinate;

    FieldObject(Coordinate coordinate) {
        this.coordinate = coordinate;
    }


    public abstract void toInteractWithSnake(Snake snake, Field field);

    public String nameOfTheObject() {
        return null;
    }
}
