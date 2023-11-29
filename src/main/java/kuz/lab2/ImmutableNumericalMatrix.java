package kuz.lab2;

import java.util.Arrays;

public class ImmutableNumericalMatrix implements Matrix {
    private final double[][] matrix;

    public ImmutableNumericalMatrix() {
        matrix = new double[0][0];
    }

    public ImmutableNumericalMatrix(double[][] matrix) {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("Empty matrix");
        }
        this.matrix = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < getRowNumbers(); i++){
            for (int j = 0; j < getColumnNumbers(); j++){
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public ImmutableNumericalMatrix(ImmutableNumericalMatrix numericalMatrix) {
        matrix = new double[numericalMatrix.getRowNumbers()][numericalMatrix.getColumnNumbers()];
        for (int i = 0; i < getRowNumbers(); i++){
            for (int j = 0; j < getColumnNumbers(); j++){
                matrix[i][j] = numericalMatrix.matrix[i][j];
            }
        }
    }

    @Override
    public double getElement(int row, int column) {
        validateRow(row);
        validateColumn(column);
        return matrix[row][column];
    }

    @Override
    public double[] getRow(int index) {
        validateRow(index);
        return Arrays.copyOf(matrix[index], matrix[index].length);
    }

    @Override
    public double[] getColumn(int index) {
        validateColumn(index);
        double[] column = new double[getRowNumbers()];
        for (int i = 0; i < getRowNumbers(); i++) {
            column[i] = matrix[i][index];
        }
        return column;
    }

    @Override
    public int getRowNumbers() {
        return matrix.length;
    }

    @Override
    public int getColumnNumbers() {
        if(matrix.length == 0){
            return 0;
        }
        return matrix[0].length;
    }

    private void validateColumn(int column) {
        if (column < 0 || column >= getColumnNumbers()) {
            throw new IllegalArgumentException("Invalid column");
        }
    }

    private void validateRow(int row) {
        if (row < 0 || row >= getRowNumbers()) {
            throw new IllegalArgumentException("Invalid row");
        }
    }


}
