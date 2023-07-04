package boardgame;

/*
Classe que representa o tabuleiro
 */
public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces; //Matriz de peças

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns]; //Instanciada com a quantida de linhas e colunas.
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    //Retorna uma peça ao ser passado uma linha e uma coluna.
    public Piece piece(int row, int column) {
        return pieces[row][column];
    }

    //Sobrecarga do método acima
    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }

    /*
       Método para receber uma peça e adicionar na posição
     */
    public void placePiece(Piece piece, Position position) {
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }


}
