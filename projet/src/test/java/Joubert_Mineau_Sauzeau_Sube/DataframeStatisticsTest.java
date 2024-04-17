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

        HashMap<String, Float> resExpected = createResult();
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

    @Test
    public void testSumSelectColString()
    {
        Dataframe df = createDataframe(
                new Object[] {"Hello", ",", "I", "Am", "A", "Test"});

        assertThrows(NotANumberException.class, () -> {
            df.sum("col1");
        });
    }

    @Test
    public void testSumSelectColFloat()
    {
        Dataframe df = createDataframe(
                new Object[] {1.1f, 2.2f, 3.3f, 4.4f, 5.5f},
                new Object[] {6.6f, 7.7f, 8.8f, 9.9f, 10.1f},
                new Object[] {11.11f, 12.12f, 13.13f, 14.14f, 15.15f});

        Float resExpected = 65.65f;
        Float resCalculated = df.sum("col3");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testSumSelectColInt()
    {
        Dataframe df = createDataframe(
                new Object[] {1, 2, 3, 4, 5},
                new Object[] {6, 7, 8, 9, 10},
                new Object[] {11, 12, 13, 14, 15});

        Float resExpected = 40f;
        Float resCalculated = df.sum("col2");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testSumSelectColSpecialCase0()
    {
        Dataframe df = createDataframe(
                new Object[] {});

        Float resExpected = null;
        Float resCalculated = df.sum("col1");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testSumSelectColSpecialCase1()
    {
        Dataframe df = createDataframe(
                new Object[] {});

        Float resExpected = null;
        Float resCalculated = df.sum("col2");

        Assertions.assertEquals(resExpected, resCalculated);
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

        HashMap<String, Float> resExpected = createResult();
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

    @Test
    public void testMeanSelectColString()
    {
        Dataframe df = createDataframe(
                new Object[] {"Hello", ",", "I", "Am", "A", "Test"});

        assertThrows(NotANumberException.class, () -> {
            df.mean("col1");
        });
    }

    @Test
    public void testMeanSelectColFloat()
    {
        Dataframe df = createDataframe(
                new Object[] {1.1f, 2.2f, 3.3f, 4.4f, 5.5f},
                new Object[] {6.6f, 7.7f, 8.8f, 9.9f, 10.1f},
                new Object[] {11.11f, 12.12f, 13.13f, 14.14f, 15.15f});

        Float resExpected = 13.13f;
        Float resCalculated = df.mean("col3");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMeanSelectColInt()
    {
        Dataframe df = createDataframe(
                new Object[] {1, 2, 3, 4, 5},
                new Object[] {6, 7, 8, 9, 10},
                new Object[] {11, 12, 13, 14, 15});

        Float resExpected = 8f;
        Float resCalculated = df.mean("col2");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMeanSelectColSpecialCase0()
    {
        Dataframe df = createDataframe(
                new Object[] {});

        Float resExpected = null;
        Float resCalculated = df.mean("col1");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMeanSelectColSpecialCase1()
    {
        Dataframe df = createDataframe(
                new Object[] {});

        Float resExpected = null;
        Float resCalculated = df.mean("col2");

        Assertions.assertEquals(resExpected, resCalculated);
    }


    /**
     * Max Tests
     */

    @Test
    public void testMaxIntegers()
    {
        Dataframe df = createDataframe(
                new Object[]{2, 2, 5, 5, 2, 2 },
                new Object[] {-100, -200, -300, -400},
                new Object[] {0,0,0,0,0,0,0});

        HashMap<String, Float> resExpected = createResult(5f, -100f, 0f);
        HashMap<String, Float> resCalculated = df.max();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMaxFloats()
    {
        Dataframe df = createDataframe(
                new Object[] {10f, 20f, 20f, -100f, 10f, 10f },
                new Object[] {0f, 0f, 0f, 18f, 0f, 0f });

        HashMap<String, Float> resExpected = createResult(20f, 18f);
        HashMap<String, Float> resCalculated = df.max();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMaxSpecialCase0()
    {
        Dataframe df = createDataframe(
                new Object[] {});

        HashMap<String, Float> resExpected = createResult();
        HashMap<String, Float> resCalculated = df.max();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMaxSelectColString()
    {
        Dataframe df = createDataframe(
                new Object[] {"Hello", ",", "I", "Am", "A", "Test"});

        assertThrows(NotANumberException.class, () -> {
            df.max("col1");
        });
    }

    @Test
    public void testMaxSelectColFloat()
    {
        Dataframe df = createDataframe(
                new Object[] {1.1f, 2.2f, 3.3f, 4.4f, 5.5f},
                new Object[] {6.6f, 7.7f, 8.8f, 9.9f, 10.1f},
                new Object[] {11.11f, 12.12f, 13.13f, 14.14f, 15.15f});

        Float resExpected = 15.15f;
        Float resCalculated = df.max("col3");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMaxSelectColInt()
    {
        Dataframe df = createDataframe(
                new Object[] {1, 2, 3, 4, 5},
                new Object[] {6, 7, 8, 9, 10},
                new Object[] {11, 12, 13, 14, 15});

        Float resExpected = 10f;
        Float resCalculated = df.max("col2");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMaxSelectColSpecialCase0()
    {
        Dataframe df = createDataframe(
                new Object[] {});

        Float resExpected = null;
        Float resCalculated = df.max("col1");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMaxSelectColSpecialCase1()
    {
        Dataframe df = createDataframe(
                new Object[] {});

        Float resExpected = null;
        Float resCalculated = df.max("col2");

        Assertions.assertEquals(resExpected, resCalculated);
    }


    /**
     * Min Tests
     */

    @Test
    public void testMinIntegers()
    {
        Dataframe df = createDataframe(
                new Object[]{2, 2, 5, 5, 2, 2 },
                new Object[] {-100, -200, -300, -400},
                new Object[] {0,0,0,0,0,0,0});

        HashMap<String, Float> resExpected = createResult(2f, -400f, 0f);
        HashMap<String, Float> resCalculated = df.min();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMinFloats()
    {
        Dataframe df = createDataframe(
                new Object[] {10f, 20f, 20f, -100f, 10f, 10f },
                new Object[] {0f, 0f, 0f, 18f, 0f, 0f });

        HashMap<String, Float> resExpected = createResult(-100f, 0f);
        HashMap<String, Float> resCalculated = df.min();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMinSpecialCase0()
    {
        Dataframe df = createDataframe(
                new Object[] {});

        HashMap<String, Float> resExpected = createResult();
        HashMap<String, Float> resCalculated = df.min();

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMinSelectColString()
    {
        Dataframe df = createDataframe(
                new Object[] {"Hello", ",", "I", "Am", "A", "Test"});

        assertThrows(NotANumberException.class, () -> {
            df.min("col1");
        });
    }

    @Test
    public void testMinSelectColFloat()
    {
        Dataframe df = createDataframe(
                new Object[] {1.1f, 2.2f, 3.3f, 4.4f, 5.5f},
                new Object[] {6.6f, 7.7f, 8.8f, 9.9f, 10.1f},
                new Object[] {11.11f, 12.12f, 13.13f, 14.14f, 15.15f});

        Float resExpected = 11.11f;
        Float resCalculated = df.min("col3");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMinSelectColInt()
    {
        Dataframe df = createDataframe(
                new Object[] {1, 2, 3, 4, 5},
                new Object[] {6, 7, 8, 9, 10},
                new Object[] {11, 12, 13, 14, 15});

        Float resExpected = 6f;
        Float resCalculated = df.min("col2");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMinSelectColSpecialCase0()
    {
        Dataframe df = createDataframe(
                new Object[] {});

        Float resExpected = null;
        Float resCalculated = df.min("col1");

        Assertions.assertEquals(resExpected, resCalculated);
    }

    @Test
    public void testMinSelectColSpecialCase1()
    {
        Dataframe df = createDataframe(
                new Object[] {});

        Float resExpected = null;
        Float resCalculated = df.min("col2");

        Assertions.assertEquals(resExpected, resCalculated);
    }


}
