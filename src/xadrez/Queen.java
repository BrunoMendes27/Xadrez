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
public class Queen extends ChessPiece {

	public Queen(Board board, Cor color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// above
		p.setValues(position.getrow() - 1, position.getcolumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
			p.setrow(p.getrow() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		// left
		p.setValues(position.getrow(), position.getcolumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
			p.setcolumn(p.getcolumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		// right
		p.setValues(position.getrow(), position.getcolumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
			p.setcolumn(p.getcolumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

		// below
		p.setValues(position.getrow() + 1, position.getcolumn());
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
			p.setrow(p.getrow() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getrow()][p.getcolumn()] = true;
		}

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
