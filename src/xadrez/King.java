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
    public King(Board board,Cor color){
    super(board,color);
    }
    
    @Override
    public String toString(){
    return "K";
    }

    
    private boolean canMove(Position position){
    ChessPiece p = (ChessPiece)getBoard().piece(position);
    return p == null || p.getColor() != getColor();
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
       
       
       return mat;
    }
}
