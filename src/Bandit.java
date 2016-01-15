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
    int index;

    public Bandit(List<Integer> values, int init, int index){
        this.init = init;
        this.values = values;
        this.index = index;
        usedValues = new ArrayList<>();
    }

    public int getIndex(){
        return  index;
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
        getMean();
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

    private int compareToIndice(int other){
        return this.index > other ? 1 : -1;
    }

    @Override
    public int compareTo(Bandit o) {
        int startComparison = compare(o.getMean(), this.getMean());
        if (startComparison == 0)
            return compareToIndice(o.getIndex());
        return startComparison;
    }
}
