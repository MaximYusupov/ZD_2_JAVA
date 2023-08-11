package view;

import model.Toy;

import java.util.Scanner;

public class View {
    public View() {
    }
    public void mainMenu() {
        System.out.println("Выберите действие:\n" +
                "1 - Показать всю игрушки в магазине, 2 - Добавить новую игрушку, 3 - Удалить игрушку, 4 - Изменить вес\n" +
                "5 - Произвести розыгрыш, 6 - Получить игрушку, 0 - выход:");
    }

    public void showAllData(String data) {
        System.out.println(data);
    }
    public void addToy(boolean res){
        if (res)
            System.out.println("Успешно!");
        else
            System.out.println("Ошибка, такая запись уже существует!");
    }
    public void removeToy(boolean res){
        if (res)
            System.out.println("Успешно!");
        else
            System.out.println("Ошибка, запись не найдена!");
    }
    public void changeWeight(boolean res){
        if (res)
            System.out.println("Успешно!");
        else
            System.out.println("Ошибка, запись не найдена или некорректное значение веса!");
    }

    public void toRaffle(Toy toy){
        if (toy == null)
            System.out.println("Увы! В магазине кончились призы!");
        else
            System.out.println("Поздравляем! Ваш приз: \n" + toy.toString());
    }

    public void takePrize(boolean res){
        if (res)
            System.out.println("Приз выдан!");
        else
            System.out.println("Ошибка, призы кончились!");
    }
    private Scanner scan = new Scanner(System.in);

    /**
     * Получение целочисленного значения от пользователя
     *
     * @param msg Текст в диалоге
     * @return
     */
    public int getIntValue(String msg) {
        System.out.println("Введите " + msg + " (целочисленное):");
        return scan.nextInt();
    }

    /**
     * Получение числа с плавающей точкой от пользователя
     *
     * @param msg Текст в диалоге
     * @return
     */
    public Double getDblValue(String msg) {
        System.out.println("Введите " + msg + " (с плавающей точкой):");
        return scan.nextDouble();
    }

    /**
     * Получение строкового значения от пользователя
     *
     * @param msg Текст в диалоге
     * @return
     */
    public String getStrValue(String msg) {
        System.out.println("Введите " + msg + " (строка):");
        String line = new String();
        while(line.isEmpty())
            line = scan.nextLine();
        return line;
    }
}
