package PathFinding.AStar;

public class Cell {
    private int x,y,g,h,f;
    private Cell parent;

    public Cell(int i, int j){
        this.x = i;
        this.y = j;
    }

    public int getX() {
        return x;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
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

    public void setH(int h) {
        this.h = h;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public int getF() {
        return f;
    }
}
