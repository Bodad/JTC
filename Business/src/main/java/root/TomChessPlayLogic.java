package root;

import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;

public class TomChessPlayLogic extends ChessPlayLogic {
    Random random = new Random();

    @Override
    public ChessMove choosePreferredMove(ChessBoard chessBoard) {

        int[] highestStrength = new int[1];
        ChessMove[] highestChessMove = new ChessMove[1];

        List<ChessMove> chessMoves1 = chessBoard.getAllPossibleMoves();
        chessMoves1.forEach(chessMove1->{
            ChessBoard chessBoard1 = chessMove1.execute(chessBoard);

            List<ChessMove> chessMoves2 = chessBoard1.getAllPossibleMoves();
            chessMoves2.forEach(chessMove2->{
                ChessBoard chessBoard2 = chessMove2.execute(chessBoard1);

                List<ChessMove> chessMoves3 = chessBoard2.getAllPossibleMoves();
                chessMoves3.forEach(chessMove3->{
                    ChessBoard chessBoard3 = chessMove3.execute(chessBoard2);
                    int strength = chessBoard3.evaluteStrength();
                    if (strength > highestStrength[0]){
                        highestChessMove[0] = chessMove1;
                        highestStrength[0] = strength;
                    }
                });
            });
        });

        return highestChessMove[0];
    }
}
