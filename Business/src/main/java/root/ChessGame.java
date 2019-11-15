package root;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    ChessBoard chessBoard = new ChessBoard();
    Player blackPlayer = new Player("Black Player", ChessPiece.Color.Black, this);
    Player whitePlayer = new Player("White Player", ChessPiece.Color.White, this);

    Player offensivePlayer = whitePlayer;
    Player defensivePlayer = blackPlayer;

    List<ChessMove> chessMoves = new ArrayList<>();

    public void start() {
        chessBoard.reset();
        blackPlayer.reset();
        whitePlayer.reset();
        if (offensivePlayer != whitePlayer) switchPlayers();

        boolean isGameOver = false;
        ChessBoard.ECoordinate eCoordinate = ChessBoard.ECoordinate.get(0, 0);
        ChessBoard.Space A1 = chessBoard.spaces.at(eCoordinate);
        ChessBoard.Space B1 = chessBoard.spaces.at(ChessBoard.ECoordinate.get(1, 0));
        ChessBoard.Space A2 = chessBoard.spaces.at(ChessBoard.ECoordinate.get(0, 1));


        while (!isGameOver) {
            ChessMove chessMove = offensivePlayer.move();
            isGameOver = makeMove(chessMove);
            switchPlayers();
        }
    }

    private void switchPlayers() {
        Player temp = offensivePlayer;
        defensivePlayer = offensivePlayer;
        offensivePlayer = temp;
    }

    private boolean makeMove(ChessMove chessMove) {
        chessMoves.add(chessMove);

        ChessBoard.Space toSpace = chessBoard.spaces.at(chessMove.to);
        ChessPiece capturedChessPiece = toSpace.occupyingChessPiece;
        if (capturedChessPiece != null) {
            capturedChessPiece.status = ChessPiece.Status.Captured;
            capturedChessPiece.space = null;
            defensivePlayer.removePiece(capturedChessPiece);
            if (capturedChessPiece.type == ChessPiece.Type.King) return true;
        }

        ChessBoard.Space fromSpace = chessBoard.spaces.at(chessMove.from);
        ChessPiece movingChessPiece = fromSpace.occupyingChessPiece;

        fromSpace.occupyingChessPiece = null;
        toSpace.occupyingChessPiece = movingChessPiece;
        movingChessPiece.space = toSpace;
        return false;
    }

}
