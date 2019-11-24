package root;

import java.util.Objects;

import static root.ChessBoard.Space.*;
import static root.ChessPiece.*;

public class ChessMove {
    public final ChessPiece fromChessPiece;
    public final ChessBoard.Space from;
    public final ChessBoard.Space to;
    public final ChessPiece toChessPiece;
    public final ChessMove.Type type;
    public ChessPiece.Type toChessPieceType;
    public ChessPiece.Type fromChessPieceType;
    public ChessGame.GameState gameState;

    public ChessMove(ChessBoard.Space from, ChessBoard.Space to) {
        this(from, to, ChessMove.Type.Normal);
    }

    public ChessMove(ChessBoard.Space from, ChessBoard.Space to, ChessMove.Type type) {
        this.from = from;
        this.to = to;
        this.fromChessPiece = from.occupyingChessPiece;
        this.toChessPiece = to.occupyingChessPiece;
        this.fromChessPieceType = from.occupyingChessPiece.chessPieceStatus.actAsType;
        this.toChessPieceType = to.occupyingChessPiece == null ? null : to.occupyingChessPiece.chessPieceStatus.actAsType;
        this.type = type;
    }


    @Override
    public String toString() {
        if (toChessPiece != null) {
            return String.format("%s %s from %s to %s capturing %s %s %s",
                    fromChessPiece.color, fromChessPieceType, from, to, toChessPiece.color, toChessPieceType, type == Type.Normal ? "" : type);
        } else {
            return String.format("%s %s from %s to %s %s", fromChessPiece.color, fromChessPieceType, from, to, type == Type.Normal ? "" : type);
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

    public static enum Type {
        Normal {
            @Override
            public boolean executeMove(ChessMove chessMove, ChessGame chessGame) {
                ChessPiece capturedChessPiece = captureChessPiece(chessGame, chessMove.to);

                chessMove.fromChessPiece.move(chessMove.from, chessMove.to);

                // Promote Pawn if appropriate
                if ((chessMove.fromChessPiece.chessPieceStatus.actAsType == ChessPiece.Type.Pawn)
                        && (
                        (chessMove.to.y == 0 && chessMove.fromChessPiece.color == ChessPiece.Color.Black) ||
                                (chessMove.to.y == 7 && chessMove.fromChessPiece.color == ChessPiece.Color.White)
                )
                ) {
                    chessMove.fromChessPiece.chessPieceStatus.actAsType = ChessPiece.Type.Queen;
                }

                return capturedChessPiece == null ? false : (capturedChessPiece.type == ChessPiece.Type.King);
            }
        },
        EnPassant {
            @Override
            public boolean executeMove(ChessMove chessMove, ChessGame chessGame) {
                int yDirection = chessGame.gameState.offensivePlayer.color == ChessPiece.Color.Black ? -1 : 1;
                ChessBoard.Space captureSpace = chessMove.to.getRelativeNeighbor(0, yDirection);
                ChessPiece capturedChessPiece = captureChessPiece(chessGame, captureSpace);

                chessMove.fromChessPiece.move(chessMove.from, chessMove.to);

                return false;
            }
        },
        Castle {
            @Override
            public boolean executeMove(ChessMove chessMove, ChessGame chessGame) {
                // the "To" space is the final home of the king
                chessMove.fromChessPiece.move(chessMove.from, chessMove.to);

                switch (chessMove.to) {
                    case C8:
                        BlackRook1.move(A8, D8);
                        break;
                    case G8:
                        BlackRook2.move(H8, F8);
                        break;
                    case C1:
                        WhiteRook1.move(A1, D1);
                        break;
                    case G1:
                        WhiteRook2.move(H1, F1);
                        break;
                }
                return false;
            }
        };

        public abstract boolean executeMove(ChessMove chessMove, ChessGame chessGame);

        public ChessPiece captureChessPiece(ChessGame chessGame, ChessBoard.Space toSpace) {
            ChessPiece capturedChessPiece = toSpace.occupyingChessPiece;

            if (capturedChessPiece != null) {
                capturedChessPiece.chessPieceStatus.status = PlayStatus.Captured;
                capturedChessPiece.chessPieceStatus.space = null;
                chessGame.gameState.defensivePlayer.removePiece(capturedChessPiece);
            }

            return capturedChessPiece;
        }
    }


    public enum Direction {
        Left(-1, 0),
        Right(1, 0),
        Up(0, 1),
        Down(0, -1),
        UpRight(1, 1),
        UpLeft(-1, 1),
        DownRight(1, -1),
        DownLeft(-1, -1);

        public final int xOffset;
        public final int yOffset;

        Direction(int xOffset, int yOffset) {
            this.xOffset = xOffset;
            this.yOffset = yOffset;
        }

    }
}
