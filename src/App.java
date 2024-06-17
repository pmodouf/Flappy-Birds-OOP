import javax.swing.JFrame;


public class App {
    public static void main(String[] args) throws Exception {
       
        int boardWidth = 360;
        int boardHeight = 640;
        

        JFrame frame = new JFrame("Flappy Bird Game");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamePanel = new GamePanel(boardWidth, boardHeight);
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);

        gamePanel.requestFocusInWindow(); // Ensure the game panel has focus

    }
}
