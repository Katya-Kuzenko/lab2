package kuz.lab2;

public interface Matrix {

    double getElement(int row, int column);
    double[] getRow(int index);
    double[] getColumn(int index);
    int getRowNumbers();
    int getColumnNumbers();
    default int dimension(){
        return getColumnNumbers() * getRowNumbers();
    }

}
