import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;


/**
 * Created by fredericmoungpacrijanot on 15/01/2016.
 */
public class Bandit implements Comparable<Bandit>{
    double mean;
    List<Integer> values;
    long init;
    List<Integer> usedValues;
    int index;
    boolean available;
    static final Logger logger = LogManager.getRootLogger();



    public Bandit(List<Integer> values, int init, int index){
        this.init = init;
        mean = init;
        this.values = values;
        this.index = index;
        usedValues = new ArrayList<>();
        available =true;
    }

    //The arm Bandit index. Very important as if two arms have the same mean, the arm with the lowest index will be chosen
    public int getIndex(){
        return  index;
    }

    //Does this arm have more games?
    public boolean getAvailable(){ return available;}

    //Compute and update the mean of the arm
    private double updateMean() {
        double sum = 0.0;
        for (Integer val: usedValues){
            sum += val;
        }
        mean = (sum + init)/(1+usedValues.size());
        return mean;
    }

    //Update the array that contains the used value of this arm
    public void updateUsedValues() throws ArrayIndexOutOfBoundsException{
        try {
            int usedValuesSize = usedValues.size();
            usedValues.add(values.get(usedValuesSize));
            updateMean();
            if (usedValues.size() >= values.size()) {
                available = false;
            }
        }
        catch (ArrayIndexOutOfBoundsException arrayexception){
            logger.error(arrayexception.getMessage());
            throw arrayexception;
        }
    }

    //Return the mean of the arm
    public double getMean(){
        return mean;
    }

    //Get the expected value of the arm
    public long getUtility(){
        long util = 0;
        for (int i: usedValues){
            util += i;
        }
        return util;
    }

    //Get the history of the rewards for this arm
    public List<Integer> getUsedValues(){
        return usedValues;
    }

    //Very useful as it orders the arms bandit
    private int compare(double a, double b) {
        return a < b ? -1
                : a > b ? 1
                : 0;
    }

    private int compareToIndice(int other){
        return this.index > other ? 1 : -1;
    }

    @Override
    public int compareTo(Bandit o) {
        int startComparison = compare(o.getMean(), this.getMean());
        //If two arms have the same mean, the arm with the lowest index will be chosen
        if (startComparison == 0)
            return compareToIndice(o.getIndex());
        return startComparison;
    }
}
