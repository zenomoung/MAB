import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by fredericmoungpacrijanot on 17/01/2016.
 */
public class QLearningTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testTrainQLearningAlgorithm() throws Exception {
        List<Double> state0 = Arrays.asList(-1.0, -1.0, -1.0, -1.0, 0.0, -1.0);
        List<Double> state1 = Arrays.asList(-1.0, -1.0, -1.0, 0.0, -1.0, 100.0);
        List<Double> state2 = Arrays.asList(-1.0, -1.0, -1.0, 0.0, -1.0, -1.0);
        List<Double> state3 = Arrays.asList(-1.0, 0.0, 0.0, -1.0, 0.0, -1.0);
        List<Double> state4 = Arrays.asList(0.0, -1.0, -1.0, 0.0, -1.0, 100.0);
        List<Double> state5 = Arrays.asList(-1.0, 0.0, -1.0, -1.0, 0.0, 100.0);

        List<Double> stateAction0 = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        List<Double> stateAction1 = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        List<Double> stateAction2 = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        List<Double> stateAction3 = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        List<Double> stateAction4 = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        List<Double> stateAction5 = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        int startState[] = new int[] {2, 5, 4, 1, 0, 3};
        double gamma = 0.9;
        int goal = 5;
        int numberOfTrain = 20;
        double alpha = 0.8;
        Map<Integer, List<Double>> rewards = new Hashtable<>();
        Map<Integer, List<Double>> stateActions = new Hashtable<>();

        rewards.put(0,state0);
        rewards.put(1,state1);
        rewards.put(2,state2);
        rewards.put(3,state3);
        rewards.put(4,state4);
        rewards.put(5,state5);

        stateActions.put(0,stateAction0);
        stateActions.put(1,stateAction1);
        stateActions.put(2,stateAction2);
        stateActions.put(3,stateAction3);
        stateActions.put(4,stateAction4);
        stateActions.put(5,stateAction5);

        Agent agent = new Agent(startState, rewards, stateActions);
        QLearning learning = new QLearning(agent, goal, gamma, alpha);
        learning.trainQLearningAlgorithm(numberOfTrain);
        for (Map.Entry entry : agent.getStateActions().entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }
}