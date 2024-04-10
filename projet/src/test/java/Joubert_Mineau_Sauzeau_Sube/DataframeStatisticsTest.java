package test.java.Joubert_Mineau_Sauzeau_Sube;

import Joubert_Mineau_Sauzeau_Sube.NotANumberException;
import main.java.Joubert_Mineau_Sauzeau_Sube.Dataframe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class DataframeStatisticsTest
{

    private Dataframe createDataframe(Object[]... arrays){

        ArrayList<String> names = new ArrayList<>();
        for(int i = 1; i <= arrays.length; i++){
            names.add("col" + i);
        }
        String[] columnNames = names.toArray(new String[0]);
        return new Dataframe(columnNames, arrays);
    }

    private HashMap<String, Float> createResult(Float... results){
        HashMap<String, Float> result = new HashMap<>();
        for(int i = 1; i <= results.length; i++){
            result.put("col" + i, results[i-1]);
        }
        return result;
    }


    /**
     * Sum Tests
     */

    @Test
    public void testSumPositiveIntegers()
    {
        Dataframe df = createDataframe(
                new Object[]{2, 2, 5, 5, 2, 2 },
                new Object[] {100, 200, 300, 400, 500, 600 },
                new Object[] {0,0,0,0,0,0,0});

        HashMap<String, Float> resExpected = createResult(18f, 2100f, 0f);
        HashMap<String, Float> resCalculated = df.sum();

        Assertions.assertEquals(resCalculated, resExpected);

    }

    @Test
    public void testSumIntegersCase0()
    {
        Dataframe df = createDataframe(
                new Object[]{});

        HashMap<String, Float> resExpected = createResult(0f);
        HashMap<String, Float> resCalculated = df.sum();

        Assertions.assertEquals(resExpected, resCalculated);

    }

    @Test
    public void testSumNegativeIntegers()
    {
        Dataframe df = createDataframe(
                new Object[] {-2, -2, -5, -5, -2, -2 },
                new Object[] {-100, 200, -300, 400, -500, 600 },
                new Object[] {-1, -2, -3, -4, -5, 15 });

        HashMap<String, Float> resExpected = createResult(-18f, 300f, 0f);
        HashMap<String, Float> resCalculated = df.sum();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testSumPositiveFloats()
    {
        Dataframe df = createDataframe(
                new Object[] {20f, 45f, 63f, 1234f, 94f, 722f });

        HashMap<String, Float> resExpected = createResult(2178f);
        HashMap<String, Float> resCalculated = df.sum();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testSumNegativeFloats()
    {
        Dataframe df = createDataframe(
                new Object[] {-20f, -45f, -63f, -1234f, 94f, 722f });

        HashMap<String, Float> resExpected = createResult(-546f);
        HashMap<String, Float> resCalculated = df.sum();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testSumStrings()
    {
        Dataframe df = createDataframe(
                new Object[] {"Hello", "World","Devops"});

        assertThrows(NotANumberException.class, () -> {
            df.sum();
        });
    }

    @Test
    public void testSumMulti()
    {
        Dataframe df = createDataframe(
                new Object[] {2, 2, 5, 5, 2, 2 },
                new Object[] {100f, 200f, 300f, 400f, 500f, 600f},
                new Object[] {"Hello", "World","Devops"});

        assertThrows(NotANumberException.class, () -> {
            df.sum();
        });
    }


    /**
     * Mean Tests
     */

    @Test
    public void testMeanIntegers()
    {
        Dataframe df = createDataframe(
                new Object[]{2, 2, 5, 5, 2, 2 },
                new Object[] {-100, -200, -300, -400},
                new Object[] {0,0,0,0,0,0,0});

        HashMap<String, Float> resExpected = createResult(3f, -250f, 0f);
        HashMap<String, Float> resCalculated = df.mean();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMeanIntegersSpecialCase0()
    {
        Dataframe df = createDataframe(
                new Object[]{});

        HashMap<String, Float> resExpected = createResult(0f);
        HashMap<String, Float> resCalculated = df.mean();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMeanFloats()
    {
        Dataframe df = createDataframe(
                new Object[] {10f, 20f, 20f, -100f, 10f, 10f },
                new Object[] {0f, 0f, 0f, 18f, 0f, 0f });

        HashMap<String, Float> resExpected = createResult(-5f, 3f);
        HashMap<String, Float> resCalculated = df.mean();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMeanStrings()
    {
        Dataframe df = createDataframe(
                new Object[] {"AaA", "BbB", "CcC", "DdD"});

        assertThrows(NotANumberException.class, () -> {
            df.mean();
        });
    }

}
