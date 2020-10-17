/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xadrez;

import java.awt.Color;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author bbrun
 */
public class Chessmatch {
    private Board board;
    private int turn;
    private Cor currentPlayer;
    private boolean check;
    private boolean checkmate;
    private ChessPiece enPassantVulnerable;
    private ChessPiece promoted;
    
    private List<Piece> pieceOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();
    
    public Chessmatch(){
        turn = 1;
        currentPlayer = Cor.WHITE;
    board = new Board(8,8);
    initialSetup();
    }
    
    public int getTurn(){
    return turn;
    }
    
    
    public ChessPiece getPromoted(){return promoted;}
    
    public boolean getCheck(){ return check;}
    
    public boolean getCheckmate(){ return checkmate;}
    
    public ChessPiece getenPassantVulnerable(){
        return enPassantVulnerable;}
    
    public Cor getCurrentPlayer(){return currentPlayer;}
    
    public ChessPiece[][] getPieces(){
    ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
    for(int i=0;i<board.getRows();i++){
    for(int j=0;j<board.getColumns();j++){
    mat[i][j] = (ChessPiece) board.piece(i,j);
    }
    
    }
    return mat;
    }
    
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
    
    Position source = sourcePosition.toPosition();
    Position target = targetPosition.toPosition();
    validateSourcePosition(source);
    validateTargetPosition(source,target);
    Piece capturedPiece = makeMove(source,target);
    
    if(testCheck(currentPlayer)){
    
    undoMove(source,target,capturedPiece);
    throw new ChessException("nao podes te meter em check");
    }
    
    ChessPiece movedPiece = (ChessPiece)board.piece(target);
    
    //special move promotion
    promoted=null;
    if(movedPiece instanceof Pawn){
    
    if((movedPiece.getColor() == Cor.WHITE && target.getrow() == 0) ||(movedPiece.getColor() == Cor.BLACK && target.getrow() == 7)){
    promoted = (ChessPiece)board.piece(target);
    promoted = replacePromotedPiece("Q");
    }
        
    }
    
    
    check= (testCheck(opponent(currentPlayer))) ? true : false;
    
    if(testCheckmate(opponent(currentPlayer))){
    checkmate = true;
    }else{
     nextTurn();
    }
    
   //enPassant
   if(movedPiece instanceof Pawn && (target.getrow() == source.getrow() -2 || target.getrow() == source.getrow() +2)){
   enPassantVulnerable = movedPiece;
   
   }else{
   enPassantVulnerable = null;
   }
    
    
    return (ChessPiece)capturedPiece;
    }
    
    
    public ChessPiece replacePromotedPiece(String type){
    
    if(promoted == null){
    throw new IllegalStateException("Nao ha peca para promover");
    }
        if(!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")){
        throw new InvalidParameterException("Tipo de peca invalido");
        }
        
        Position pos = promoted.getChessPosition().toPosition();
        Piece p = board.removePiece(pos);
        pieceOnTheBoard.remove(p);
        
        ChessPiece newPiece = newPiece(type,promoted.getColor());
        board.placePiece(newPiece, pos);
        pieceOnTheBoard.add(newPiece);
        
        return newPiece;
    }
    
    private ChessPiece newPiece(String type,Cor cor){
    
    if(type.equals("B")) return new Bishop(board,cor);
    if(type.equals("N")) return new Knight(board,cor);
    if(type.equals("Q")) return new Queen(board,cor);
    return new Rook(board,cor);
    }
    
    private void validateTargetPosition(Position source,Position target){
    
    if(!board.piece(source).possibleMove(target)){
    
    throw new ChessException("A peca escolhida nao pode ser movida para o destino");
    }
    
    }
    
    
    
    private void validateSourcePosition(Position position){
    
    if(!board.thereIsAPiece(position)){
    throw new ChessException("There is no piece at this position");
    }
    
    if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
    throw new ChessException("A peça nao e sua!");
    }
    
    if(!board.piece(position).isThereAnyPossibleMove()){
    
    throw new ChessException("There is no possible moves");
    }
    
    
    
    }
    
    
    private void undoMove(Position source, Position target,Piece capturedPiece){
    
    ChessPiece p = (ChessPiece)board.removePiece(target);
    p.decreaseMoveCount();
    board.placePiece(p, source);
    
    if(capturedPiece != null){
    board.placePiece(capturedPiece, target);
    capturedPieces.remove(capturedPiece);
    pieceOnTheBoard.add(capturedPiece);

    }
    
     if(p instanceof King && target.getcolumn() == source.getcolumn()+2){
    
        Position sourceT = new Position(source.getrow(),source.getcolumn()+3);
        Position targetT = new Position(target.getrow(),target.getcolumn()+1);
        
        ChessPiece rook = (ChessPiece)board.removePiece(targetT);
        board.placePiece(rook,sourceT);
        rook.decreaseMoveCount();
    
    }
    
     if(p instanceof King && target.getcolumn() == source.getcolumn()-2){
    
        Position sourceT = new Position(source.getrow(),source.getcolumn()-4);
        Position targetT = new Position(target.getrow(),target.getcolumn()-1);
        
        ChessPiece rook = (ChessPiece)board.removePiece(targetT);
        board.placePiece(rook,sourceT);
        rook.decreaseMoveCount();
    
    }
     
       //enPassant
     if(p instanceof Pawn){
     
     if(source.getcolumn() != target.getcolumn() && capturedPiece == enPassantVulnerable){
     ChessPiece pawn = (ChessPiece)board.removePiece(target);
     Position pawnPosition;
     if(p.getColor()== Cor.WHITE){
     
     pawnPosition = new Position(3,target.getcolumn());
     }else{
     pawnPosition = new Position(4,target.getcolumn());
     }
     board.placePiece(pawn, pawnPosition);
 
     }
         
     }
    
     
    }
    
    
    private Piece makeMove(Position source,Position target){
    ChessPiece p= (ChessPiece)board.removePiece(source);
    p.increaseMoveCount();
    Piece capturedPiece = board.removePiece(target);
    board.placePiece(p, target);
    
    if(capturedPiece != null){
    pieceOnTheBoard.remove(capturedPiece);
    capturedPieces.add(capturedPiece);
    
    }
    //special move castling kingside rook
    if(p instanceof King && target.getcolumn() == source.getcolumn()+2){
    
        Position sourceT = new Position(source.getrow(),source.getcolumn()+3);
        Position targetT = new Position(target.getrow(),target.getcolumn()+1);
        
        ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
        board.placePiece(rook,targetT);
        rook.increaseMoveCount();
    
    }
    
     if(p instanceof King && target.getcolumn() == source.getcolumn()-2){
    
        Position sourceT = new Position(source.getrow(),source.getcolumn()-4);
        Position targetT = new Position(target.getrow(),target.getcolumn()-1);
        
        ChessPiece rook = (ChessPiece)board.removePiece(sourceT);
        board.placePiece(rook,targetT);
        rook.increaseMoveCount();
    
    }
    
     //enPassant
     if(p instanceof Pawn){
     
     if(source.getcolumn() != target.getcolumn() && capturedPiece == null){
     
     Position pawnPosition;
     if(p.getColor()== Cor.WHITE){
     
     pawnPosition = new Position(target.getrow()+1,target.getcolumn());
     }else{
     pawnPosition = new Position(target.getrow() -1,target.getcolumn());
     }
     capturedPiece = board.removePiece(pawnPosition);
     capturedPieces.add(capturedPiece);
     pieceOnTheBoard.remove(capturedPiece);
     }
         
     }
     
     
    return capturedPiece;
    }
    
    private void nextTurn(){
    turn++;
    
    currentPlayer = (currentPlayer == Cor.WHITE ) ? Cor.BLACK : Cor.WHITE;
    
    }
    
    private Cor opponent(Cor cor){
    
    return (cor == Cor.WHITE) ? Cor.BLACK: Cor.WHITE;
    
    }
    
    
    private ChessPiece king(Cor cor){
    
    List<Piece> list = pieceOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == cor).collect(Collectors.toList());
    for(Piece p : list){
    
    if(p instanceof King){
    
    return (ChessPiece)p;
    }
    }
    
    throw new IllegalStateException("Nao existe " + cor + "rei no tabuleiro");
    
    }
    
    private boolean testCheckmate(Cor cor){
    
    if(!testCheck(cor)){return false;}
    //todas as peças com a mesma cor
    List<Piece> list =pieceOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == cor).collect(Collectors.toList());
    
    for(Piece p : list){
    
   boolean[][]mat= p.possibleMoves();
   for(int i=0;i<board.getRows();i++){
   for(int j=0;j<board.getColumns();j++){
   if(mat[i][j]){
   Position source = ((ChessPiece)p).getChessPosition().toPosition();
   Position target = new Position(i,j);
   Piece capturedPiece = makeMove(source,target);
   boolean testCheck = testCheck(cor);
   undoMove(source,target,capturedPiece);
   if(!testCheck){
   
   return false;
   }
   
   }
   }
   
   }
        
    }
    return true;
    }
    
    
    private boolean testCheck(Cor cor){
    
    Position kingPosition =king(cor).getChessPosition().toPosition();
    List<Piece> opponentPieces = pieceOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == opponent(cor)).collect(Collectors.toList());
    for(Piece p :opponentPieces){
    boolean[][] mat = p.possibleMoves();
    if(mat[kingPosition.getrow()][kingPosition.getcolumn()]){
    
    return true;
    }
    
    }
    return false;
    }
    
    
    
    
    
    private void placeNewPiece(char column,int row,ChessPiece piece){
    
    board.placePiece(piece,new ChessPosition(column,row).toPosition());
    pieceOnTheBoard.add(piece);
    
    
    }
    
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
    Position position = sourcePosition.toPosition();
    validateSourcePosition(position);
    return board.piece(position).possibleMoves();
    }
    
    
    private void initialSetup(){
        
        placeNewPiece('a', 1, new Rook(board, Cor.WHITE));
        placeNewPiece('b', 1, new Knight(board, Cor.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Cor.WHITE));
        placeNewPiece('d', 1, new Queen(board, Cor.WHITE));
        placeNewPiece('e', 1, new King(board, Cor.WHITE,this));
        placeNewPiece('f', 1, new Bishop(board, Cor.WHITE));
        placeNewPiece('g', 1, new Knight(board, Cor.WHITE));
        placeNewPiece('h', 1, new Rook(board, Cor.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Cor.WHITE,this));
        placeNewPiece('b', 2, new Pawn(board, Cor.WHITE,this));
        placeNewPiece('c', 2, new Pawn(board, Cor.WHITE,this));
        placeNewPiece('d', 2, new Pawn(board, Cor.WHITE,this));
        placeNewPiece('e', 2, new Pawn(board, Cor.WHITE,this));
        placeNewPiece('f', 2, new Pawn(board, Cor.WHITE,this));
        placeNewPiece('g', 2, new Pawn(board, Cor.WHITE,this));
        placeNewPiece('h', 2, new Pawn(board, Cor.WHITE,this));

        placeNewPiece('a', 8, new Rook(board, Cor.BLACK));
        placeNewPiece('b', 8, new Knight(board, Cor.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Cor.BLACK));
        placeNewPiece('d', 8, new Queen(board, Cor.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Cor.BLACK));
        placeNewPiece('g', 8, new Knight(board, Cor.BLACK));
        placeNewPiece('e', 8, new King(board, Cor.BLACK,this));
        placeNewPiece('h', 8, new Rook(board, Cor.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Cor.BLACK,this));
        placeNewPiece('b', 7, new Pawn(board, Cor.BLACK,this));
        placeNewPiece('c', 7, new Pawn(board, Cor.BLACK,this));
        placeNewPiece('d', 7, new Pawn(board, Cor.BLACK,this));
        placeNewPiece('e', 7, new Pawn(board, Cor.BLACK,this));
        placeNewPiece('f', 7, new Pawn(board, Cor.BLACK,this));
        placeNewPiece('g', 7, new Pawn(board, Cor.BLACK,this));
        placeNewPiece('h', 7, new Pawn(board, Cor.BLACK,this));
    }
    
}
