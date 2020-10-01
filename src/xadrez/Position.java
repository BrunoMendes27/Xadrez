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
public class Position {
    private int row;
    private int column;
    
    public Position(int row,int column){
    this.row=row;
    this.column=column;
    }
    
    public int getrow(){return row;}
    
     public void setrow(int row){
    this.row=row;
    }
    public void setcolumn(int column){
    this.column=column;
    }
    
    public int getcolumn(){return column;}
   
    @Override
    public String toString(){
    return row + " , " + column;
    }
    
}
