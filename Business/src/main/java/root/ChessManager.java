package root;

import business.BusinessManager;
import business.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class ChessManager extends BusinessManager {
    public ChessManager() {
        super("Chess Manager");
    }

    private Logger logger = new Logger();

    public void run() {

//        logger.info("Logging");
        List<ChessGame> chessGames = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            ChessGame chessGame = new ChessGame();
            chessGame.start();
            chessGames.add(chessGame);
//            if (chessGame.chessMoves.size() < 1000){
//                chessGame.logger.info("Winner Winner Chicken Dinner");
//            }
        }

        OptionalDouble average = chessGames.stream().mapToInt(chessGame -> chessGame.chessPlays.size()).filter(i -> i < 199).average();

        Map<ChessPiece.Color, Long> winners = chessGames.stream()
                .filter(chessGame->chessGame.winner != null)
                .collect(Collectors.groupingBy(chessGame -> chessGame.winner.color, Collectors.counting()));


        getHealthCheck();

    }
}

