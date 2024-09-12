package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes program on close
        window.setResizable(false); //doesnt allow user to resize
        window.setTitle("Degama 2D Adventure"); //frame title

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); //adds the game panel to the window
        window.pack(); //Causes this window to be resized to fit the preferred size and layouts of its subcomponents

        window.setLocationRelativeTo(null); //centers the window
        window.setVisible(true);
    }
}
