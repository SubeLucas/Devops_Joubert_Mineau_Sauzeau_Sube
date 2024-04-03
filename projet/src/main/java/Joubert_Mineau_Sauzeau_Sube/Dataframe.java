package main.java.Joubert_Mineau_Sauzeau_Sube;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dataframe {

    HashMap<String, Vector<Object>> matElements;

    public Dataframe(String[] columnNames, Object[]... columnArrays){
        createDataframe(columnNames, columnArrays);
    }

    public Dataframe(String filename) throws IOException {

        Vector<Vector<String>> records = new Vector<>();
        try (BufferedReader br = new BufferedReader(new FileReader("book.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add((Vector<String>) Arrays.asList(values));
            }
        }

        String[] columnNames = new String[records.get(0).size()];
        columnNames = (String[]) records.get(0).toArray();

        Object[][] columns = new Object[records.size()-1][];
        for(int l = 1; l < records.size(); l++){
            Object[] column = new Object[records.size()];
            for(int c = 0; c < records.get(l).size(); c++){
                column[c] = records.get(l).get(c);
            }
            columns[l-1] = column;
        }

        createDataframe(columnNames, columns);
    }

    public void createDataframe(String[] columnNames, Object[]... columnArrays){
        matElements = new HashMap<String, Vector<Object>>();

        int i = 0;
        String columnName = "";
        for(Object[] columnArray : columnArrays){
            Vector<Object> columnVector = new Vector<>(Arrays.asList(columnArray));
            if (i >= columnNames.length) {
                columnName = String.valueOf(i);
            }
            else{
                columnName = columnNames[i];
            }
            matElements.put(columnName, columnVector);
            i++;
        }
    }

    public void print (){
        String tab = "      ";
        for (String key : matElements.keySet()){System.out.print(key + tab);}
        System.out.println();
        int i = 0;
        boolean endlist = true;
        while (endlist){
            endlist = false;
            for (String key : matElements.keySet()){
                Vector<Object> vec = matElements.get(key);
                if(i < vec.size()) {
                    System.out.print(vec.get(i) + tab);
                    endlist = true;
                }else{
                    System.out.print("Nan" + tab);
                }
            }
            i++;
            System.out.println();
        }
    }
}
