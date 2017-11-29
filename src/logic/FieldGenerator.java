package logic;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import fieldObjects.*;


public class FieldGenerator {
    private HashMap<Integer, String[]> maps;
    private int height;
    private int width;

    public int ROOM_SIZE = 4;
    public int MAP_SIZE = 64;
    public RoomPatterns patterns = new RoomPatterns();
    public HashMap<Integer,MapPattern> ROOMS = patterns.ROOMS;


    public String[] getMapByPattern(String[] rooms){

        String[] map = new String[ROOM_SIZE*MAP_SIZE];
        for(int i = 0; i < rooms.length; i++) {
            String[] roomsLine = new String[ROOM_SIZE];
            for(int j = 0; j < rooms[i].length(); j++){
                String[] room = ROOMS.get(Character.getNumericValue(rooms[i].charAt(j))).pattern;//TODO
                if(j == 0)
                    roomsLine = room;
                else
                    roomsLine = roomsLineConcat(roomsLine, room);
            }
            if(i == 0)
                map = roomsLine;
            else
                map = concatArray(map,roomsLine);
        }
        map[10] = "#                            <>                                #";
        return map;
    }

    public String[] roomsLineConcat(String[] roomsLine, String[] newCells){
        if(roomsLine.length != newCells.length )
            throw new InvalidParameterException("Комнаты должны быть одного размера!");
        String[] result = new String[roomsLine.length];
        for(int i = 0; i < newCells.length; i++){
            result[i] = roomsLine[i] + newCells[i];
        }
        return result;
    }

    public String[] concatArray(String[] a, String[] b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        String[] r = new String[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }

    public FieldGenerator(){
        //here will be working with seeds for maps, maybe levels will be from txt files
        //What do this class: upload seed.txt, parsing data, put to FieldObject[][], set fieldWidth and fieldHeight
        maps = new HashMap<Integer, String[]>();
        maps.put(1, new String[] {
                "##########",
                "#        #",
                "#        #",
                "#        #",
                "#        #",
                "#        #",
                "#   A    #",
                "##########"
        });
        maps.put(2, new String[] {
                "####################",
                "#   >              #",
                "#     ######       #",
                "#  ####            #",
                "#  #    ####       #",
                "#  # ####          #",
                "#### #       #######",
                "     #    ####      ",
                "######    #    #####",
                "#         # ####   #",
                "#      #### #      #",
                "#           #      #",
                "#      ######      #",
                "#                  #",
                "####################"
        });
        maps.put(3, new String[] {
                "########################",
                "#                      #",
                "#      #######         #",
                "#   ####               #",
                "#   #                  #",
                "#   #     ####         #",
                "#   #  ####            #",
                "#####  #        ########",
                "       #     ####       ",
                "       #     #          ",
                "########     #     #####",
                "#            #  ####   #",
                "#         ####  #      #",
                "#               #      #",
                "#               #      #",
                "#         #######      #",
                "#                   <  #",
                "########################"
        });
        maps.put(4, new String[] {
                "                        ",
                "                        ",
                "                        ",
                "                        ",
                "                        ",
                "                        ",
                "                        ",
                "                        ",
                "                        ",
                "       V         A      ",
                "                        ",
                "                        ",
                "                        ",
                "                        ",
                "                        ",
                "                        ",
                "                        "
        });
        maps.put(5, new String[] {
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "               A                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
                "                                    ",
        });
        /*
        maps.put(0, new String[] {
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                 A                                              ",
                "                                      V                         ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
                "                                                                ",
        });*/
        maps.put(0, getMapByPattern(new String[]{
                "52222226",
                "10000003",
                "10000003",
                "84444447"
        }));
    }

    private Field parseMaps(String[] map) {
        ArrayList<FieldObject> all = new ArrayList<>();
        Field field = new Field(height, width);
        Random rand = new Random();
        int relativityNumber;
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                char currentSymb = map[i].charAt(j);
                switch (currentSymb) {
                    case '#':
                        all.add(new Wall(j, i));
                        break;
                    case '<':
                        all.add(new SnakeHead(j, i, SnakeDirection.Left, field));
                        break;
                    case '>':
                        all.add(new SnakeHead(j, i, SnakeDirection.Right, field));
                        break;
                    case 'A':
                        all.add(new SnakeHead(j, i, SnakeDirection.Up, field));
                        break;
                    case 'V':
                        all.add(new SnakeHead(j, i, SnakeDirection.Down, field));
                        break;
                    case '1':
                        //50%
                        relativityNumber = rand.nextInt(2);
                        if (relativityNumber == 0)
                            all.add(new Wall(j, i));
                        break;
                    case '2':
                        //75%
                        relativityNumber = rand.nextInt(3);
                        if (relativityNumber < 3)
                            all.add(new Wall(j, i));
                        break;
                    case '3':
                        //25%
                        relativityNumber = rand.nextInt(3);
                        if (relativityNumber == 3)
                            all.add(new Wall(j, i));
                        break;

                }
            }
        field.setAllObjects(all);
        return field;
    }


    public Field generate(int seed) {
        String[] map = maps.get(seed);
        System.out.println();
        height = map.length;

        if (height > 0)
            width = map[0].length();
        else
            width = 0;
        return parseMaps(map);
    }
    
    public Field generate(String[] map){
        height = map.length;
        if (height > 0)
            width = map[0].length();
        else
            width = 0;
        return parseMaps(map);
    }
}
