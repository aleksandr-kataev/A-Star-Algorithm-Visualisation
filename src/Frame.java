package PathFinding.AStar;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;



public class Frame extends JPanel {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    JFrame window;
    private int size;

    public Frame() {
        size = 25;
        window = new JFrame();




        window.setContentPane(this);




        window.setTitle("A* Pathfinding Visualization");
        window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack(); // size the window to preferred size
        window.setLocationRelativeTo(null); // center the window
        window.setVisible(true);
        window.setResizable(false);


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.setColor(Color.lightGray);
        for (int j = 0; j < this.getHeight(); j += size) {
            for (int i = 0; i < this.getWidth(); i += size) {
                g.drawRect(i, j, size, size);
            }
        }

    }


    public void Show(Cell cell){


    }

    public static void main(String[] args) {
        new Frame();
    }

}
