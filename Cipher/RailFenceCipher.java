package Cipher;

public class RailFenceCipher {
    private String OriginalText;
    private String CipherText;
    private int Key;
    private char [][] N;

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

    public void show (){
        System.out.println("Исходный текст: " + OriginalText + "; Шифротекст: " + CipherText + "; Ключ: " + Key );
    }

    public void reset(){
        setOriginalText("");
        setCipherText("");
        setKey(0);
    }

    public void setKey(int k){
        Key = k;
    }

    public int getKey (){
        return Key;
    }

    private void MakeMatrixForCoding (){
        int index=0;
        N = new char[Key][OriginalText.length()];
        for (int j = 0; j < OriginalText.length();j++){
            for (int i = 0; (i < Key) && (index < OriginalText.length()); i++){
                N[i][j] = OriginalText.charAt(index);
                index++;
                j++;
            }
            for (int i = Key-2; (i > 0) && (index < OriginalText.length()); i--){
                N[i][j] = OriginalText.charAt(index);
                index++;
                j++;
            }
            j--;
        }

    }

    private void MakeCipherText () {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < Key; i++){
            for (int j = 0; j < OriginalText.length(); j++){
                if (N[i][j] != 0){
                    s.append(N[i][j]);
                }
            }
        }
        CipherText = s.toString();
    }

    private void MakeMatrixForUncoding (){
        boolean [][] MatrixBol = new boolean [Key][CipherText.length()];
        N = new char[Key][CipherText.length()];
        int index=0;
        for (int j = 0; j < CipherText.length();j++){
            for (int i = 0; (i < Key) && (index < CipherText.length()); i++){
                MatrixBol[i][j] = true;
                index++;
                j++;
            }
            for (int i = Key-2; (i > 0) && (index < CipherText.length()); i--){
                MatrixBol[i][j] = true;
                index++;
                j++;
            }
            j--;
        }
        index = 0;
        for (int i = 0; i < Key; i++){
            for (int j = 0; j < CipherText.length(); j++){
                if (MatrixBol[i][j]){
                    N[i][j] = CipherText.charAt(index);
                    index++;
                }
            }
        }

    }

    private void MakeOriginalText (){
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < CipherText.length(); j++){
            for (int i = 0; i < Key; i++){
                if (N[i][j] != 0){
                    s.append(N[i][j]);
                }
            }
        }
        OriginalText = s.toString();
    }

    public void coding (){
        MakeMatrixForCoding();
        MakeCipherText();

    }

    public void uncoding (){
        MakeMatrixForUncoding();
        MakeOriginalText();
    }
}
