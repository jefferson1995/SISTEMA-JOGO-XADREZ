package chess;

/*
    Partida jogo de xadrez.
    Nesta classe contém todas as regras da partida instanciada.
 */

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;

        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck(){
        return check;
    }

    //Retorna uma matriz de peças de xadrez da partida atual.
    public ChessPiece[][] getPieces() {

        //Método para não acessar as peças internas e sim um ChessPiece.
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    /*
        Método para adicionar a cor azul nos movimentos possiveis
     */
    public boolean[][] possibleMove(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    /*
        Método para capturar a peça de origem e mover para outra posição
     */
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        //Converte para peça de xadrez
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validateSourcePosition(source); //Responsável para validar a posição de origem.

        validateTargetPosition(source, target); //Valida a posição de destino

        Piece capturedPiece = makeMove(source, target); //Responsável para movimentar a peça.

        if(testCheck(currentPlayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("Você não pode se colocar em check");
        }

        check = testCheck(opponent(currentPlayer)) ? true : false; //Verifica se o oponente ficou em check

        //Troca o jogador
        nextTurn();

        return (ChessPiece) capturedPiece;
    }

    /*
        Métodos auxiliares de performChessMove.
     */
    private void validateSourcePosition(Position position) {
        if (!board.thereIsPiece(position)) {
            throw new ChessException("Não existe peça na posição de origem");
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessException("A peça escolhida não é sua! ");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("Não existe movimento possível para peça escolhida");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("A peça escolhida não pode ser mover para posição de destino");
        }
    }

    /*
        Método para trocar o jogador
     */
    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }


    //Devolve a cor do oponente
    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    /*
        Lógica de check
     */
    private ChessPiece King(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }

        }
        throw new IllegalStateException("Não existe o rei " + color + " no tabuleiro!");
    }

    private boolean testCheck(Color color){
        Position kingPosition = King(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
        for(Piece p : opponentPieces){
            boolean[][] mat = p.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }
        }
        return false;
    }

    /*
        Método para fazer um movimento
     */
    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source); //Retira peça da posição de origem.
        Piece capturedPiece = board.removePiece(target); // Remove a possível peça que esteja na posição de destino.
        board.placePiece(p, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    /*
        Método para desfazer um movimento
     */
    private void undoMove(Position source, Position target, Piece capturedPiece) {
        Piece p = board.removePiece(target); //Remove a peça do destino
        board.placePiece(p, source); //Devolve para posiçao de origem

        //Caso a peça for capturada, volta para posição.
        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }


    /*
    Método para receber as coordenadas do xadrez
     */
    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition()); //Converte para posição de xadrez
        piecesOnTheBoard.add(piece);
    }

    /*
        Método responsável para iniciar a partida de xadrez e adicionar as peças.
     */
    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
}
