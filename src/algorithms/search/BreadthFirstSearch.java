package algorithms.search;

import javax.print.attribute.standard.QueuedJobCount;
import java.util.*;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class BreadthFirstSearch extends ASearchingAlgorithms{
    public BreadthFirstSearch() {
        this.VQ = new LinkedList<AState>();
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
        ISearchable p=Solver(domain,'b');
        Solution S=new Solution();
        TheShortestPath(p,S,p.getGoal());


        return S;

}

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}

