package root.logic;

import root.ChessBoard;
import root.ChessMove;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessEvaluation {
    public int depth;
    public List<ChessBoard> evaluatedChessBoards = new ArrayList<>();
    public List<ChessEvaluation> childChessEvaluations = new ArrayList<>();
    public ChessEvaluation chosenEvaluation;
    public ChessBoard chosenChessBoard;
    public ChessMove chosenChessMove;
    public ChessMove parentChessMove;
    public ChessBoard parentChessBoard;

    public void setChosenEvaluation(ChessEvaluation chosenChessEvaluation) {
        this.chosenEvaluation = chosenChessEvaluation;
        this.chosenChessBoard = chosenChessEvaluation.parentChessBoard;
        this.chosenChessMove = chosenChessEvaluation.parentChessMove;
    }

    @Override
    public String toString() {
        return String.format("chosen board %s", chosenChessBoard);
    }
}
