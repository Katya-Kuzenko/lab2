package kuz.lab2;

public class NumericalMatrix implements Matrix{
    private final double[][] matrix;

    public NumericalMatrix() {
        matrix = new double[0][0];
    }
    public NumericalMatrix(int m, int n) {
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
        matrix[row][column] = el;
    }

    @Override
    public double getElement(int row, int column) {
        return matrix[row][column];
    }

    @Override
    public double[] getRow(int index) {
        return matrix[index];
    }

    @Override
    public double[] getColumn(int index) {
        double[] column = new double[getRowNumbers()];
        for (int i = 0; i < getRowNumbers(); i++){
            column[i] = matrix[i][index];
        }
        return new double[0];
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
}
