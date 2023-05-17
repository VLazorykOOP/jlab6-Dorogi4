package ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ex31Swing {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Matrix Manipulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton loadButton = new JButton("Load Matrix Data");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int[][] matrix = readMatrixFromFile("D:\\Documents\\GitHub\\jlab6-Dorogi4\\src\\ex2\\input.txt");
                    textArea.append("Original matrix:\n");
                    printMatrix(matrix, textArea);

                    int[] maxInfo = findMax(matrix);
                    swapRowsAndColumns(matrix, maxInfo[1], maxInfo[2]);

                    textArea.append("\nModified matrix:\n");
                    printMatrix(matrix, textArea);
                } catch (FileNotFoundException ex) {
                    textArea.append("Error: Input file not found.\n");
                } catch (NumberFormatException ex) {
                    textArea.append("Error: Invalid number format.\n");
                } catch (Exception ex) {
                    textArea.append("Error: " + ex.getMessage() + "\n");
                }
            }
        });

        panel.add(loadButton, BorderLayout.SOUTH);

        Font font = textArea.getFont();
        Font newFont = font.deriveFont(font.getSize() + 3f);
        textArea.setFont(newFont);
        frame.add(panel);

        frame.setVisible(true);
    }

    private static int[][] readMatrixFromFile(String fileName) throws FileNotFoundException {
        Scanner in = new Scanner(new File(fileName));

        int n = in.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        in.close();
        return matrix;
    }

    private static void printMatrix(int[][] matrix, JTextArea textArea) {
        for (int[] row : matrix) {
            for (int elem : row) {
                textArea.append(elem + " | ");
            }
            textArea.append("\n");
        }
    }

    private static int[] findMax(int[][] matrix) {
        int max = matrix[0][0];
        int maxRowIndex = 0;
        int maxColIndex = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    maxRowIndex = i;
                    maxColIndex = j;
                }
            }
        }

        return new int[]{max, maxRowIndex, maxColIndex};
    }

    private static void swapRowsAndColumns(int[][] matrix, int maxRowIndex, int maxColIndex) {
        int[] tempRow = matrix[0];
        matrix[0] = matrix[maxRowIndex];
        matrix[maxRowIndex] = tempRow;

        for (int i = 0; i < matrix.length; i++) {
            int tempCol = matrix[i][0];
            matrix[i][0] = matrix[i][maxColIndex];
            matrix[i][maxColIndex] = tempCol;
        }
    }
}
