package root;

public class SpaceStatus {
    public final ChessSpace space;
    public final ChessPiece occupyingChessPiece;



    public SpaceStatus(ChessSpace space) {
        this(space, space.initialChessPiece);
    }

    public SpaceStatus(ChessSpace space, ChessPiece occupyingChessPiece){
        this.space = space;
        this.occupyingChessPiece = occupyingChessPiece;
    }

    public SpaceStatus createNewEmptySpaceStatus() {
        return new SpaceStatus(space, null);
    }

    public SpaceStatus createNewSpaceStatus(ChessPiece movingChessPiece) {
        return new SpaceStatus(space, movingChessPiece);
    }
}
