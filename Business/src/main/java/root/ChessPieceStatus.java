package root;

public class ChessPieceStatus {
    public final ChessPiece chessPiece;
    public final int numberOfMoves;
    public final ChessSpace currentSpace;
    public final ChessPiece.Type actAsType;
    public final ChessPiece.PlayStatus playStatus;
    public int strength;

    public ChessPieceStatus(ChessPiece chessPiece, ChessSpace currentSpace){
        this(chessPiece, currentSpace, chessPiece.type, 0, ChessPiece.PlayStatus.Active);
    }

    public ChessPieceStatus(ChessPiece chessPiece, ChessSpace currentSpace, ChessPiece.Type actAsType, int numberOfMoves, ChessPiece.PlayStatus playStatus){
        this.chessPiece = chessPiece;
        this.actAsType = actAsType;
        this.numberOfMoves = numberOfMoves;
        this.currentSpace = currentSpace;
        this.playStatus = playStatus;
    }

    public boolean isFirstMove() {
        return numberOfMoves == 0;
    }

    public ChessPieceStatus createNewCapturedStatus() {
        return new ChessPieceStatus(chessPiece, null, actAsType, numberOfMoves, ChessPiece.PlayStatus.Captured);
    }

    public ChessPieceStatus createNewSpaceStatus(ChessSpace newSpace, ChessPiece.Type newActAsType) {
        return new ChessPieceStatus(chessPiece, newSpace, newActAsType, numberOfMoves+1, playStatus);
    }

    public int evaluteStrength() {
        if (currentSpace == null) return 0;
        if (playStatus == ChessPiece.PlayStatus.Captured) return 0;

        return actAsType.evaluateStrength(currentSpace, chessPiece.color);
    }
}
