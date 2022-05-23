package algorithms.search;

import java.util.*;

public abstract class ASearchingAlgorithms implements ISearchingAlgorithm {
    protected Queue<AState> VQ;
    protected int counter;

    public ASearchingAlgorithms() {
        this.counter = 0;
    }

    public ISearchable Solver(ISearchable problemC, char m) {

        ISearchable problem=problemC.clone();
        AState Start=problem.getStart();
        Start.setColor(AState.color.gray);
        VQ.add(Start);
        while (!VQ.isEmpty()){
            AState u=VQ.stream().findFirst().get();
            VQ.remove(u);
            ArrayList<AState> uAdj=problem.getAllPossibleStates(u);
            if(m=='b') {
                for (int i = 0; i < uAdj.size(); i++) {
                    AState v = uAdj.get(i);
                    if (v.getColor() == AState.color.white) {
                        v.setColor(AState.color.gray);
                        v.setParent(u);
                        VQ.add(v);
                        counter++;
                    }
                }
            }else{
                for (int i=uAdj.size()-1;i>=0;i--){
                    AState v=uAdj.get(i);
                    if(v.getColor()== AState.color.white){
                        v.setColor(AState.color.gray);
                        v.setParent(u);
                        VQ.add(v);
                        counter++;}
                }
            }
            u.setColor(AState.color.black);
            if (u.equals(problem.getGoal())){break;}
        }
        return problem;
    }

}
