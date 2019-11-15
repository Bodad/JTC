package root;

public class ChessGame {
    ChessBoard chessBoard = new ChessBoard();
    Player blackPlayer = new Player("Black Player", ChessPiece.Color.Black, this);
    Player whitePlayer = new Player("White Player", ChessPiece.Color.White, this);

    private boolean isGameOver = false;

    public void start() {
        chessBoard.resetPieces();
        isGameOver = false;

        while (!isGameOver){
            whitePlayer.move();
            if (!isGameOver) blackPlayer.move();
        }
    }
}
