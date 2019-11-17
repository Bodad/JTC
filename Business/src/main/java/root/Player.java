package root;

import business.Logger;

import java.util.*;

import java.util.stream.Collectors;

public class Player {
    private Logger logger = new Logger();

    public final String name;
    public final ChessPiece.Color color;
    public final ChessGame chessGame;
    private final Random random = new Random();

    private final ChessPlayLogic chessPlayLogic;

    public List<ChessPiece> activeChessPieces;
    public List<ChessPiece> capturedChessPieces = new ArrayList<>();

    public Player(String name, ChessPiece.Color color, ChessGame chessGame, ChessPlayLogic chessPlayLogic) {
        this.name = name;
        this.color = color;
        this.chessGame = chessGame;
        this.chessPlayLogic = chessPlayLogic;
    }

    public ChessMove choosePreferredMove(List<ChessMove> illegalChessMoves) {
        List<ChessMove> chessMoves = getAllPossibleMoves();

        logger.info("Player {} has {} moves minus {} illegals", this, chessMoves.size(), illegalChessMoves.size());

        chessMoves = chessMoves.stream()
                .filter(chessMove-> !illegalChessMoves.contains(chessMove))
                .collect(Collectors.toList());

        if (chessMoves.size() == 0) return null;

        Optional<ChessMove> kingCaptureMove = chessMoves.stream().filter(chessMove -> chessMove.to.contains(ChessPiece.Type.King)).findFirst();
        if (kingCaptureMove.isPresent()) return kingCaptureMove.get();

        ChessMove finalMove = chessPlayLogic.choosePreferredMove(chessMoves);
        return finalMove;
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
