import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    int[][] maze;

    public View(){
        setTitle("Assignment 1");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D g2d = (Graphics2D) graphics; // Cast to Graphics2D to access additional functionality

        int squareSize = 32; // The size of each square
        int xOffset = (getWidth() - maze[0].length * squareSize) / 2;
        int yOffset = (getHeight() - maze.length * squareSize) / 2;

        // Translate the graphics context
        g2d.translate(xOffset, yOffset);

        // Set a thicker stroke (line thickness)
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2.0f)); // You can adjust the line thickness as needed

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;

                switch (maze[row][col]) {
                    case 1:
                        color = Color.GRAY;
                        break;
                    case 2:
                        color = Color.CYAN;
                        break;
                    case 8:
                        color = Color.GREEN;
                        break;
                    case 9:
                        color = Color.RED;
                        break;
                    default:
                        color = Color.WHITE;
                }

                g2d.setColor(color);
                int x = col * squareSize;
                int y = row * squareSize;
                g2d.fillRect(x, y, squareSize, squareSize);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(x, y, squareSize, squareSize);
            }
        }

        // Restore the previous stroke
        g2d.setStroke(oldStroke);
    }

        public void setMaze(int[][] maze) {
            this.maze = maze;
        }
    }

