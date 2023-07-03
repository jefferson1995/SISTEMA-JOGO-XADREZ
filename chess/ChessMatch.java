package chess;

/*
    Partida jogo de xadrez.
    Nesta classe contém todas as regras da partida instanciada.
 */

import boardgame.Board;

public class ChessMatch {

    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
    }

    //Retorna uma matriz de peças de xadrez da partida atual.
    public ChessPiece[][] getPieces(){

        //Método para não acessar as peças internas e sim um ChessPiece
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i=0; i< board.getRows(); i++){
           for(int j=0; j< board.getColumns(); j++){
               mat[i][j] = (ChessPiece) board.piece(i, j);
           }
        }
        return  mat;
    }
}
