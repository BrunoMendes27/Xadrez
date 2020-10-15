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
public class Pawn extends ChessPiece{
 
    private Chessmatch chessmatch;
    public Pawn(Board board,Cor cor,Chessmatch chessmatch){
    super(board,cor);
    this.chessmatch=chessmatch;
    }
    
    @Override
    public boolean[][] possibleMoves(){
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position p = new Position(0,0);
    
    if (getColor() == Cor.WHITE) {
			p.setValues(position.getrow() - 1, position.getcolumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getrow()][p.getcolumn()] = true;
			}
			p.setValues(position.getrow() - 2, position.getcolumn());
			Position p2 = new Position(position.getrow() - 1, position.getcolumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getrow()][p.getcolumn()] = true;
			}
			p.setValues(position.getrow() - 1, position.getcolumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getrow()][p.getcolumn()] = true;
			}			
			p.setValues(position.getrow() - 1, position.getcolumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getrow()][p.getcolumn()] = true;
			}
                        
                        // enPassant
                        if(position.getrow() ==3){
                            Position left = new Position(position.getrow(),position.getcolumn()-1);
                            if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessmatch.getenPassantVulnerable()){
                            mat[left.getrow()-1][left.getcolumn()]=true;
                            }
                         Position right = new Position(position.getrow(),position.getcolumn()+1);
                            if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessmatch.getenPassantVulnerable()){
                            mat[right.getrow()-1][right.getcolumn()]=true;
                            }
                        }
                        
		}
		else {
			p.setValues(position.getrow() + 1, position.getcolumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getrow()][p.getcolumn()] = true;
			}
			p.setValues(position.getrow() + 2, position.getcolumn());
			Position p2 = new Position(position.getrow() + 1, position.getcolumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getrow()][p.getcolumn()] = true;
			}
			p.setValues(position.getrow() + 1, position.getcolumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getrow()][p.getcolumn()] = true;
			}			
			p.setValues(position.getrow() + 1, position.getcolumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getrow()][p.getcolumn()] = true;
			}
                        // enPassant black
                        if(position.getrow() ==4){
                            Position left = new Position(position.getrow(),position.getcolumn()-1);
                            if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessmatch.getenPassantVulnerable()){
                            mat[left.getrow()+1][left.getcolumn()]=true;
                            }
                         Position right = new Position(position.getrow(),position.getcolumn()+1);
                            if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessmatch.getenPassantVulnerable()){
                            mat[right.getrow()+1][right.getcolumn()]=true;
                            }
                        }
		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
}
