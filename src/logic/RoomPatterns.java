package logic;

import java.util.HashMap;

public class RoomPatterns {
    public static HashMap<Integer,MapPattern> ROOMS = new HashMap<>();
    public RoomPatterns(){

        ROOMS.put(0, new MapPattern(new String[]{
                "123 1332",
                "3     33",
                "23   331",
                "333 2123"
        }));
        ROOMS.put(1, new MapPattern(new String[]{
                "#1 3   1",
                "#2    11",
                "#  3   1",
                "#2     1"
        }));
        ROOMS.put(2, new MapPattern(new String[]{
                "########",
                " 3   1  ",
                "   2    ",
                "13112112"
        }));
        ROOMS.put(3, new MapPattern(new String[]{
                "1     2#",
                "3  2   #",
                "13     #",
                "1  2   #"
        }));
        ROOMS.put(4, new MapPattern(new String[]{
                "13112112",
                " 12  32 ",
                "  1  2  ",
                "########"
        }));
        ROOMS.put(5, new MapPattern(new String[]{
                "########",
                "#3  1   ",
                "#   3   ",
                "#   232 "
        }));
        ROOMS.put(6, new MapPattern(new String[]{
                "##3#####",
                "       #",
                " 1     #",
                " 21222 #"
        }));
        ROOMS.put(7, new MapPattern(new String[]{
                "  232  #",
                "  1    #",
                "  1    #",
                "##3#####"
        }));
        ROOMS.put(8, new MapPattern(new String[]{
                "#  2212 ",
                "#     2 ",
                "#     3 ",
                "##3#####"
        }));
    }
}
