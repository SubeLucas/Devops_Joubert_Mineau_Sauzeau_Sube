package test.java.Joubert_Mineau_Sauzeau_Sube;

import main.java.Joubert_Mineau_Sauzeau_Sube.Dataframe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

/**
 * Unit test for simple App.
 */
public class DataframeTest
{
    /**
     * Rigorous Test :-)
     */

    public boolean equalsObjectVectors(Vector<Object> arr1, Vector<Object> arr2){
        if(arr1.size() != arr2.size()) {
            return false;
        }

        for(int i = 0; i < arr1.size(); i++){
            //if(!(arr1.get(i).toString()).equals(arr2.get(i).toString())) {
            if ((arr1.get(i) instanceof Integer)) {
                if(((int) arr1.get(i)) != ((int) arr2.get(i))) {
                    return false;
                }
            }
            else if((arr1.get(i) instanceof String)){
                return false;
            }
        }
        return true;
    }
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

    @Test
    public void testCreationWithFile1() throws IOException {
        Dataframe df = new Dataframe("src/test/java/Joubert_Mineau_Sauzeau_Sube/input_test1.txt");
        Object[] c1 = new Object[] {1, 5, 19, 8412};
        Object[] c2 = new Object[] {2, 9, 502, 5936458};
        Object[] c3 = new Object[] {3, 6, 13, 861265};

        Assertions.assertTrue(equalsObjectVectors(df.getColumn("c1"), new Vector<>(Arrays.asList(c1))));
        Assertions.assertTrue(equalsObjectVectors(df.getColumn("c2"), new Vector<>(Arrays.asList(c2))));
        Assertions.assertTrue(equalsObjectVectors(df.getColumn("c3"), new Vector<>(Arrays.asList(c3))));
    }
}
