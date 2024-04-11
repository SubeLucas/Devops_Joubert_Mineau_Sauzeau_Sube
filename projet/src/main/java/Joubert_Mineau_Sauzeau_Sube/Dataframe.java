//package Joubert_Mineau_Sauzeau_Sube;
package test.java.Joubert_Mineau_Sauzeau_Sube;

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
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(new Vector<String> (Arrays.asList(values)));
            }
        }

        String[] columnNames = new String[records.get(0).size()];
        columnNames = Arrays.stream(records.get(0).toArray()).toArray(String[]::new);

        Object[][] columns = new Object[records.get(0).size()][];

        for(int c = 0; c < records.get(0).size(); c++){
            Object[] column = new Object[records.size()-1];
            for(int l = 1, i = 0;l < records.size(); l++, i++){
                column[i] = Integer.parseInt(records.get(l).get(c));
            }
            columns[c] = column;
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


    private void print_column_names(String tab){
        for (String key : matElements.keySet()){System.out.print(key + tab);}
        System.out.println();
    }

    private int get_size_column_max (){
        int maxi = 0;
        for(Vector<Object> vec : matElements.values()){
            if (vec.size() > maxi) maxi = vec.size();
        }
        return maxi;
    }
    private void print_lines(String tab, int from, int to){
        int i = from;
        boolean endlist = true;
        int max_size = get_size_column_max();

        while (endlist && i != to && i < max_size){
            endlist = false;
            for (Vector<Object> vec : matElements.values()){
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

    public void display_first_lines(int number_of_lines){
        String tab = "      ";
        print_column_names(tab);
        print_lines(tab, 0, number_of_lines);
    }

    public void display_first_lines(){
        display_first_lines(5);
    }

    public void display_all_lines (){
        String tab = "      ";
        print_column_names(tab);
        print_lines(tab, 0, -1);
    }


    public Vector<Object> getColumn(String columnName){
        return matElements.get(columnName);
    }
}
