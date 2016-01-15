import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by fredericmoungpacrijanot on 15/01/2016.
 */
public class Glouton {

    public List<Bandit> ComputeGloutonAlgorithm(int actions, List<Bandit> bandits){
        for (int i = 1; i <= actions; i++){
            Bandit bandit = getHighestMean(bandits);
            bandit.updateUsedValues();
        }
        return bandits;
    }

    public long getExpectation(List<Bandit> bandits){
        long result = 0;
        for(Bandit bandit: bandits){
            result += bandit.getUtility();
        }
        return result;
    }

    private Bandit getHighestMean(List<Bandit> bandits){
        Collections.sort(bandits);
        return bandits.get(0);
    }
}
