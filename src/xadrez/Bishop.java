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
public class Bishop extends ChessPiece{
    
    public Bishop(Board board, Cor cor){
    super(board,cor);
    }
    
    @Override
    public String toString(){
    return "B";
    }
    
    @Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// nw
		p.setValues(position.getrow() - 1, position.getcolumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
			p.setValues(p.getrow() - 1, p.getcolumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		// ne
		p.setValues(position.getrow() - 1, position.getcolumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
			p.setValues(p.getrow() - 1, p.getcolumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		// se
		p.setValues(position.getrow() + 1, position.getcolumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
			p.setValues(p.getrow() + 1, p.getcolumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		// sw
		p.setValues(position.getrow() + 1, position.getcolumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
			p.setValues(p.getrow() + 1, p.getcolumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		return mat;
	}
    
    
}
