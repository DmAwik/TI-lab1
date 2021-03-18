package Cipher;

import java.util.Scanner;

public class CardanGrilleCipher {
    private String OriginalText;
    private String CipherText;
    private boolean [][] Key;


    public void setOriginalText (String sOriginalText){
        OriginalText = sOriginalText;
    }

    public void setCipherText (String sCipherText){
        CipherText = sCipherText;
    }



    public void setKey (){
        int size;
        int [][] InputArr ;
        Scanner in = new Scanner(System.in);
        System.out.print("Введите размерность решетки: ");
        size = in.nextInt();
        Key = new boolean[size][size];
        InputArr = new int[size][size];
        for (int i = 0; i<size; i++){
            System.out.println("Введите " + (i+1) + " строку");
            for (int j =0; j < size; j++){
                InputArr[i][j] = in.nextInt();
                if (InputArr[i][j] == 1){
                    Key[i][j] = true;
                }
            }
        }
    }

    public void show (){
        System.out.println("Исходный текст: " + OriginalText + "; Шифротекст: " + CipherText + ";"); // + "; Ключ: " + Key );
    }

    public void reset(){
        setOriginalText(null);
        setCipherText(null);
        for (int i =0 ; i < Key.length; i++){
            for (int j =0; j < Key.length; j++){
                Key[i][j] = false;
            }
        }
    }

    private void MakeChipherText (){
        StringBuilder s = new StringBuilder();
        int size = Key.length;
        char [][] MatrixForCoding = new char [size][size];
        int index = 0;

        for (int i = 0; i < size; i++){
            for (int j = 0; (j < size) && (index < OriginalText.length()); j++){
                if ((Key[i][j]) && (MatrixForCoding[i][j] == 0)){
                    MatrixForCoding[i][j] = OriginalText.charAt(index);
                    index++;
                }
            }
        }
        int imatr = 0;
        int jmatr;
        for (int i = 0; i < size; i++){
            jmatr = 0;
            for (int j = size-1; (j >=0) && (index < OriginalText.length()); j--){
                if ((Key[j][i]) && (MatrixForCoding[imatr][jmatr] == 0)){
                    MatrixForCoding[imatr][jmatr] = OriginalText.charAt(index);
                    index++;
                }
                jmatr++;
            }
            imatr++;
        }

        imatr = 0;
        // jmatr = 0;
        for (int i = size-1; i >= 0; i--){
            jmatr = 0;
            for (int j = size-1; (j >=0) && (index < OriginalText.length()); j--){
                if ((Key[i][j]) && (MatrixForCoding[imatr][jmatr] == 0)){
                    MatrixForCoding[imatr][jmatr] = OriginalText.charAt(index);
                    index++;
                }
                jmatr++;
            }
            imatr++;
        }

        imatr = 0;
        for (int i = size-1; i >= 0; i--){
            jmatr = 0;
            for (int j = 0; (j < size) && (index < OriginalText.length()); j++){
                if ((Key[j][i]) && (MatrixForCoding[imatr][jmatr] == 0)){
                    MatrixForCoding[imatr][jmatr] = OriginalText.charAt(index);
                    index++;
                }
                jmatr++;
            }
            imatr++;
        }

        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                s.append(MatrixForCoding[i][j]);
            }
        }

        CipherText = s.toString();
    }

    private void MakeOriginalText (){
        StringBuilder s = new StringBuilder();
        int size = Key.length;
        char [][] MatrixForUncoding = new char [size][size];
        int index = 0;

        for (int i = 0; i<size; i++){
            for (int j = 0;j<size;j++){
                MatrixForUncoding[i][j]= ' ';
            }
        }

        for (int i = 0; i<size; i++){
            for (int j = 0;j<size;j++){
                MatrixForUncoding[i][j]= CipherText.charAt(index);
                index++;
            }
        }

        if (size % 2 == 1){
            Key[size/2][size/2]=false;
        }

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (Key[i][j]){
                    s.append(MatrixForUncoding[i][j]);
                    index++;
                }
            }
        }

        int imatr = 0;
        int jmatr;
        for (int i = 0; i < size; i++){
            jmatr = 0;
            for (int j = size-1; j >=0; j--){
                if (Key[j][i]){
                    s.append(MatrixForUncoding[imatr][jmatr]);
                    index++;
                }
                jmatr++;
            }
            imatr++;
        }

        imatr = 0;
        for (int i = size-1; i >= 0; i--){
            jmatr = 0;
            for (int j = size-1; j >=0; j--){
                if (Key[i][j]){
                    s.append(MatrixForUncoding[imatr][jmatr]);
                    index++;
                }
                jmatr++;
            }
            imatr++;
        }

        imatr = 0;

        for (int i = size-1; i >= 0; i--){
            jmatr = 0;
            for (int j = 0; j < size; j++){
                if (Key[j][i]){
                    s.append(MatrixForUncoding[imatr][jmatr]);
                    index++;
                }
                jmatr++;
            }
            imatr++;
        }

        OriginalText = s.toString();
    }

    public void coding (){
        MakeChipherText();
    }

    public void uncoding () {
        MakeOriginalText();
    }

}
