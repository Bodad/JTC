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

    ChessPieceStatus chessPieceStatus = new ChessPieceStatus();

    ChessPiece(Type chessPieceType, Color chessPieceColor) {
        this.type = chessPieceType;
        this.color = chessPieceColor;
        this.chessPieceStatus.actAsType = chessPieceType;
    }

    boolean isFirstMove() {
        return chessPieceStatus.numberOfMoves == 0;
    }

    List<ChessMove> getPossibleMoves(ChessGame.GameState gameState) {
        List<ChessMove> possibleMoves = chessPieceStatus.actAsType.getPossibleMoves(gameState, chessPieceStatus.space);
        return possibleMoves;
    }

    public void move(ChessBoard.Space fromSpace, ChessBoard.Space toSpace) {
            fromSpace.occupyingChessPiece = null;
            toSpace.occupyingChessPiece = this;
        chessPieceStatus.space = toSpace;
        chessPieceStatus.numberOfMoves++;
    }

    public void reset(ChessBoard.Space space) {
        chessPieceStatus.space = space;
        chessPieceStatus.status = PlayStatus.Active;
        chessPieceStatus.actAsType = this.type;
        chessPieceStatus.numberOfMoves = 0;
 }

    public enum Color {
        Black,
        White;
    }

    public enum Type {
        Rook(525) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame.GameState gameState, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();

                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.Right);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.Left);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.Up);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.Down);
                return possibleMoves;
            }
        },
        Knight(350) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame.GameState gameState, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                addPossibleRelativeMove(gameState, space, possibleMoves, 1, 2);
                addPossibleRelativeMove(gameState, space, possibleMoves, -1, 2);
                addPossibleRelativeMove(gameState, space, possibleMoves, -2, 1);
                addPossibleRelativeMove(gameState, space, possibleMoves, -2, -1);
                addPossibleRelativeMove(gameState, space, possibleMoves, 1, -2);
                addPossibleRelativeMove(gameState, space, possibleMoves, -1, -2);
                addPossibleRelativeMove(gameState, space, possibleMoves, 2, 1);
                addPossibleRelativeMove(gameState, space, possibleMoves, 2, -1);

                return possibleMoves;
            }
        },
        Bishop(350) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame.GameState gameState, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();

                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.UpRight);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.UpLeft);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.DownRight);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.DownLeft);
                return possibleMoves;
            }
        },
        Queen(1000) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame.GameState gameState, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.Right);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.Left);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.Up);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.Down);

                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.UpRight);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.UpLeft);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.DownRight);
                addDirectionalMoves(gameState, space, possibleMoves, ChessMove.Direction.DownLeft);
                return possibleMoves;
            }
        },
        King(10000) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame.GameState gameState, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                addPossibleRelativeMove(gameState, space, possibleMoves, 0, 1);
                addPossibleRelativeMove(gameState, space, possibleMoves, 1, 1);
                addPossibleRelativeMove(gameState, space, possibleMoves, 1, 0);
                addPossibleRelativeMove(gameState, space, possibleMoves, 1, -1);

                addPossibleRelativeMove(gameState, space, possibleMoves, 0, -1);
                addPossibleRelativeMove(gameState, space, possibleMoves, -1, -1);
                addPossibleRelativeMove(gameState, space, possibleMoves, -1, 0);
                addPossibleRelativeMove(gameState, space, possibleMoves, -1, 1);

                // consider castle
                if (space.occupyingChessPiece.chessPieceStatus.numberOfMoves == 0) {
                    if (gameState.offensivePlayer.color == Color.Black) {
                        ChessPiece rookSpaceOccupant = ChessBoard.Space.A8.occupyingChessPiece;
                        if ((rookSpaceOccupant != null && rookSpaceOccupant.chessPieceStatus.numberOfMoves == 0)
                                && (ChessBoard.Space.B8.occupyingChessPiece == null)
                                && (ChessBoard.Space.C8.occupyingChessPiece == null)
                                && (ChessBoard.Space.D8.occupyingChessPiece == null)) {
                            possibleMoves.add(new ChessMove(space, ChessBoard.Space.C8, ChessMove.Type.Castle));
                        }

                        rookSpaceOccupant = ChessBoard.Space.H8.occupyingChessPiece;
                        if ((rookSpaceOccupant != null && rookSpaceOccupant.chessPieceStatus.numberOfMoves == 0)
                                && (ChessBoard.Space.G8.occupyingChessPiece == null)
                                && (ChessBoard.Space.F8.occupyingChessPiece == null)){
                            possibleMoves.add(new ChessMove(space, ChessBoard.Space.G8, ChessMove.Type.Castle));
                        }

                    } else {
                        ChessPiece rookSpaceOccupant = ChessBoard.Space.A1.occupyingChessPiece;
                        if ((rookSpaceOccupant != null && rookSpaceOccupant.chessPieceStatus.numberOfMoves == 0)
                                && (ChessBoard.Space.B1.occupyingChessPiece == null)
                                && (ChessBoard.Space.C1.occupyingChessPiece == null)
                                && (ChessBoard.Space.D1.occupyingChessPiece == null)) {
                            possibleMoves.add(new ChessMove(space, ChessBoard.Space.C1, ChessMove.Type.Castle));
                        }

                        rookSpaceOccupant = ChessBoard.Space.H1.occupyingChessPiece;
                        if ((rookSpaceOccupant != null && rookSpaceOccupant.chessPieceStatus.numberOfMoves == 0)
                                && (ChessBoard.Space.G1.occupyingChessPiece == null)
                                && (ChessBoard.Space.F1.occupyingChessPiece == null)){
                            possibleMoves.add(new ChessMove(space, ChessBoard.Space.G1, ChessMove.Type.Castle));
                        }
                    }
                }


                return possibleMoves;
            }
        },
        Pawn(100) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessGame.GameState gameState, ChessBoard.Space space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                int yDirection = space.occupyingChessPiece.color == Color.White ? 1 : -1;

                addPawnMoves(gameState, space, possibleMoves, yDirection);

                if (space.occupyingChessPiece.isFirstMove()) {
                    ChessBoard.Space space1 = space.getRelativeNeighbor(0, yDirection);
                    if (space1.occupyingChessPiece == null) {
                        ChessBoard.Space space2 = space.getRelativeNeighbor(0, yDirection * 2);
                        if (space2.occupyingChessPiece == null) {
                            possibleMoves.add(new ChessMove(space, space2));
                        }
                    }
                }

                // en passant
                //TODO This is not right
                ChessMove previousMove = gameState.chessMoves.size()==0 ? null : gameState.chessMoves.peek() ;
                if (previousMove != null && previousMove.fromChessPieceType == Type.Pawn
                        && previousMove.from.y == yDirection
                        && previousMove.to.y == yDirection + 2
                ) {
                    int opponentColumn = previousMove.from.x;
                    int myColumn = space.x;

                    if (((opponentColumn - myColumn) == 1) || ((opponentColumn - myColumn) == -1)) {
                        ChessBoard.Space moveToSpace = previousMove.to.getRelativeNeighbor(0, yDirection);
                        possibleMoves.add(new ChessMove(space, moveToSpace, ChessMove.Type.EnPassant));
                    }

                }


                return possibleMoves;
            }

            private void addPawnMoves(ChessGame.GameState gameState, ChessBoard.Space space, List<ChessMove> possibleMoves, int yDirection) {
                ChessBoard.Space leftSpace = space.getRelativeNeighbor(-1, yDirection);
                if (leftSpace != null && leftSpace.occupyingChessPiece != null && leftSpace.occupyingChessPiece.color != gameState.offensivePlayer.color)
                    possibleMoves.add(new ChessMove(space, leftSpace));

                ChessBoard.Space rightSpace = space.getRelativeNeighbor(1, yDirection);
                if (rightSpace != null && rightSpace.occupyingChessPiece != null && rightSpace.occupyingChessPiece.color != gameState.offensivePlayer.color)
                    possibleMoves.add(new ChessMove(space, rightSpace));

                ChessBoard.Space frontSpace = space.getRelativeNeighbor(0, yDirection);
                if (frontSpace != null && frontSpace.occupyingChessPiece == null)
                    possibleMoves.add(new ChessMove(space, frontSpace));
            }
        };

        private static void addPossibleRelativeMove(ChessGame.GameState gameState, ChessBoard.Space space, List<ChessMove> possibleMoves, int x, int y) {
            ChessBoard.Space relativeNeighbor = space.getRelativeNeighbor(x, y);
            if (relativeNeighbor == null) return;
            if (relativeNeighbor.occupyingChessPiece == null) {
                possibleMoves.add(new ChessMove(space, relativeNeighbor));
            } else {
                if (relativeNeighbor.occupyingChessPiece.color != gameState.offensivePlayer.color) {
                    possibleMoves.add(new ChessMove(space, relativeNeighbor));
                }
            }
        }

        private static void addDirectionalMoves(ChessGame.GameState gameState, ChessBoard.Space space, List<ChessMove> possibleMoves, ChessMove.Direction moveDirection) {
            boolean done = false;
            ChessBoard.Space newSpace = space;
            while (!done) {
                newSpace = newSpace.getNeighbor(moveDirection);
                if (newSpace == null) {
                    done = true;
                } else {
                    if (newSpace.occupyingChessPiece == null) {
                        possibleMoves.add(new ChessMove(space, newSpace));
                    } else {
                        if (newSpace.occupyingChessPiece.color != gameState.offensivePlayer.color) {
                            possibleMoves.add(new ChessMove(space, newSpace));
                        }
                        done = true;
                    }
                }
            }
        }


        public final int pointValue;

        Type(int pointValue){
            this.pointValue = pointValue;
        }

        public abstract List<ChessMove> getPossibleMoves(ChessGame.GameState gameState, ChessBoard.Space space);
    }

    public enum PlayStatus {
        Active,
        Captured
    }

    public class ChessPieceStatus{
        public PlayStatus status;
        public ChessBoard.Space space;
        public int numberOfMoves;
        public Type actAsType;
    }
}


