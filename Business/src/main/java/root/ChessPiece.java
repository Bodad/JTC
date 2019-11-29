package root;

import java.util.ArrayList;
import java.util.HashMap;
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

    ChessPiece(Type chessPieceType, Color chessPieceColor) {
        this.type = chessPieceType;
        this.color = chessPieceColor;
    }


//    List<ChessMove> getPossibleMoves(GameState gameState) {
//        List<ChessMove> possibleMoves = chessPieceStatus.actAsType.getPossibleMoves(gameState, chessPieceStatus.space);
//        return possibleMoves;
//    }

//    public void move(ChessBoard.Space fromSpace, ChessBoard.Space toSpace) {
//            fromSpace.occupyingChessPiece = null;
//            toSpace.occupyingChessPiece = this;
//        chessPieceStatus.space = toSpace;
//        chessPieceStatus.numberOfMoves++;
//    }
//
//    public void reset(ChessBoard.Space space) {
//        chessPieceStatus.space = space;
//        chessPieceStatus.status = PlayStatus.Active;
//        chessPieceStatus.actAsType = this.type;
//        chessPieceStatus.numberOfMoves = 0;
// }

    public enum Color {
        Black,
        White;
    }

    public enum Type {
        Rook(525, 0, 0, 0, 0, 0, 0, 0, 0,
                5, 10, 10, 10, 10, 10, 10, 5,
                -5, 0, 0, 0, 0, 0, 0, -5,
                -5, 0, 0, 0, 0, 0, 0, -5,
                -5, 0, 0, 0, 0, 0, 0, -5,
                -5, 0, 0, 0, 0, 0, 0, -5,
                -5, 0, 0, 0, 0, 0, 0, -5,
                0, 0, 0, 5, 5, 0, 0, 0) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessBoard chessBoard, ChessSpace space) {
                List<ChessMove> possibleMoves = new ArrayList<>();

                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.Right);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.Left);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.Up);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.Down);
                return possibleMoves;
            }
        },
        Knight(350, -50, -40, -30, -30, -30, -30, -40, -50,
                -40, -20, 0, 0, 0, 0, -20, -40,
                -30, 0, 10, 15, 15, 10, 0, -30,
                -30, 5, 15, 20, 20, 15, 5, -30,
                -30, 0, 15, 20, 20, 15, 0, -30,
                -30, 5, 10, 15, 15, 10, 5, -30,
                -40, -20, 0, 5, 5, 0, -20, -40,
                -50, -40, -30, -30, -30, -30, -40, -50) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessBoard chessBoard, ChessSpace space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                addPossibleRelativeMove(chessBoard, space, possibleMoves, 1, 2);
                addPossibleRelativeMove(chessBoard, space, possibleMoves, -1, 2);
                addPossibleRelativeMove(chessBoard, space, possibleMoves, -2, 1);
                addPossibleRelativeMove(chessBoard, space, possibleMoves, -2, -1);
                addPossibleRelativeMove(chessBoard, space, possibleMoves, 1, -2);
                addPossibleRelativeMove(chessBoard, space, possibleMoves, -1, -2);
                addPossibleRelativeMove(chessBoard, space, possibleMoves, 2, 1);
                addPossibleRelativeMove(chessBoard, space, possibleMoves, 2, -1);

                return possibleMoves;
            }
        },
        Bishop(350, -20, -10, -10, -10, -10, -10, -10, -20,
                -10, 0, 0, 0, 0, 0, 0, -10,
                -10, 0, 5, 10, 10, 5, 0, -10,
                -10, 5, 5, 10, 10, 5, 5, -10,
                -10, 0, 10, 10, 10, 10, 0, -10,
                -10, 10, 10, 10, 10, 10, 10, -10,
                -10, 5, 0, 0, 0, 0, 5, -10,
                -20, -10, -10, -10, -10, -10, -10, -20) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessBoard chessBoard, ChessSpace space) {
                List<ChessMove> possibleMoves = new ArrayList<>();

                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.UpRight);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.UpLeft);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.DownRight);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.DownLeft);
                return possibleMoves;
            }
        },
        Queen(1000, -20, -10, -10, -5, -5, -10, -10, -20,
                -10, 0, 0, 0, 0, 0, 0, -10,
                -10, 0, 5, 5, 5, 5, 0, -10,
                -5, 0, 5, 5, 5, 5, 0, -5,
                0, 0, 5, 5, 5, 5, 0, -5,
                -10, 5, 5, 5, 5, 5, 0, -10,
                -10, 0, 5, 0, 0, 0, 0, -10,
                -20, -10, -10, -5, -5, -10, -10, -20) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessBoard chessBoard, ChessSpace space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.Right);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.Left);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.Up);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.Down);

                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.UpRight);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.UpLeft);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.DownRight);
                addDirectionalMoves(chessBoard, space, possibleMoves, ChessMove.Direction.DownLeft);
                return possibleMoves;
            }
        },
        King(10000) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessBoard chessBoard, ChessSpace startingSpace) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                addPossibleRelativeMove(chessBoard, startingSpace, possibleMoves, 0, 1);
                addPossibleRelativeMove(chessBoard, startingSpace, possibleMoves, 1, 1);
                addPossibleRelativeMove(chessBoard, startingSpace, possibleMoves, 1, 0);
                addPossibleRelativeMove(chessBoard, startingSpace, possibleMoves, 1, -1);

                addPossibleRelativeMove(chessBoard, startingSpace, possibleMoves, 0, -1);
                addPossibleRelativeMove(chessBoard, startingSpace, possibleMoves, -1, -1);
                addPossibleRelativeMove(chessBoard, startingSpace, possibleMoves, -1, 0);
                addPossibleRelativeMove(chessBoard, startingSpace, possibleMoves, -1, 1);

                // consider castle

                if (startingSpace.getOccupyingPiece(chessBoard).getStatus(chessBoard).numberOfMoves == 0) {

                    if (chessBoard.offensivePlayer.color == Color.Black) {

                        if (BlackRook1.getStatus(chessBoard).numberOfMoves == 0
                                && ChessSpace.B8.getOccupyingPiece(chessBoard) == null
                                && ChessSpace.C8.getOccupyingPiece(chessBoard) == null
                                && ChessSpace.D8.getOccupyingPiece(chessBoard) == null
                        ) {
                            possibleMoves.add(new ChessMove(chessBoard, startingSpace, ChessSpace.C8, ChessMove.Type.Castle));
                        }

                        if (BlackRook2.getStatus(chessBoard).numberOfMoves == 0
                                && ChessSpace.G8.getOccupyingPiece(chessBoard) == null
                                && ChessSpace.F8.getOccupyingPiece(chessBoard) == null
                        ) {
                            possibleMoves.add(new ChessMove(chessBoard, startingSpace, ChessSpace.G8, ChessMove.Type.Castle));
                        }

                    } else {

                        if (WhiteRook1.getStatus(chessBoard).numberOfMoves == 0
                                && ChessSpace.B1.getOccupyingPiece(chessBoard) == null
                                && ChessSpace.C1.getOccupyingPiece(chessBoard) == null
                                && ChessSpace.D1.getOccupyingPiece(chessBoard) == null
                        ) {
                            possibleMoves.add(new ChessMove(chessBoard, startingSpace, ChessSpace.C1, ChessMove.Type.Castle));
                        }

                        if (WhiteRook2.getStatus(chessBoard).numberOfMoves == 0
                                && ChessSpace.G1.getOccupyingPiece(chessBoard) == null
                                && ChessSpace.F1.getOccupyingPiece(chessBoard) == null
                        ) {
                            possibleMoves.add(new ChessMove(chessBoard, startingSpace, ChessSpace.G1, ChessMove.Type.Castle));
                        }

                    }

                }
                return possibleMoves;
            }
        },

        Pawn(100, 0, 0, 0, 0, 0, 0, 0, 0,
                50, 50, 50, 50, 50, 50, 50, 50,
                10, 10, 20, 30, 30, 20, 10, 10,
                5, 5, 10, 25, 25, 10, 5, 5,
                0, 0, 0, 20, 20, 0, 0, 0,
                5, -5, -10, 0, 0, -10, -5, 5,
                5, 10, 10, -20, -20, 10, 10, 5,
                0, 0, 0, 0, 0, 0, 0, 0) {
            @Override
            public List<ChessMove> getPossibleMoves(ChessBoard chessBoard, ChessSpace space) {
                List<ChessMove> possibleMoves = new ArrayList<>();
                SpaceStatus spaceStatus = chessBoard.getSpaceStatus(space);
                int yDirection = spaceStatus.occupyingChessPiece.color == Color.White ? 1 : -1;

                addPawnMoves(chessBoard, space, possibleMoves, yDirection);

                if (chessBoard.getPieceStatus(spaceStatus.occupyingChessPiece).isFirstMove()) {
                    ChessSpace space1 = space.getRelativeNeighbor(0, yDirection);
                    SpaceStatus space1Status = chessBoard.getSpaceStatus(space1);
                    if (space1Status.occupyingChessPiece == null) {
                        ChessSpace space2 = space.getRelativeNeighbor(0, yDirection * 2);
                        SpaceStatus space2Status = chessBoard.getSpaceStatus(space2);
                        if (space2Status.occupyingChessPiece == null) {
                            possibleMoves.add(new ChessMove(chessBoard, space, space2));
                        }
                    }
                }

                // en passant
                //TODO This is not right
                ChessMove previousMove = chessBoard.previousMove;
                if (previousMove != null && previousMove.fromChessPieceType == Type.Pawn
                        && previousMove.from.y == yDirection
                        && previousMove.to.y == yDirection + 2
                ) {
                    int opponentColumn = previousMove.from.x;
                    int myColumn = space.x;

                    if (((opponentColumn - myColumn) == 1) || ((opponentColumn - myColumn) == -1)) {
                        ChessSpace moveToSpace = previousMove.to.getRelativeNeighbor(0, yDirection);
                        possibleMoves.add(new ChessMove(chessBoard, space, moveToSpace, ChessMove.Type.EnPassant));
                    }

                }


                return possibleMoves;
            }

            private void addPawnMoves(ChessBoard chessBoard, ChessSpace
                    space, List<ChessMove> possibleMoves, int yDirection) {
                ChessSpace leftSpace = space.getRelativeNeighbor(-1, yDirection);
                SpaceStatus leftSpaceStatus = chessBoard.getSpaceStatus(leftSpace);
                if (leftSpace != null && leftSpaceStatus.occupyingChessPiece != null && leftSpaceStatus.occupyingChessPiece.color != chessBoard.offensivePlayer.color)
                    possibleMoves.add(new ChessMove(chessBoard, space, leftSpace));

                ChessSpace rightSpace = space.getRelativeNeighbor(1, yDirection);
                SpaceStatus rightSpaceStatus = chessBoard.getSpaceStatus(rightSpace);
                if (rightSpace != null && rightSpaceStatus.occupyingChessPiece != null && rightSpaceStatus.occupyingChessPiece.color != chessBoard.offensivePlayer.color)
                    possibleMoves.add(new ChessMove(chessBoard, space, rightSpace));

                ChessSpace frontSpace = space.getRelativeNeighbor(0, yDirection);
                SpaceStatus frontSpaceStatus = chessBoard.getSpaceStatus(frontSpace);
                if (frontSpace != null && frontSpaceStatus.occupyingChessPiece == null)
                    possibleMoves.add(new ChessMove(chessBoard, space, frontSpace));
            }
        };

        private static void addPossibleRelativeMove(ChessBoard chessBoard, ChessSpace space, List<ChessMove> possibleMoves, int x, int y) {
            ChessSpace relativeNeighbor = space.getRelativeNeighbor(x, y);
            if (relativeNeighbor == null) return;

            SpaceStatus relativeNeighborStatus = chessBoard.getSpaceStatus(relativeNeighbor);
            if (relativeNeighborStatus.occupyingChessPiece == null) {
                possibleMoves.add(new ChessMove(chessBoard, space, relativeNeighbor));
            } else {
                if (relativeNeighborStatus.occupyingChessPiece.color != chessBoard.offensivePlayer.color) {
                    possibleMoves.add(new ChessMove(chessBoard, space, relativeNeighbor));
                }
            }
        }

        private static void addDirectionalMoves(ChessBoard chessBoard, ChessSpace space, List<ChessMove> possibleMoves, ChessMove.Direction moveDirection) {
            boolean done = false;
            ChessSpace newSpace = space;

            while (!done) {
                newSpace = newSpace.getNeighbor(moveDirection);
                if (newSpace == null) {
                    done = true;
                } else {
                    SpaceStatus newSpaceStatus = chessBoard.getSpaceStatus(newSpace);
                    if (newSpaceStatus.occupyingChessPiece == null) {
                        possibleMoves.add(new ChessMove(chessBoard, space, newSpace));
                    } else {
                        if (newSpaceStatus.occupyingChessPiece.color != chessBoard.offensivePlayer.color) {
                            possibleMoves.add(new ChessMove(chessBoard, space, newSpace));
                        }
                        done = true;
                    }
                }
            }
        }


        public final int pointValue;
        public final HashMap<Color, int[][]> spaceStrengthOffsets;


        Type(int pointValue, int... intOffsets) {
            this.pointValue = pointValue;
            spaceStrengthOffsets = new HashMap<>();

            int blackStrengthArray[][] = new int[8][];
            int whiteStrengthArray[][] = new int[8][];
//            HashMap<ChessSpace, Integer> blackStrenghHashMap = new HashMap<>();
//            HashMap<ChessSpace, Integer> whiteStrenghHashMap = new HashMap<>();

            spaceStrengthOffsets.put(Color.Black, blackStrengthArray);
            spaceStrengthOffsets.put(Color.White, whiteStrengthArray);
            for (int i = 0; i < 8; i++) {
                whiteStrengthArray[i] = new int[8];
                blackStrengthArray[i] = new int[8];
            }

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    int value = 0;
                    if (intOffsets.length > i*8+j) {
                        value = intOffsets[i * 8 + j];
                    }

                    whiteStrengthArray[j][7 - i] = value;
                    blackStrengthArray[j][i] = value;
                }
            }
        }

        public abstract List<ChessMove> getPossibleMoves(ChessBoard chessBoard, ChessSpace space);

        public boolean matches(ChessPiece chessPiece) {
            if (chessPiece == null) return false;
            return chessPiece.type.equals(this);
        }

        public int evaluateStrength(ChessSpace currentSpace, Color color) {
            int spaceStrengthOffset = spaceStrengthOffsets.get(color)[currentSpace.y][currentSpace.x];
            return pointValue + spaceStrengthOffset;
        }
    }

    public ChessPieceStatus getStatus(ChessBoard chessBoard) {
        return chessBoard.getPieceStatus(this);
    }

    public enum PlayStatus {
        Active,
        Captured
    }

}




