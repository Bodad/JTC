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
import java.util.List;

public class ChessManager extends BusinessManager {
    public ChessManager(){
        super("Chess Manager");
    }

    public void run() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();


        getHealthCheck();

    }
}

