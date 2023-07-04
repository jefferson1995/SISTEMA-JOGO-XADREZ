package boardgame;

/*
Classe que representa o tabuleiro
 */
public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces; //Matriz de peças

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("Erro para criar tabuleiro: É preciso pelo menos 1 linha e 1 coluna. ");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns]; //Instanciada com a quantida de linhas e colunas.
    }

    public int getRows() {
        return rows;
    }


    public int getColumns() {
        return columns;
    }


    //Retorna uma peça ao ser passado uma linha e uma coluna.
    public Piece piece(int row, int column) {
        if(!positionExists(row, column)){
            throw new BoardException("Peça não encontrada no tabuleiro");
        }
        return pieces[row][column];
    }

    //Sobrecarga do método acima
    public Piece piece(Position position) {
        if(!positionExists(position)){
            throw new BoardException("Posição não encontrada no tabuleiro");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    /*
       Método para receber uma peça e adicionar na posição
     */
    public void placePiece(Piece piece, Position position) {
        if(thereIsPiece(position)){
            throw new BoardException("Já existe uma peça na posição: " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    //Método auxiliar para verificar linha e coluna.
    private boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    /*
        Método para verificar se existe uma posição no tabuleiro.
     */
    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    /*
        Método para verificar se existe uma peça na posição
     */
    public boolean thereIsPiece(Position position) {
        if(!positionExists(position)){
            throw new BoardException("Posição não encontrada no tabuleiro");
        }
        return piece(position) != null;
    }


}
