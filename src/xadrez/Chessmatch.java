/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xadrez;

import java.awt.Color;

/**
 *
 * @author bbrun
 */
public class Chessmatch {
    private Board board;
    private int turn;
    private Cor currentPlayer;
    
    
    
    public Chessmatch(){
        turn = 1;
        currentPlayer = Cor.WHITE;
    board = new Board(8,8);
    initialSetup();
    }
    
    public int getTurn(){
    return turn;
    }
    
    public Cor getCurrentPlayer(){return currentPlayer;}
    
    public ChessPiece[][] getPieces(){
    ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
    for(int i=0;i<board.getRows();i++){
    for(int j=0;j<board.getColumns();j++){
    mat[i][j] = (ChessPiece) board.piece(i,j);
    }
    
    }
    return mat;
    }
    
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
    
    Position source = sourcePosition.toPosition();
    Position target = targetPosition.toPosition();
    validateSourcePosition(source);
    validateTargetPosition(source,target);
    Piece capturedPiece = makeMove(source,target);
    nextTurn();
    return (ChessPiece)capturedPiece;
    }
    
    private void validateTargetPosition(Position source,Position target){
    
    if(!board.piece(source).possibleMove(target)){
    
    throw new ChessException("A peca escolhida nao pode ser movida para o destino");
    }
    
    }
    
    
    
    private void validateSourcePosition(Position position){
    
    if(!board.thereIsAPiece(position)){
    throw new ChessException("There is no piece at this position");
    }
    
    if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
    throw new ChessException("A peça nao e sua!");
    }
    
    if(!board.piece(position).isThereAnyPossibleMove()){
    
    throw new ChessException("There is no possible moves");
    }
    
    
    
    }
    
    private Piece makeMove(Position source,Position target){
    Piece p= board.removePiece(source);
    Piece capturedPiece = board.removePiece(target);
    board.placePiece(p, target);
    
    return capturedPiece;
    }
    
    private void nextTurn(){
    turn++;
    
    currentPlayer = (currentPlayer == Cor.WHITE ) ? Cor.BLACK : Cor.WHITE;
    
    }
    
    private void placeNewPiece(char column,int row,ChessPiece piece){
    
    board.placePiece(piece,new ChessPosition(column,row).toPosition());
    }
    
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
    Position position = sourcePosition.toPosition();
    validateSourcePosition(position);
    return board.piece(position).possibleMoves();
    }
    
    
    private void initialSetup(){
    placeNewPiece('a', 1, new Rook(board, Cor.WHITE));
     //   placeNewPiece('b', 1, new Knight(board, Cor.WHITE));
       // placeNewPiece('c', 1, new Bishop(board, Cor.WHITE));
       // placeNewPiece('d', 1, new Queen(board, Cor.WHITE));
       // placeNewPiece('e', 1, new King(board, Cor.WHITE));
       // placeNewPiece('f', 1, new Bishop(board, Cor.WHITE));
       // placeNewPiece('g', 1, new Knight(board, Cor.WHITE));
        placeNewPiece('h', 1, new Rook(board, Cor.WHITE));
       // placeNewPiece('a', 2, new Pawn(board, Cor.WHITE, this));
       // placeNewPiece('b', 2, new Pawn(board, Cor.WHITE, this));
        //placeNewPiece('c', 2, new Pawn(board, Cor.WHITE, this));
        //placeNewPiece('d', 2, new Pawn(board, Cor.WHITE, this));
        //placeNewPiece('e', 2, new Pawn(board, Cor.WHITE, this));
        //placeNewPiece('f', 2, new Pawn(board, Cor.WHITE, this));
        //placeNewPiece('g', 2, new Pawn(board, Cor.WHITE, this));
        //placeNewPiece('h', 2, new Pawn(board, Cor.WHITE, this));

        placeNewPiece('a', 8, new Rook(board, Cor.BLACK));
        //placeNewPiece('b', 8, new Knight(board, Cor.BLACK));
        //placeNewPiece('c', 8, new Bishop(board, Cor.BLACK));
       // placeNewPiece('d', 8, new Queen(board, Cor.BLACK));
        placeNewPiece('e', 8, new King(board, Cor.BLACK));
      //  placeNewPiece('f', 8, new Bishop(board, Cor.BLACK));
       // placeNewPiece('g', 8, new Knight(board, Cor.BLACK));
        placeNewPiece('h', 8, new Rook(board, Cor.BLACK));
       // placeNewPiece('a', 7, new Pawn(board, Cor.BLACK, this));
       // placeNewPiece('b', 7, new Pawn(board, Cor.BLACK, this));
       // placeNewPiece('c', 7, new Pawn(board, Cor.BLACK, this));
       // placeNewPiece('d', 7, new Pawn(board, Cor.BLACK, this));
       // placeNewPiece('e', 7, new Pawn(board, Cor.BLACK, this));
        //placeNewPiece('f', 7, new Pawn(board, Cor.BLACK, this));
       // placeNewPiece('g', 7, new Pawn(board, Cor.BLACK, this));
       // placeNewPiece('h', 7, new Pawn(board, Cor.BLACK, this));
    }
    
}
