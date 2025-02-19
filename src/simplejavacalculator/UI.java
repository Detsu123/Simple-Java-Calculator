package simplejavacalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UI implements ActionListener {

    private final JFrame frame;
    private final JTextArea text;
    private final Calculator calc;

    private final JButton[] numButtons = new JButton[10];
    private final JButton addButton, subButton, mulButton, divButton, equalButton, clearButton;

    public UI() throws IOException {
        frame = new JFrame("Simple Calculator");
        text = new JTextArea(1, 20);
        calc = new Calculator();

        addButton = createButton("+");
        subButton = createButton("-");
        mulButton = createButton("*");
        divButton = createButton("/");
        equalButton = createButton("=");
        clearButton = createButton("C");

        for (int i = 0; i < 10; i++) {
            numButtons[i] = createButton(String.valueOf(i));
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.addActionListener(this);
        return button;
    }

    public void init() {
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        textPanel.add(text);
        frame.add(textPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        for (int i = 1; i <= 9; i++) buttonPanel.add(numButtons[i]);
        buttonPanel.add(addButton);
        buttonPanel.add(numButtons[0]);
        buttonPanel.add(subButton);
        buttonPanel.add(mulButton);
        buttonPanel.add(divButton);
        buttonPanel.add(equalButton);
        buttonPanel.add(clearButton);

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String command = source.getText();

        if ("0123456789".contains(command)) {
            text.append(command);
        } else if ("+".equals(command)) {
            calc.calculateBi(Calculator.BiOperatorModes.ADD, reader());
            text.setText("");
        } else if ("-".equals(command)) {
            calc.calculateBi(Calculator.BiOperatorModes.MINUS, reader());
            text.setText("");
        } else if ("*".equals(command)) {
            calc.calculateBi(Calculator.BiOperatorModes.MULTIPLY, reader());
            text.setText("");
        } else if ("/".equals(command)) {
            calc.calculateBi(Calculator.BiOperatorModes.DIVIDE, reader());
            text.setText("");
        } else if ("=".equals(command)) {
            writer(calc.calculateEqual(reader()));
        } else if ("C".equals(command)) {
            calc.reset();
            text.setText("");
        }
    }

    private Double reader() {
        try {
            return Double.parseDouble(text.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void writer(Double value) {
        text.setText(String.valueOf(value));
    }
}
