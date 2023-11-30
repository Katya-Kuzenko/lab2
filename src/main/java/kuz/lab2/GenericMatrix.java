package kuz.lab2;

public class GenericMatrix<T> {

    private final Object[][] matrix;

    public GenericMatrix() {
        matrix = new Object[0][0];
    }

    public GenericMatrix(int m, int n) {
        if (m < 0 || n < 0) {
            throw new IllegalArgumentException("Invalid row on column size");
        }
        matrix = new Object[m][n];
    }

    public GenericMatrix(T[][] matrix) {
        this.matrix = matrix;
    }

    public void setElement(int row, int column, T el) {
        matrix[row][column] = el;
    }

    public void setMatrix(T[][] matrix) {
        for (int i = 0; i < getRowNumbers(); i++) {
            for (int j = 0; j < getColumnNumbers(); j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public T getElement(int row, int column) {
        return (T) matrix[row][column];
    }

    public T[] getRow(int index) {
        return (T[]) matrix[index];
    }

    public T[] getColumn(int index) {
        Object[] column = new Object[getRowNumbers()];
        for (int i = 0; i < getRowNumbers(); i++) {
            column[i] = matrix[i][index];
        }
        return (T[]) column;
    }

    public int getRowNumbers() {
        return matrix.length;
    }

    public int getColumnNumbers() {
        if (matrix.length == 0) {
            return 0;
        }
        return matrix[0].length;
    }

    public int dimension() {
        return getColumnNumbers() * getRowNumbers();
    }

    public GenericMatrix<T> transpose() {
        GenericMatrix<T> result = new GenericMatrix<>(getColumnNumbers(), getRowNumbers());
        for (int i = 0; i < result.getRowNumbers(); i++) {
            for (int j = 0; j < result.getColumnNumbers(); j++) {
                result.setElement(i, j, getElement(j, i));
            }
        }
        return result;
    }

    public static <E> GenericMatrix<E> diagonalMatrix(E[] diagonal) {
        GenericMatrix<E> matrix = new GenericMatrix<>(diagonal.length, diagonal.length);
        for (int i = 0; i < matrix.getRowNumbers(); i++) {
            for (int j = 0; j < matrix.getColumnNumbers(); j++) {
                if (i == j) {
                    matrix.setElement(i, j, diagonal[i]);
                }
            }
        }
        return matrix;
    }
}
