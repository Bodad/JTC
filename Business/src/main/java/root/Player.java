package root;

import business.Logger;
import root.logic.ChessPlayLogic;

import java.util.*;

import java.util.stream.Collectors;

public class Player {
    private Logger logger = new Logger();

    public final String name;
    public final ChessPiece.Color color;
    private final Random random = new Random();

    private final ChessPlayLogic chessPlayLogic;

    public List<ChessPiece> activeChessPieces;
    public List<ChessPiece> capturedChessPieces = new ArrayList<>();

    public Player(String name, ChessPiece.Color color, ChessPlayLogic chessPlayLogic) {
        this.name = name;
        this.color = color;
        this.chessPlayLogic = chessPlayLogic;
    }

    public ChessMove choosePreferredMove(ChessBoard chessBoard) {
        List<ChessMove> chessMoves = getAllPossibleMoves(chessBoard);

        logger.info("Player {} has {} moves", this, chessMoves.size());

        if (chessMoves.size() == 0) return null;

//        Optional<ChessMove> kingCaptureMove = chessMoves.stream().filter(chessMove -> chessMove.capturedChessPieceType == ChessPiece.Type.King).findFirst();
//        if (kingCaptureMove.isPresent()) return kingCaptureMove.get();
//
        chessBoard.preferredMove = chessPlayLogic.choosePreferredMove(chessBoard);
        return chessBoard.preferredMove;
    }

    private List<ChessMove> getAllPossibleMoves(ChessBoard chessBoard) {
        List<ChessMove> possibleMoves = chessBoard.getAllPossibleMoves();
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

    @Override
    public String toString() {
        return String.format("Player %s (%s)", name, color);
    }
}
