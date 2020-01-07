package PathFinding.AStar.src;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


//optimise the getOpen set and get close set


public class Frame extends JPanel implements ActionListener {

    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    JFrame window;
    private int size;
    private AStar aStar;
    Timer timer = new Timer(5, this);

    public Frame() {

        aStar = new AStar();
        size = aStar.getSize();
        window = new JFrame();
        window.setContentPane(this);
        window.setTitle("A* Pathfinding Visualization");
        window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack(); // size the window to preferred size
        window.setLocationRelativeTo(null); // center the window
        window.setVisible(true);
        window.setResizable(false);
        timer.start();
        this.revalidate();
        this.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (aStar.getMode().equals("RUNNING")){
            aStar.lookForPath();
        } else if (aStar.getMode().equals("PATH_FOUND")) {
            timer.stop();
            System.out.println("Done");
            JOptionPane.showMessageDialog(null, "Path Found");
        } else if ((aStar.getMode().equals("PATH__NOT_FOUND"))){
            timer.stop();
            System.out.println("Not Found");
        }
        this.revalidate();
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.renderGrid(g);
        this.renderCloseCells(g);
        this.renderOpenCells(g);
        this.renderPath(g);
    }

    public void renderObstacles(Graphics g){
        for(int i = 0; i<aStar.getGrid().length; i++){
            for(int j = 0; j<aStar.getGrid().length; j++){
                if (aStar.getGrid()[i][j].isObstacle()){
                    g.setColor(Color.lightGray);
                    g.drawRect(aStar.getGrid()[i][j].getX()*size, aStar.getGrid()[i][j].getY()*size, size,size);
                    g.setColor(Color.BLACK);
                    g.fillRect(aStar.getGrid()[i][j].getX()*size, aStar.getGrid()[i][j].getY()*size, size,size);
                }
            }
        }
    }

    public void renderGrid(Graphics g){
        g.setColor(Color.lightGray);
        for (int j = 0; j < this.getHeight(); j += size) {
            for (int i = 0; i < this.getWidth(); i += size) {
                g.drawRect(i, j, size, size);
            }
        }
        this.renderObstacles(g);
    }

    public void renderCloseCells(Graphics g){
        for (int i = 0; i < aStar.getCloseSet().size(); i++) {
            Cell current = aStar.getCloseSet().get(i);
            current.render(g,Color.RED,size);
        }
    }

    public void renderOpenCells(Graphics g){
        for (int j = 0; j < aStar.getOpenSet().size(); j++) {
            Cell current = aStar.getOpenSet().get(j);
            current.render(g,Color.GREEN,size);
        }
    }

    public void renderPath(Graphics g){

        for (int i = 0; i<aStar.getPath().size(); i++){
            Cell pathCell = aStar.getPath().get(i);
            pathCell.render(g,Color.YELLOW,size);
        }
    }

    public static void main(String[] args) {
        new Frame();
    }

}
