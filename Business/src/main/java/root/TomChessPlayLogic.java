package root;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TomChessPlayLogic extends ChessPlayLogic {
    Random random = new Random();

    @Override
    public ChessMove choosePreferredMove(List<ChessMove> chessMoves) {
        List<ChessPiece.Type> distinctChessPieceType = chessMoves.stream().map(chessMove -> chessMove.fromChessPieceType).distinct().collect(Collectors.toList());

        ChessPiece.Type randomChessPieceType = distinctChessPieceType.get(random.nextInt(distinctChessPieceType.size()));
        chessMoves = chessMoves.stream().filter(chessMove->chessMove.fromChessPieceType == randomChessPieceType).collect(Collectors.toList());

        return chessMoves.get(random.nextInt(chessMoves.size()));
    }
}
