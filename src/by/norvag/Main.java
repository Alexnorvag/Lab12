package by.norvag;

import javax.swing.*;


/**
 * Created by norvag on 12.03.2016.
 * Создать апплет с областью для рисования.
 * Добавить кнопки для выбора цвета (каждому цвету соответствует своя кнопка),
 * кнопку для очистки окна.
 * Рисование на панели со скроллингом.
 * стр 288.
 */
public class Main {
    public static void main(String[] args) {
        AppPainting painting = new AppPainting();
        painting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painting.setSize(1200, 800);
        painting.setTitle("Your Paint");
        painting.setVisible(true);
    }
}
