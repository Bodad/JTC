package root;

public class ChessMove {
    public final ChessBoard.ECoordinate from;
    public final ChessBoard.ECoordinate to;

    public ChessMove(ChessBoard.ECoordinate from, ChessBoard.ECoordinate to) {
        this.from = from;
        this.to = to;
    }
}
