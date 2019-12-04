package root.logic;

import root.ChessBoard;
import root.ChessMove;
import root.ChessSpace;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class ManualPlayLogic extends ChessPlayLogic {
    Random random = new Random();

    @Override
    public ChessMove choosePreferredMove(ChessBoard chessBoard) {
        List<ChessMove> chessMoves = chessBoard.getAllPossibleMoves();

        Optional<ChessMove> theMove = Optional.empty();

        while (theMove.isPresent() == false) {
            System.out.println("Enter your move: ");
            Scanner scanner = new Scanner(System.in);
            String nextLine = scanner.nextLine();

            String[] spaces = nextLine.split(" ");

            if (spaces.length == 2) {
                try {
                    ChessSpace from = ChessSpace.valueOf(spaces[0]);
                    ChessSpace to = ChessSpace.valueOf(spaces[1]);

                    theMove = chessMoves.stream().filter(chessMove -> chessMove.from == from && chessMove.to == to).findFirst();
                }catch(Exception e){}
            }

            if (spaces.length == 3) {
                try {
                    ChessSpace from = ChessSpace.valueOf(spaces[0]);
                    ChessSpace to = ChessSpace.valueOf(spaces[1]);
                    ChessMove.Type type = ChessMove.Type.valueOf(spaces[2]);

                    theMove = chessMoves.stream().filter(chessMove -> chessMove.from == from && chessMove.to == to && chessMove.type == type).findFirst();
                }catch(Exception e){}
            }

            if (theMove.isPresent() == false){
                System.out.println("Invalid... try again");
                chessMoves.stream().forEach(System.out::println);
            }

        }

        return theMove.get();
    }
}
