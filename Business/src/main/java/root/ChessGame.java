package root;

import business.Logger;
import root.logic.AIPlayLogic;
import root.logic.ManualPlayLogic;

import java.util.Stack;

public class ChessGame {
    Logger logger = new Logger();

    static Player whitePlayer = new Player("Jake", ChessPiece.Color.White, new ManualPlayLogic());
    static Player blackPlayer = new Player("Tom", ChessPiece.Color.Black, new AIPlayLogic());

    Stack<ChessBoard> chessPlays = new Stack<>();

    Player winner = null;

    public void start() {
        ChessBoard chessBoard = new ChessBoard(whitePlayer, blackPlayer);

        while (chessBoard.winner == null && chessPlays.size() < 500) {
            ChessMove chessMove = chessBoard.offensivePlayer.choosePreferredMove(chessBoard);
            if (chessMove == null) {
                chessBoard.winner = chessBoard.defensivePlayer;
                break;
            }
            System.out.println(String.format("Player %s moving %s", chessBoard.offensivePlayer.name, chessMove));
            chessBoard = chessMove.execute(chessBoard);

            chessPlays.add(chessBoard);
        }

        winner = chessBoard.winner;

    }

    @Override
    public String toString() {
        return String.format("ChessGame - size: %d", this.chessPlays.size());
    }

}
