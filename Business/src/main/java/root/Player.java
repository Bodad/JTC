package root;

import java.util.*;
import java.util.stream.Collectors;

public class Player {
    public final String name;
    public final ChessPiece.Color color;
    public final ChessGame chessGame;
    private final Random random = new Random();

    public List<ChessPiece> activeChessPieces;
    public List<ChessPiece> capturedChessPieces = new ArrayList<>();

    public Player(String name, ChessPiece.Color color, ChessGame chessGame) {
        this.name = name;
        this.color = color;
        this.chessGame = chessGame;
    }

    public ChessMove move() {
        List<ChessMove> chessMoves = getAllPossibleMoves();
        return chessMoves.get(random.nextInt(chessMoves.size()-1));
    }

    private List<ChessMove> getAllPossibleMoves() {
        List<ChessMove> possibleMoves = activeChessPieces.stream()
                .flatMap(chessPiece->chessPiece.getPossibleMoves(chessGame).stream())
                .collect(Collectors.toList());

        return possibleMoves;
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
