package root;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    final Spaces spaces;

    public ChessBoard() {
        this.spaces = new Spaces();
        for (ECoordinate coordinate : ECoordinate.values()){
            spaces.define(coordinate);
        }

    }
    
    public void resetPieces(){
        spaces.clear();
        spaces.at(ECoordinate.A1).occupyingChessPiece = ChessPiece.WhiteRook1;
        spaces.at(ECoordinate.B1).occupyingChessPiece = ChessPiece.WhiteKnight1;
        spaces.at(ECoordinate.C1).occupyingChessPiece = ChessPiece.WhiteBishop1;
        spaces.at(ECoordinate.D1).occupyingChessPiece = ChessPiece.WhiteQueen;
        spaces.at(ECoordinate.E1).occupyingChessPiece = ChessPiece.WhiteKing;
        spaces.at(ECoordinate.F1).occupyingChessPiece = ChessPiece.WhiteBishop2;
        spaces.at(ECoordinate.G1).occupyingChessPiece = ChessPiece.WhiteKnight2;
        spaces.at(ECoordinate.H1).occupyingChessPiece = ChessPiece.WhiteRook2;
        
        spaces.at(ECoordinate.A2).occupyingChessPiece = ChessPiece.WhitePawn1;
        spaces.at(ECoordinate.B2).occupyingChessPiece = ChessPiece.WhitePawn2;
        spaces.at(ECoordinate.C2).occupyingChessPiece = ChessPiece.WhitePawn3;
        spaces.at(ECoordinate.D2).occupyingChessPiece = ChessPiece.WhitePawn4;
        spaces.at(ECoordinate.E2).occupyingChessPiece = ChessPiece.WhitePawn5;
        spaces.at(ECoordinate.F2).occupyingChessPiece = ChessPiece.WhitePawn6;
        spaces.at(ECoordinate.G2).occupyingChessPiece = ChessPiece.WhitePawn7;
        spaces.at(ECoordinate.H2).occupyingChessPiece = ChessPiece.WhitePawn8;

        spaces.at(ECoordinate.A8).occupyingChessPiece = ChessPiece.BlackRook1;
        spaces.at(ECoordinate.B8).occupyingChessPiece = ChessPiece.BlackKnight1;
        spaces.at(ECoordinate.C8).occupyingChessPiece = ChessPiece.BlackBishop1;
        spaces.at(ECoordinate.D8).occupyingChessPiece = ChessPiece.BlackQueen;
        spaces.at(ECoordinate.E8).occupyingChessPiece = ChessPiece.BlackKing;
        spaces.at(ECoordinate.F8).occupyingChessPiece = ChessPiece.BlackBishop2;
        spaces.at(ECoordinate.G8).occupyingChessPiece = ChessPiece.BlackKnight2;
        spaces.at(ECoordinate.H8).occupyingChessPiece = ChessPiece.BlackRook2;

        spaces.at(ECoordinate.A7).occupyingChessPiece = ChessPiece.BlackPawn1;
        spaces.at(ECoordinate.B7).occupyingChessPiece = ChessPiece.BlackPawn2;
        spaces.at(ECoordinate.C7).occupyingChessPiece = ChessPiece.BlackPawn3;
        spaces.at(ECoordinate.D7).occupyingChessPiece = ChessPiece.BlackPawn4;
        spaces.at(ECoordinate.E7).occupyingChessPiece = ChessPiece.BlackPawn5;
        spaces.at(ECoordinate.F7).occupyingChessPiece = ChessPiece.BlackPawn6;
        spaces.at(ECoordinate.G7).occupyingChessPiece = ChessPiece.BlackPawn7;
        spaces.at(ECoordinate.H7).occupyingChessPiece = ChessPiece.BlackPawn8;
    }

    private static class Spaces {
        Map<ECoordinate, Space> spaces = new HashMap<>();

        public void define(ECoordinate coordinate) {
            Space space = new Space(coordinate);
            spaces.put(space.coordinate, space);
        }

        public void clear() {
            spaces.clear();
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

        public ECoordinate get(int x, int y){
            return valueOf(String.format("%c%d", x+64, y));
        }
    }

}
