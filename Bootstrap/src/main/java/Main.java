import data.HealthCheck;
import root.ChessApplication;

public class Main {

    public static void main(String[] args){
        ChessApplication chessApplication = new ChessApplication();
        HealthCheck healthCheck = chessApplication.getHealthCheck();
        chessApplication.run();
    }
}

