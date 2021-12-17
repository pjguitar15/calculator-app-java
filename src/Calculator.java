import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Philcob Suzuki Josol
 */
public class Calculator extends JFrame {
    int FRAME_WIDTH = 430, FRAME_HEIGHT = 650;
    JPanel screenPanel, screenTop, screenBot, buttonPanel;
    JButton one, two, three, four, five, six, seven, eight, nine, zero, point, del,
    c, posOrNeg, modulus, divide, multiply, subtract, add, equals;
    JLabel topText, botText;
    String FONT_FAMILY = "Century Gothic";

    String[] numOnly;
    String operations;
    double[] toDouble;
    double currResult = 0;

    public Calculator() {
        this.setTitle("Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        northPanel();
        centerPanel();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        getContentPane().requestFocusInWindow();
    }

    public void northPanel() {
        screenPanel = new JPanel();
        screenPanel.setBackground(Color.WHITE);
        screenPanel.setPreferredSize(new Dimension(FRAME_WIDTH, 180));
        screenPanel.setLayout(new BorderLayout());

        screenTop = new JPanel();
        screenTop.setBackground(Color.decode("#fafafa"));
        screenTop.setPreferredSize(new Dimension(FRAME_WIDTH, 90));
        topText = new JLabel("0", SwingConstants.RIGHT);
        topText.setFont(new Font(FONT_FAMILY, Font.BOLD, 20));
//        topText.setBorder(new EmptyBorder(10, 100, 10, 20)); // top, left, bottom, right
//        topText.setHorizontalAlignment(SwingConstants.RIGHT);
        topText.setHorizontalTextPosition(SwingConstants.RIGHT);
        screenTop.add(topText);
        screenPanel.add(screenTop, BorderLayout.NORTH);


        screenBot = new JPanel();
        screenBot.setBackground(Color.WHITE);
        screenBot.setPreferredSize(new Dimension(FRAME_WIDTH, 90));
        botText = new JLabel("0", SwingConstants.RIGHT);
        botText.setFont(new Font(FONT_FAMILY, Font.BOLD, 30));
        botText.setBorder(new EmptyBorder(15, 0, 0, 0));
        screenBot.add(botText);
        screenPanel.add(screenBot, BorderLayout.CENTER);

        this.add(screenPanel, BorderLayout.NORTH);
    }

    public void centerPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);
        buttonPanel.setPreferredSize(new Dimension(FRAME_WIDTH, 470));
        buttonPanel.setLayout(new GridLayout(5, 4, 1, 1));
        buttonGrid();
        styleAllButtons();
        this.add(buttonPanel, BorderLayout.CENTER);
    }

    public void buttonGrid() {
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eight = new JButton("8");
        nine = new JButton("9");
        zero = new JButton("0");
        add = new JButton("+");
        subtract = new JButton("-");
        multiply = new JButton("×");
        divide = new JButton("÷");
        modulus = new JButton("%");
        posOrNeg = new JButton("+/-");
        point = new JButton(".");
        c = new JButton("C");

        equals = new JButton("=");
        del = new JButton("del");

        JButton[] allButtons = {c, posOrNeg, modulus, divide, seven, eight, nine, multiply, four, five, six, subtract,
                one, two, three, add, zero, point, del, equals};
        for (JButton allButton : allButtons) {
            buttonPanel.add(allButton);
        }
    }

    public void styleAllButtons() {
        JButton[] allButtons = {one, two, three, four, five, six, seven, eight, nine, zero, point, del,
                c, posOrNeg, modulus, divide, multiply, subtract, add, equals};
        for (JButton allButton : allButtons) {
            numAndOperatorAction(allButton);
            allButton.setFocusPainted(false);
            allButton.setForeground(Color.decode("#FAFAFA"));
            Border emptyBorder = BorderFactory.createEmptyBorder();
            allButton.setBorder(emptyBorder);
            allButton.setFont(new Font(FONT_FAMILY, Font.BOLD, 20));
            allButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            if (allButton.getText().equals("=")) {
                allButton.setForeground(Color.decode("#23272B"));
                allButton.setBackground(Color.decode("#FFC207"));
                allButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        allButton.setBackground(Color.yellow);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        allButton.setBackground(Color.decode("#FFC207"));
                    }
                });
            } else if (!allButton.getText().equals("÷")) {
                allButton.setBackground(Color.decode("#1F2326"));
                allButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        allButton.setBackground(Color.decode("#23272B"));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        allButton.setBackground(Color.decode("#1F2326"));
                    }
                });
            } else if (allButton.getText().equals("÷")) {
                allButton.setBackground(Color.decode("#5F4BB6"));
                allButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        allButton.setBackground(Color.decode("#7459AE"));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        allButton.setBackground(Color.decode("#5F4BB6"));
                    }
                });
            }
        }

    }

    public void numAndOperatorAction(JButton btn) {

        if (btn.getText().equals("C")) {
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    topText.setText("0");
                    botText.setText("0");
                    currResult = 0;
                }
            });
        }

        if (btn.getText().equals("=")) {
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < toDouble.length; i++) {
                        if (i == 0) {
                            currResult += toDouble[i];
                            botText.setText(Double.toString(currResult));
                        } else if (operations.charAt(i - 1) == '+' && i < toDouble.length - 1) {
                            currResult += toDouble[i];
                            botText.setText(Double.toString(currResult));
                        } else if (operations.charAt(i - 1) == '-' && i < toDouble.length - 1) {
                            currResult -= toDouble[i];
                            botText.setText(Double.toString(currResult));
                        } else if (operations.charAt(i - 1) == '×' && i < toDouble.length - 1) {
                            currResult *= toDouble[i];
                            botText.setText(Double.toString(currResult));
                        } else if (operations.charAt(i - 1) == '÷' && i < toDouble.length - 1) {
                            currResult /= toDouble[i];
                            botText.setText(Double.toString(currResult));
                        } else {
                            if (operations.charAt(i - 1) == '+') {
                                currResult += toDouble[i];
                                botText.setText(Double.toString(currResult));
                            } else if (operations.charAt(i - 1) == '-') {
                                currResult -= toDouble[i];
                                botText.setText(Double.toString(currResult));
                                botText.setText(Double.toString(currResult));
                            } else if (operations.charAt(i - 1) == '×') {
                                currResult *= toDouble[i];
                                botText.setText(Double.toString(currResult));
                            } else if (operations.charAt(i - 1) == '÷') {
                                currResult /= toDouble[i];
                                botText.setText(Double.toString(currResult));
                            }
                        }
                    }
                    currResult = 0;
                }
            });
        }
        if (!btn.getText().equals("=") && !btn.getText().equals("del") && !btn.getText().equals("C")) {
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (topText.getText().equals("0")) {
                        topText.setText("");
                    }
                    topText.setText(topText.getText() + btn.getText());

                    numOnly = topText.getText().split("[÷×+-]");

                    // String array to Integer array
                    toDouble = new double[numOnly.length];
                    for (int i = 0; i < numOnly.length; i++) {
                        toDouble[i] = Double.parseDouble(numOnly[i]);
                    }

                    // take operations
                    operations = topText.getText().replaceAll("[0-9,.]", "");
                }
            });
        }
    }

}
