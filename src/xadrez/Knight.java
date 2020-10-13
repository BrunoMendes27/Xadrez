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
public class Knight extends ChessPiece {
    
    public Knight(Board board, Cor color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "N";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		p.setValues(position.getrow() - 1, position.getcolumn() - 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		p.setValues(position.getrow() - 2, position.getcolumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		p.setValues(position.getrow() - 2, position.getcolumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		p.setValues(position.getrow() - 1, position.getcolumn() + 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		p.setValues(position.getrow() + 1, position.getcolumn() + 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		p.setValues(position.getrow() + 2, position.getcolumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		p.setValues(position.getrow() + 2, position.getcolumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		p.setValues(position.getrow() + 1, position.getcolumn() - 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		return mat;
	}
}
