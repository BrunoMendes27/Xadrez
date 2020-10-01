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
public class ChessPiece extends Piece{
    private Cor color;
    
    
public    ChessPiece(Board board,Cor color){
    super(board);
    this.color=color;
    }

public Cor getColor(){
return color;
}

}
