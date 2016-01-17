/**
 * Created by fredericmoungpacrijanot on 17/01/2016.
 */
public class QLearning {

    Agent agent;
    int currentState = 0;
    int goal;
    double alpha;
    double gamma;

    public QLearning(Agent agent, int goal, double alpha, double gamma){
        this.agent = agent;
        this.goal = goal;
        this.alpha = alpha;
        this.gamma = gamma;
    }

    public void trainQLearningAlgorithm(int count){
        for(int j = 0; j < count; j++) {
            for (int i = 0; i < agent.getStartStateCount(); i++) {
                train(agent.getStartState(i));
            }
        }
    }

    private void train(int initialState){
        currentState = initialState;
        do
        {
            takeAction(currentState);
        }while(currentState == goal);

        for(int i = 0; i < agent.getSateSize(); i++)
        {
            takeAction(currentState);
        }
    }

    private int computeReward(int state, int action)
    {
        return (int)(agent.getStateActionValue(state,action) + alpha * (agent.getReward(state, action) + gamma * agent.getMaximumStateAction(state) - agent.getStateActionValue(state,action)));

        //return (int)(agent.getReward(state, action) + gamma * agent.getMaximumStateAction(state, action));
    }

    private void takeAction(int currentState){

        int possibleAction;

        possibleAction = agent.getNextAction(currentState);

        if(agent.getReward(currentState, possibleAction) >= 0){
            double reward = computeReward(currentState, possibleAction);
            agent.setStateActionValue(currentState, possibleAction, reward);
            this.currentState = possibleAction;
        }

    }
}
