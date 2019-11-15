package root;

import java.awt.*;

public class ChessMove {
    public final ChessPiece fromChessPiece;
    public final ChessBoard.Space from;
    public final ChessBoard.Space to;
    public final ChessPiece toChessPiece;

    public ChessMove(ChessBoard.Space from, ChessBoard.Space to) {
        this.from = from;
        this.to = to;
        this.fromChessPiece = from.occupyingChessPiece;
        this.toChessPiece = to.occupyingChessPiece;
    }

    @Override
    public String toString() {
        if (toChessPiece  != null){
            return String.format("%s %s from %s to %s capturing %s %s", fromChessPiece.color, fromChessPiece.type, from.coordinate, to.coordinate, toChessPiece.color, toChessPiece.type);
        }else{
            return String.format("%s %s from %s to %s", fromChessPiece.color, fromChessPiece.type, from.coordinate, to.coordinate);
        }
    }

    public enum Direction {
        Left {
            @Override
            public ChessBoard.Space getSpace(ChessBoard.Space space) {
                Point xyCoordinates = space.getXYCoordinates();
                if (xyCoordinates.x == 0) return null;
                return ChessBoard.spaces.at(ChessBoard.ECoordinate.get(xyCoordinates.x-1, xyCoordinates.y));
            }
        },
        Right {
            @Override
            public ChessBoard.Space getSpace(ChessBoard.Space space) {
                Point xyCoordinates = space.getXYCoordinates();
                if (xyCoordinates.x ==7) return null;
                return ChessBoard.spaces.at(ChessBoard.ECoordinate.get(xyCoordinates.x+1, xyCoordinates.y));
            }
        },
        Up {
            @Override
            public ChessBoard.Space getSpace(ChessBoard.Space space) {
                Point xyCoordinates = space.getXYCoordinates();
                if (xyCoordinates.y ==7) return null;
                return ChessBoard.spaces.at(ChessBoard.ECoordinate.get(xyCoordinates.x, xyCoordinates.y+1));
            }
        },
        Down {
            @Override
            public ChessBoard.Space getSpace(ChessBoard.Space space) {
                Point xyCoordinates = space.getXYCoordinates();
                if (xyCoordinates.y ==0) return null;
                return ChessBoard.spaces.at(ChessBoard.ECoordinate.get(xyCoordinates.x, xyCoordinates.y-1));
            }
        },
        UpRight {
            @Override
            public ChessBoard.Space getSpace(ChessBoard.Space space) {
                Point xyCoordinates = space.getXYCoordinates();
                if (xyCoordinates.x == 7 || xyCoordinates.y == 7) return null;
                return ChessBoard.spaces.at(ChessBoard.ECoordinate.get(xyCoordinates.x+1, xyCoordinates.y+1));
            }
        },
        UpLeft {
            @Override
            public ChessBoard.Space getSpace(ChessBoard.Space space) {
                Point xyCoordinates = space.getXYCoordinates();
                if (xyCoordinates.x == 0 || xyCoordinates.y == 7) return null;
                return ChessBoard.spaces.at(ChessBoard.ECoordinate.get(xyCoordinates.x-1, xyCoordinates.y+1));
            }
        },
        DownRight {
            @Override
            public ChessBoard.Space getSpace(ChessBoard.Space space) {
                Point xyCoordinates = space.getXYCoordinates();
                if (xyCoordinates.x == 7 || xyCoordinates.y == 0) return null;
                return ChessBoard.spaces.at(ChessBoard.ECoordinate.get(xyCoordinates.x+1, xyCoordinates.y-1));
            }
        },
        DownLeft {
            @Override
            public ChessBoard.Space getSpace(ChessBoard.Space space) {
                Point xyCoordinates = space.getXYCoordinates();
                if (xyCoordinates.x == 0 || xyCoordinates.y == 0) return null;
                return ChessBoard.spaces.at(ChessBoard.ECoordinate.get(xyCoordinates.x-1, xyCoordinates.y-1));
            }
        };

        public abstract ChessBoard.Space getSpace(ChessBoard.Space space);
    }
}
