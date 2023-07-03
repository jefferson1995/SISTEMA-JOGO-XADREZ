package boardgame;

/*
    Peça: Existe associação com tabuleiro 1:N
 */
public class Piece {

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

}
