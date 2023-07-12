package chess;

//Peça do xadrez

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
    private Color color;
    private int moveCount;

    public ChessPiece(Board board, Color color) {
        super(board); //Repassa para o contrutor da super classe.
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCout(){
        return moveCount;
    }

    public void increaseMoveCount(){
        moveCount++;
    }

    public void decreaseMoveCount(){
        moveCount--;
    }


    /*
        Verifica se existe uma peça adversária
     */

    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);
    }
    protected boolean isThereOpponentPiece(Position position){
       ChessPiece p = (ChessPiece) getBoard().piece(position);
       return p != null && p.getColor() != color;
    }

}
