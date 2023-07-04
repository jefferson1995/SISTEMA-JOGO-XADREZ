package chess;

import boardgame.Position;

/*
    Classe responsável para converter a posição de xadrez para uma posição de xadrez comum.
    Converte também de uma posição comum para de xadrez.
 */
public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            throw new ChessException("Erro para instanciar ChessPosition. Valores válidos são de a1 até h8");
        }
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    /*
        Método para converter de xadrez para posição comum.
        Para identificar a linha é feito = 8 - a linha do xadrez
        column será feito a subtração do unicode dos caracteres, exemplo:
        'a' - 'a' = 0
        'b' - 'a' = 1
        'c' - 'a' = 2
        Para encontrar será feito a coluna do xadrez(a,b,c) - 'a'
     */
    protected Position toPosition() {
        return new Position(8 - row, column - 'a');
    }

    //Método para converter da posição comum para uma posição de xadrez(Retorna a fórmula inversa).
    protected static ChessPosition fromPosition(Position position) {
        return new ChessPosition((char) ((char) 'a' - position.getColumn()), '8' - position.getRow());
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
