import java.util.Scanner;

public class TicTacToe {

    // Create a 3x3 grid
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    // Initialize the game board with numbers 1-9
    public static void initializeBoard() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Character.forDigit(count++, 10);
            }
        }
    }

    // Display the board
    public static void displayBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j]);
                if (j < 2) System.out.print(" |");
            }
            System.out.println();
            if (i < 2) System.out.println("---+---+---");
        }
    }

    // Check for a winner
    public static boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true;
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true;
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;

        return false;
    }

    // Check for a draw
    public static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Character.isDigit(board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    // Handle player move
    public static boolean playerMove(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;

        if (Character.isDigit(board[row][col])) {
            board[row][col] = currentPlayer;
            return true;
        } else {
            System.out.println("Invalid move. Cell is already taken. Try again.");
            return false;
        }
    }

    // Switch player
    public static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playAgain;

        do {
            initializeBoard();
            currentPlayer = 'X';
            boolean gameWon = false;
            boolean gameDraw = false;

            System.out.println("Welcome to Tic Tac Toe!");

            while (!gameWon && !gameDraw) {
                displayBoard();
                System.out.println("Player " + currentPlayer + ", enter your move (1-9): ");
                int move = scanner.nextInt();

                if (move < 1 || move > 9) {
                    System.out.println("Invalid input. Please enter a number between 1 and 9.");
                    continue;
                }

                if (playerMove(move)) {
                    gameWon = checkWin();
                    gameDraw = checkDraw();

                    if (!gameWon && !gameDraw) {
                        switchPlayer();
                    }
                }
            }

            displayBoard();

            if (gameWon) {
                System.out.println("Player " + currentPlayer + " wins!");
            } else if (gameDraw) {
                System.out.println("It's a draw!");
            }

            // Ask if players want to play again
            System.out.println("Do you want to play again? (yes/no): ");
            playAgain = scanner.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}