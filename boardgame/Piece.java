package boardgame;

/*
    Peça: Existe associação com tabuleiro 1:N
 */
public abstract class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;

    }

    //Usado somente na camada interna do boardGame
    protected Board getBoard() {
        return board;
    }

    /*
        Método para verificar os possiveis movimentos da peça
     */

    public abstract boolean[][] possibleMoves();


    /*
        Verifica se a peça pode ser movida para a posição informada
     */
    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    /*
        Verifica se existe pelo menos um movimento
     */

    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = possibleMoves();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
