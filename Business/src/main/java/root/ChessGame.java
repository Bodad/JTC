package root;

import business.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ChessGame {
    Logger logger = new Logger();

    ChessBoard chessBoard = new ChessBoard();

    Player blackPlayer = new Player("Black Player", ChessPiece.Color.Black, this, new TomChessPlayLogic());
    Player whitePlayer = new Player("White Player", ChessPiece.Color.White, this, new JakeChessPlayLogic());

    Player winner = null;

    Player offensivePlayer = whitePlayer;
    Player defensivePlayer = blackPlayer;

    Stack<ChessMove> chessMoves = new Stack<>();

    public void start() {
        chessMoves.clear();
        chessBoard.reset();
        blackPlayer.reset();
        whitePlayer.reset();
        winner = null;

        if (offensivePlayer != whitePlayer) switchPlayers();

        HashMap<ChessPiece.Color, List<ChessMove>> illegalChessMoves = new HashMap<>();
        illegalChessMoves.put(ChessPiece.Color.Black, new ArrayList<>());
        illegalChessMoves.put(ChessPiece.Color.White, new ArrayList<>());

        while (winner == null && chessMoves.size() < 500) {
            ChessMove chessMove = offensivePlayer.choosePreferredMove(illegalChessMoves.get(offensivePlayer.color));
            if (chessMove == null) {
                winner = defensivePlayer;
                break;
            }

            if (chessMove.to.contains(ChessPiece.Type.King)) {
                ChessMove newCheckMove = chessMoves.pop();
                reverseMove(newCheckMove);
                illegalChessMoves.get(defensivePlayer.color).add(newCheckMove);
            } else {
                illegalChessMoves.get(defensivePlayer.color).clear();
                boolean isGameOver = makeMove(chessMove);
                if (isGameOver){
                    winner = offensivePlayer;
                }
            }

            switchPlayers();
        }

    }

    private void switchPlayers() {
        Player temp = offensivePlayer;
        offensivePlayer = defensivePlayer;
        defensivePlayer = temp;
    }

    private boolean makeMove(ChessMove chessMove) {
        //     logger.info(chessMove.toString());
        chessMoves.push(chessMove);

        chessMove.execute(this);

        return false;
    }

    private void reverseMove(ChessMove chessMove) {
        ChessBoard.Space toSpace = chessMove.to;
        ChessBoard.Space fromSpace = chessMove.from;
        ChessPiece fromChessPiece = chessMove.fromChessPiece;
        ChessPiece toChessPiece = chessMove.toChessPiece;

        fromSpace.occupyingChessPiece = fromChessPiece;
        fromChessPiece.numberOfMoves--;
        fromChessPiece.space = fromSpace;
        toSpace.occupyingChessPiece = toChessPiece;
        if (toChessPiece != null) toChessPiece.space = toSpace;
    }

    @Override
    public String toString() {
        return String.format("ChessGame - size: %d", chessMoves.size());
    }
}
