package PathFinding.AStar;
import java.util.ArrayList;

public class AStar {
    private final int dimension = 50;
    private Cell[][] grid;
    private ArrayList<Cell> openSet, closeSet;
    private Cell start;
    private Cell end;


    public AStar(){
        this.grid = new Cell[50][50];
        this.openSet = new ArrayList<Cell>();
        this.closeSet = new ArrayList<Cell>();
        for (int i = 0; i < this.grid.length; i++){
            for(int j = 0; j < this.grid.length; j++){
                grid[i][j] = new Cell(i,j);
            }
        }

        this.start = this.grid[0][0];
        this.end = this.grid[this.dimension-1][this.dimension-1];
        this.openSet.add(start);
    }

    public void run(){
        while (true) {
            if (this.openSet.size() > 0) {
                // keep going
            } else {
                // no solution
            }

            //20:44
            //create new project call it lets say algorithms and call the package a* and git bash in there without com.company

        }

    }

}
