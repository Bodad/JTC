package root;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    public final static Spaces spaces = new Spaces();

    public ChessBoard() {
        for (ECoordinate coordinate : ECoordinate.values()){
            spaces.define(coordinate);
        }

    }
    
    public void reset(){
        spaces.clear();
        spaces.at(ECoordinate.A1).resetChessPiece(ChessPiece.WhiteRook1);
        spaces.at(ECoordinate.B1).resetChessPiece(ChessPiece.WhiteKnight1);
        spaces.at(ECoordinate.C1).resetChessPiece(ChessPiece.WhiteBishop1);
        spaces.at(ECoordinate.D1).resetChessPiece(ChessPiece.WhiteQueen);
        spaces.at(ECoordinate.E1).resetChessPiece(ChessPiece.WhiteKing);
        spaces.at(ECoordinate.F1).resetChessPiece(ChessPiece.WhiteBishop2);
        spaces.at(ECoordinate.G1).resetChessPiece(ChessPiece.WhiteKnight2);
        spaces.at(ECoordinate.H1).resetChessPiece(ChessPiece.WhiteRook2);
        
        spaces.at(ECoordinate.A2).resetChessPiece(ChessPiece.WhitePawn1);
        spaces.at(ECoordinate.B2).resetChessPiece(ChessPiece.WhitePawn2);
        spaces.at(ECoordinate.C2).resetChessPiece(ChessPiece.WhitePawn3);
        spaces.at(ECoordinate.D2).resetChessPiece(ChessPiece.WhitePawn4);
        spaces.at(ECoordinate.E2).resetChessPiece(ChessPiece.WhitePawn5);
        spaces.at(ECoordinate.F2).resetChessPiece(ChessPiece.WhitePawn6);
        spaces.at(ECoordinate.G2).resetChessPiece(ChessPiece.WhitePawn7);
        spaces.at(ECoordinate.H2).resetChessPiece(ChessPiece.WhitePawn8);

        spaces.at(ECoordinate.A8).resetChessPiece(ChessPiece.BlackRook1);
        spaces.at(ECoordinate.B8).resetChessPiece(ChessPiece.BlackKnight1);
        spaces.at(ECoordinate.C8).resetChessPiece(ChessPiece.BlackBishop1);
        spaces.at(ECoordinate.D8).resetChessPiece(ChessPiece.BlackQueen);
        spaces.at(ECoordinate.E8).resetChessPiece(ChessPiece.BlackKing);
        spaces.at(ECoordinate.F8).resetChessPiece(ChessPiece.BlackBishop2);
        spaces.at(ECoordinate.G8).resetChessPiece(ChessPiece.BlackKnight2);
        spaces.at(ECoordinate.H8).resetChessPiece(ChessPiece.BlackRook2);

        spaces.at(ECoordinate.A7).resetChessPiece(ChessPiece.BlackPawn1);
        spaces.at(ECoordinate.B7).resetChessPiece(ChessPiece.BlackPawn2);
        spaces.at(ECoordinate.C7).resetChessPiece(ChessPiece.BlackPawn3);
        spaces.at(ECoordinate.D7).resetChessPiece(ChessPiece.BlackPawn4);
        spaces.at(ECoordinate.E7).resetChessPiece(ChessPiece.BlackPawn5);
        spaces.at(ECoordinate.F7).resetChessPiece(ChessPiece.BlackPawn6);
        spaces.at(ECoordinate.G7).resetChessPiece(ChessPiece.BlackPawn7);
        spaces.at(ECoordinate.H7).resetChessPiece(ChessPiece.BlackPawn8);
    }

    public static class Spaces {
        Map<ECoordinate, Space> spaces = new HashMap<>();

        public void define(ECoordinate coordinate) {
            Space space = new Space(coordinate);
            spaces.put(space.coordinate, space);
        }

        public void clear() {
            spaces.values().stream().forEach(space->space.clear());
        }

        public Space at(ECoordinate coordinate) {
            return spaces.get(coordinate);
        }

    }

    public static class Space{
        public final ECoordinate coordinate;
        public ChessPiece occupyingChessPiece;

        public Space(ECoordinate coordinate) {
            this.coordinate = coordinate;
        }

        @Override
        public String toString() {
            return String.format("%s - %s", coordinate, occupyingChessPiece);
        }

        public void resetChessPiece(ChessPiece chessPiece) {
            occupyingChessPiece = chessPiece;
            chessPiece.space = this;
            chessPiece.status= ChessPiece.Status.Active;
        }

        public void clear() {
            occupyingChessPiece = null;
        }

        public Point getXYCoordinates() {
            return coordinate.getXYCoordinates();
        }

        public Space getNeighbor(ChessMove.Direction moveDirection) {
            return moveDirection.getSpace(this);
        }

        public Space getRelativeNeighbor(int x, int y) {
            Point xyCoordinates = getXYCoordinates();
            return spaces.at(ECoordinate.get(xyCoordinates.x+x, xyCoordinates.y+y));
        }
    }

    public enum ECoordinate {
        A1,A2,A3,A4,A5,A6,A7,A8,
        B1,B2,B3,B4,B5,B6,B7,B8,
        C1,C2,C3,C4,C5,C6,C7,C8,
        D1,D2,D3,D4,D5,D6,D7,D8,
        E1,E2,E3,E4,E5,E6,E7,E8,
        F1,F2,F3,F4,F5,F6,F7,F8,
        G1,G2,G3,G4,G5,G6,G7,G8,
        H1,H2,H3,H4,H5,H6,H7,H8;

        public static ECoordinate get(int x, int y){
            if (x<0 || x>7 || y<0 || y>7) return null;

            String format = String.format("%c%d", y + 65, x+1);
            ECoordinate eCoordinate = valueOf(format);
            return eCoordinate;
        }

        public Point getXYCoordinates() {
            char[] chars = this.name().toCharArray();
            Point point = new Point(chars[0] - 65, chars[1] - 49);
            return point;
        }
    }

}
