package graphics;

import static java.awt.image.AffineTransformOp.TYPE_BILINEAR;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import game.Direction;
import game.MapOfDirections;
import objects.FieldObject;
import objects.PieceOfSnake;

class GetImage {

    GetImage() {
        this.initAnimation();
        this.initDirectionsOfSnake();
    }

    private Map<String, String[]> animationImages = new HashMap<>();

    private Map<String, Map<Direction, Image>> images = new HashMap<>();
    private MapOfDirections mapOfDirections = new MapOfDirections();

    private Direction chooseSnakePieceTurn(PieceOfSnake pieceOfSnake) {
        if (pieceOfSnake.nameOfTheObject().equals("SnakeTail"))
            return pieceOfSnake.nextPiece.direction;
        if (pieceOfSnake.nameOfTheObject().equals("SnakeTwist"))
            return this.mapOfDirections.get(pieceOfSnake.nextPiece.direction, pieceOfSnake.direction);
        return pieceOfSnake.direction;
    }

    Image getImage(FieldObject fieldObject, int counter) {
        Direction direction = Direction.Right;
        if (fieldObject instanceof PieceOfSnake) {
            PieceOfSnake pieceOfSnake = (PieceOfSnake) fieldObject;
            direction = this.chooseSnakePieceTurn(pieceOfSnake);
        }
        String obj = fieldObject.nameOfTheObject();
        return images.get(animationNameOfTheObject(obj, counter)).get(direction);
    }

    private BufferedImage rotateImage(BufferedImage img, int angle) {
        int x = img.getWidth(null) / 2;
        int y = img.getHeight(null) / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(angle), x, y);
        AffineTransformOp op = new AffineTransformOp(tx, TYPE_BILINEAR);
        return op.filter(img, null);
    }

    private void initDirectionsOfSnake() {
        this.mapOfDirections.addDoubleDirections(Direction.Down, Direction.Right, Direction.Right);
        this.mapOfDirections.addDoubleDirections(Direction.Down, Direction.Left, Direction.Up);
        this.mapOfDirections.addDoubleDirections(Direction.Up, Direction.Right, Direction.Down);
        this.mapOfDirections.addDoubleDirections(Direction.Right, Direction.Down, Direction.Left);
    }


    private String animationNameOfTheObject(String objectName, int counter) {
        if (animationImages.containsKey(objectName))
            return animationImages.get(objectName)[counter % animationImages.get(objectName).length];
        return "Undefined0";
    }

    private void initAnimation() {
        animationImages.put("SnakeTail", new String[]{"SnakeTail0", "SnakeTail1"});
        animationImages.put("SnakeHead", new String[]{"SnakeHead0", "SnakeHead1"});
        animationImages.put("SnakeTwist", new String[]{"SnakeTwist0"});
        animationImages.put("SnakePart", new String[]{"SnakePart0"});
        animationImages.put("Wall", new String[]{"Wall0"});
        animationImages.put("RottenApple", new String[]{"RottenApple0"});
        animationImages.put("EmptySpace", new String[]{"EmptySpace0"});
        animationImages.put("Clock", new String[]{"Clock0", "Clock1"});
        animationImages.put("Cherry", new String[]{"Cherry0"});
        animationImages.put("Apple", new String[]{"Apple0", "Apple1"});
        animationImages.put("Undefined", new String[]{"Undefined0"});

        BufferedImage undefined = null;
        try {
            undefined = ImageIO.read(new File("images/Undefined0.png"));
        } catch (IOException a) {
        }

        for (String objectsNames : animationImages.keySet()) {
            for (String objectsAnimation : animationImages.get(objectsNames)) {
                BufferedImage image;
                try {
                    image = ImageIO.read(
                            new File(String.format("images/%s.png", objectsAnimation)));
                    Map<Direction, Image> map = new HashMap<>();
                    map.put(Direction.Down, rotateImage(image, 90));
                    map.put(Direction.Left, rotateImage(image, 180));
                    map.put(Direction.Up, rotateImage(image, 270));
                    map.put(Direction.Right, rotateImage(image, 0));
                    this.images.put(objectsAnimation, map);
                } catch (IOException e) {
                    Map<Direction, Image> map = new HashMap<>();
                    map.put(Direction.Down, rotateImage(undefined, 90));
                    map.put(Direction.Left, rotateImage(undefined, 180));
                    map.put(Direction.Up, rotateImage(undefined, 270));
                    map.put(Direction.Right, rotateImage(undefined, 0));
                    this.images.put(objectsAnimation, map);
                }
            }
        }
    }

}

