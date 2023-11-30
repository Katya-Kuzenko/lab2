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

    public NumericalMatrix(double[][] matrix) {
        this.matrix = matrix;
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

    public static NumericalMatrix diagonalMatrix(double[] diagonal) {
        NumericalMatrix matrix = new NumericalMatrix(diagonal.length, diagonal.length);
        for (int i = 0; i < matrix.getRowNumbers(); i++) {
            for (int j = 0; j < matrix.getColumnNumbers(); j++) {
                if (i == j) {
                    matrix.setElement(i, j, diagonal[i]);
                }
            }
        }
        return matrix;
    }

    public static NumericalMatrix identityMatrix(int size) {
        double[] diagonal = new double[size];
        Arrays.fill(diagonal, 1);
        return diagonalMatrix(diagonal);
    }

    public static NumericalMatrix randomRowMatrix(int size) {
        NumericalMatrix matrix = new NumericalMatrix(1, size);
        for (int i = 0; i < matrix.getColumnNumbers(); i++) {
            matrix.setElement(0, i, Math.random());
        }
        return matrix;
    }

    public static NumericalMatrix randomColumnMatrix(int size) {
        NumericalMatrix matrix = new NumericalMatrix(size, 1);
        for (int i = 0; i < matrix.getRowNumbers(); i++) {
            matrix.setElement(i, 0, Math.random());
        }
        return matrix;
    }

    public NumericalMatrix invertibleMatrix() {
        double determinant = determinant();
        if (determinant == 0) {
            throw new IllegalArgumentException("Matrix is singular");
        }
        NumericalMatrix adjugateMatrix = adjugateMatrix();
        return adjugateMatrix.multiply(1 / determinant);
    }

    private NumericalMatrix adjugateMatrix() {
        int n = matrix.length;
        double[][] adjugateMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjugateMatrix[j][i] = Math.pow(-1, i + j) * removeRowAndColumn(i, j).determinant();
            }
        }
        return new NumericalMatrix(adjugateMatrix);
    }

    public double determinant() {
        validateSquareMatrix();
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        double determine = 0;
        for (int k = 0; k < matrix.length; k++) {
            determine = determine + Math.pow(-1, k) * matrix[0][k] * removeRowAndColumn(0, k).determinant();
        }
        return determine;
    }

    private NumericalMatrix removeRowAndColumn(int row, int column) {
        int n = this.matrix.length - 1;
        double[][] newMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int rowPointer = i;
                if (i >= row) {
                    rowPointer++;
                }
                int columnPointer = j;
                if (j >= column) {
                    columnPointer++;
                }
                newMatrix[i][j] = matrix[rowPointer][columnPointer];
            }
        }
        return new NumericalMatrix(newMatrix);
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

    private void validateSquareMatrix() {
        if (getRowNumbers() != getColumnNumbers()) {
            throw new IllegalArgumentException("Columns and rows number not equal");
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
