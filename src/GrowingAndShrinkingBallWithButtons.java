import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GrowingAndShrinkingBallWithButtons extends JPanel implements ActionListener {

    private final int WIDTH = 800; // ширина вікна
    private final int HEIGHT = 600; // висота вікна
    private int ballSize = 20; // початковий розмір кулі
    private int ballGrowthRate = 5; // швидкість збільшення розміру кулі

    public GrowingAndShrinkingBallWithButtons() {
        JFrame frame = new JFrame("Growing and Shrinking Ball With Buttons");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);

        // створюємо кнопки для збільшення та зменшення розміру кулі
        JButton growButton = new JButton("Grow");
        growButton.addActionListener(this);
        frame.add(growButton, "North");

        JButton shrinkButton = new JButton("Shrink");
        shrinkButton.addActionListener(this);
        frame.add(shrinkButton, "South");

        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillOval((WIDTH - ballSize) / 2, (HEIGHT - ballSize) / 2, ballSize, ballSize);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Grow")) {
            ballSize += ballGrowthRate;
        } else if (command.equals("Shrink")) {
            ballSize -= ballGrowthRate;
        }

        // перемальовуємо екран
        repaint();
    }

    public static void main(String[] args) {
        new GrowingAndShrinkingBallWithButtons();
    }
}