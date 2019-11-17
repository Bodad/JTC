package root;

import java.util.List;

public abstract class ChessPlayLogic {
    public abstract ChessMove choosePreferredMove(List<ChessMove> chessMoves);
}
