package root;

public class GameState {
    Player winner = null;

    Player offensivePlayer = ChessGame.whitePlayer;
    Player defensivePlayer = ChessGame.blackPlayer;

    ChessBoard chessBoard;

//    public void reset(){
//        ChessGame.chessBoard.reset();
//        ChessGame.blackPlayer.reset();
//        ChessGame.whitePlayer.reset();
//        winner = null;
//
//    }
}
