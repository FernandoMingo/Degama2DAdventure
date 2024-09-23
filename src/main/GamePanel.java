package main;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile default size of player character, npc, map tile...
    final int scale = 3;  //factor to scale the tile sizes due to it looking small on modern resolution

    public final int tileSize = originalTileSize * scale; // 48x48 tile size
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; //keeps your program running until you stop it 
    Player player = new Player(this,keyH);

    //set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);  //allows for better renderisation
        this.addKeyListener(keyH); //recognizing key input
        this.setFocusable(true); //Sets or gets the focusable state of the component. A component must be focusable in order to gain the focus
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        //variables go outside the game loop

        double drawInterval = 1000000000 / FPS; //one second (1 billion nanoseconds) divided by FPS ---> 0.016 updates per second
        double nextDrawTime = System.nanoTime() + drawInterval; //decides when the game reupdates 

        while(gameThread != null) {
            //as long as the game method is running, it updates and repaints

            long currentTime = System.nanoTime();
            System.out.println("current Time: " + currentTime);
            // 1 UPDATE: Update information such as character position
            update();
            // 2 DRAW: draw the screen with the updated information
            repaint();

           
            try {

                double remainingTime = nextDrawTime - System.nanoTime(); //shows for how long the system has to sleep before the next update
                remainingTime /= 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
                
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public void update(){

        //controls player movements
        player.update();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D)g; //changes graphics to graphics2D
    
        player.draw(g2);
        
        g2.dispose(); //practice to solve memory

    }
}
