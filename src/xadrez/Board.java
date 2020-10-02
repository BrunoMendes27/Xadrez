/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xadrez;

/**
 *
 * @author bbrun
 */
public class Board {
    
    private int rows;
    private int columns;
    private Piece[][] pieces;
    
    public Board(int rows,int columns){
        if(rows <1 || columns <1){
        throw new BoardException("Erro a criar tabuleiro");
        }
    this.rows=rows;
    this.columns=columns;
    pieces = new Piece[rows][columns];
    }
    
    public int getRows(){
    return rows;
    }
    
    public int getColumns(){
    return columns;
    }
    
    public Piece piece(int row,int column){
    if(!positionExists(row,column)){
    throw new BoardException("Posicao invalida");
    }
    return pieces[row][column];
    }
    
    
    public Piece piece(Position position){
        if(!positionExists(position)){
    throw new BoardException("Posicao invalida");
    }
    return pieces[position.getrow()][position.getcolumn()];
    }
    
    public void placePiece(Piece piece,Position position){
        if(thereIsAPiece(position)){
        throw new BoardException("Ja a uma peca nessa posicao"+ position);
        }
        
    pieces[position.getrow()][position.getcolumn()] = piece;
    piece.position = position;
    }
    
    public Piece removePiece(Position position){
    if(!positionExists(position)){
     throw new BoardException("Posicao invalida");
    }
    if(piece(position)==null){
    return null;
    }
    
    Piece aux= piece(position);
    aux.position=null;
    pieces[position.getrow()][position.getcolumn()] = null;
    return aux;
    }
    
    
    
    
   public boolean positionExists(int row, int column){
   return row >= 0 && row < rows && column >=0 && column < columns;
    }
    
     boolean positionExists(Position position){
     return positionExists(position.getrow(),position.getcolumn());
     }
     
     public boolean thereIsAPiece(Position position){
      if(!positionExists(position)){
    throw new BoardException("Posicao invalida");
    }
      return piece(position) != null;
     }
}
