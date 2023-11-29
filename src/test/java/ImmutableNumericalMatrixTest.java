import kuz.lab2.ImmutableNumericalMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImmutableNumericalMatrixTest {
    private ImmutableNumericalMatrix immutableNumericalMatrix;

    @BeforeEach
    public void init() {
        /*
            1 2 3
            3 2 1
            7 4 8
         */
        immutableNumericalMatrix = new ImmutableNumericalMatrix(new double[][]{{1, 2, 3}, {3, 2, 1}, {7, 4, 8}});
    }

    @Test
    public void testGetMatrixElement() {
        assertEquals(1, immutableNumericalMatrix.getElement(0, 0));
    }

    @Test
    public void tesGetMatrixColumn() {
        double[] column = immutableNumericalMatrix.getColumn(2);
        assertArrayEquals(new double[]{3, 1, 8}, column);
    }

    @Test
    public void tesGetMatrixRow() {
        double[] row = immutableNumericalMatrix.getRow(0);
        assertArrayEquals(new double[]{1, 2, 3}, row);
    }

    @Test
    public void testGetRowNumbers() {
        assertEquals(3, immutableNumericalMatrix.getRowNumbers());
    }

    @Test
    public void testGetColumnNumbers() {
        assertEquals(3, immutableNumericalMatrix.getColumnNumbers());
    }

    @Test
    public void testDimension() {
        assertEquals(9, immutableNumericalMatrix.dimension());
    }



}
