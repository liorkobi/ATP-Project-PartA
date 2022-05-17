package algorithms.mazeGenerators;

public abstract class AmazeGenerator implements IMazeGenerator {
    @Override
    public long measureAlgorithmTimeMillis(int r,int c){
        long start=System.currentTimeMillis();
        generate(r,c);
        long end=System.currentTimeMillis();
        long res=end-start;
        System.out.println(res);
        return res;
    }
}
