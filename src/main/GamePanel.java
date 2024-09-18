package main;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile default size of player character, npc, map tile...
    final int scale = 3;  //factor to scale the tile sizes due to it looking small on modern resolution

    final int tileSize = originalTileSize * scale; // 48x48 tile size
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();

    Thread gameThread; //keeps your program running until you stop it 

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
        while(gameThread != null) {
            //as long as the game method is running, it updates and repaints

            long currentTime = System.nanoTime();
            System.out.println("current Time: " + currentTime);
            // 1 UPDATE: Update information such as character position
            update();
            // 2 DRAW: draw the screen with the updated information
            repaint();

        }
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public void update(){

        //controls player movements
        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }else if(keyH.downPressed == true){
            playerY += playerSpeed;
        }else if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }else if(keyH.leftPressed ==true){
            playerX -= playerSpeed;
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g); //super means parent class (JPanel)
        Graphics2D g2 = (Graphics2D)g; //changes graphics to graphics2D
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, 48, 48);
        g2.dispose(); //practice to solve memory

    }
}
