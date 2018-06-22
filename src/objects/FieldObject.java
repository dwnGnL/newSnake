package objects;

import game.Coordinate;
import game.Field;
import game.Snake;

public abstract class FieldObject {
    private Coordinate coordinate;

    public Coordinate getCoordinate() {

        return new  Coordinate(coordinate.x, coordinate.y);
    }



    FieldObject(Coordinate coordinate) {
        this.coordinate = coordinate;
    }



    public abstract void toInteractWithSnake(Snake snake, Field field);

    public String nameOfTheObject() {
        return null;
    }
}
