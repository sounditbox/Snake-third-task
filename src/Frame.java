import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

import logic.*;
import views.FieldView;


public class Frame extends JFrame {
    private GameController gameController;
    private FieldView fieldView;
    private ISnakeBot bot;
    private boolean hasBot;
    private int WIN_WIDTH = 500;
    private int WIN_HEIGHT = 500;

    private int SELL_SIZE = 25;

    private class MyPanel extends JPanel {
        public MyPanel() {
            super();
        }

        @Override
        public void paint(Graphics g) {
            g.clearRect(0, 0, WIN_WIDTH, WIN_HEIGHT);

            super.paint(g);
            Graphics2D gr2d = (Graphics2D) g;

            if (gameController.isGameOver()) gr2d.drawString("GAME OVER", 15, 15);

            gr2d.setPaint(Color.BLACK);
            fieldView.getWalls().forEach(wall ->
                    g.fillRect(wall.getPosition().x*SELL_SIZE, wall.getPosition().y*SELL_SIZE, SELL_SIZE, SELL_SIZE));

            gr2d.setPaint(Color.GREEN);
            fieldView.getSnakeParts().forEach(snakePart ->
                    g.fillRect(snakePart.getPosition().x*SELL_SIZE, snakePart.getPosition().y*SELL_SIZE, SELL_SIZE, SELL_SIZE));

            gr2d.setPaint(Color.YELLOW);
            fieldView.getSnakeHeads().forEach(snakePart ->
                    g.fillRect(snakePart.getPosition().x*SELL_SIZE, snakePart.getPosition().y*SELL_SIZE, SELL_SIZE, SELL_SIZE));

            gr2d.setPaint(Color.RED);
            fieldView.getApples().forEach(snakePart ->
                    g.fillRect(snakePart.getPosition().x*SELL_SIZE, snakePart.getPosition().y*SELL_SIZE, SELL_SIZE, SELL_SIZE));

        }
    }

    private MyPanel panel = new MyPanel();

    public Frame(boolean withInnerBot) {
        super("Snake");
        InitFrame();

        hasBot = withInnerBot;

        if (hasBot) {
            try {
                InitExternalBot();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public Frame(ISnakeBot bot) {
        super("Snake");
        InitFrame();

        hasBot = true;
        if (hasBot) {
            this.bot = bot;
        }
    }

    private void InitFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);

        panel.setDoubleBuffered(true);
        this.add(panel);

        setVisible(true);
    }

    private void InitExternalBot() throws MalformedURLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        /*URL u = new URL("file://A:/Programs/IntelliJ IDEA Community Edition 2017.2.5/!Projects/Snake/jars/botek.jar");


        Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(ClassLoader.getSystemClassLoader(), new Object[]{u});

        Class<?> myClassFromMyBot = ClassLoader.getSystemClassLoader().loadClass("MyBot");

        myClassFromMyBot.getInterfaces();
        Constructor<?> constructor = myClassFromMyBot.getConstructor();
        Object obj = constructor.newInstance();
        //bot = new SnakeBot();
        bot = (ISnakeBot) obj;*/
        bot = new SnakeBot();
    }

    private SnakeDirection snakeDir;
    private boolean isDirectionChanged = false;

    public void execute() throws Exception {
        int playersCount = 1;
        if (hasBot)
            playersCount = 2;
        gameController = new GameController(playersCount);
        fieldView = gameController.getFieldView();
        WIN_HEIGHT = fieldView.getHeight() * SELL_SIZE+37;
        WIN_WIDTH = fieldView.getWidth() * SELL_SIZE+14;

        this.setBounds(100, 100, WIN_WIDTH, WIN_HEIGHT);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 37:
                        snakeDir = SnakeDirection.Left;
                        isDirectionChanged = true;
                        break;
                    case 38:
                        snakeDir = SnakeDirection.Up;
                        isDirectionChanged = true;
                        break;
                    case 39:
                        snakeDir = SnakeDirection.Right;
                        isDirectionChanged = true;
                        break;
                    case 40:
                        snakeDir = SnakeDirection.Down;
                        isDirectionChanged = true;
                        break;

                }
            }
        });

        if (hasBot || true) {
            if (bot == null || true) {
                // * * *
                // initialize bot from jars !!!

            }
            bot.SetSnake(gameController.snakes.get(1));
        }

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(isDirectionChanged) {
                    gameController.snakes.get(0).tryChangeSnakeDirection(snakeDir);
                    isDirectionChanged = false;
                };
                if (hasBot) {
                    bot.getNextDirection(gameController.getFieldView());
                    /*
                    try {
                        Method method = myClassFromMyBot.getMethod("getNextDirection");
                        method.invoke(obj, gameController.getFieldView());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                }
                gameController.tick();
                if(gameController.isGameOver())
                    t.cancel();
                panel.repaint();

            }

        }, 0 , 300);
    }
}
