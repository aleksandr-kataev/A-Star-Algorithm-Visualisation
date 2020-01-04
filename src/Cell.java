package PathFinding.AStar.src;

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
    }


    public String neighborsToString(){
        String string = "";
        for (int i = 0; i < this.neighbors.size(); i++){
            string += this.neighbors.get(i).getX() + ", " + this.neighbors.get(i).getY() + "\n";
        }
        return string;
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
