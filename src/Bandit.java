import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by fredericmoungpacrijanot on 15/01/2016.
 */
public class Bandit implements Comparable<Bandit>{
    double mean;
    List<Integer> values;
    long init;
    List<Integer> usedValues;

    public Bandit(List<Integer> values, int init){
        this.init = init;
        this.values = new ArrayList<>();
        usedValues = new ArrayList<>();
        this.values.addAll(values);
    }

    public double getMean() {
        if(usedValues.size() == 0){
            mean = init;
            return mean;
        }
        double sum = 0.0;
        for (Integer val: usedValues){
            sum += val;
        }
        mean = (sum + init)/(1+usedValues.size());
        return mean;
    }

    public void updateUsedValues(){
        int usedValuesSize = usedValues.size();
        usedValues.add(values.get(usedValuesSize));
    }

    public long getUtility(){
        long util = 0;
        for (int i: usedValues){
            util += i;
        }
        return util;
    }

    public List<Integer> getUsedValues(){
        return usedValues;
    }


    public double getInit() {
        return init;
    }

    private int compare(double a, double b) {
        return a < b ? -1
                : a > b ? 1
                : 0;
    }

    @Override
    public int compareTo(Bandit o) {
        int startComparison = compare(o.getMean(), this.getMean());
        return startComparison;
    }
}
