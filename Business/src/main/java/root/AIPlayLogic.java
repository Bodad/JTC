package root;

import business.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class AIPlayLogic extends ChessPlayLogic {
    Random random = new Random();

    Logger logger = new Logger();

    @Override
    public ChessMove choosePreferredMove(ChessBoard chessBoard) {
        boolean redo = false;
        ChessMove preferredMove = alphaBetaMax(chessBoard, null, null, 0, 4);
//        while (redo) {
//            preferredMove = alphaBetaMax(chessBoard, null, null, 0, 3);
//        }
        chessBoard.preferredMove = preferredMove;
        return preferredMove;
    }


    private ChessMove alphaBetaMax(ChessBoard chessBoard, ChessMove alpha, ChessMove beta, int depth, int maxDepth) {
        if (depth == maxDepth) {
            //ChessMove finalChessMove = chessBoard.previousMoves.elementAt(chessBoard.previousMoves.size() - depth);
            ChessMove finalChessMove = chessBoard.previousMoves.elementAt(chessBoard.previousMoves.size() - depth);
            finalChessMove.finalBoardStrength *= -1;
            return finalChessMove;
        }

        List<ChessMove> allPossibleMoves = chessBoard.getAllPossibleMoves();

        for(ChessMove chessMove : allPossibleMoves){
            ChessBoard newChessBoard = chessMove.execute(chessBoard);
            ChessMove newMove = alphaBetaMin(newChessBoard, alpha, beta, depth+1, maxDepth);

            if (beta!= null && newMove.finalBoardStrength >= beta.finalBoardStrength){
                return beta;
            }
            if (alpha == null || newMove.finalBoardStrength > alpha.finalBoardStrength){
                alpha = newMove;
            }
        }
        return alpha;
    }

    private ChessMove alphaBetaMin(ChessBoard chessBoard, ChessMove alpha, ChessMove beta, int depth, int maxDepth){
        if (depth == maxDepth) {
            ChessMove finalChessMove = chessBoard.previousMoves.elementAt(chessBoard.previousMoves.size() - depth);
            finalChessMove.finalBoardStrength *= -1;
            return finalChessMove;
        }
        List<ChessMove> allPossibleMoves = chessBoard.getAllPossibleMoves();

        for(ChessMove chessMove : allPossibleMoves){
            ChessBoard newChessBoard = chessMove.execute(chessBoard);
            chessMove.finalBoardStrength *= -1;


            ChessMove newChessMove = alphaBetaMax(newChessBoard, alpha, beta, depth+1, maxDepth);
            if (alpha != null && newChessMove.finalBoardStrength <= alpha.finalBoardStrength){
                return alpha;
            }
            if (beta == null || newChessMove.finalBoardStrength < beta.finalBoardStrength){
                beta = newChessMove;
            }
        }
        return beta;
    }
}
