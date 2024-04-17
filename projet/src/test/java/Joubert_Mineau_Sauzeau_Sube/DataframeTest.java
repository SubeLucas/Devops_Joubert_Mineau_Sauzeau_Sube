package test.java.Joubert_Mineau_Sauzeau_Sube;

import main.java.Joubert_Mineau_Sauzeau_Sube.Dataframe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
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

    private static final String input_path = "src/test/java/Joubert_Mineau_Sauzeau_Sube/";

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

        String fileName = "testWithSeveralColumn.txt";
        try {
            File myObj = new File(fileName);
            myObj.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

        df.display_lines(fileName,2,5);
        df.display_first_lines(fileName, 5);
    }

    @Test
    public void testWithSeveralColumnTypesAndPrint()
    {
        String[] columnNames = new String[] {"col1", "col2", "col3", "very long column :(("};
        Object[] column1 = new Object[] {1, 2, 3, 4, 5, 6, 7 };
        Object[] column4 = new Object[] {};
        Object[] column2 = new Object[] {1.0, 5.8, 9.2, 1.548521, 4.3, 7.6, 9.54410};
        Object[] column3 = new Object[] {"Antoine", "Sylvain", "Luca", "Oliver", "Very long string > 30 !!!!!!!!!!!!!!!!!!!!"};
        Dataframe df = new Dataframe(columnNames, column1, column2, column3, column4);


        String fileName = "testPrintAll.txt";
        String fileName_res = "testPrintAll_.txt";
        String fileName2 = "testPrintFirst.txt";
        String fileName2_res = "testPrintFirst_.txt";
        String fileName3 = "testPrintFromTo.txt";
        String fileName3_res = "testPrintFromTo_.txt";


        try {
            File myObj = new File(fileName);
            myObj.createNewFile();
            File myObj2 = new File(fileName2);
            myObj2.createNewFile();
            File myObj3 = new File(fileName3);
            myObj3.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }


        df.display_all_lines(input_path + fileName);
        df.display_first_lines(input_path + fileName2,5);
        df.display_lines(input_path + fileName3,2, 7);

        Assertions.assertTrue(sameFiles(input_path + fileName, input_path + fileName_res));
        Assertions.assertTrue(sameFiles(input_path + fileName2, input_path + fileName2_res));
        Assertions.assertTrue(sameFiles(input_path + fileName3, input_path + fileName3_res));

    }

    @Test
    public void readFileAndPrintAll(){

        try {
            Dataframe df = new Dataframe(input_path + "input_test1.txt");

            String fileName = "testReadPrintAll.txt";
            String fileName_res = "testReadPrintAll_.txt";

            File myObj = new File(fileName);
            myObj.createNewFile();

            df.display_all_lines(input_path + fileName);
            Assertions.assertTrue(sameFiles(input_path + fileName, input_path + fileName_res));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void testCreationWithFile1() throws IOException {
        Dataframe df = new Dataframe(input_path + "input_test1.txt");
        Object[] c1 = new Object[] {1, 5, 19, 8412};
        Object[] c2 = new Object[] {2, 9, 502, 5936458};
        Object[] c3 = new Object[] {3, 6, 13, 861265};

        Assertions.assertTrue(equalsObjectVectors(df.getColumn("c1"), new Vector<>(Arrays.asList(c1))));
        Assertions.assertTrue(equalsObjectVectors(df.getColumn("c2"), new Vector<>(Arrays.asList(c2))));
        Assertions.assertTrue(equalsObjectVectors(df.getColumn("c3"), new Vector<>(Arrays.asList(c3))));
    }

    @Test
    public void testPrinting (){

    }

    @Test
    public void testgetRows() throws IOException{
        Dataframe df = new Dataframe(input_path + "input_test1.txt");

        //Object[] c1 = new Object[] {1, 5, 19, 8412};
        //Object[] c2 = new Object[] {2, 9, 502, 5936458};
        //Object[] c3 = new Object[] {3, 6, 13, 861265};

        Object[] l1 = new Object[] {1, 2, 3};
        //Object[] l2 = new Object[] {5, 9, 6};
        Object[] l3 = new Object[] {19, 502, 13};
        //Object[] l4 = new Object[] {8412, 5936458, 861265};



        Vector<Vector<Object>> vect = new Vector();
        vect.add(new Vector<>(Arrays.asList(l1)));
        vect.add(new Vector<>(Arrays.asList(l3)));

        int[] idx = {0,2};  //l1 et l3

        Vector<Vector<Object>> vect2 = new Vector();
        vect2 = df.getRows(idx);

        Assertions.assertTrue(equalsObjectVectors(vect.get(0), vect2.get(0)));
        Assertions.assertTrue(equalsObjectVectors(vect.get(1), vect2.get(1)));
    }

    @Test
    public void testgetRowsRobustesse() throws IOException{
        Dataframe df = new Dataframe(input_path + "input_test1.txt");

        //Object[] c1 = new Object[] {1, 5, 19, 8412};
        //Object[] c2 = new Object[] {2, 9, 502, 5936458};
        //Object[] c3 = new Object[] {3, 6, 13, 861265};

        Object[] l1 = new Object[] {1, 2, 3};
        //Object[] l2 = new Object[] {5, 9, 6};
        Object[] l3 = new Object[] {19, 502, 13};
        //Object[] l4 = new Object[] {8412, 5936458, 861265};
        Object[] vide = new Object[] {"Nan", "Nan", "Nan"};



        // vecteur attendu
        Vector<Vector<Object>> vect = new Vector();
        vect.add(new Vector<>(Arrays.asList(l1)));
        vect.add(new Vector<>(Arrays.asList(l3)));
        vect.add(new Vector<>(Arrays.asList(vide)));

        //Les indices des lignes selectionnées
        int[] idx = {0,2,12};  //l1 et l3 + une ligne trop loin

        Vector<Vector<Object>> vect2 = new Vector();
        vect2 = df.getRows(idx);


        Assertions.assertTrue(equalsObjectVectors(vect.get(0), vect2.get(0)));
        Assertions.assertTrue(equalsObjectVectors(vect.get(1), vect2.get(1)));

        //A mettre quand comparaison de String faite dans equalsObjectVectors
        //Assertions.assertTrue(equalsObjectVectors(vect.get(2), vect2.get(2)));
    }



    @Test
    public void testGetMultiplesColumns() throws IOException {
        Dataframe df = new Dataframe("src/test/java/Joubert_Mineau_Sauzeau_Sube/input_test2.txt");
        Object[] c2 = new Object[] {852, 15, 25, 21};
        Object[] c4 = new Object[] {852, 52, 65, 952};

        Vector<Vector<Object>> columns = df.getColumns(new String[] {"c2", "c4"});

        Assertions.assertTrue(equalsObjectVectors(columns.get(0), new Vector<>(Arrays.asList(c2))));
        Assertions.assertTrue(equalsObjectVectors(columns.get(1), new Vector<>(Arrays.asList(c4))));
    }

    private Boolean sameFiles(String file1, String file2){

        try {
            File myObj = new File(file1);
            myObj.createNewFile();
            File myObj2 = new File(file2);
            myObj2.createNewFile();

            return Files.mismatch(myObj.toPath(), myObj2.toPath()) == -1;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
