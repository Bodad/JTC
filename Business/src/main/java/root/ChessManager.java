package root;

import business.BusinessManager;
import data.HealthCheck;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.text.PDFTextStripperByArea;


import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ChessManager extends BusinessManager {
    public ChessManager() {
        super("Chess Manager");
    }


    public void run() {
        List<ChessGame> chessGames = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            ChessGame chessGame = new ChessGame();
            chessGame.start();
            chessGames.add(chessGame);
        }


        getHealthCheck();

    }
}

