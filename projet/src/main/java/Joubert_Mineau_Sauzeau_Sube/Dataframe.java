package main.java.Joubert_Mineau_Sauzeau_Sube;

import Joubert_Mineau_Sauzeau_Sube.NotANumberException;

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

    public Vector<Object> getColumn(String columnName){
        return matElements.get(columnName);
    }


    private boolean isANumber(Object obj){
        return obj instanceof Float || obj instanceof Integer ||
                obj instanceof Double || obj instanceof Long;
    }

    /**
     *
     * @return The sum of each column or throws NotANumberException if elements are not Integers, Floats or Doubles
     */
    public HashMap<String, Float> sum() throws NotANumberException {
        HashMap<String, Float> res = new HashMap<>();

        for (String key : matElements.keySet()){
            Vector<Object> vec = matElements.get(key);

            float sum = 0;
            for (int i = 0; i < vec.size(); i++) {
                if(!isANumber(vec.get(i)))throw new NotANumberException();
                sum +=  Float.parseFloat(vec.get(i).toString());
            }
            res.put(key,sum);

        }
        return res;
    }

    /**
     *
     * @return The mean of each column or throws NotANumberException if elements are not Integers, Floats or Doubles
     */
    public HashMap<String, Float> mean() throws NotANumberException {
        HashMap<String, Float> res = new HashMap<>();

        for (String key : matElements.keySet()){
            Vector<Object> vec = matElements.get(key);

            if(vec.isEmpty()){
                res.put(key,0f);

            }else{
                float sum = 0;
                for (int i = 0; i < vec.size(); i++) {
                    if(!isANumber(vec.get(i)))throw new NotANumberException();
                    sum +=  Float.parseFloat(vec.get(i).toString());
                }
                res.put(key,sum/vec.size());
            }
        }
        return res;
    }






}
