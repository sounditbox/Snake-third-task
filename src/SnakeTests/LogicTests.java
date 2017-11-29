//package SnakeTests;
//import fieldObjects.*;
//import logic.*;
//
//import org.junit.Test;
//
//import java.util.HashMap;
//
//import static org.junit.Assert.*;
//
//public class LogicTests {
//    private Field GenerateField(String map_name) {
//        HashMap<String, String[]> maps = new HashMap<>();
//        FieldGenerator gen = new FieldGenerator();
//        maps.put("simple", new String[] {
//                "      ",
//                "      ",
//                "      ",
//                "      ",
//                "      ",
//                "      "
//        });
//        maps.put("walls_around", new String[] {
//                "######",
//                "# A  #",
//                "#    #",
//                "#    #",
//                "#    #",
//                "######"
//        });
//        return gen.generate(maps.get(map_name));
//    }
//
//    @Test
//    public void testPositionAfterMovingInCenter() {
//        SnakeDirection[] dirs = {
//                SnakeDirection.Up,
//                SnakeDirection.Down,
//                SnakeDirection.Right,
//                SnakeDirection.Left
//                };
//        Point start = new Point(3,3);
//        Point[] finish = {
//                new Point(3,2),
//                new Point(3,4),
//                new Point(4,3),
//                new Point(2,3)
//                };
//        Field field = GenerateField("simple");
//
//        for (int i = 0; i < 4; i++) {
//        	assertEquals(finish[i],
//        			field.getPositionAfterMovement(dirs[i], start));
//        }
//    }
//
//    @Test
//    public void testPositionAfterMovingInEdges() {
//        SnakeDirection[] dirs = {
//                SnakeDirection.Up,
//                SnakeDirection.Down,
//                SnakeDirection.Right,
//                SnakeDirection.Left
//                };
//        Point[] start = {
//                new Point(0,0),
//                new Point(5,5)
//                };
//        Point[][] finish = { {
//                new Point(0,5),
//                new Point(0,1),
//                new Point(1,0),
//                new Point(5,0)
//                }, {
//        		new Point(5,4),
//                new Point(5,0),
//                new Point(0,5),
//                new Point(4,5)
//                }
//        };
//        Field field = GenerateField("simple");
//
//        for (int i = 0; i < 2; i++) {
//        	for (int j = 0; j < 4; j++) {
//                assertEquals(finish[i][j],
//        		    	field.getPositionAfterMovement(dirs[j], start[i]));
//            }
//        }
//    }
//
//    @Test
//    public void testSnakeInterractWithWall(){
//        Field field = GenerateField("walls_around");
//        GameModel gameModel = new GameModel(field);
//        gameModel.tick();
//        assertTrue(gameModel.getSnakes().get(0).isDead());
//    }
//
//    @Test
//    public void testAppleGenerator(){
//        Field field = GenerateField("simple");
//        GameModel gameModel = new GameModel(field);
//
//        gameModel.generateApple();
//        field.getAllObjects().removeIf(obj -> !(obj instanceof Apple));
//        assertEquals(1, field.getAllObjects().size());
//    }
//
//    @Test
//    public void testIncreaseSnake(){
//        Field field = GenerateField("simple");
//        GameModel gameModel = new GameModel(field);
//
//        SnakeHead snakeHead = new SnakeHead(3,3, SnakeDirection.Up, field);
//        gameModel.increaseSnake(snakeHead);
//        int snake_len = 1;
//        SnakePart snakePart = snakeHead;
//        while (snakePart.getNext() != null) {
//            snake_len++;
//            snakePart = snakePart.getNext();
//        }
//        assertEquals(2, snake_len);
//    }
//
//    @Test
//    public void testSnakeInterractWithApple(){
//        Field field = GenerateField("walls_around");
//        GameModel gameModel = new GameModel(field);
//
//        SnakeHead snakeHead = new SnakeHead(3,3, SnakeDirection.Up, field);
//        Apple apple = new Apple(3,3);
//        apple.interactWithSnake(snakeHead, gameModel);
//        assertFalse(apple.isActive());
//    }
//
//    @Test
//    public void testSnakeInterractWithSelf(){
//        Field field = GenerateField("simple");
//        GameModel gameModel = new GameModel(field);
//        SnakeHead snakeHead = new SnakeHead(3,3,SnakeDirection.Up,field);
//        SnakePart snakePart = new SnakePart(3,3);
//        snakePart.interactWithSnake(snakeHead, gameModel);
//        assertTrue(snakeHead.isDead());
//    }
//}