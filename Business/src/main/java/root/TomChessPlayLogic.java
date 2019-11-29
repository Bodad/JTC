package root;

import business.Logger;

import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;

public class TomChessPlayLogic extends ChessPlayLogic {
    Random random = new Random();

    Logger logger = new Logger();

    @Override
    public ChessMove choosePreferredMove(ChessBoard chessBoard) {
        return choosePreferredMove(chessBoard, 0);
    }

    private ChessMove choosePreferredMove(ChessBoard chessBoard, int depth) {

        int[] highestStrength = new int[1];
        highestStrength[0] = -100000;
        ChessMove[] highestChessMove = new ChessMove[1];

        List<ChessMove> chessMoves1 = chessBoard.getAllPossibleMoves();
        chessMoves1.forEach(chessMove1 -> {
            ChessBoard chessBoard1 = chessMove1.execute(chessBoard);
if (depth < 1)
            logger.info("Depth: {}, Move: {}, Strength: {}", depth, chessMove1, chessBoard1.evaluteStrength());

            if (depth == 3){
                int strength = chessBoard1.evaluteStrength();
                if (strength > highestStrength[0]) {
                    highestChessMove[0] = chessMove1;
                    highestStrength[0] = strength;
                }

            }else {


                ChessMove opponentsMove = choosePreferredMove(chessBoard1, depth + 1);
                ChessBoard chessBoard2 = opponentsMove.execute(chessBoard1);

                List<ChessMove> board2Moves = chessBoard2.getAllPossibleMoves();

                board2Moves.forEach(chessMove2 -> {
                    ChessBoard chessBoard3 = chessMove2.execute(chessBoard2);
                    int strength = chessBoard3.evaluteStrength();
                    if (strength > highestStrength[0]) {
                        highestChessMove[0] = chessMove1;
                        highestStrength[0] = strength;
                    }
                });
            }

        });

        return highestChessMove[0];
    }
}
