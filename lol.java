import Cipher.*;
import java.util.Scanner;

public class lol {

    static String Choice (){
        Scanner in = new Scanner(System.in);
        String s;
        s = in.nextLine();
        return s;
    }

    static void  RailFenceMenu () {
        Scanner in = new Scanner(System.in);
        String Answer = "0";
        String Ask = "3";
        while (!Answer.equals(Ask)){
            System.out.println("1. Зашифровать сообщение");
            System.out.println("2. Расшифровать сообщение");
            System.out.println("3. Выход");
            switch (Answer = Choice()){
                case "1": { RailFenceCipher RailFence = new RailFenceCipher();
                    RailFence.reset();
                    System.out.print("Введите Исходный текст: ");
                    RailFence.setOriginalText(in.nextLine());
                    System.out.print("Введите ключ (глубина изгороди): ");
                    RailFence.setKey(in.nextInt());
                    RailFence.coding();
                    RailFence.show(); break;}
                case "2": { RailFenceCipher RailFence = new RailFenceCipher();
                    RailFence.reset();
                    System.out.print("Введите зашифрованный текст: ");
                    RailFence.setCipherText(in.nextLine());
                    System.out.print("Введите ключ (глубина изгороди): ");
                    RailFence.setKey(in.nextInt());
                    RailFence.uncoding();
                    RailFence.show(); break;}
            }
        }
    }

    static void  ColumnMethodMenu () {
        Scanner in = new Scanner(System.in);
        String Answer = "0";
        String Ask = "3";
        while (!Answer.equals(Ask)){
            System.out.println("1. Зашифровать сообщение");
            System.out.println("2. Расшифровать сообщение");
            System.out.println("3. Выход");
            switch (Answer = Choice()){
                case "1": { ColumnMethodCipher ColumnMethod = new ColumnMethodCipher();
                    ColumnMethod.reset();
                    System.out.print("Введите Исходный текст: ");
                    ColumnMethod.setOriginalText(in.nextLine());
                    System.out.print("Введите ключ: ");
                    ColumnMethod.setKey(in.nextLine());
                    ColumnMethod.coding();
                    ColumnMethod.show(); break;}
                case "2": { ColumnMethodCipher ColumnMethod = new ColumnMethodCipher();
                    ColumnMethod.reset();
                    System.out.print("Введите зашифрованный текст: ");
                    ColumnMethod.setCipherText(in.nextLine());
                    System.out.print("Введите ключ: ");
                    ColumnMethod.setKey(in.nextLine());
                    ColumnMethod.uncoding();
                    ColumnMethod.show(); break;}
            }
        }
    }

    static void VignerMenu () {
        Scanner in = new Scanner(System.in);
        String Answer = "0";
        String Ask = "3";
        while (!Answer.equals(Ask)){
            System.out.println("1. Зашифровать сообщение");
            System.out.println("2. Расшифровать сообщение");
            System.out.println("3. Выход");
            switch (Answer = Choice()){
                case "1": { VigenerCipher Vigener = new VigenerCipher();
                    Vigener.reset();
                    System.out.print("Введите Исходный текст: ");
                    Vigener.setOriginalText(in.nextLine());
                    System.out.print("Введите ключ: ");
                    Vigener.setKey(in.nextLine());
                    Vigener.coding();
                    Vigener.show(); break;}
                case "2": { VigenerCipher Vigener = new VigenerCipher();
                    Vigener.reset();
                    System.out.print("Введите зашифрованный текст: ");
                    Vigener.setCipherText(in.nextLine());
                    System.out.print("Введите ключ: ");
                    Vigener.setKey(in.nextLine());
                    Vigener.uncoding();
                    Vigener.show(); break;}
            }
        }
    }

    static void CardanGrilleMenu () {
        Scanner in = new Scanner(System.in);
        String Answer = "0";
        String Ask = "3";
        while (!Answer.equals(Ask)){
            System.out.println("1. Зашифровать сообщение");
            System.out.println("2. Расшифровать сообщение");
            System.out.println("3. Выход");
            switch (Answer = Choice()){
                case "1": { CardanGrilleCipher CardanGrille = new CardanGrilleCipher();
                    System.out.print("Введите Исходный текст: ");
                    CardanGrille.setOriginalText(in.nextLine());
                    System.out.println("1. Ввести таблицу");
                    System.out.println("2. Ввести ключ");
                    switch (Answer = Choice()){
                        case "1" : { CardanGrille.setKey(); break;}
                        case "2" : { System.out.print("Введите размерность ключа: ");CardanGrille.GenerateKey(in.nextInt()); break;}
                    }
                    CardanGrille.coding();
                    CardanGrille.show();
                    CardanGrille.reset(); break;}
                case "2": { CardanGrilleCipher CardanGrille = new CardanGrilleCipher();
                    System.out.print("Введите зашифрованный текст: ");
                    CardanGrille.setCipherText(in.nextLine());
                    System.out.println("1. Ввести таблицу");
                    System.out.println("2. Ввести ключ");
                    switch (Answer = Choice()){
                        case "1" : { CardanGrille.setKey(); break;}
                        case "2" : { System.out.print("Введите размерность ключа: ");CardanGrille.GenerateKey(in.nextInt()); break;}
                    }
                    CardanGrille.uncoding();
                    CardanGrille.show();
                    CardanGrille.reset(); break;}
            }
        }
    }

    static void MainMenu (){
        String Answer = "0";
        String Ask = "5";
        while (!Answer.equals(Ask)){
            System.out.println("1. Метод железнодорожной изгороди");
            System.out.println("2. Столбцовый метод");
            System.out.println("3. Метод поворачивающейся решётки");
            System.out.println("4. Метод Виженера");
            System.out.println("5. Выход");
            switch (Answer = Choice()){
                case "1":  RailFenceMenu(); break;
                case "2":  ColumnMethodMenu(); break;
                case "3":  CardanGrilleMenu (); break;
                case "4":  VignerMenu (); break;
            }
        }
    }

    public static void main (String [] args){
        MainMenu();
    }
}
