import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by fredericmoungpacrijanot on 15/01/2016.
 */
public class GloutonTest {


    @Test
    public void testComputeGloutonAlgorithm() throws Exception {
        int init =2;
        int actions = 20;
        List<Bandit> bandits = new ArrayList<>();
        List<Integer> b1 = Arrays.asList(1,0,1,0,1,0);
        List<Integer> b2 = Arrays.asList(0,1,0,1,0,1);
        List<Integer> b3 = Arrays.asList(1,1,0,0,1,1,0,0,1,1);
        List<Integer> b4 = Arrays.asList(0,0,1,1,0,0,1,1,0,0);
        Bandit bandit1 = new Bandit(b1,init, 1);
        Bandit bandit2 = new Bandit(b2,init, 2);
        Bandit bandit3 = new Bandit(b3,init, 3);
        Bandit bandit4 = new Bandit(b4,init, 4);
        bandits.add(bandit1);
        bandits.add(bandit2);
        bandits.add(bandit3);
        bandits.add(bandit4);
        Glouton glouton = new Glouton();
        List<Bandit> result = glouton.ComputeGloutonAlgorithm(actions,bandits);
        assertEquals(9, glouton.getExpectation(result));
    }
}