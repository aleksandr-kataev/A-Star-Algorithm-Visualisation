package PathFinding.AStar.src;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


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

        if (aStar.getOpenSet().size() > 0) {
            int lowestCostIndex = 0;
            for (int i = 0; i < aStar.getOpenSet().size(); i++){
                if (aStar.getOpenSet().get(i).getF() < aStar.getOpenSet().get(lowestCostIndex).getF()){
                    lowestCostIndex = i;
                }
            }
            Cell current = aStar.getOpenSet().get(lowestCostIndex);

            if (aStar.getOpenSet().get(lowestCostIndex) == aStar.getEnd()){
                this.pathFound(current);
                timer.stop();
                //return;
            }

            aStar.removeCellOpenSet(current);
            aStar.addCellCloseSet(current);

            for (int i = 0; i < current.getNeighbors().size(); i++){
                Cell neighbor = current.getNeighbors().get(i);
                if (!aStar.getCloseSet().contains(neighbor)){
                    int tempG = current.getG() + 1;
                    if (aStar.getOpenSet().contains(neighbor)) {
                        if(tempG < neighbor.getG()){
                            neighbor.setG(tempG);
                        }
                    } else {
                        neighbor.setG(tempG);
                        aStar.addCellOpenSet(neighbor);
                    }
                    neighbor.setH(aStar.heuristic(neighbor, aStar.getEnd()));
                    neighbor.setF(neighbor.getG() + neighbor.getH());
                    neighbor.setParent(current);
                }
            }



        } else {
            this.pathNotFound();
        }
        this.repaint();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.renderGrid(g);
        this.renderCloseCells(g);
        this.renderOpenCells(g);
        this.renderPath(g);
    }


    //break down the paintComponent
    //different methods to draw the components
    //e.g.
    //one for closed cells
    //one for green cells
    //one for path



    public void pathNotFound(){
        JOptionPane.showMessageDialog(null, "Path Not Found!");
    }

    public void pathFound(Cell current){
        Cell temp = current;
        aStar.addItemPath(temp);//
        while (temp.getParent() != null){
            aStar.addItemPath(temp.getParent());
            temp = temp.getParent();
        }
        System.out.println("Done");

        //JOptionPane.showMessageDialog(null, "Path Found");
    }

    public void renderGrid(Graphics g){
        g.setColor(Color.lightGray);
        for (int j = 0; j < this.getHeight(); j += size) {
            for (int i = 0; i < this.getWidth(); i += size) {
                g.drawRect(i, j, size, size);
            }
        }
    }

    public void renderCloseCells(Graphics g){
        for (int i = 0; i < aStar.getCloseSet().size(); i++) {
            g.setColor(Color.RED);
            Cell current = aStar.getCloseSet().get(i);
            g.fillRect(current.getX() * size, current.getY() * size,size,size);
            g.setColor(Color.lightGray);
            g.drawRect(current.getX() * size, current.getY() * size,size,size);
        }
    }

    public void renderOpenCells(Graphics g){
        g.setColor(Color.GREEN);
        for (int j = 0; j < aStar.getOpenSet().size(); j++) {
            Cell current = aStar.getOpenSet().get(j);
            g.fillRect(current.getX() * size, current.getY() * size,size,size);
        }
    }

    public void renderPath(Graphics g){

        for (int i = 0; i<aStar.getPath().size(); i++){
            g.setColor(Color.YELLOW);
            Cell pathCell = aStar.getPath().get(i);
            g.fillRect(pathCell.getX() * size, pathCell.getY() * size,size,size);
            g.setColor(Color.lightGray);
            g.drawRect(pathCell.getX() * size, pathCell.getY() * size,size,size);
        }
    }






    public static void main(String[] args) {
        new Frame();
    }

}
