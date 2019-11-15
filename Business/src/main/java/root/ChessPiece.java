package root;

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
        return type.getPossibleMoves(chessGame, space);
    }

    public enum Color{
        Black,
        White;
    }

    public enum Type {
        Rook{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                return null;
            }
        },
        Knight{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                return null;
            }
        },
        Bishop{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                return null;
            }
        },
        Queen{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                return null;
            }
        },
        King{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                return null;
            }
        },
        Pawn{
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space) {
                return null;
            }
        };

        public abstract List<ChessMove> getPossibleMoves(ChessGame chessGame, ChessBoard.Space space);
    }

    public enum Status{
        Active,
        Captured
    }

}


