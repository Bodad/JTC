package root;

import business.BusinessApplication;

public class ChessApplication extends BusinessApplication {
    ChessManager chessManager = new ChessManager();

    public ChessApplication(){
        super("Chess Application");
        registerBusinessManager(chessManager);
    }

    @Override
    public void run() {
        chessManager.run();
    }
}
