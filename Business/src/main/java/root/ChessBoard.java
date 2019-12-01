package root;


import java.util.*;
import java.util.stream.Collectors;

import static root.ChessPiece.*;

public class ChessBoard {
    public Player winner;

    public ChessMove preferredMove;
    public ChessMove expectedOpponentsMove;
    public ChessMove executedMove;
    public Stack<ChessMove> previousMoves = new Stack<>();

    public List<ChessMove> possibleMoves = new ArrayList<>();

//    public static List<List<ChessSpace>> spaceGrid = new ArrayList<>();

    final HashMap<ChessSpace, SpaceStatus> spaceStatusMap;
    final HashMap<ChessPiece, ChessPieceStatus> chessPieceStatusMap;

    final Player offensivePlayer;
    final Player defensivePlayer;

    public int myStrength;
    public int opponentStrength;
    public int overallStrength;

    public int evaluateStrength(){
            chessPieceStatusMap.values().parallelStream().forEach(status->{
                status.strength = status.evaluteStrength();
            });

            myStrength = chessPieceStatusMap.values().stream()
                    .filter(chessPieceStatus -> chessPieceStatus.playStatus == PlayStatus.Active && chessPieceStatus.chessPiece.color == defensivePlayer.color)
                    .mapToInt(chessPieceStatus -> chessPieceStatus.strength)
                    .reduce(0, Integer::sum);

            opponentStrength = chessPieceStatusMap.values().stream()
                    .filter(chessPieceStatus -> chessPieceStatus.playStatus == PlayStatus.Active && chessPieceStatus.chessPiece.color == offensivePlayer.color)
                    .mapToInt(chessPieceStatus -> chessPieceStatus.strength)
                    .reduce(0, Integer::sum);

            overallStrength = myStrength - opponentStrength;

            return overallStrength;
    }

    public ChessBoard(ChessBoard chessBoard) {
        this.offensivePlayer = chessBoard.defensivePlayer;
        this.defensivePlayer = chessBoard.offensivePlayer;

        this.spaceStatusMap = (HashMap<ChessSpace, SpaceStatus>) chessBoard.spaceStatusMap.clone();
        this.chessPieceStatusMap = (HashMap<ChessPiece, ChessPieceStatus>) chessBoard.chessPieceStatusMap.clone();

        winner = chessBoard.winner;
        this.previousMoves = (Stack<ChessMove>)chessBoard.previousMoves.clone();
        this.previousMoves.push(chessBoard.executedMove);
    }

    public ChessBoard(Player offensivePlayer, Player defensivePlayer) {
        this.offensivePlayer = offensivePlayer;
        this.defensivePlayer = defensivePlayer;

        this.spaceStatusMap = new HashMap<>();
        this.chessPieceStatusMap = new HashMap<>();

        spaceStatusMap.clear();
        for (ChessSpace space : ChessSpace.values()) {
            spaceStatusMap.put(space, new SpaceStatus(space));
        }

        chessPieceStatusMap.clear();
        for (ChessSpace space : ChessSpace.values()) {
            if (space.initialChessPiece != null) {
                ChessPieceStatus chessPieceStatus = new ChessPieceStatus(space.initialChessPiece, space);
                chessPieceStatusMap.put(space.initialChessPiece, chessPieceStatus);
            }
        }
        evaluateStrength();
    }


    public ChessPiece getOccupyingPiece(ChessSpace space) {
        return spaceStatusMap.get(space).occupyingChessPiece;
    }

    public SpaceStatus getSpaceStatus(ChessSpace space) {
        return spaceStatusMap.get(space);
    }

    public ChessPieceStatus getPieceStatus(ChessPiece chessPiece) {
        return chessPieceStatusMap.get(chessPiece);
    }

    public ChessPiece executeMove(ChessSpace from, ChessSpace to) {
        return executeMove(from, to, from.getOccupyingPiece(this).getStatus(this).actAsType);
    }

    public ChessPiece executeMove(ChessSpace from, ChessSpace to, ChessPiece.Type actingType) {
        SpaceStatus fromSpaceStatus = getSpaceStatus(from);
        SpaceStatus toSpaceStatus = getSpaceStatus(to);

        ChessPiece movingChessPiece = fromSpaceStatus.occupyingChessPiece;
        ChessPiece capturedChessPiece = toSpaceStatus.occupyingChessPiece;

        captureChessPiece(capturedChessPiece);

        ChessPieceStatus newMovingPieceStatus = getPieceStatus(movingChessPiece).createNewSpaceStatus(to, actingType);
        chessPieceStatusMap.put(movingChessPiece, newMovingPieceStatus);

        SpaceStatus newFromSpaceStatus = fromSpaceStatus.createNewEmptySpaceStatus();
        spaceStatusMap.put(from, newFromSpaceStatus);

        SpaceStatus newToSpaceStatus = toSpaceStatus.createNewSpaceStatus(movingChessPiece);
        spaceStatusMap.put(to, newToSpaceStatus);

        return capturedChessPiece;
    }

    public ChessPiece captureChessPieceFromSpace(ChessSpace space) {
        ChessPiece capturedChessPiece = getOccupyingPiece(space);
        spaceStatusMap.put(space, getSpaceStatus(space).createNewEmptySpaceStatus());
        return captureChessPiece(capturedChessPiece);
    }

    public ChessPiece captureChessPiece(ChessPiece capturedChessPiece) {
        if (capturedChessPiece != null) {
            ChessPieceStatus previousCapturedPieceStatus = getPieceStatus(capturedChessPiece);
            ChessPieceStatus newCapturedPieceStatus = previousCapturedPieceStatus.createNewCapturedStatus();
            chessPieceStatusMap.put(capturedChessPiece, newCapturedPieceStatus);
        }
        return capturedChessPiece;
    }

    public List<ChessMove> getAllPossibleMoves(Player player){
        possibleMoves = chessPieceStatusMap.values().stream()
                .filter(chessPieceStatus -> chessPieceStatus.playStatus == PlayStatus.Active && chessPieceStatus.chessPiece.color == player.color)
                .flatMap(chessPieceStatus -> chessPieceStatus.actAsType.getPossibleMoves(this, chessPieceStatus.currentSpace).stream())
                .collect(Collectors.toList());

//
//        if (player == offensivePlayer) {
//            possibleMoves = possibleMoves.stream().filter(m-> (m.fromChessPieceType!= Type.King) || ((m.fromChessPieceType == Type.King) && (isSpaceAttackableByOpponent(m.to)==false))).collect(Collectors.toList());
//
//
//            if (player.color == Color.White) {
//                possibleMoves = possibleMoves.stream().filter(move -> isSpaceAttackableByOpponent(WhiteKing.getStatus(this).currentSpace) == false).collect(Collectors.toList());
//            } else {
//                possibleMoves = possibleMoves.stream().filter(move -> isSpaceAttackableByOpponent(BlackKing.getStatus(this).currentSpace) == false).collect(Collectors.toList());
//            }
//        }

        return possibleMoves;
    }

    public List<ChessMove> getAllPossibleMoves() {
        return getAllPossibleMoves(offensivePlayer);
    }

    @Override
    public String toString() {
        return String.format("Current Player: %s.  Expected Move: %s,  Opponent Expected Move: %s, Strength: %d", offensivePlayer.name, preferredMove, expectedOpponentsMove, overallStrength);
    }

    public boolean isSpaceAttackableByOpponent(ChessSpace chessSpace) {
        return getAllPossibleMoves(defensivePlayer).stream().filter(move->move.to == chessSpace).findFirst().isPresent();
    }

    //    static {
//        spaceGrid = new ArrayList<>();
//
//        for (int i = 0; i < 8; i++) {
//            spaceGrid.add(new ArrayList<>());
//        }
//
//        EnumSet<ChessSpace> enumSet = EnumSet.allOf(ChessSpace.class);
//        enumSet.stream().forEach(space -> spaceGrid.get(space.x).add(space));
//    }


}
