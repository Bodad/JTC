package root;


import java.util.*;
import java.util.stream.Collectors;

import static root.ChessPiece.*;

public class ChessBoard {
    public ChessMove previousMove;
    public Player winner;

//    public static List<List<ChessSpace>> spaceGrid = new ArrayList<>();

    HashMap<ChessSpace, SpaceStatus> spaceStatusMap = new HashMap<>();
    HashMap<ChessPiece, ChessPieceStatus> chessPieceStatusMap = new HashMap<>();

    Player offensivePlayer;
    Player defensivePlayer;

    public ChessBoard(ChessBoard chessBoard) {
        this.offensivePlayer = chessBoard.offensivePlayer;
        this.defensivePlayer = chessBoard.defensivePlayer;

        this.spaceStatusMap = (HashMap<ChessSpace, SpaceStatus>) chessBoard.spaceStatusMap.clone();
        this.chessPieceStatusMap = (HashMap<ChessPiece, ChessPieceStatus>) chessBoard.chessPieceStatusMap.clone();

        winner = chessBoard.winner;
    }

    public ChessBoard(Player offensivePlayer, Player defensivePlayer) {
        this.offensivePlayer = offensivePlayer;
        this.defensivePlayer = defensivePlayer;

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

    public List<ChessMove> getAllPossibleMoves() {
        List<ChessMove> possibleMoves = chessPieceStatusMap.values().stream()
                .filter(chessPieceStatus -> chessPieceStatus.playStatus == PlayStatus.Active && chessPieceStatus.chessPiece.color == offensivePlayer.color)
                .flatMap(chessPieceStatus -> chessPieceStatus.actAsType.getPossibleMoves(this, chessPieceStatus.currentSpace).stream())
                .collect(Collectors.toList());

        return possibleMoves;
    }

    public void switchPlayers() {
        Player temp = offensivePlayer;
        offensivePlayer = defensivePlayer;
        defensivePlayer = temp;
    }

    public int evaluteStrength() {
        int strength = chessPieceStatusMap.values().stream()
                .filter(chessPieceStatus -> chessPieceStatus.playStatus == PlayStatus.Active && chessPieceStatus.chessPiece.color == offensivePlayer.color)
                .mapToInt(chessPieceStatus -> chessPieceStatus.evaluteStrength())
                .reduce(0, Integer::sum);

        return strength;
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
