/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cifracesar;

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
        
        //String texto = "Isto não é uma menssagem é uma cachaça 99725400 Lorem Ipsum é simplesmente uma simulação de texto da indústria tipográfica e de impressos, e vem sendo utilizado desde o século XVI, quando um impressor desconhecido pegou uma bandeja de tipos e os embaralhou para fazer um livro de modelos de tipos. Lorem Ipsum sobreviveu não só a cinco séculos, como também ao salto para a editoração eletrônica, permanecendo essencialmente inalterado. Se popularizou na década de 60, quando a Letraset lançou decalques contendo passagens de Lorem Ipsum, e mais recentemente quando passou a ser integrado a softwares de editoração eletrônica como Aldus PageMaker.";
        String texto = "Um texto cuja modalidade se define pela natureza argumentativa representa, sobretudo, aquele texto em que se atesta a capacidade de o emissor discorrer, defender seu ponto de vista acerca deste ou daquele assunto.";
        int shift = 9;
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
        
        String cipher = encrypt(texto, shiftChar, shiftNum);
        System.out.println(cipher);
        
        String decrypted = decrypt(cipher, shiftChar, shiftNum);
        System.out.println(decrypted);
    }
    
    public static String removeAcento(String str) {
        
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
    
}
