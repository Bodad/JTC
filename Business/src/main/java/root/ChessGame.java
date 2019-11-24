package root;

import business.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ChessGame {
    Logger logger = new Logger();

    static ChessBoard chessBoard = new ChessBoard();

    static Player blackPlayer = new Player("Black Player", ChessPiece.Color.Black, new TomChessPlayLogic());
    static Player whitePlayer = new Player("White Player", ChessPiece.Color.White, new JakeChessPlayLogic());


    GameState gameState = new GameState();

    public void start() {
        gameState.reset();

//        if (offensivePlayer != whitePlayer) switchPlayers();

        HashMap<ChessPiece.Color, List<ChessMove>> illegalChessMoves = new HashMap<>();
        illegalChessMoves.put(ChessPiece.Color.Black, new ArrayList<>());
        illegalChessMoves.put(ChessPiece.Color.White, new ArrayList<>());

        while (gameState.winner == null && gameState.chessMoves.size() < 500) {
            ChessMove chessMove = gameState.offensivePlayer.choosePreferredMove(gameState, illegalChessMoves.get(gameState.offensivePlayer.color));
            if (chessMove == null) {
                gameState.winner = gameState.defensivePlayer;
                break;
            }

            if (chessMove.to.contains(ChessPiece.Type.King)) {
                ChessMove newCheckMove = gameState.chessMoves.pop();
                reverseMove(newCheckMove);
                illegalChessMoves.get(gameState.defensivePlayer.color).add(newCheckMove);
            } else {
                illegalChessMoves.get(gameState.defensivePlayer.color).clear();
                boolean isGameOver = makeMove(chessMove);
                if (isGameOver){
                    gameState.winner = gameState.offensivePlayer;
                }
            }

            switchPlayers();
        }

    }

    private void switchPlayers() {
        Player temp = gameState.offensivePlayer;
        gameState.offensivePlayer = gameState.defensivePlayer;
        gameState.defensivePlayer = temp;
    }

    private boolean makeMove(ChessMove chessMove) {
        gameState.chessMoves.push(chessMove);
        chessMove.execute(this);

        return false;
    }

    private void reverseMove(ChessMove chessMove) {
        ChessBoard.Space toSpace = chessMove.to;
        ChessBoard.Space fromSpace = chessMove.from;
        ChessPiece fromChessPiece = chessMove.fromChessPiece;
        ChessPiece toChessPiece = chessMove.toChessPiece;

        fromSpace.occupyingChessPiece = fromChessPiece;
        fromChessPiece.chessPieceStatus.numberOfMoves--;
        fromChessPiece.chessPieceStatus.space = fromSpace;
        toSpace.occupyingChessPiece = toChessPiece;
        if (toChessPiece != null) toChessPiece.chessPieceStatus.space = toSpace;
    }

    @Override
    public String toString() {
        return String.format("ChessGame - size: %d", gameState.chessMoves.size());
    }

    public static class GameState{
        Player winner = null;

        Player offensivePlayer = whitePlayer;
        Player defensivePlayer = blackPlayer;

        Stack<ChessMove> chessMoves = new Stack<>();

        public void reset(){
            chessMoves.clear();
            chessBoard.reset();
            blackPlayer.reset();
            whitePlayer.reset();
            winner = null;

        }
    }
}
