package tictactoe.models;

public class Board {

    private final int size;

    private final Piece[][] board;

    public Board(int size) {
        this.size = size;
        board = new Piece[size][size];
    }

    public void placePiece(int x, int y, Piece piece) {
        board[x][y] = piece;
    }

    public void print() {
        System.out.print("   ");
        for (int col = 0; col < size; col++) {
            System.out.print(col + "   ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < size; j++) {
                System.out.print("| ");
                if (board[i][j] != null) {
                    System.out.print(board[i][j].getPieceType().name());
                } else {
                    System.out.print(" ");
                }
                System.out.print(" ");
            }
            System.out.println("|");

            System.out.print("  ");
            for (int j = 0; j < size; j++) {
                System.out.print("----");
            }
            System.out.println("-");
        }
    }


    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && board[x][y] == null;
    }

    public boolean hasWinner(int x, int y, Piece piece) {
        return checkVertical(y, piece)
                || checkHorizontal(x, piece)
                || checkDiagonal(x, y, piece);
    }

    private boolean checkVertical(int y, Piece piece) {
        for (int i = 0; i < size; i++) {
            if (board[i][y] == null || board[i][y].getPieceType() != piece.getPieceType()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkHorizontal(int x, Piece piece) {
        for (int j = 0; j < size; j++) {
            if (board[x][j] == null || board[x][j].getPieceType() != piece.getPieceType()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(int x, int y, Piece piece) {
        boolean leftToRight = true;
        boolean rightToLeft = true;

        for (int i = 0; i < size; i++) {
            if (board[i][i] == null || board[i][i].getPieceType() != piece.getPieceType()) {
                leftToRight = false;
            }
            if (board[i][size - 1 - i] == null || board[i][size - 1 - i].getPieceType() != piece.getPieceType()) {
                rightToLeft = false;
            }
        }

        return leftToRight || rightToLeft;
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }
}
