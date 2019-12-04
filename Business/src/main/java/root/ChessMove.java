package root;

import java.util.Objects;

import static root.ChessSpace.*;

public class ChessMove {
    public final ChessSpace from;
    public final ChessSpace to;
    public final ChessMove.Type type;
    public final ChessPiece capturedChessPiece;
    public final ChessPiece.Type capturedChessPieceType;
    public final ChessPiece fromChessPiece;
    public final ChessPiece.Type fromChessPieceType;
    public int finalBoardStrength;
    public int strength;

    public ChessMove(ChessBoard chessBoard, ChessSpace from, ChessSpace to) {
        this(chessBoard, from, to, ChessMove.Type.Normal);
    }

    public ChessMove(ChessBoard chessBoard, ChessSpace from, ChessSpace to, ChessMove.Type type) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.capturedChessPiece = to.getOccupyingPiece(chessBoard);
        this.capturedChessPieceType = capturedChessPiece == null ? null : capturedChessPiece.getStatus(chessBoard).actAsType;
        this.fromChessPiece = from.getOccupyingPiece(chessBoard);
        this.fromChessPieceType = fromChessPiece.getStatus(chessBoard).actAsType;
    }


    @Override
    public String toString() {
        if (capturedChessPiece != null) {
            return String.format("%s %s from %s to %s capturing %s %s %s (Strength: %d)",
                    fromChessPiece.color, fromChessPieceType, from, to, capturedChessPiece.color, capturedChessPieceType, type == Type.Normal ? "" : type, finalBoardStrength);
        } else {
            return String.format("%s %s from %s to %s %s (Strength: %d)", fromChessPiece.color, fromChessPieceType, from, to, type == Type.Normal ? "" : type, finalBoardStrength);
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

    public ChessBoard execute(ChessBoard chessBoard) {
        chessBoard.executedMove = this;
        ChessBoard newChessBoard = new ChessBoard(chessBoard);
        type.executeMove(this, newChessBoard);
        newChessBoard.evaluateStrength();
        strength = newChessBoard.getCurrentPlayerStrength() - chessBoard.getCurrentPlayerStrength();
        finalBoardStrength = newChessBoard.getCurrentPlayerStrength();
        return newChessBoard;
    }

    public static enum Type {
        Normal {
            @Override
            public ChessBoard executeMove(ChessMove chessMove, ChessBoard newChessBoard) {
                ChessPiece movingChessPiece = chessMove.fromChessPiece;
                ChessPieceStatus movingChessPieceStatus = movingChessPiece.getStatus(newChessBoard);
                ChessPiece.Type newActAsType = movingChessPieceStatus.actAsType;

                // Promote Pawn if appropriate
                if ((movingChessPieceStatus.actAsType == ChessPiece.Type.Pawn)
                        && (
                            (chessMove.to.y == 0 && movingChessPiece.color == ChessPiece.Color.Black) ||
                            (chessMove.to.y == 7 && movingChessPiece.color == ChessPiece.Color.White)
                        )
                ) {
                    newActAsType = ChessPiece.Type.Queen;
                }

                ChessPiece capturedChessPiece = newChessBoard.executeMove(chessMove.from, chessMove.to, newActAsType);

                if (ChessPiece.Type.King.matches(capturedChessPiece) ){
                    newChessBoard.winner = newChessBoard.offensivePlayer;
                }

                return newChessBoard;
            }
        },
        EnPassant {
            @Override
            public ChessBoard executeMove(ChessMove chessMove, ChessBoard newChessBoard) {
                int yDirection = newChessBoard.defensivePlayer.color == ChessPiece.Color.Black ? -1 : 1;
                ChessSpace captureSpace = chessMove.to.getRelativeNeighbor(0, yDirection);
                ChessPiece capturedChessPiece = newChessBoard.captureChessPieceFromSpace(captureSpace);

                newChessBoard.executeMove(chessMove.from, chessMove.to);

                return newChessBoard;
            }
        },
        Castle {
            @Override
            public ChessBoard executeMove(ChessMove chessMove, ChessBoard newChessBoard) {
                newChessBoard.executeMove(chessMove.from, chessMove.to);

                switch (chessMove.to) {
                    case C8:
                        newChessBoard.executeMove(A8, D8);
                        break;
                    case G8:
                        newChessBoard.executeMove(H8, F8);
                        break;
                    case C1:
                        newChessBoard.executeMove(A1, D1);
                        break;
                    case G1:
                        newChessBoard.executeMove(H1, F1);
                        break;
                }
                return newChessBoard;
            }
        };

        public abstract ChessBoard executeMove(ChessMove chessMove, ChessBoard chessBoard);

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
