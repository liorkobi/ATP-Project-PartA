package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithms{

    public DepthFirstSearch(){
        this.VQ=new LinkedList<>();
    }




    //public ISearchable DFS(ISearchable problem){ return problem}

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
    public Solution solve(ISearchable domain) {
        ISearchable p = Solver(domain,'d');
        Solution S = new Solution();
        TheShortestPath(p, S, p.getGoal());
        return S;
    }

    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return counter;
    }

}
