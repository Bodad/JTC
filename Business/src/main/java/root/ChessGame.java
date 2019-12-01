package root;

import business.Logger;

import java.util.Stack;

public class ChessGame {
    Logger logger = new Logger();

    static Player whitePlayer = new Player("Jake", ChessPiece.Color.White, new ManualPlayLogic());
    static Player blackPlayer = new Player("Tom", ChessPiece.Color.Black, new AIPlayLogic());

    Stack<ChessPlay> chessPlays = new Stack<>();

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
            ChessMove expectedOpponentsMove = chessBoard.expectedOpponentsMove;
            chessBoard = chessMove.execute(chessBoard);
//            System.out.println(String.format("           opponent expectedd move: %s", expectedOpponentsMove));

            chessPlays.add(new ChessPlay(chessBoard, chessMove));

            //chessBoard.switchPlayers();
        }

        winner = chessBoard.winner;

    }

    private void switchPlayers(GameState gameState) {
        Player temp = gameState.offensivePlayer;
        gameState.offensivePlayer = gameState.defensivePlayer;
        gameState.defensivePlayer = temp;
    }

//    //TODO This needs work
//    private void reverseMove(ChessMove chessMove) {
//        ChessBoard.Space toSpace = chessMove.to;
//        ChessBoard.Space fromSpace = chessMove.from;
//        ChessPiece fromChessPiece = chessMove.fromChessPiece;
//        ChessPiece toChessPiece = chessMove.capturedChessPiece;
//
//        fromSpace.occupyingChessPiece = fromChessPiece;
//        fromChessPiece.chessPieceStatus.numberOfMoves--;
//        fromChessPiece.chessPieceStatus.space = fromSpace;
//        toSpace.occupyingChessPiece = toChessPiece;
//        if (toChessPiece != null) toChessPiece.chessPieceStatus.space = toSpace;
//    }

    @Override
    public String toString() {
        return String.format("ChessGame - size: %d", this.chessPlays.size());
    }

}
