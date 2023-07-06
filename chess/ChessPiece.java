package chess;

//Peça do xadrez

import boardgame.Board;
import boardgame.Piece;

public abstract class ChessPiece extends Piece {
    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board); //Repassa para o contrutor da super classe.
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
