package root;

import java.util.*;
import java.util.stream.Collectors;

public class Player {
    public final String name;
    public final ChessPiece.Color color;
    public final ChessGame chessGame;

    public List<ChessPiece> activeChessPieces;
    public List<ChessPiece> capturedChessPieces = new ArrayList<>();

    public Player(String name, ChessPiece.Color color, ChessGame chessGame) {
        this.name = name;
        this.color = color;
        this.chessGame = chessGame;
    }

    public ChessMove move() {
        return null;
    }

    public void reset() {
        activeChessPieces = Arrays.stream(ChessPiece.values())
                .filter(chessPiece -> chessPiece.color == this.color)
                .collect(Collectors.toList());

        capturedChessPieces.clear();
    }

    public void removePiece(ChessPiece capturedChessPiece) {
        activeChessPieces.remove(capturedChessPiece);
        capturedChessPieces.add(capturedChessPiece);
    }
}
