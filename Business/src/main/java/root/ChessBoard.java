package root;


import java.util.*;
import java.util.stream.Collectors;

import static root.ChessPiece.*;

public class ChessBoard {
    public Player winner;

    public ChessMove enterMove;
    public ChessMove exitMove;

    public ChessMove preferredMove;
    public ChessMove expectedOpponentsMove;

    public Stack<ChessMove> previousMoves = new Stack<>();

    public List<ChessMove> possibleMoves = new ArrayList<>();

    final HashMap<ChessSpace, SpaceStatus> spaceStatusMap;
    final HashMap<ChessPiece, ChessPieceStatus> chessPieceStatusMap;

    public final Player offensivePlayer;
    public final Player defensivePlayer;

    public int whiteStrength;
    public int blackStrength;

    public void evaluateStrength(){
            chessPieceStatusMap.values().parallelStream().forEach(status->{
                status.strength = status.evaluteStrength();
            });

            whiteStrength = chessPieceStatusMap.values().stream()
                    .filter(chessPieceStatus -> chessPieceStatus.playStatus == PlayStatus.Active && chessPieceStatus.chessPiece.color == Color.White)
                    .mapToInt(chessPieceStatus -> chessPieceStatus.strength)
                    .reduce(0, Integer::sum);

            blackStrength = chessPieceStatusMap.values().stream()
                    .filter(chessPieceStatus -> chessPieceStatus.playStatus == PlayStatus.Active && chessPieceStatus.chessPiece.color == Color.Black)
                    .mapToInt(chessPieceStatus -> chessPieceStatus.strength)
                    .reduce(0, Integer::sum);

    }

    public ChessBoard(ChessBoard chessBoard) {
        this.offensivePlayer = chessBoard.defensivePlayer;
        this.defensivePlayer = chessBoard.offensivePlayer;

        this.spaceStatusMap = (HashMap<ChessSpace, SpaceStatus>) chessBoard.spaceStatusMap.clone();
        this.chessPieceStatusMap = (HashMap<ChessPiece, ChessPieceStatus>) chessBoard.chessPieceStatusMap.clone();

        winner = chessBoard.winner;
        this.previousMoves = (Stack<ChessMove>)chessBoard.previousMoves.clone();
        this.previousMoves.push(chessBoard.exitMove);
        this.enterMove = chessBoard.exitMove;
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

        return possibleMoves;
    }

    public List<ChessMove> getAllPossibleMoves() {
        return getAllPossibleMoves(offensivePlayer);
    }

    public int getOffensivePlayerStrength() {
        return offensivePlayer.color == Color.Black ? blackStrength : whiteStrength;
    }

    public int getDefensivePlayerStrength() {
        return defensivePlayer.color == Color.Black ? blackStrength : whiteStrength;
    }


    public boolean isSpaceAttackableByOpponent(ChessSpace chessSpace) {
        return getAllPossibleMoves(defensivePlayer).stream().filter(move->move.to == chessSpace).findFirst().isPresent();
    }

    @Override
    public String toString() {
        return String.format("Enter Move: %s,  Preferred Move: %s, White: %d, Black: %d, Black Advantage: %d",
                enterMove, preferredMove, this.whiteStrength, this.blackStrength, this.blackStrength - this.whiteStrength);
    }


    public int getColorAdvantage(Color playerColor) {
        return playerColor == Color.Black ? blackStrength-whiteStrength : whiteStrength-blackStrength;
    }
}
