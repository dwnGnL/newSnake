package objects;

import game.Coordinate;
import game.Field;
import game.Snake;

public abstract class ImpenetrableObject extends FieldObject {
    ImpenetrableObject(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void toInteractWithSnake(Snake snake, Field field) {
    	PieceOfSnake snakeHead = (PieceOfSnake)field.getObjectOnField((snake.getHeadCoordinate()));
    	snake.setDirection(snakeHead.direction);
    }
}
