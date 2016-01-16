import java.util.*;

/**
 * Created by fredericmoungpacrijanot on 15/01/2016.
 */
public class Glouton {

    int actions;
    List<Bandit> bandits;

    public Glouton(int actions, List<Bandit> bandits){
        this.actions = actions;
        this.bandits = bandits;
    }

    //The glouton algorithm
    //The arm with the highest mean is chosen
    public List<Bandit> ComputeGloutonAlgorithm(){
        for (int i = 1; i <= actions; i++){
            Bandit bandit = getHighestMean();
            bandit.updateUsedValues();
        }
        return bandits;
    }

    //Get the expected value of each arm
    public long getExpectation(){
        long result = 0;
        for(Bandit bandit: bandits){
            result += bandit.getUtility();
        }
        return result;
    }

    //Get the expected value of the whole game
    public double [] getMeans(){
        double [] means = new double[bandits.size()];
        for(Bandit bandit: bandits){
            means[bandit.getIndex() - 1] = bandit.getMean();
        }
        return means;
    }

    //Get the history of each game
    public Map<Integer, List<Integer>> getUsedBandits(){
        Map<Integer, List<Integer>> usedBandits = new Hashtable<>();
        for (Bandit bandit: bandits){
            usedBandits.put(bandit.getIndex(), bandit.getUsedValues());
        }
        return usedBandits;
    }

    //Get the highest mean
    private Bandit getHighestMean(){
        List<Bandit> filteredBandits = filterAvailableBandits();
        Collections.sort(filteredBandits);
        //Collections.sort(bandits);
        return filteredBandits.get(0);
        //return bandits.get(0);
    }

    //Remove bandit that doesn't have any more game
    private List<Bandit> filterAvailableBandits(){
        List<Bandit> filteredBandits = new ArrayList<>();
        for (Bandit bandit: bandits){
            if (bandit.getAvailable())
                filteredBandits.add(bandit);
        }
        return filteredBandits;
    }

    //Util method: Convert and Integer List to in array
    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i);
        }
        return ret;
    }

}
