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
    String[] keys;

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
        keys = columnNames;

        Object[][] columns = new Object[records.get(0).size()][];

        for(int c = 0; c < records.get(0).size(); c++) {
            Object[] column = new Object[records.size() - 1];
            for (int l = 1, i = 0; l < records.size(); l++, i++) {
                column[i] = Integer.parseInt(records.get(l).get(c));
            }
            columns[c] = column;
        }

        createDataframe(columnNames, columns);
    }

    public void createDataframe(String[] columnNames, Object[]... columnArrays){
        matElements = new HashMap<String, Vector<Object>>();

        keys = columnNames;
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
            Float m = sum(key);
            if(m != null) res.put(key,m);
        }
        return res;
    }

    /**
     *
     * @return The sum of the chosen column or throws NotANumberException if elements are not Integers, Floats or Doubles
     */
    public Float sum(String key) throws NotANumberException {

        if(!matElements.containsKey(key)) return null;

        Vector<Object> vec = matElements.get(key);

        if(vec.isEmpty()) return null;

        float sum = 0;
        for (int i = 0; i < vec.size(); i++) {
            if(!isANumber(vec.get(i)))throw new NotANumberException();
            sum +=  Float.parseFloat(vec.get(i).toString());
        }

        return sum;
    }

    /**
     *
     * @return The mean of each column or throws NotANumberException if elements are not Integers, Floats or Doubles
     */
    public HashMap<String, Float> mean() throws NotANumberException {
        HashMap<String, Float> res = new HashMap<>();

        for (String key : matElements.keySet()){
            Float m = mean(key);
            if(m != null) res.put(key,m);
        }

        return res;
    }

    /**
     *
     * @return The mean of the chosen column or throws NotANumberException if elements are not Integers, Floats or Doubles
     */
    public Float mean(String key) throws NotANumberException {

        if(!matElements.containsKey(key)) return null;

        Vector<Object> vec = matElements.get(key);

        if(vec.isEmpty()){
            return null;

        }else{
            float sum = 0;
            for (int i = 0; i < vec.size(); i++) {
                if(!isANumber(vec.get(i)))throw new NotANumberException();
                sum +=  Float.parseFloat(vec.get(i).toString());
            }
            return sum/vec.size();
        }
    }

    /**
     *
     * @return The max of each column or throws NotANumberException if elements are not Integers, Floats or Doubles
     */
    public HashMap<String, Float> max() throws NotANumberException {
        HashMap<String, Float> res = new HashMap<>();

        for (String key : matElements.keySet()){
            Float m = max(key);
            if(m != null) res.put(key,m);
        }

        return res;
    }

    /**
     *
     * @return The max of the chosen column or throws NotANumberException if elements are not Integers, Floats or Doubles
     */
    public Float max(String key) throws NotANumberException {

        if(!matElements.containsKey(key)) return null;

        Vector<Object> vec = matElements.get(key);

        if(!vec.isEmpty()){

            if(!isANumber(vec.get(0)))throw new NotANumberException();

            float m = Float.parseFloat(vec.get(0).toString());
            for (int i = 0; i < vec.size(); i++) {
                if(m < Float.parseFloat(vec.get(i).toString())){
                    m = Float.parseFloat(vec.get(i).toString());
                }
            }

            return m;
        }

        return null;
    }

    /**
     *
     * @return The min of each column or throws NotANumberException if elements are not Integers, Floats or Doubles
     */
    public HashMap<String, Float> min() throws NotANumberException {
        HashMap<String, Float> res = new HashMap<>();

        for (String key : matElements.keySet()){
            Float m = min(key);
            if(m != null) res.put(key,m);
        }
        return res;
    }

    /**
     *
     * @return The min of the chosen column or throws NotANumberException if elements are not Integers, Floats or Doubles
     */
    public Float min(String key) throws NotANumberException {

        if(!matElements.containsKey(key)) return null;

        Vector<Object> vec = matElements.get(key);

        if(!vec.isEmpty()){

            if(!isANumber(vec.get(0)))throw new NotANumberException();

            float m = Float.parseFloat(vec.get(0).toString());
            for (int i = 0; i < vec.size(); i++) {
                if(m > Float.parseFloat(vec.get(i).toString())){
                    m = Float.parseFloat(vec.get(i).toString());
                }
            }
            return m;
        }

        return null;
    }

    public Vector<Vector<Object>> getColumns(String[] colNames){
        Vector<Vector<Object>> columns = new Vector<Vector<Object>>();
        for(int i = 0; i < colNames.length; i++){
            columns.add(matElements.get(colNames[i]));
        }
        return columns;
    }

    /**
     *
     * @return A vector of vector for each row for each index of idxRows
     */
    public Vector<Vector<Object>> getRows(int[] idxRows) {
        Vector<Vector<Object>> res = new Vector<Vector<Object>>();

        for (int i = 0; i < idxRows.length; i++) {
            Vector<Object> row = new Vector<Object>();
            for (String key : keys) {
                Vector<Object> column = matElements.get(key);
                if (idxRows[i] < column.size()) {
                    row.add(column.get(idxRows[i]));
                }
                else {
                    //Si on a des colonnes plus petites que d'autre
                    row.add("Nan");
                }
            }
            res.add(row);
        }

        return res;
    }
}
