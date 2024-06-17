import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private Game game;
    private Timer gameLoop;
    private Timer pipeSpawner;
    private Image backgroundImage;
    private Image birdImage;
    private Image topPipeImage;
    private Image bottomPipeImage;

    public GamePanel(int boardWidth, int boardHeight) {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon(getClass().getResource("/images/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("/images/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("/images/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("/images/bottompipe.png")).getImage();

        game = new Game(boardWidth, boardHeight, birdImage, topPipeImage, bottomPipeImage);

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        pipeSpawner = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                game.addPipes();
            }
        });
        pipeSpawner.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        // Background
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        // Bird
        Bird bird = game.getBird();
        g.drawImage(bird.getImage(), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight(), null);

        // Pipes
        for (Pipe pipe : game.getPipes()) {
            g.drawImage(pipe.getImage(), pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        // Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (game.isGameOver()) {
            g.drawString("Game Over: " + game.getScore(), 10, 35);
        } else {
            g.drawString(String.valueOf(game.getScore()), 10, 35);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
        repaint();
        if (game.isGameOver()) {
            pipeSpawner.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (game.isGameOver()) {
                game.restart();
                gameLoop.start();
                pipeSpawner.start();
            } else {
                game.getBird().flap();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}

