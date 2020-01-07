package PathFinding.AStar.src;
import java.util.ArrayList;

public class AStar {

    private final int DIMENSION = 800;
    private int size = 20;
    private Cell[][] grid;
    private ArrayList<Cell> openSet, closeSet, path;
    private Cell start;
    private Cell end;
    int arrayLength = this.DIMENSION/size;

    public ArrayList<Cell> getPath() {
        return path;
    }
    public void addItemPath(Cell cell){
        this.path.add(cell);
    }


    public void setPath(ArrayList<Cell> path) {
        this.path = path;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void addCellOpenSet(Cell cell){ this.openSet.add(cell); }
    public void addCellCloseSet(Cell cell){
        this.closeSet.add(cell);
    }
    public void removeCellOpenSet(Cell cell){
        this.openSet.remove(cell);
    }
    public void removeCellCloseSet(Cell cell){
        this.openSet.remove(cell);
    }

    public double heuristic(Cell a, Cell b){
        return Math.abs(a.getX()-b.getX()) + Math.abs(a.getY()-b.getY());
    }


    public void gridToString(){
        for (int i = 0; i < this.grid.length; i++){
            for(int j = 0; j < this.grid.length; j++){
                System.out.println(this.grid[i][j].neighborsToString());
            }
        }
    }


    public void pathToString(){
        for (int i = 0; i<this.path.size(); i++){
            System.out.println("x = " + path.get(i).getX() + ", " + "y = " + path.get(i).getY());
        }
    }

    public void openCloseSetToString(){

        for (int i = 0; i<this.closeSet.size(); i++){
            System.out.println(this.closeSet.get(i).getX());
        }
    }

    public Cell getStart() {
        return start;
    }


    public Cell getEnd() {
        return end;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Cell> getOpenSet() {
        return openSet;
    }

    public ArrayList<Cell> getCloseSet() {
        return closeSet;
    }

    public void setOpenSet(ArrayList<Cell> openSet) {
        this.openSet = openSet;
    }

    public void setCloseSet(ArrayList<Cell> closeSet) {
        this.closeSet = closeSet;
    }

    public AStar(){
        this.grid = new Cell[arrayLength][arrayLength];
        this.path = new ArrayList<Cell>();
        this.openSet = new ArrayList<Cell>();
        this.closeSet = new ArrayList<Cell>();
        for (int i = 0; i < this.grid.length; i++){
            for(int j = 0; j < this.grid.length; j++){
                grid[i][j] = new Cell(i,j);
            }
        }

        for (int i = 0; i < this.grid.length; i++){
            for(int j = 0; j < this.grid.length; j++){
                this.constructNeighbors(this.grid[i][j]);
            }
        }

        this.start = this.grid[0][0];
        this.end = this.grid[arrayLength-1][arrayLength-1];
        this.openSet.add(start);
    }

    public void constructNeighbors(Cell cell){
        int x = cell.getX();
        int y = cell.getY();
        ArrayList<Cell> newList = new ArrayList<Cell>(cell.getNeighbors());
        if (x < arrayLength - 1){
            newList.add(this.grid[x+1][y]);
        }
        if (x > 0){
            newList.add(this.grid[x-1][y]);
        }
        if (y < arrayLength - 1){
            newList.add(this.grid[x][y+1]);
        }
        if (y > 0){
            newList.add(this.grid[x][y-1]);
        }
        cell.setNeighbors(newList);
    }


}
