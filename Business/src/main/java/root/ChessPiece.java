package root;

public enum ChessPiece {
    BlackRook(Type.Rook, Color.Black),
    BlackKnight(Type.Knight, Color.Black),
    BlackBishop(Type.Bishop, Color.Black),
    BlackQueen(Type.Queen, Color.Black),
    BlackKing(Type.King, Color.Black),
    BlackPawn(Type.Pawn, Color.Black),
    WhiteRook(Type.Rook, Color.White),
    WhiteKnight(Type.Knight, Color.White),
    WhiteBishop(Type.Bishop, Color.White),
    WhiteQueen(Type.Queen, Color.White),
    WhiteKing(Type.King, Color.White),
    WhitePawn(Type.Pawn, Color.White);

    public final Type type;
    public final Color color;

    ChessPiece(Type chessPieceType, Color chessPieceColor){
        this.type = chessPieceType;
        this.color = chessPieceColor;
    }

    public enum Color{
        Black,
        White;
    }

    public enum Type {
        Rook,
        Knight,
        Bishop,
        Queen,
        King,
        Pawn;
    }

}


