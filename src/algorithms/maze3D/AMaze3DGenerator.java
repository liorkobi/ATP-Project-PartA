package algorithms.maze3D;

/**
 * Abstract class of 3D generators
 * All generators have same implementestion of "measureAlgorithmTimeMillis()"
 * "generate()" is implemented uniquely in each generator
 */
public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    /**
     *
     * @param depth is size of maze's depth
     * @param r is number of maze's rows
     * @param c is number of maze's columns
     * @return return the measured maze generation time
     */
    @Override
    public long measureAlgorithmTimeMillis(int depth,int r,int c){
        long start=System.currentTimeMillis();
        generate(depth,r,c);
        long end=System.currentTimeMillis();
        long res=end-start;
        return res;
    }
}
