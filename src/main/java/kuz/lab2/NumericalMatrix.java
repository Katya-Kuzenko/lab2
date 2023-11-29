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
        matrix = new double[numericalMatrix.matrix.length][numericalMatrix.matrix[0].length];
    }

    public void setElement(int row, int column, double el){
        matrix[row][column] = el;
    }
}
