package kuz.lab2;

import java.util.Arrays;

public class NumericalMatrix implements Matrix{
    private final double[][] matrix;

    public NumericalMatrix() {
        matrix = new double[0][0];
    }
    public NumericalMatrix(int m, int n) {
        if (m < 0 || n < 0) {
            throw new IllegalArgumentException("Invalid row on column size");
        }
        matrix = new double[m][n];
    }
    public NumericalMatrix(NumericalMatrix numericalMatrix) {
        matrix = new double[numericalMatrix.getRowNumbers()][numericalMatrix.getColumnNumbers()];
        for (int i = 0; i < getRowNumbers(); i++){
            for (int j = 0; j < getColumnNumbers(); j++){
                matrix[i][j] = numericalMatrix.matrix[i][j];
            }
        }
    }

    public void setElement(int row, int column, double el){
        validateRow(row);
        validateColumn(column);
        matrix[row][column] = el;
    }

    public void setMatrix(double[][] matrix) {
        if (matrix.length != getRowNumbers() || matrix[0].length != this.getColumnNumbers()) {
            throw new IllegalArgumentException("Matrix with wrong size");
        }
        for (int i = 0; i < getRowNumbers(); i++){
            for (int j = 0; j < getColumnNumbers(); j++){
                this.matrix[i][j] = matrix[i][j];
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
        return matrix[index];
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

    public NumericalMatrix add(NumericalMatrix matrix) {
        if (this.getRowNumbers() != matrix.getRowNumbers() || this.getColumnNumbers() != matrix.getColumnNumbers()) {
            throw new IllegalArgumentException("Size is not the same");
        }

        NumericalMatrix sumMatrix = new NumericalMatrix(getRowNumbers(), getColumnNumbers());
        for (int i = 0; i < getRowNumbers(); i++) {
            for (int j = 0; j < getColumnNumbers(); j++) {
                sumMatrix.setElement(i, j, this.matrix[i][j] + matrix.matrix[i][j]);
            }
        }
        return sumMatrix;
    }

    public NumericalMatrix multiply(double scalar) {
        NumericalMatrix newMatrix = new NumericalMatrix(getRowNumbers(), getColumnNumbers());
        for (int i = 0; i < getRowNumbers(); i++) {
            for (int j = 0; j < getColumnNumbers(); j++) {
                newMatrix.setElement(i, j, scalar * matrix[i][j]);
            }
        }
        return newMatrix;
    }

    public NumericalMatrix multiply(NumericalMatrix otherMatrix) {
        if (getColumnNumbers() != otherMatrix.getRowNumbers()) {
            throw new IllegalArgumentException("Columns and rows number not equal");
        }

        NumericalMatrix result = new NumericalMatrix(getRowNumbers(), otherMatrix.getColumnNumbers());
        for (int i = 0; i < result.getRowNumbers(); i++) {
            for (int j = 0; j < result.getColumnNumbers(); j++) {
                double resultEl = 0;
                for (int r = 0; r < getColumnNumbers(); r++) {
                    resultEl = resultEl + matrix[i][r] * otherMatrix.matrix[r][j];
                }
                result.setElement(i, j, resultEl);
            }
        }
        return result;
    }

    public NumericalMatrix transpose() {
        NumericalMatrix result = new NumericalMatrix(getColumnNumbers(), getRowNumbers());
        for (int i = 0; i < result.getRowNumbers(); i++) {
            for (int j = 0; j < result.getColumnNumbers(); j++) {
                result.setElement(i, j, matrix[j][i]);
            }
        }
        return result;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumericalMatrix that = (NumericalMatrix) o;

        return Arrays.deepEquals(matrix, that.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }
}
