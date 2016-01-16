import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by fredericmoungpacrijanot on 15/01/2016.
 */
public class GloutonTest {

    int init;
    int actions;
    List<Bandit> bandits;
    List<Integer> b1;
    List<Integer> b2;
    List<Integer> b3;
    List<Integer> b4;
    Bandit bandit1;
    Bandit bandit2;
    Bandit bandit3;
    Bandit bandit4;
    Glouton glouton;
    List<Bandit> result;

    @Before
    public void setup() {
        init =2;
        actions = 20;
        bandits = new ArrayList<>();
        b1 = Arrays.asList(1,0,1,0,1,0);
        b2 = Arrays.asList(0,1,0,1,0,1);
        b3 = Arrays.asList(1,1,0,0,1,1,0,0,1,1);
        b4 = Arrays.asList(0,0,1,1,0,0,1,1,0,0);
        bandit1 = new Bandit(b1,init, 1);
        bandit2 = new Bandit(b2,init, 2);
        bandit3 = new Bandit(b3,init, 3);
        bandit4 = new Bandit(b4,init, 4);
        bandits.add(bandit1);
        bandits.add(bandit2);
        bandits.add(bandit3);
        bandits.add(bandit4);
        glouton = new Glouton(actions, bandits);
        result = glouton.ComputeGloutonAlgorithm();
    }


    @Test
    public void testComputeGloutonAlgorithmAction() throws Exception {
        int expectedResult = 9;
        long actualResult = glouton.getExpectation();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testComputeGloutonAlgorithmMean() throws Exception {
        double [] expectedResult = {0.71, 0.66, 0.75, 0.66};
        double [] actualResult = glouton.getMeans();
        double delta = 0.01;
        assertArrayEquals(expectedResult, actualResult, delta);
        assertEquals(9, glouton.getExpectation());
    }

    @Test
    public void testComputeGloutonUsedValues() throws Exception {
        int [] expectedB1Result = {1, 0, 1, 0, 1, 0};
        int [] expectedB2Result = {0, 1, 0, 1, 0};
        int [] expectedB3Result = {1, 1, 0, 0, 1, 1, 0};
        int [] expectedB4Result = {0, 0};
        Map<Integer, List<Integer>> actualResult = glouton.getUsedBandits();
        int [] actualB1Result = Glouton.convertIntegers(actualResult.get(1));
        int [] actualB2Result = Glouton.convertIntegers(actualResult.get(2));
        int [] actualB3Result = Glouton.convertIntegers(actualResult.get(3));
        int [] actualB4Result = Glouton.convertIntegers(actualResult.get(4));

        assertArrayEquals(expectedB1Result, actualB1Result);
        assertArrayEquals(expectedB2Result, actualB2Result);
        assertArrayEquals(expectedB3Result, actualB3Result);
        assertArrayEquals(expectedB4Result, actualB4Result);
    }
}