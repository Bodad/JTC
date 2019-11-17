package root;

import java.awt.*;
import java.util.Objects;

public class ChessMove {
    public final ChessPiece fromChessPiece;
    public final ChessBoard.Space from;
    public final ChessBoard.Space to;
    public final ChessPiece toChessPiece;
    public final ChessMove.Type type;
    public ChessPiece.Type toChessPieceType;
    public ChessPiece.Type fromChessPieceType;

    public ChessMove(ChessBoard.Space from, ChessBoard.Space to) {
        this(from, to, ChessMove.Type.Normal);
    }

    public ChessMove(ChessBoard.Space from, ChessBoard.Space to, ChessMove.Type type) {
        this.from = from;
        this.to = to;
        this.fromChessPiece = from.occupyingChessPiece;
        this.toChessPiece = to.occupyingChessPiece;
        this.fromChessPieceType = from.occupyingChessPiece.actAsType;
        this.toChessPieceType = to.occupyingChessPiece == null ? null : to.occupyingChessPiece.actAsType;
        this.type = type;
    }


    @Override
    public String toString() {
        if (toChessPiece  != null){
            return String.format("%s %s from %s to %s capturing %s %s %s",
                    fromChessPiece.color, fromChessPieceType, from.coordinate, to.coordinate, toChessPiece.color, toChessPieceType, type==Type.Normal ? "" : type);
        }else{
            return String.format("%s %s from %s to %s %s", fromChessPiece.color, fromChessPieceType, from.coordinate, to.coordinate, type==Type.Normal ? "" : type);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessMove chessMove = (ChessMove) o;
        return from.equals(chessMove.from) &&
                to.equals(chessMove.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    public boolean execute(ChessGame chessGame) {
        return this.type.executeMove(this, chessGame);
    }

    public static enum Type{
        Normal{
            @Override
            public boolean executeMove(ChessMove chessMove, ChessGame chessGame) {
                ChessPiece capturedChessPiece = captureChessPiece(chessGame, chessMove.to);

                chessMove.fromChessPiece.move(chessMove.from, chessMove.to);

                // Promote Pawn if appropriate
                if ((chessMove.fromChessPiece.actAsType == ChessPiece.Type.Pawn)
                        && (
                        (chessMove.to.coordinate.getXYCoordinates().y == 0 && chessMove.fromChessPiece.color == ChessPiece.Color.Black)  ||
                                (chessMove.to.coordinate.getXYCoordinates().y == 7 && chessMove.fromChessPiece.color == ChessPiece.Color.White)
                )
                ) {
                    chessMove.fromChessPiece.actAsType = ChessPiece.Type.Queen;
                }

                return capturedChessPiece == null ? false : (capturedChessPiece.type == ChessPiece.Type.King);
            }
        },
        EnPassant{
            @Override
            public boolean executeMove(ChessMove chessMove, ChessGame chessGame) {
                int yDirection = chessGame.offensivePlayer.color == ChessPiece.Color.Black ? -1 : 1;
                ChessBoard.Space captureSpace = chessMove.to.getRelativeNeighbor(0, yDirection);
                ChessPiece capturedChessPiece = captureChessPiece(chessGame, captureSpace);

                chessMove.fromChessPiece.move(chessMove.from, chessMove.to);

                return false;
            }
        },
        Castle{
            @Override
            public boolean executeMove(ChessMove chessMove, ChessGame chessGame) {
                // the "To" space is the final home of the king
                chessMove.fromChessPiece.move(chessMove.from, chessMove.to);
                ChessBoard.Space rookFromSpace = chessMove.to.getRelativeNeighbor(chessMove.to.getXYCoordinates().x == 1 ? -1 : 1, 0);
                ChessBoard.Space rookToSpace = chessMove.to.getRelativeNeighbor(chessMove.to.getXYCoordinates().x == 1 ? 1 : -1, 0);
                rookFromSpace.occupyingChessPiece.move(rookFromSpace, rookToSpace);
                return false;
            }
        };

        public abstract boolean executeMove(ChessMove chessMove, ChessGame chessGame);

        public ChessPiece captureChessPiece(ChessGame chessGame, ChessBoard.Space toSpace){
            ChessPiece capturedChessPiece = toSpace.occupyingChessPiece;

            if (capturedChessPiece != null) {
                capturedChessPiece.status = ChessPiece.Status.Captured;
                capturedChessPiece.space = null;
                chessGame.defensivePlayer.removePiece(capturedChessPiece);
            }

            return capturedChessPiece;
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
