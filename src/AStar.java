package PathFinding.AStar.src;
import java.util.ArrayList;

public class AStar {

    private final int DIMENSION = 800;
    private int size = 10;
    private Cell[][] grid;
    private ArrayList<Cell> openSet, closeSet, path;
    private Cell start;
    private Cell end;
    int arrayLength = this.DIMENSION/size;
    String mode;

    public AStar(){
        this.grid = new Cell[arrayLength][arrayLength];
        this.mode = "RUNNING";
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
        this.start.setObstacle(false);
        this.end.setObstacle(false);

        this.openSet.add(start);
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

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

    public String getMode() {
        return mode;
    }

    public double heuristic(Cell a, Cell b){

        return Math.hypot((Math.abs(b.getY() - a.getY())), (Math.abs(b.getX() - a.getX())));
        //return Math.abs(a.getX()-b.getX()) + Math.abs(a.getY()-b.getY());
    }


    public void constructPath(Cell current){
        Cell temp = current;
        this.addItemPath(temp);//
        while (temp.getParent() != null){
            this.addItemPath(temp.getParent());
            temp = temp.getParent();
        }
        //JOptionPane.showMessageDialog(null, "Path Found");
    }

    public void lookForPath(){
        if (this.getOpenSet().size() > 0) {
            int lowestCostIndex = 0;
            for (int i = 0; i < this.getOpenSet().size(); i++){
                if (this.getOpenSet().get(i).getF() < this.getOpenSet().get(lowestCostIndex).getF()){
                    lowestCostIndex = i;
                }
            }
            Cell current = this.getOpenSet().get(lowestCostIndex);

            if (this.getOpenSet().get(lowestCostIndex) == this.getEnd()){
                this.setMode("PATH_FOUND");
                this.constructPath(current);
            }

            this.removeCellOpenSet(current);
            this.addCellCloseSet(current);

            for (int i = 0; i < current.getNeighbors().size(); i++){
                Cell neighbor = current.getNeighbors().get(i);
                if (!this.getCloseSet().contains(neighbor) && !neighbor.isObstacle()){
                    int tempG = current.getG() + 1;

                    boolean newPath = false;
                    if (this.getOpenSet().contains(neighbor)) {
                        if(tempG < neighbor.getG()){
                            neighbor.setG(tempG);
                            newPath = true;
                        }
                    } else {
                        neighbor.setG(tempG);
                        newPath = true;
                        this.addCellOpenSet(neighbor);
                    }
                    if (newPath){
                        neighbor.setH(this.heuristic(neighbor, this.getEnd()));
                        neighbor.setF(neighbor.getG() + neighbor.getH());
                        neighbor.setParent(current);
                    }

                }
            }
        } else {
            this.pathNotFound();

        }
    }


    public void pathNotFound(){
        this.mode = "PATH__NOT_FOUND";
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
        if (x > 0 && y > 0){
            newList.add(this.grid[x-1][y-1]);
        }
        if (x < arrayLength - 1 && y > 0){
            newList.add(this.grid[x+1][y-1]);
        }
        if (x > 0 && y < arrayLength - 1){
            newList.add(this.grid[x-1][y+1]);
        }
        if (x < arrayLength - 1 && y < arrayLength -1){
            newList.add(this.grid[x+1][y+1]);
        }
        cell.setNeighbors(newList);
    }

}
