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
public class Rook extends ChessPiece {
    
    public Rook(Board board,Cor color){
    super(board,color);
    
    }
    
    
    @Override
    public String toString(){
    return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
      
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        
        Position p = new Position(0,0);
        
        //cima
        p.setValues(position.getrow() - 1,position.getcolumn());
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
        mat[p.getrow()][p.getcolumn()] = true;
        p.setrow(p.getrow()-1);
        }
        
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
        mat[p.getrow()][p.getcolumn()] = true;
        
        }
        
        //esquerda
                p.setValues(position.getrow(),position.getcolumn()-1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
        mat[p.getrow()][p.getcolumn()] = true;
        p.setcolumn(p.getcolumn()-1);
        }
        
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
        mat[p.getrow()][p.getcolumn()] = true;
        
        }
        
        //direita
                 p.setValues(position.getrow(),position.getcolumn()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
        mat[p.getrow()][p.getcolumn()] = true;
        p.setcolumn(p.getcolumn()+1);
        }
        
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
        mat[p.getrow()][p.getcolumn()] = true;
        
        }
        
        //baixo
        
         p.setValues(position.getrow() + 1,position.getcolumn());
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
        mat[p.getrow()][p.getcolumn()] = true;
        p.setrow(p.getrow()+1);
        }
        
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
        mat[p.getrow()][p.getcolumn()] = true;
        
        }
        
        
        return mat;
    }
}
