package root;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class JakeChessPlayLogic extends ChessPlayLogic {
    Random random = new Random();

    @Override
    public ChessMove choosePreferredMove(List<ChessMove> chessMoves) {
        List<ChessPiece> distinctChessPiece = chessMoves.stream().map(chessMove -> chessMove.fromChessPiece).distinct().collect(Collectors.toList());

        ChessPiece randomChessPiece = distinctChessPiece.get(random.nextInt(distinctChessPiece.size()));
        chessMoves = chessMoves.stream().filter(chessMove->chessMove.fromChessPiece == randomChessPiece).collect(Collectors.toList());


        return chessMoves.get(random.nextInt(chessMoves.size()));

    }
}
