package root;

public class ChessPlay {
    public final ChessMove chessMove;
    public final ChessBoard chessBoard;

    public ChessPlay(ChessBoard chessBoard, ChessMove chessMove) {
        this.chessBoard = chessBoard;
        this.chessMove = chessMove;
    }
}
