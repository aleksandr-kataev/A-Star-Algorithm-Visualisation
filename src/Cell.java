package PathFinding.AStar.src;

import java.awt.*;
import java.util.ArrayList;

public class Cell {
    private int x,y,g;
    private double h,f;
    private Cell parent;
    private ArrayList<Cell> neighbors;
    private boolean isObstacle;

    public Cell(int i, int j){
        this.x = i;
        this.y = j;
        this.neighbors = new ArrayList<Cell>();
        this.parent = null;
        this.isObstacle = Math.random() < 0.3;
    }




    public void render(Graphics g, Color color, int size){
        g.setColor(Color.lightGray);
        g.drawRect(this.x*size, this.y*size, size,size);
        if (this.isObstacle){
            g.setColor(Color.BLACK);
        } else {
            g.setColor(color);
        }
        g.fillRect(this.x*size, this.y*size, size,size);
    }


    public ArrayList<Cell> getNeighbors() {
        return neighbors;
    }

    public int getX() {
        return x;
    }



    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public void setNeighbors(ArrayList<Cell> neighbors) {
        this.neighbors = neighbors;
    }

    public void setObstacle(boolean obstacle) {
        isObstacle = obstacle;
    }


    public Cell getParent() {
        return parent;
    }

    public int getY() {
        return y;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setF(double f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public double getH() {
        return h;
    }

    public double getF() {
        return f;
    }
}
