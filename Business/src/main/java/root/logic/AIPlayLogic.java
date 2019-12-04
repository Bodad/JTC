package root.logic;

import business.Logger;
import root.ChessBoard;
import root.ChessMove;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class AIPlayLogic extends ChessPlayLogic {
    Random random = new Random();

    Logger logger = new Logger();

    @Override
    public ChessMove choosePreferredMove(ChessBoard chessBoard) {
        boolean redo = false;

        ChessMove bestChessMove = evaluateMyMoves(chessBoard, 2);

        chessBoard.preferredMove = bestChessMove;
        return chessBoard.preferredMove;
    }

    private ChessMove evaluateMyMoves(ChessBoard chessBoard, int depth) {
        List<ChessMove> allPossibleMoves = chessBoard.getAllPossibleMoves();
        ChessMove bestChessMove = null;

        if (depth == 0) {
            bestChessMove = allPossibleMoves.stream()
                    .min(Comparator.comparing(chessMove->chessMove.execute(chessBoard).getCurrentPlayerStrength()))
                    .get();
        }else{
            bestChessMove = allPossibleMoves.stream()
                    .max(Comparator.comparing(chessMove -> evaluateMyOpponentsMoves(chessMove.execute(chessBoard), depth-1).finalBoardStrength))
                    .get();
        }
        return bestChessMove;
    }

    private ChessMove evaluateMyOpponentsMoves(ChessBoard chessBoard, int depth) {
        List<ChessMove> allPossibleMoves = chessBoard.getAllPossibleMoves();
        ChessMove bestChessMove = null;

        if (depth == 0) {
            bestChessMove = allPossibleMoves.stream()
                    .min(Comparator.comparing(chessMove -> chessMove.execute(chessBoard).getCurrentPlayerStrength()))
                    .get();
        }else{
            bestChessMove = allPossibleMoves.stream()
                    .max(Comparator.comparing(chessMove -> evaluateMyMoves(chessMove.execute(chessBoard), depth-1).finalBoardStrength))
                    .get();
        }
        return bestChessMove;
    }

}
