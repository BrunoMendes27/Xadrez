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
}
