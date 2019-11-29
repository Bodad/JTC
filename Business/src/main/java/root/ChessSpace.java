package root;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static root.ChessPiece.*;

public enum ChessSpace {
    A1(0,0, WhiteRook1),
    A2(0,1, WhitePawn1),
    A3(0,2),
    A4(0,3),
    A5(0,4),
    A6(0,5),
    A7(0,6, BlackPawn1),
    A8(0,7, BlackRook1),
    B1(1,0, WhiteKnight1),
    B2(1,1, WhitePawn2),
    B3(1,2),
    B4(1,3),
    B5(1,4),
    B6(1,5),
    B7(1,6, BlackPawn2),
    B8(1,7, BlackKnight1),
    C1(2,0, WhiteBishop1),
    C2(2,1, WhitePawn3),
    C3(2,2),
    C4(2,3),
    C5(2,4),
    C6(2,5),
    C7(2,6, BlackPawn3),
    C8(2,7, BlackBishop1),
    D1(3,0, WhiteQueen),
    D2(3,1, WhitePawn4),
    D3(3,2),
    D4(3,3),
    D5(3,4),
    D6(3,5),
    D7(3,6, BlackPawn4),
    D8(3,7, BlackQueen),
    E1(4,0, WhiteKing),
    E2(4,1, WhitePawn5),
    E3(4,2),
    E4(4,3),
    E5(4,4),
    E6(4,5),
    E7(4,6, BlackPawn5),
    E8(4,7, BlackKing),
    F1(5,0, WhiteBishop2),
    F2(5,1, WhitePawn6),
    F3(5,2),
    F4(5,3),
    F5(5,4),
    F6(5,5),
    F7(5,6, BlackPawn6),
    F8(5,7, BlackBishop2),
    G1(6,0, WhiteKnight2),
    G2(6,1, WhitePawn7),
    G3(6,2),
    G4(6,3),
    G5(6,4),
    G6(6,5),
    G7(6,6, BlackPawn7),
    G8(6,7, BlackKnight2),
    H1(7,0, WhiteRook2),
    H2(7,1, WhitePawn8),
    H3(7,2),
    H4(7,3),
    H5(7,4),
    H6(7,5),
    H7(7,6, BlackPawn8),
    H8(7,7, BlackRook2);

    public final int x;
    public final int y;
    public final ChessPiece initialChessPiece;
//        public ChessPiece occupyingChessPiece;

    ChessSpace(int x, int y){
        this(x, y, null);
    }

    ChessSpace(int x, int y, ChessPiece initialChessPiece){
        this.x = x;
        this.y = y;
        this.initialChessPiece = initialChessPiece;
    }


//        public void reset() {
//            occupyingChessPiece = initialChessPiece;
//            if (occupyingChessPiece != null) {
//                occupyingChessPiece.reset(this);
//            }
//        }

//        public void clear() {
//            occupyingChessPiece = null;
//        }

    public ChessSpace getNeighbor(ChessMove.Direction moveDirection) {
        return getRelativeNeighbor(moveDirection.xOffset, moveDirection.yOffset);
    }

    public ChessSpace getRelativeNeighbor(int x, int y) {
        int newX = this.x+x;
        int newY = this.y+y;

        if (newX < 0 || newX > 7) return null;
        if (newY < 0 || newY > 7) return null;

        return at(newX, newY);
    }

    public ChessSpace at(int newX, int newY) {
        ChessSpace chessSpace = Arrays.stream(values()).filter(space -> space.x == newX && space.y == newY).findFirst().get();
        return chessSpace;
    }

    public boolean contains(ChessBoard chessBoard, Type type) {
        final ChessPiece occupyingChessPiece = chessBoard.getOccupyingPiece(this);
        if (occupyingChessPiece == null) return false;

        return occupyingChessPiece.getStatus(chessBoard).actAsType == type;
    }

    public ChessPiece getOccupyingPiece(ChessBoard chessBoard) {
        return chessBoard.getOccupyingPiece(this);
    }
}
