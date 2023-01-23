import javax.security.sasl.SaslClient;
import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    static char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    static void placeMark(int row, int column, char mark) {
        if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
            board[row][column] = mark;
        } else {
            System.out.println("Invalid input");
        }
    }

    static boolean checkColWin() {
        for (int j = 0; j <= 2; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkRowWin() {
        for (int i = 0; i <= 2; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagWin() {
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
                || board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        } else {
            return false;
        }
    }

    static  boolean checkDraw(){
        for (int i = 0; i <=2 ; i++) {
            for (int j = 0; j <=2 ; j++) {
                if (board[i][j]==' '){
                    return  false;
                }
            }
        }
        return  true;
    }

}

abstract class Player {
    String name;
    char mark;

    abstract void makeMove();

    boolean isValidMove(int row, int column) {
        if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
            if (TicTacToe.board[row][column] == ' ') {
                return true;
            }
        }
        return false;
    }
}


class HumanPlayer extends Player {

    HumanPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    void makeMove() {
        Scanner scanner = new Scanner(System.in);
        int row, column;
        do {
            System.out.print("Enter the row : ");
            row = scanner.nextInt();
            System.out.print("Enter the column : ");
            column = scanner.nextInt();
        } while (!isValidMove(row, column));

        TicTacToe.placeMark(row, column, mark);
    }


}

class AIPlayer extends Player {

    AIPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    void makeMove() {
        Scanner scanner = new Scanner(System.in);
        int row, column;
        do {
            Random random = new Random();
            row = random.nextInt(3);
            column = random.nextInt(3);
        } while (!isValidMove(row, column));

        TicTacToe.placeMark(row, column, mark);
    }

}


public class LaunchGame {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();

        HumanPlayer p1 = new HumanPlayer("Bob", 'X');
        AIPlayer p2 = new AIPlayer("San", 'O');

        Player currentPlayer;
        currentPlayer = p1;

        while (true) {
            System.out.println(currentPlayer.name + " Turn ");
            currentPlayer.makeMove();
            TicTacToe.displayBoard();

            if (TicTacToe.checkColWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagWin()) {
                System.out.println(currentPlayer.name + " has won");
                break;
            } else if (TicTacToe.checkDraw()) {
                System.out.println("Game is a draw");
                break;
            } else {
                if (currentPlayer == p1) {
                    currentPlayer = p2;
                } else {
                    currentPlayer = p1;
                }
            }
        }

    }
}
