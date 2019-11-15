package root;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public enum ChessPiece {
    BlackRook1(Type.Rook, Color.Black),
    BlackRook2(Type.Rook, Color.Black),
    BlackKnight1(Type.Knight, Color.Black),
    BlackKnight2(Type.Knight, Color.Black),
    BlackBishop1(Type.Bishop, Color.Black),
    BlackBishop2(Type.Bishop, Color.Black),
    BlackQueen(Type.Queen, Color.Black),
    BlackKing(Type.King, Color.Black),
    BlackPawn1(Type.Pawn, Color.Black),
    BlackPawn2(Type.Pawn, Color.Black),
    BlackPawn3(Type.Pawn, Color.Black),
    BlackPawn4(Type.Pawn, Color.Black),
    BlackPawn5(Type.Pawn, Color.Black),
    BlackPawn6(Type.Pawn, Color.Black),
    BlackPawn7(Type.Pawn, Color.Black),
    BlackPawn8(Type.Pawn, Color.Black),

    WhiteRook1(Type.Rook, Color.White),
    WhiteRook2(Type.Rook, Color.White),
    WhiteKnight1(Type.Knight, Color.White),
    WhiteKnight2(Type.Knight, Color.White),
    WhiteBishop1(Type.Bishop, Color.White),
    WhiteBishop2(Type.Bishop, Color.White),
    WhiteQueen(Type.Queen, Color.White),
    WhiteKing(Type.King, Color.White),
    WhitePawn1(Type.Pawn, Color.White),
    WhitePawn2(Type.Pawn, Color.White),
    WhitePawn3(Type.Pawn, Color.White),
    WhitePawn4(Type.Pawn, Color.White),
    WhitePawn5(Type.Pawn, Color.White),
    WhitePawn6(Type.Pawn, Color.White),
    WhitePawn7(Type.Pawn, Color.White),
    WhitePawn8(Type.Pawn, Color.White);

    public final Type type;
    public final Color color;
    public Status status;
    public ChessBoard.Space space;

    ChessPiece(Type chessPieceType, Color chessPieceColor){
        this.type = chessPieceType;
        this.color = chessPieceColor;
    }

    List<ChessMove> getPossibleMoves(ChessGame chessGame){
        List<ChessMove> possibleMoves = type.getPossibleMoves(chessGame, space);
        return possibleMoves;
    }

    public enum Color{
        Black,
        White;
    }

    public enum Type {
        Rook{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                Point xyCoordinates = space.getXYCoordinates();
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.Right);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.Left);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.Up);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.Down);
                return possibleMoves;
            }
        },
        Knight{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                addPossibleRelativeMove(chessGame, space, possibleMoves, 1, 2);
                addPossibleRelativeMove(chessGame, space, possibleMoves, -1, 2);
                addPossibleRelativeMove(chessGame, space, possibleMoves, -2, 1);
                addPossibleRelativeMove(chessGame, space, possibleMoves, -2, -1);
                addPossibleRelativeMove(chessGame, space, possibleMoves, 1, -2);
                addPossibleRelativeMove(chessGame, space, possibleMoves, -1, -2);
                addPossibleRelativeMove(chessGame, space, possibleMoves, 2, 1);
                addPossibleRelativeMove(chessGame, space, possibleMoves, 2, -1);

                return possibleMoves;
            }
        },
        Bishop{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                Point xyCoordinates = space.getXYCoordinates();
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.UpRight);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.UpLeft);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.DownRight);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.DownLeft);
                return possibleMoves;
            }
        },
        Queen{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                Point xyCoordinates = space.getXYCoordinates();
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.Right);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.Left);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.Up);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.Down);

                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.UpRight);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.UpLeft);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.DownRight);
                addDirectionalMoves(chessGame, space, possibleMoves, ChessMove.Direction.DownLeft);
                return possibleMoves;
            }
        },
        King{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                addPossibleRelativeMove(chessGame, space, possibleMoves, 0, 1);
                addPossibleRelativeMove(chessGame, space, possibleMoves, 1, 1);
                addPossibleRelativeMove(chessGame, space, possibleMoves, 1, 0);
                addPossibleRelativeMove(chessGame, space, possibleMoves, 1, -1);

                addPossibleRelativeMove(chessGame, space, possibleMoves, 0, -1);
                addPossibleRelativeMove(chessGame, space, possibleMoves, -1, -1);
                addPossibleRelativeMove(chessGame, space, possibleMoves, -1, 0);
                addPossibleRelativeMove(chessGame, space, possibleMoves, -1, 1);
                return possibleMoves;
            }
        },
        Pawn{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();

                ChessBoard.Space leftSpace = space.getRelativeNeighbor(-1, 1);
                if (leftSpace != null && leftSpace.occupyingChessPiece != null && leftSpace.occupyingChessPiece.color != chessGame.offensivePlayer.color) possibleMoves.add(new ChessMove(space.coordinate, leftSpace.coordinate));

                ChessBoard.Space rightSpace = space.getRelativeNeighbor(1, 1);
                if (rightSpace != null && rightSpace.occupyingChessPiece != null && rightSpace.occupyingChessPiece.color != chessGame.offensivePlayer.color) possibleMoves.add(new ChessMove(space.coordinate, rightSpace.coordinate));

                ChessBoard.Space frontSpace = space.getRelativeNeighbor(0, 1);
                if (frontSpace != null && frontSpace.occupyingChessPiece == null) possibleMoves.add(new ChessMove(space.coordinate, frontSpace.coordinate));

                return possibleMoves;
            }
        };

        private static void addPossibleRelativeMove(ChessGame chessGame, ChessBoard.Space space, List<ChessMove> possibleMoves, int x, int y) {
            ChessBoard.Space relativeNeighbor = space.getRelativeNeighbor(x, y);
            if (relativeNeighbor == null) return;
            if (relativeNeighbor.occupyingChessPiece == null){
                possibleMoves.add(new ChessMove(space.coordinate, relativeNeighbor.coordinate));
            }else{
                if (relativeNeighbor.occupyingChessPiece.color != chessGame.offensivePlayer.color){
                    possibleMoves.add(new ChessMove(space.coordinate, relativeNeighbor.coordinate));
                }
            }
        }

        private static void addDirectionalMoves(ChessGame chessGame, ChessBoard.Space space, List<ChessMove> possibleMoves, ChessMove.Direction moveDirection) {
            Point xyCoordinates = space.getXYCoordinates();

            boolean done = false;
            ChessBoard.Space newSpace = space;
            while (!done){
                newSpace = newSpace.getNeighbor(moveDirection);
                if (newSpace == null){
                    done = true;
                }else {
                    if (newSpace.occupyingChessPiece == null) {
                        possibleMoves.add(new ChessMove(space.coordinate, newSpace.coordinate));
                    } else {
                        if (newSpace.occupyingChessPiece.color != chessGame.offensivePlayer.color) {
                            possibleMoves.add(new ChessMove(space.coordinate, newSpace.coordinate));
                        }
                        done = true;
                    }
                }
            }
        }

        public abstract List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space);
    }

    public enum Status{
        Active,
        Captured
    }

}


