import java.awt.Image;
import java.util.ArrayList;

public class Game {
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private int score;
    private boolean gameOver;
    private int boardWidth, boardHeight;
    private int pipeWidth = 64;
    private int pipeHeight = 512;
    private int velocityX = -4;
    private Image topPipeImage;
    private Image bottomPipeImage;

    public Game(int boardWidth, int boardHeight, Image birdImage, Image topPipeImage, Image bottomPipeImage) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.bird = new Bird(boardWidth / 8, boardHeight / 2, 34, 24, birdImage);
        this.pipes = new ArrayList<>();
        this.score = 0;
        this.gameOver = false;
        this.topPipeImage = topPipeImage;
        this.bottomPipeImage = bottomPipeImage;
    }

    public void update() {
        if (!gameOver) {
            bird.move();
            movePipes();
            checkCollisions();
            removeOffScreenPipes();
        }
    }

    private void movePipes() {
        for (Pipe pipe : pipes) {
            pipe.move(velocityX);
        }
    }   

    private void checkCollisions() {
        for (Pipe pipe : pipes) {
            if (!pipe.isPassed() && pipe.isTopPipe() && bird.getX() > pipe.getX() + pipe.getWidth()) {
                pipe.setPassed(true);
                score++;
               
            }
            if (pipe.checkCollision(bird)) {
                gameOver = true;
            }
        }

        if (bird.isOffScreen(boardHeight)) {
            gameOver = true;
        }
    }

 

    private void removeOffScreenPipes() {
        pipes.removeIf(pipe -> pipe.getX() + pipe.getWidth() < 0);
    }


    public void restart() {
        bird.reset(boardHeight / 2);
        pipes.clear();
        score = 0;
        gameOver = false;
    }

    // Method to add new pipes
    public void addPipes() {
        //(0-1) * pipeHeight/2.
        // 0 -> -128 (pipeHeight/4)
        // 1 -> -128 - 256 (pipeHeight/4 - pipeHeight/2) = -3/4 pipeHeight
        int randomPipeY = (int) (0 - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = boardHeight / 4;

        Pipe topPipe = new Pipe(boardWidth, randomPipeY, pipeWidth, pipeHeight, topPipeImage, true);
        Pipe bottomPipe = new Pipe(boardWidth, topPipe.getY() + pipeHeight + openingSpace, pipeWidth, pipeHeight, bottomPipeImage, false);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    public Bird getBird() {
        return bird;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
