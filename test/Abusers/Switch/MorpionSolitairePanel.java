package Abusers.Switch;
// From https://rosettacode.org/wiki/Morpion_solitaire/Java
// Switching on an enum
// also a couple of long methods

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

class MorpionSolitairePanel extends JPanel {
    enum State {
        START, HUMAN, BOT, OVER
    }

    State gameState = State.START;
    Grid grid;
    String message = "Click to start a new game.";
    int playerScore, botScore;
    Font scoreFont;

    public MorpionSolitairePanel() {
        setPreferredSize(new Dimension(1000, 750));
        setBackground(Color.white);

        setFont(new Font("SansSerif", Font.BOLD, 16));
        scoreFont = new Font("SansSerif", Font.BOLD, 12);

        grid = new Grid(35, 9);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch (gameState) {
                    case START:
                        gameState = State.HUMAN;
                        message = "Your turn";
                        playerScore = botScore = 0;
                        grid.newGame();
                        break;
                    case HUMAN:
                        if (SwingUtilities.isRightMouseButton(e))
                            grid.showHints();
                        else {
                            Grid.Result res = grid.playerMove(e.getX(), e.getY());
                            if (res == Grid.Result.GOOD) {
                                playerScore++;

                                if (grid.possibleMoves().isEmpty())
                                    gameState = State.OVER;
                                else {
                                    gameState = State.BOT;
                                    message = "Computer plays...";
                                }
                            }
                        }
                        break;
                }
                repaint();
            }
        });

        start();
    }

    public final void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Random rand = new Random();
                while (true) {
                    try {
                        if (gameState == State.BOT) {
                            Thread.sleep(1500L);

                            List<Point> moves = grid.possibleMoves();
                            Point move = moves.get(rand.nextInt(moves.size()));
                            grid.computerMove(move.y, move.x);
                            botScore++;

                            if (grid.possibleMoves().isEmpty()) {
                                gameState = State.OVER;
                            } else {
                                gameState = State.HUMAN;
                                message = "Your turn";
                            }
                            repaint();
                        }
                        Thread.sleep(100L);
                    } catch (InterruptedException ignored) {
                    }
                }
            }
        }).start();
    }

    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        grid.draw(g, getWidth(), getHeight());

        if (gameState == State.OVER) {
            message = "No more moves available. ";
            if (playerScore > botScore)
                message += "You win. ";
            else if (botScore > playerScore)
                message += "Computer wins. ";
            else
                message += "It's a tie. ";
            message += "Click to start a new game.";
            gameState = State.START;
        }

        g.setColor(Color.white);
        g.fillRect(0, getHeight() - 50, getWidth(), getHeight() - 50);

        g.setColor(Color.lightGray);
        g.setStroke(new BasicStroke(1));
        g.drawLine(0, getHeight() - 50, getWidth(), getHeight() - 50);

        g.setColor(Color.darkGray);

        g.setFont(getFont());
        g.drawString(message, 20, getHeight() - 18);

        g.setFont(scoreFont);
        String s1 = "Player " + String.valueOf(playerScore);
        g.drawString(s1, getWidth() - 180, getHeight() - 20);

        String s2 = "Computer " + String.valueOf(botScore);
        g.drawString(s2, getWidth() - 100, getHeight() - 20);
    }
}

