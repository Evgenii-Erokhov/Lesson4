package Lesson4;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game_XO {

    private final static char EMPTY = '_';
    private final static char DOT_X = 'X';
    private final static char DOT_O = 'O';
    //private static int fieldSize = 3;
    //|_|_|_|
    //|_|_|_|
    //|_|_|_|

    private void printTab(char[][] tab) {
        int counter = 1;
        System.out.print("   ");
        for (int i = 0; i < tab.length; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
        for (char[] chars : tab) {
            System.out.print(counter + " ");
            counter++;
            for (char sym : chars) {
                System.out.print("|" + sym);
            }
            System.out.println("|");
        }
    }

    private void fillTab(char[][] tab) {
        for (char[] chars : tab) {
            Arrays.fill(chars, EMPTY);
        }
    }

    public void startGame(int fieldSize) {
        // TODO: 23.03.2020 *** fieldSize > 3, 4(WIN)
        char[][] tab = new char[fieldSize][fieldSize];
        fillTab(tab);
        System.out.println("Игра крестики нолики. Вы играете за Х");
        printTab(tab);
        System.out.println("Для того, чтобы совершить ход введите номер строки" +
                "и номер столбца таблицы");
        boolean inGame = true;
        Scanner in = new Scanner(System.in);
        while (inGame) {
            System.out.println("Ваш ход:");
            int x, y;
            try {
                x = in.nextInt();
                y = in.nextInt();
                x--;
                y--;
                if (isValid(x, y, tab)) {
                    tab[x][y] = DOT_X;
                    printTab(tab);
                    if (isVictory(tab, DOT_X)) {
                        System.out.println("Вы победили");
                        break;
                    }
                    if (isFull(tab)) {
                        System.out.println("Ничья");
                        break;
                    }

                    System.out.print("Компьютер совершает ход");
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(300);
                        System.out.print(".");
                    }
                    System.out.println();
                    movePC(tab);
                    if (isVictory(tab, DOT_O)) {
                        System.out.println("Вы проиграли");
                        break;
                    }
                    if (isFull(tab)) {
                        System.out.println("Ничья");
                        break;
                    }

                } else {
                    System.out.println("Данный ход невозможен! Введите новые значения");
                }
            } catch (InputMismatchException exception) {
                System.out.println("Вы ввели не цифры! Введите цифры");
                in.next();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Игра закончена");
        //..... game .....
    }

    private boolean isVictory(char [][] tab, char sym) {  //TODO: Проверка победы
        for (char[] chars : tab) {
            for (int j = 0; j < chars.length; j++) {
                if (tab[0][0] == sym && tab[0][1] == sym && tab[0][2] == sym) return true;
                if (tab[1][0] == sym && tab[1][1] == sym && tab[1][2] == sym) return true;
                if (tab[2][0] == sym && tab[2][1] == sym && tab[2][2] == sym) return true;
                if (tab[0][0] == sym && tab[1][0] == sym && tab[2][0] == sym) return true;
                if (tab[0][1] == sym && tab[1][1] == sym && tab[2][1] == sym) return true;
                if (tab[0][2] == sym && tab[1][2] == sym && tab[2][2] == sym) return true;
                if (tab[0][0] == sym && tab[1][1] == sym && tab[2][2] == sym) return true;
                if (tab[2][0] == sym && tab[1][1] == sym && tab[0][2] == sym) return true;
            }
        }
        return false;
    }

    public boolean isFull(char [][] tab) {          //TODO: НИЧЬЯ
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == EMPTY)
                    return false;
                }
            }
        return true;
    }


    private void movePC(char[][] tab) {
        // TODO: 23.03.2020 smart strategy  // рандомный выбор клетки
        int len = tab.length;
        label:
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int x = (int) (Math.random() * 3);
                int y = (int) (Math.random() * 3);
                if (tab[x][y] == EMPTY) {
                    tab[x][y] = DOT_O;
                    printTab(tab);
                        }else {
                    continue label;
                }
                return;
            }
        }
    }

    private boolean isValid(int x, int y, char[][] tab) {
        int len = tab.length;
        if (x >= 0 && x < len && y >= 0 && y < len) {
            if (tab[x][y] == EMPTY) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        if (args != null && args.length == 1) {
            new Game_XO().startGame(Integer.parseInt(args[0]));
        } else {
            new Game_XO().startGame(3);
        }

    }
}