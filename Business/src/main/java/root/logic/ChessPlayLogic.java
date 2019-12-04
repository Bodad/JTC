package root.logic;

import root.ChessBoard;
import root.ChessMove;

import java.util.List;

public abstract class ChessPlayLogic {
    public abstract ChessMove choosePreferredMove(ChessBoard chessBoard);
}
