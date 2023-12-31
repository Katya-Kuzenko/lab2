import kuz.lab2.NumericalMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumericalMatrixTest {
    private NumericalMatrix numericalMatrix;

    @BeforeEach
    public void init() {
        /*
            1 2 3
            3 2 1
            7 4 8
         */
        numericalMatrix = new NumericalMatrix(new double[][]{{1, 2, 3}, {3, 2, 1}, {7, 4, 8}});
    }

    @Test
    public void testSetGetMatrixElement() {
        numericalMatrix.setElement(0, 0, 99);
        assertEquals(99, numericalMatrix.getElement(0, 0));
    }

    @Test
    public void tesGetMatrixColumn() {
        double[] column = numericalMatrix.getColumn(2);
        assertArrayEquals(new double[]{3, 1, 8}, column);
    }

    @Test
    public void tesGetMatrixRow() {
        double[] row = numericalMatrix.getRow(0);
        assertArrayEquals(new double[]{1, 2, 3}, row);
    }

    @Test
    public void testGetRowNumbers() {
        assertEquals(3, numericalMatrix.getRowNumbers());
    }

    @Test
    public void testGetColumnNumbers() {
        assertEquals(3, numericalMatrix.getColumnNumbers());
    }

    @Test
    public void testDimension() {
        assertEquals(9, numericalMatrix.dimension());
    }

    @Test
    public void testAdd() {
        NumericalMatrix matrixB = new NumericalMatrix(3, 3);
        matrixB.setMatrix(new double[][]{{1, 2, 3}, {3, 2, 1}, {7, 4, 8}});

        NumericalMatrix result = numericalMatrix.add(matrixB);
        for (int i = 0; i < numericalMatrix.getRowNumbers(); i++) {
            for (int j = 0; j < numericalMatrix.getColumnNumbers(); j++) {
                assertEquals(numericalMatrix.getElement(i, j) * 2, result.getElement(i, j));
            }
        }
    }

    @Test
    public void testScalarMultiply() {
        NumericalMatrix result = numericalMatrix.multiply(3);
        for (int i = 0; i < numericalMatrix.getRowNumbers(); i++) {
            for (int j = 0; j < numericalMatrix.getColumnNumbers(); j++) {
                assertEquals(numericalMatrix.getElement(i, j) * 3, result.getElement(i, j));
            }
        }
    }

    @Test
    public void testMatrixMultiply() {
        NumericalMatrix matrixA = new NumericalMatrix(2, 3);
        matrixA.setMatrix(new double[][]{{2, 3, -1}, {6, 1, -2}});

        NumericalMatrix matrixB = new NumericalMatrix(3, 2);
        matrixB.setMatrix(new double[][]{{4, -5}, {-3, 0}, {1, 2}});

        NumericalMatrix result = matrixA.multiply(matrixB);
        assertEquals(2, result.getRowNumbers());
        assertEquals(2, result.getColumnNumbers());

        assertEquals(-2, result.getElement(0, 0));
        assertEquals(-12, result.getElement(0, 1));
        assertEquals(19, result.getElement(1, 0));
        assertEquals(-34, result.getElement(1, 1));
    }

    @Test
    public void testMatrixTranspose() {
        NumericalMatrix matrixA = new NumericalMatrix(3, 2);
        matrixA.setMatrix(new double[][]{{1, 2}, {3, 4}, {5, 6}});

        NumericalMatrix result = matrixA.transpose();
        assertEquals(2, result.getRowNumbers());
        assertEquals(3, result.getColumnNumbers());

        assertEquals(1, result.getElement(0, 0));
        assertEquals(3, result.getElement(0, 1));
        assertEquals(5, result.getElement(0, 2));
        assertEquals(2, result.getElement(1, 0));
        assertEquals(4, result.getElement(1, 1));
        assertEquals(6, result.getElement(1, 2));
    }

    @Test
    public void testDiagonalMatrix() {
        NumericalMatrix matrix = NumericalMatrix.diagonalMatrix(new double[]{1, 2, 3, 4});
        assertEquals(1, matrix.getElement(0, 0));
        assertEquals(2, matrix.getElement(1, 1));
        assertEquals(3, matrix.getElement(2, 2));
        assertEquals(4, matrix.getElement(3, 3));
    }

    @Test
    public void testIdentityMatrix() {
        NumericalMatrix matrix = NumericalMatrix.identityMatrix(2);
        assertEquals(1, matrix.getElement(0, 0));
        assertEquals(1, matrix.getElement(1, 1));
    }

    @Test
    public void testDetermineMatrix() {
        double determinant = numericalMatrix.determinant();
        assertEquals(-28, determinant);
    }

    @Test
    public void testInvertibleMatrix() {
        NumericalMatrix matrix = new NumericalMatrix(new double[][]{{1, 3, -5}, {0, 1, 2}, {0, 0, 1}});
        NumericalMatrix result = matrix.invertibleMatrix();
        assertEquals(1, result.getElement(0, 0));
        assertEquals(-3, result.getElement(0, 1));
        assertEquals(11, result.getElement(0, 2));
        assertEquals(-0.0, result.getElement(1, 0));
        assertEquals(1, result.getElement(1, 1));
        assertEquals(-2, result.getElement(1, 2));
        assertEquals(0, result.getElement(2, 0));
        assertEquals(-0.0, result.getElement(2, 1));
        assertEquals(1, result.getElement(2, 2));
    }

    @Test
    public void testThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> numericalMatrix.getColumn(-1));
        assertThrows(IllegalArgumentException.class, () -> numericalMatrix.getColumn(3));
        assertThrows(IllegalArgumentException.class, () -> numericalMatrix.getRow(-1));
        assertThrows(IllegalArgumentException.class, () -> numericalMatrix.getRow(3));
        assertThrows(IllegalArgumentException.class, () -> numericalMatrix.getElement(-1, 1));
        assertThrows(IllegalArgumentException.class, () -> numericalMatrix.getElement(1, -1));
        assertThrows(IllegalArgumentException.class, () -> numericalMatrix.setElement(3, 1, 99));
        assertThrows(IllegalArgumentException.class, () -> numericalMatrix.setElement(1, 3, 99));
        assertThrows(IllegalArgumentException.class, () -> numericalMatrix.add(new NumericalMatrix()));
        assertThrows(IllegalArgumentException.class, () -> numericalMatrix.multiply(new NumericalMatrix(4, 3)));
    }


}
