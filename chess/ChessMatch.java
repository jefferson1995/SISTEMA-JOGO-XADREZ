package chess;

/*
    Partida jogo de xadrez.
    Nesta classe contém todas as regras da partida instanciada.
 */

import boardgame.Board;
import boardgame.Position;
import chess.chess.pieces.King;
import chess.chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
    }

    //Retorna uma matriz de peças de xadrez da partida atual.
    public ChessPiece[][] getPieces(){

        //Método para não acessar as peças internas e sim um ChessPiece.
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i=0; i< board.getRows(); i++){
           for(int j=0; j< board.getColumns(); j++){
               mat[i][j] = (ChessPiece) board.piece(i, j);
           }
        }
        return  mat;
    }

    /*
        Método responsável para iniciar a partida de xadrez e adicionar as peças.
     */
    private void initialSetup(){
        board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
        board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
        board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
    }
}
