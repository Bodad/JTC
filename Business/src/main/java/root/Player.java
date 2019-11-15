package root;

public class Player {
    public final String name;
    public final ChessPiece.Color color;
    public final ChessGame chessGame;




    public Player(String name, ChessPiece.Color color, ChessGame chessGame) {
        this.name = name;
        this.color = color;
        this.chessGame = chessGame;
    }

    public ChessMove move() {
        return null;
    }
}
