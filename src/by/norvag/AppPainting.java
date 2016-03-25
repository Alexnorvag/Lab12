package by.norvag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by norvag on 12.03.2016.
 * Создать апплет с областью для рисования.
 * Добавить кнопки для выбора цвета (каждому цвету соответствует своя кнопка),
 * кнопку для очистки окна.
 * Рисование на панели со скроллингом.
 * стр 288.
 */
public class AppPainting extends JFrame{
    private int prevX;
    private int prevY;
    private Color color;
    private JButton chooseColorButton;
    private JButton clearButton;
    private JSlider slider;

    public AppPainting () {
        color = Color.BLACK;
        chooseColorButton = new JButton("Color");
        clearButton = new JButton("Clear");

        float min = 1.0f;
        float max = 10.0f;
        float initialValue = 1.0f;
        slider = new JSlider((int)min, (int)max, (int)initialValue);

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.setBackground(Color.WHITE);
        container.add(chooseColorButton);
        container.add(clearButton);
        container.add(slider);

        chooseColorButton.addActionListener(new ButtonListeners());
        clearButton.addActionListener(new ClearButtonListeners());

        addMouseListener(new PaintMouseAdapter());
        addMouseMotionListener(new PaintMouseMotionAdapter());
    }

    private class ButtonListeners implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            color = JColorChooser.showDialog(((Component) e.getSource()).getParent(), "Choose your color", color);
        }
    }

    private class ClearButtonListeners implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }

    private class PaintMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            setPreviousCoordinates(e.getX(), e.getY());
        }
    }

    private class PaintMouseMotionAdapter extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            int value = slider.getValue();
            Graphics g = getGraphics();
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(color);
            g2.setStroke(new BasicStroke((float)value));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawLine(prevX, prevY, e.getX(), e.getY());
            setPreviousCoordinates(e.getX(), e.getY());
        }
    }

    private void setPreviousCoordinates(int x, int y) {
        prevX = x;
        prevY = y;
    }
}
