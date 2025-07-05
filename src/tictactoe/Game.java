package tictactoe;

import tictactoe.models.Board;
import tictactoe.models.PieceO;
import tictactoe.models.PieceX;
import tictactoe.models.Player;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {

    private final Scanner scanner;
    private Deque<Player> players;
    private Board board;

    public Game() {
        scanner = new Scanner(System.in);
        initializeGame();
    }

    private void initializeGame() {
        System.out.println("Welcome to Tic Tac Toe Game");
        int size = 0;
        while (size < 3 || size > 10) {
            System.out.print("Please select board size (between 3 and 10): ");
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if (size < 3 || size > 10) {
                    System.out.println("Invalid size. Try again.");
                }
            } else {
                System.out.println("Please enter a valid number: ");
                scanner.next();
            }
        }

        board = new Board(size);

        scanner.nextLine();
        System.out.print("Enter name for Player 1 (X): ");
        String name1 = scanner.nextLine().trim();
        if (name1.isEmpty()) {
            name1 = "Player 1";
        }

        System.out.print("Enter name for Player 2 (O): ");
        String name2 = scanner.nextLine().trim();
        if (name2.isEmpty()) {
            name2 = "Player 2";
        }

        Player player1 = new Player(name1, new PieceX());
        Player player2 = new Player(name2, new PieceO());

        players = new LinkedList<>();
        players.add(player1);
        players.add(player2);

        System.out.println("\nInitial Board:");
        board.print();
    }

    public void play() {
        boolean gameOn = true;

        while (gameOn) {
            Player currPlayer = players.removeFirst();

            int x = -1, y = -1;
            boolean validMove = false;

            while (!validMove) {
                System.out.println(currPlayer.getName() + ", it's your turn! Please select a position (row,col): ");
                String move = scanner.nextLine().trim();
                String[] splits = move.split(",");

                if (splits.length != 2) {
                    System.out.println("Invalid input format. Please enter as row,col (e.g., 1,2)");
                    continue;
                }

                try {
                    x = Integer.parseInt(splits[0].trim());
                    y = Integer.parseInt(splits[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numbers. Please enter integers.");
                    continue;
                }

                if (!board.isValidMove(x, y)) {
                    System.out.println("Invalid move. Cell is occupied or out of bounds. Try again.");
                } else {
                    validMove = true;
                }
            }

            board.placePiece(x, y, currPlayer.getPiece());

            System.out.println("\nBoard after " + currPlayer.getName() + "'s move:");
            board.print();

            if (board.hasWinner(x, y, currPlayer.getPiece())) {
                System.out.println(currPlayer.getName() + " wins!");
                gameOn = false;
            } else if (board.isFull()) {
                System.out.println("It's a draw!");
                gameOn = false;
            } else {
                players.addLast(currPlayer);
            }
        }
        close();
    }

    private void close() {
        scanner.close();
    }
}