/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xadrez;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author bbrun
 */
public class Xadrez {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Chessmatch chessMatch = new Chessmatch();
        List<ChessPiece> captured = new ArrayList<>();
        
        while(!chessMatch.getCheckmate()){
            try{
            UI.clearScreen();
        UI.printMatch(chessMatch,captured);
        System.out.println();
        System.out.print("Source: ");
        ChessPosition source = UI.readChessPosition(sc);
        
        boolean[][] possibleMoves = chessMatch.possibleMoves(source);
        UI.clearScreen();
        UI.printBoard(chessMatch.getPieces(),possibleMoves);
        
        
        System.out.println();
        System.out.print("Target: ");
        ChessPosition target = UI.readChessPosition(sc);
        
        ChessPiece capturedPiece = chessMatch.performChessMove(source,target);
        
        if(capturedPiece != null){
        captured.add(capturedPiece);
        }
        
        if(chessMatch.getPromoted() != null){
        
        System.out.print("peca para promocao (B/N/R/Q): ");
        String type = sc.nextLine();
        chessMatch.replacePromotedPiece(type);
            
        }
        
            }catch(ChessException e){
            System.out.println(e.getMessage());
            sc.nextLine();
            }  catch(InputMismatchException e){
            System.out.println(e.getMessage());
            sc.nextLine();
            }
        }
        
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
    
}
