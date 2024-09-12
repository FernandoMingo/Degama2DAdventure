package main;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile default size of player character, npc, map tile...
    final int scale = 3;  //factor to scale the tile sizes due to it looking small on modern resolution

    final int tileSize = originalTileSize * scale; // 48x48 tile size
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; //768 pixels
    final int screenHeight = tileSize * maxScreenRow; //576 pixels

    Thread gameThread; //keeps your program running until you stop it 

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);  //allows for better renderisation
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }


}
