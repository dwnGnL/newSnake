package objects;

import game.Coordinate;
import game.Field;
import game.Snake;

public abstract class ObjectOnField {
    public Coordinate coordinate;

     ObjectOnField(Coordinate coordinate) {
        this.coordinate = coordinate;
    }



    public abstract void toInteractWithSnake(Snake snake, Field field);

    public String nameOfTheObject() {
        return null;
    }
}
