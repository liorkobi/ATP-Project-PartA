package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D {
    @Override
    public long measureAlgorithmTimeMillis(int depth,int r,int c){
        long start=System.currentTimeMillis();
        generate(depth,r,c);
        long end=System.currentTimeMillis();
        long res=end-start;
        return res;
    }
}
