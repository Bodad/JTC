package root.logic;

import business.Logger;
import root.ChessBoard;
import root.ChessMove;
import root.ChessPiece;

import java.util.*;

public class AIPlayLogic extends ChessPlayLogic {
    Random random = new Random();

    Logger logger = new Logger();

    @Override
    public ChessMove choosePreferredMove(ChessBoard chessBoard) {
        boolean redo = false;

        ChessEvaluation chessEvaluation = evaluateMyMoves(chessBoard, 3);

        chessBoard.preferredMove = chessEvaluation.chosenChessMove;
        return chessBoard.preferredMove;
    }

    private ChessEvaluation evaluateMyMoves(ChessBoard chessBoard, int depth) {
        ChessEvaluation chessEvaluation = new ChessEvaluation();
        List<ChessMove> allPossibleMoves = chessBoard.getAllPossibleMoves();

        ChessPiece.Color playerColor = chessBoard.offensivePlayer.color;
        ChessPiece.Color opponentColor = playerColor == ChessPiece.Color.Black ? ChessPiece.Color.White : ChessPiece.Color.Black;

        if (depth == 0) {
            for (ChessMove chessMove : allPossibleMoves){
                ChessBoard newChessBoard = chessMove.execute(chessBoard);
                if (chessEvaluation.chosenChessBoard == null || chessEvaluation.chosenChessBoard.getColorAdvantage(playerColor) < newChessBoard.getColorAdvantage(playerColor)){
                    chessEvaluation.chosenChessBoard = newChessBoard;
                    chessEvaluation.chosenChessMove = chessMove;
                }
                chessEvaluation.evaluatedChessBoards.add(newChessBoard);
            }
        }else{
            for (ChessMove chessMove : allPossibleMoves) {
                ChessBoard newChessBoard = chessMove.execute(chessBoard);
                ChessEvaluation childChessEvaluation = evaluateMyOpponentsMoves(newChessBoard, depth - 1);
                childChessEvaluation.parentChessMove = chessMove;
                childChessEvaluation.parentChessBoard = newChessBoard;
                chessEvaluation.childChessEvaluations.add(childChessEvaluation);
            }

            ChessEvaluation chosenChessEvaluation = chessEvaluation.childChessEvaluations.stream().min(Comparator.comparing(ce -> ce.chosenChessMove.getColorAdvantage(opponentColor))).get();
            chessEvaluation.setChosenEvaluation(chosenChessEvaluation);
            //maybe//
            chessEvaluation.childChessEvaluations.clear();
        }
        return chessEvaluation;
    }

    private ChessEvaluation evaluateMyOpponentsMoves(ChessBoard chessBoard, int depth) {
        ChessEvaluation chessEvaluation = new ChessEvaluation();

        ChessPiece.Color playerColor = chessBoard.offensivePlayer.color;
        ChessPiece.Color opponentColor = playerColor == ChessPiece.Color.Black ? ChessPiece.Color.White : ChessPiece.Color.Black;

        List<ChessMove> allPossibleMoves = chessBoard.getAllPossibleMoves();

        if (depth == 0) {
            for (ChessMove chessMove : allPossibleMoves){
                ChessBoard newChessBoard = chessMove.execute(chessBoard);
                if (chessEvaluation.chosenChessBoard == null || chessEvaluation.chosenChessBoard.getColorAdvantage(playerColor) < newChessBoard.getColorAdvantage(playerColor)){
                    chessEvaluation.chosenChessBoard = newChessBoard;
                    chessEvaluation.chosenChessMove = chessMove;
                }
                chessEvaluation.evaluatedChessBoards.add(newChessBoard);
            }
        }else{
            for (ChessMove chessMove : allPossibleMoves) {
                ChessBoard newChessBoard = chessMove.execute(chessBoard);
                ChessEvaluation childChessEvaluation = evaluateMyOpponentsMoves(newChessBoard, depth - 1);
                childChessEvaluation.parentChessMove = chessMove;
                childChessEvaluation.parentChessBoard = newChessBoard;
                chessEvaluation.childChessEvaluations.add(childChessEvaluation);
            }

            ChessEvaluation chosenChessEvaluation = chessEvaluation.childChessEvaluations.stream().min(Comparator.comparing(ce -> ce.chosenChessMove.getColorAdvantage(opponentColor))).get();
            chessEvaluation.setChosenEvaluation(chosenChessEvaluation);
            // Maybe //
            chessEvaluation.childChessEvaluations.clear();

//            bestChessMove = allPossibleMoves.stream()
//                    .min(Comparator.comparing(chessMove -> evaluateMyMoves(chessMove.execute(chessBoard), depth-1).finalBoardStrength))
//                    .get();
        }
        return chessEvaluation;
    }

}
