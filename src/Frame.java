package PathFinding.AStar.src;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Frame extends JPanel {

    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    JFrame window;
    private int size;
    private AStar aStar;


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

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.lightGray);
        for (int j = 0; j < this.getHeight(); j += size) {
            for (int i = 0; i < this.getWidth(); i += size) {
                g.drawRect(i, j, size, size);
            }
        }
        for (int i = 0; i < aStar.getCloseSet().size(); i++) {
            g.setColor(Color.RED);
            Cell current = aStar.getCloseSet().get(i);
            g.fillRect(current.getX() * size, current.getY() * size,size,size);
            g.setColor(Color.BLACK);
            g.drawRect(current.getX() * size, current.getY() * size,size,size);
        }

        for (int j = 0; j < aStar.getOpenSet().size(); j++) {
            g.setColor(Color.GREEN);
            Cell current = aStar.getOpenSet().get(j);
            g.fillRect(current.getX() * size, current.getY() * size,size,size);
        }




        if (aStar.getOpenSet().size() > 0) {
            int lowestCostIndex = 0;
            for (int i = 0; i < aStar.getOpenSet().size(); i++){
                if (aStar.getOpenSet().get(i).getF() < aStar.getOpenSet().get(lowestCostIndex).getF()){
                    lowestCostIndex = i;
                }
            }

            Cell current = aStar.getOpenSet().get(lowestCostIndex);



            if (aStar.getOpenSet().get(lowestCostIndex) == aStar.getEnd()){
                ArrayList<Cell> path = new ArrayList<Cell>();
                Cell temp = current;
                path.add(temp);
                while (temp.getParent() != null){
                    path.add(temp.getParent());
                    temp = temp.getParent();
                }
                aStar.setPath(path);
                for (int i = 0; i<aStar.getPath().size(); i++){
                    Cell pathCell = aStar.getPath().get(i);
                    g.setColor(Color.YELLOW);
                    g.fillRect(pathCell.getX() * size, pathCell.getY() * size,size,size);
                }

                System.out.println("Done");

            }
            aStar.removeCellOpenSet(current);
            aStar.addCellCloseSet(current);


            //aStar.openCloseSetToString();


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




            revalidate();
            repaint();




        } else {
            // no solution
        }



    }



    public static void main(String[] args) {
        new Frame();
    }

}
