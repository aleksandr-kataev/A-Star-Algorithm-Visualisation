package PathFinding.AStar.src;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



//mayve add some obstacle configuration
//optimise get open set ect
//clean up and annotate code


public class Frame extends JPanel implements ActionListener {
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    JFrame window;
    private AStar aStar;
    private int size = AStar.size;
    Timer timer = new Timer(10, this);

    public Frame() {
        window = new JFrame();
        window.setContentPane(this);
        window.setTitle("A* Pathfinding Visualization");
        window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack(); // size the window to preferred size
        window.setLocationRelativeTo(null); // center the window
        window.setVisible(true);
        window.setResizable(false);
        aStar = new AStar();
        setupSearch();
        size = aStar.getSize();
        this.revalidate();
        this.repaint();
    }


    //Get the start and the end points from and setup the algorithm using those points
    public void setupSearch(){
        String[] startEnd = inputStartEnd();
        while (startEnd[0].equals(startEnd[2]) && startEnd[1].equals(startEnd[3])){
            JOptionPane.showMessageDialog(null, "Cannot have the same start and end");
            startEnd = inputStartEnd();
        }
        aStar.setup(new Cell(Integer.parseInt(startEnd[0]),
                Integer.parseInt(startEnd[1])),
                new Cell(Integer.parseInt(startEnd[2]),
                        Integer.parseInt(startEnd[3])));
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (aStar.getMode().equals("RUNNING")){
            aStar.run();
        } else if (aStar.getMode().equals("PATH_FOUND")) {
            timer.stop();
            outputDialog(true);
        } else if ((aStar.getMode().equals("PATH__NOT_FOUND"))){
            timer.stop();
            outputDialog(false);
        }
        this.revalidate();
        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.renderGridAndObstacles(g);
        this.renderCloseCells(g);
        this.renderOpenCells(g);
        if (aStar.getMode().equals("RUNNING")){
            this.renderStartEnd(g);
        }
        if (aStar.getMode().equals("PATH_FOUND")){
            this.renderPath(g);
        }
    }


    public void outputDialog(boolean pathFound){
        String message;
        Object[] options = {"Yes", "Quit"};
        if (pathFound){
            message = "Path found, would you like to try again?";
        } else {
            message = "Path not found, would you like to try again?";
        }
        int n = JOptionPane.showOptionDialog(this,
                message,
                "Completed",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (n == 0){
            reset();
        } else {
            System.exit(1);
        }
    }



    public void reset(){
        aStar.emptyOpenSet();
        aStar.emptyCloseSet();
        aStar.emptyPath();
        aStar.setMode("INITIALISED");
        setupSearch();
    }


    public String[] inputStartEnd(){
        String[] inpDialog = {
                "X coordinates for start (1-80)",
                "Y coordinates for start (1-80)",
                "X coordinates for end (1-80)",
                "Y coordinates for end (1-80)"
        };

        String[] inpArr = new String[4];

        //validation to only accept (1-80)
        for(int i = 0; i<inpArr.length; i++) {
            do {
                inpArr[i] = JOptionPane.showInputDialog(inpDialog[i]);
                //regex (1-80)
                if (inpArr[i].matches("[1-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]|80")) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid input, try again");
                }
            } while (!inpArr[i].matches("[1-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]|80"));
        }
        return inpArr;
    }



    public void renderGridAndObstacles(Graphics g) {
        for (int i = 0; i < aStar.getGrid().length; i++) {
            for (int j = 0; j < aStar.getGrid().length; j++) {
                if (aStar.getGrid()[i][j].isObstacle()) {
                    aStar.getGrid()[i][j].render(g, Color.BLACK, size);
                }
                g.setColor(Color.lightGray);
                g.drawRect(aStar.getGrid()[i][j].getX() * size, aStar.getGrid()[i][j].getY() * size, size, size);
            }
        }
    }

    public void renderStartEnd(Graphics g){
        aStar.getStart().render(g,Color.YELLOW,size);
        aStar.getEnd().render(g,Color.YELLOW,size);
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
