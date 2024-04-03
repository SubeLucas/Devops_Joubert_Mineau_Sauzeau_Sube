package test.java.Joubert_Mineau_Sauzeau_Sube;

import main.java.Joubert_Mineau_Sauzeau_Sube.Dataframe;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class DataframeTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testWithSeveralColumn()
    {
        String[] columnNames = new String[] {"col1", "col2", "col3"};
        Object[] column1 = new Object[] {1, 2, 3, 4, 5, 6 };
        Object[] column2 = new Object[] {88, 89, 90, 91, 92, 93, 94 };
        Object[] column3 = new Object[] {-1, -2, -3, -4, -5, -6, -7 };
        Dataframe df = new Dataframe(columnNames, column1, column2, column3);
    }

    @Test
    public void testWithSeveralColumnTypes()
    {
        String[] columnNames = new String[] {"col1", "col2", "col3"};
        Object[] column1 = new Object[] {1, 2, 3, 4, 5, 6 };
        Object[] column2 = new Object[] {1.0, 5.8, 9.2, 1.5, 4.3, 7.6};
        Object[] column3 = new Object[] {"Antoine", "Sylvain", "Luca", "Oliver"};
        Dataframe df = new Dataframe(columnNames, column1, column2, column3);
    }
}
