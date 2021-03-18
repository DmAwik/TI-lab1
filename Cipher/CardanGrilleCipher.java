package Cipher;

import java.util.Scanner;

public class CardanGrilleCipher {
    private String OriginalText;
    private String CipherText;
    private boolean [][] Key;
    private int [][] MatrixForKey;

    public void setOriginalText (String sOriginalText){
        OriginalText = sOriginalText;
    }

    public void setCipherText (String sCipherText){
        CipherText = sCipherText;
    }

    public String getOriginalText (){
        return OriginalText;
    }

    public String getCipherText (){
        return CipherText;
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

    private void MakeMatrixForKey (){
        int size = 0;
        if (OriginalText != null){
            size = (int) Math.ceil(Math.sqrt(OriginalText.length()));
            MatrixForKey = new int[size][size];
            Key = new boolean[size][size];
        }
        if  (CipherText != null) {
            size = (int) Math.ceil(Math.sqrt(CipherText.length()));
            MatrixForKey = new int[size][size];
            Key = new boolean[size][size];
        }
        int number = 1;
        int j;
        for (int spire = 1; spire <= size / 2; spire++ ){
            for (j = spire - 1; j < size - spire; j++) {
                MatrixForKey[spire - 1][j] = number;
                MatrixForKey[size - spire][(size-1)-j]  = number;
                MatrixForKey[j][size - spire] = number;
                MatrixForKey[(size-1)-j][spire-1] = number++;
            }
        }
        if (size % 2 != 0){
            MatrixForKey [(size-1)/2][(size-1)/2] = (size*size)/4 + (size*size)% 4;
        }
    }

    private void MakeKey (int size){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ключ");
        int [] KEYchr = new int [size];
        for (int i =0; i < size; i++){
            KEYchr[i] = in.nextInt();
        }
        int number;
        int knumber;
        int kkey;

        for (int index = 0; index < KEYchr.length; index++){
            number = KEYchr[index];
            knumber = (index % 4) + 1;
            kkey = 1;
            for (int i = 0; i < Key.length; i++){
                for (int j = 0; j < Key.length; j++){
                    if (MatrixForKey[i][j] == number){
                        if (kkey == knumber){
                            Key [i][j] = true;
                        }
                        kkey++;
                    }
                }
            }
        }

    }

    public void GenerateKey (int Key) {
        MakeMatrixForKey();
        MakeKey(Key);
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
            for (int j = 4; j >=0; j--){
                if (Key[j][i]){
                    s.append(MatrixForUncoding[imatr][jmatr]);
                    index++;
                }
                jmatr++;
            }
            imatr++;
        }

        imatr = 0;
        for (int i = 4; i >= 0; i--){
            jmatr = 0;
            for (int j = 4; j >=0; j--){
                if (Key[i][j]){
                    s.append(MatrixForUncoding[imatr][jmatr]);
                    index++;
                }
                jmatr++;
            }
            imatr++;
        }

        imatr = 0;

        for (int i = 4; i >= 0; i--){
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
