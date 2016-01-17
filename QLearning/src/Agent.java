import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by fredericmoungpacrijanot on 17/01/2016.
 */
public class Agent {

    int [] startState;
    Map<Integer, List<Double>> rewards;
    Map<Integer, List<Double>> stateActions;

    public Agent(int [] startState, Map<Integer, List<Double>> rewards, Map<Integer, List<Double>> stateActions){
        this.startState = startState;
        this.rewards = rewards;
        this.stateActions = stateActions;
    }

    public int getStartState(int index){
        return startState[index];
    }

    public int getStartStateCount(){
        return startState.length;
    }

    public Map<Integer, List<Double>> getStateActions(){
        return stateActions;
    }

    public int getSateSize(){
        return stateActions.size();
    }

    public void setStateActionValue(int state, int action, double value){
        stateActions.get(state).set(action, value);
    }

    public double getStateActionValue(int state, int action){
        return stateActions.get(state).get(action);
    }

    public double getReward(int state, int action){
        return rewards.get(state).get(action);
    }

    public int getNextAction(int currentState)
    {
        int action = 0;
        boolean choiceIsValid = false;

        while(choiceIsValid == false)
        {
            action = new Random().nextInt(rewards.size());
            if(rewards.get(currentState).get(action) > -1){
                choiceIsValid = true;
            }
        }
        return action;
    }


    public double getMaximumStateAction(int currentState){
        int newValue = 0;
        boolean found;
        boolean done = false;

        while(!done)
        {
            found = false;
            for(int i = 0; i < stateActions.size(); i++)
            {
                if(i != newValue){
                    if(stateActions.get(currentState).get(i) > stateActions.get(currentState).get(newValue)){
                        newValue = i;
                        found = true;
                    }
                }
            }

            if(found == false){
                done = true;
            }
        }
        return stateActions.get(currentState).get(newValue);
    }
}
