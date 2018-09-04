/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifracesar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 *
 * @author Felipe
 */
public class CifraCesar {
    
    public static String encrypt(String plainText, int shiftChar, int shiftNum){
        
        String cipherText = "";
        
        int length = plainText.length();
        
        for(int i = 0; i < length; i++){
            char ch = plainText.charAt(i);
            
            if(Character.isLetter(ch)){
                
                if(Character.isLowerCase(ch)){
                    char c = (char) (ch+shiftChar);
                    
                    if(c > 'z'){
                        cipherText += (char) (ch - (26-shiftChar));
                    } else {
                        cipherText += c;
                    }
                    
                } else if(Character.isUpperCase(ch)){
                    char c = (char) (ch+shiftChar);
                    
                     if(c > 'Z'){
                        cipherText += (char) (ch - (26-shiftChar));
                    } else {
                        cipherText += c;
                    }
                }
            
            } else if (Character.isDigit(ch)){
                
                char c = (char) (ch+shiftNum);
                    
                    if(c > '9'){
                        cipherText += (char) (ch - (10-shiftNum));
                    } else {
                        cipherText += c;
                    }
                    
            } else {
                
                cipherText += ch;
                
            }
        }
        
        return cipherText;
        
    }
    
    public static String decrypt(String plainText, int shiftChar, int shiftNum){
        
        String cipherText = "";
        
        int length = plainText.length();
        
        for(int i = 0; i < length; i++){
            char ch = plainText.charAt(i);
            
            if(Character.isLetter(ch)){
                
                if(Character.isLowerCase(ch)){
                    char c = (char) (ch-shiftChar);
                    
                    if(c < 'a'){
                        cipherText += (char) (ch + (26-shiftChar));
                    } else {
                        cipherText += c;
                    }
                    
                } else if(Character.isUpperCase(ch)){
                    char c = (char) (ch-shiftChar);
                    
                     if(c < 'A'){
                        cipherText += (char) (ch + (26-shiftChar));
                    } else {
                        cipherText += c;
                    }
                }
                
                
            } else if (Character.isDigit(ch)){
                
                char c = (char) (ch-shiftNum);
                    
                    if(c < '0'){
                        cipherText += (char) (ch + (10-shiftNum));
                    } else {
                        cipherText += c;
                    }
                    
            } else {
                
                cipherText += ch;
                
            }
        }
        
        return cipherText;
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BufferedReader buff = 
                new BufferedReader(new InputStreamReader(System.in));
        
        String texto = "";
        String str = null;
        try{
            while ((str = buff.readLine()) != null) {
                texto = texto + str + "\r\n";
            }
        } catch(IOException e){
            e.printStackTrace();
        }        
        
        String type = new String(args[0]);
        int shift = Integer.parseInt(args[1]);
        
//String texto = "A a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a a E e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e e O o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o o S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s S s s R r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r r I i i i i i i i i i i i i i i i i i i i i i i i i i i  i i i i i i i i i i i i i";
        //String type = "c";
        //int shift = 5;
        int shiftNum = shift;
        int shiftChar = shift;
                
        if(shift > 26){
            shiftChar = shift % 26;
        } else if(shift > 10) {
            shiftNum = shift % 10;
        } else if (shift < 0){
            shiftChar = (shift%26) + 26;
            shiftNum = (shift%10) + 10;
        }
        
        texto = removeAcento(texto);
        
        if(type.equalsIgnoreCase("c")){
            
            String cipher = encrypt(texto, shiftChar, shiftNum);
            System.out.println(cipher);
            
        } else if(type.equalsIgnoreCase("d")){
            
            String decrypted = decrypt(texto, shiftChar, shiftNum);
            System.out.println(decrypted);
            
        } else {
            System.out.println("Operação '" + type + "' inválido");
        }
    }
    
    public static String removeAcento(String str) {
        
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
    
}
