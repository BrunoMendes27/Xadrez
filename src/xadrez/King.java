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
public class King extends ChessPiece{
    
    private Chessmatch chessMatch;
    
    
    public King(Board board,Cor color,Chessmatch chessMatch){
    super(board,color);
    this.chessMatch = chessMatch;
    }
    
    @Override
    public String toString(){
    return "K";
    }

    
    private boolean canMove(Position position){
    ChessPiece p = (ChessPiece)getBoard().piece(position);
    return p == null || p.getColor() != getColor();
    }
    
    
    private boolean testRookCastling(Position position){
    
     ChessPiece p = (ChessPiece)getBoard().piece(position);
     
    return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
    }
    
    
    @Override
    public boolean[][] possibleMoves() {
       boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
       
       Position p = new Position(0,0);
       
       //cima
       p.setValues(position.getrow() -1, position.getcolumn());
       if(getBoard().positionExists(p) && canMove(p)){
       mat[p.getrow()][p.getcolumn()]=true;
       }
       
       //baixo
       p.setValues(position.getrow() + 1, position.getcolumn());
       if(getBoard().positionExists(p) && canMove(p)){
       mat[p.getrow()][p.getcolumn()]=true;
       }
       
       //esq
       p.setValues(position.getrow(), position.getcolumn()-1);
       if(getBoard().positionExists(p) && canMove(p)){
       mat[p.getrow()][p.getcolumn()]=true;
       }
       
       
       //dir
       p.setValues(position.getrow(), position.getcolumn()+1);
       if(getBoard().positionExists(p) && canMove(p)){
       mat[p.getrow()][p.getcolumn()]=true;
       }
       
       //nw
       p.setValues(position.getrow() -1, position.getcolumn() -1);
       if(getBoard().positionExists(p) && canMove(p)){
       mat[p.getrow()][p.getcolumn()]=true;
       }
       
       //ne
       p.setValues(position.getrow() -1, position.getcolumn()+1);
       if(getBoard().positionExists(p) && canMove(p)){
       mat[p.getrow()][p.getcolumn()]=true;
       }
       
       //sw
         p.setValues(position.getrow() +1, position.getcolumn()-1);
       if(getBoard().positionExists(p) && canMove(p)){
       mat[p.getrow()][p.getcolumn()]=true;
       }
       
       //se
         p.setValues(position.getrow() +1, position.getcolumn()+1);
       if(getBoard().positionExists(p) && canMove(p)){
       mat[p.getrow()][p.getcolumn()]=true;
       }
       
       // special move castling
       if(getMoveCount()==0 && !chessMatch.getCheck()){
       //castling kingside rook
       Position posT1 = new Position(position.getrow(), position.getcolumn()+3);
       if(testRookCastling(posT1)){
       
       Position p1 = new Position(position.getrow(), position.getcolumn()+1);
       Position p2 = new Position(position.getrow(), position.getcolumn()+2);
       
       if(getBoard().piece(p1)== null && getBoard().piece(p2) == null){
       mat[position.getrow()][position.getcolumn()+2]=true;
       
       }
       }
        //castling quuenside rook
       Position posT2 = new Position(position.getrow(), position.getcolumn()-4);
       if(testRookCastling(posT2)){
       
       Position p1 = new Position(position.getrow(), position.getcolumn()-1);
       Position p2 = new Position(position.getrow(), position.getcolumn()-2);
       Position p3 = new Position(position.getrow(), position.getcolumn()-3);
       
       if(getBoard().piece(p1)== null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
       mat[position.getrow()][position.getcolumn()-2]=true;
       
       }
       }
           
       }
       
       
       return mat;
    }
}
