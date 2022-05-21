package algorithms.search;

import javax.print.attribute.standard.QueuedJobCount;
import java.util.*;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class BreadthFirstSearch extends ASearchingAlgorithms{
    int counter;
    public BreadthFirstSearch() {
        this.counter =0;

//        Comparator<AState> comparator = (o1, o2) -> {
//            return 0;
//        };
        this.VQ = new LinkedTransferQueue<>();
    }

    public ISearchable BreadthFirstSearch(ISearchable problemC) {

        ISearchable problem=problemC.clone();
        AState Start=problem.getStart();
        Start.setColor(AState.color.gray);
        VQ.add(Start);
        while (!VQ.isEmpty()){
            AState u=VQ.stream().findFirst().get();
            VQ.remove(u);
            ArrayList<AState> uAdj=problem.getAllPossibleStates(u);
            for (int i=0;i<uAdj.size();i++){
                AState v=uAdj.get(i);
                if(v.getColor()== AState.color.white){
                    v.setColor(AState.color.gray);
                    v.setParent(u);
                    VQ.add(v);
                counter++;}
                }
            u.setColor(AState.color.black);
            if (u.equals(problem.getGoal())){break;}
            }
        return problem;
        }
public int getNumberOfNodesEvaluated(){
        return counter;}


    public void TheShortestPath(ISearchable p,Solution S,AState curr){

        if (curr==p.getStart()) {
            S.setSol(p.getStart());
        }
        else {
            if (curr.getParent()!=null){
                S.setSol(curr);
                TheShortestPath(p,S,curr.getParent());}
        }

        }

    @Override
    public Solution solve(ISearchable domain){
        ISearchable p=BreadthFirstSearch(domain);
        Solution S=new Solution();
        TheShortestPath(p,S,p.getGoal());


        return S;

}

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}

