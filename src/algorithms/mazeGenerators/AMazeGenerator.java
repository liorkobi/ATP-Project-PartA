package algorithms.mazeGenerators;

import java.io.Serializable;

/**
 * Abstract class of 2D generators
 * All generators have same implementestion of "measureAlgorithmTimeMillis()"
 * "generate()" is implemented uniquely in each generator
 */
public abstract class AMazeGenerator implements IMazeGenerator , Serializable {
    /**
     *
     * @param r is number of maze's rows
     * @param c is number of maze's columns
     * @return return the measured maze generation time
     */
    @Override
    public long measureAlgorithmTimeMillis(int r,int c){
        long start=System.currentTimeMillis();
        generate(r,c);
        long end=System.currentTimeMillis();
        long res=end-start;
        return res;
    }
}
