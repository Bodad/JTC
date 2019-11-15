package root;

import java.awt.*;

public class ChessMove {
    public final ChessBoard.ECoordinate from;
    public final ChessBoard.ECoordinate to;

    public ChessMove(ChessBoard.ECoordinate from, ChessBoard.ECoordinate to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("%s to %s", from, to);
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
