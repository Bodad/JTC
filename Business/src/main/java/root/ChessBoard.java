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
        spaces.at(ECoordinate.A1).occupyingChessPiece = ChessPiece.BlackRook;
        spaces.at(ECoordinate.A2).occupyingChessPiece = ChessPiece.BlackKnight;
        spaces.at(ECoordinate.A3).occupyingChessPiece = ChessPiece.BlackBishop;
        spaces.at(ECoordinate.A4).occupyingChessPiece = ChessPiece.BlackQueen;
        spaces.at(ECoordinate.A5).occupyingChessPiece = ChessPiece.BlackKing;
        spaces.at(ECoordinate.A6).occupyingChessPiece = ChessPiece.BlackBishop;
        spaces.at(ECoordinate.A7).occupyingChessPiece = ChessPiece.BlackKnight;
        spaces.at(ECoordinate.A8).occupyingChessPiece = ChessPiece.BlackRook;

        spaces.at(ECoordinate.B1).occupyingChessPiece = ChessPiece.BlackPawn;
        spaces.at(ECoordinate.B2).occupyingChessPiece = ChessPiece.BlackPawn;
        spaces.at(ECoordinate.B3).occupyingChessPiece = ChessPiece.BlackPawn;
        spaces.at(ECoordinate.B4).occupyingChessPiece = ChessPiece.BlackPawn;
        spaces.at(ECoordinate.B5).occupyingChessPiece = ChessPiece.BlackPawn;
        spaces.at(ECoordinate.B6).occupyingChessPiece = ChessPiece.BlackPawn;
        spaces.at(ECoordinate.B7).occupyingChessPiece = ChessPiece.BlackPawn;
        spaces.at(ECoordinate.B8).occupyingChessPiece = ChessPiece.BlackPawn;

        spaces.at(ECoordinate.H1).occupyingChessPiece = ChessPiece.WhiteRook;
        spaces.at(ECoordinate.H2).occupyingChessPiece = ChessPiece.WhiteKnight;
        spaces.at(ECoordinate.H3).occupyingChessPiece = ChessPiece.WhiteBishop;
        spaces.at(ECoordinate.H4).occupyingChessPiece = ChessPiece.WhiteQueen;
        spaces.at(ECoordinate.H5).occupyingChessPiece = ChessPiece.WhiteKing;
        spaces.at(ECoordinate.H6).occupyingChessPiece = ChessPiece.WhiteBishop;
        spaces.at(ECoordinate.H7).occupyingChessPiece = ChessPiece.WhiteKnight;
        spaces.at(ECoordinate.H8).occupyingChessPiece = ChessPiece.WhiteRook;

        spaces.at(ECoordinate.G1).occupyingChessPiece = ChessPiece.WhitePawn;
        spaces.at(ECoordinate.G2).occupyingChessPiece = ChessPiece.WhitePawn;
        spaces.at(ECoordinate.G3).occupyingChessPiece = ChessPiece.WhitePawn;
        spaces.at(ECoordinate.G4).occupyingChessPiece = ChessPiece.WhitePawn;
        spaces.at(ECoordinate.G5).occupyingChessPiece = ChessPiece.WhitePawn;
        spaces.at(ECoordinate.G6).occupyingChessPiece = ChessPiece.WhitePawn;
        spaces.at(ECoordinate.G7).occupyingChessPiece = ChessPiece.WhitePawn;
        spaces.at(ECoordinate.G8).occupyingChessPiece = ChessPiece.WhitePawn;
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
