package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithms{
    public BreadthFirstSearch() {
        this.VQ = new LinkedList<AState>();
    }

    /**
     *
     * implementation of BFS algorithm using ordinary Queue
     */
    public ISearchable BFS(ISearchable problemC) {
    if(problemC.getStart()==null){return null;}
        ISearchable problem=problemC.clone();
        AState Start=problem.getStart();
        Start.setColor(AState.color.gray);
        VQ.add(Start);
        while (!VQ.isEmpty()){
            AState u=VQ.poll();
            if (u.equals(problem.getGoal())){u.setColor(AState.color.gray);return problem;}
            counter++;
            ArrayList<AState> uAdj=problem.getAllPossibleStates(u);
                for (int i = 0; i < uAdj.size(); i++) {
                    AState v = uAdj.get(i);
                    if (v.getColor() == AState.color.white) {
                        v.setColor(AState.color.gray);
                        v.setParent(u);
                        VQ.add(v);

                    }
                }
            u.setColor(AState.color.black);
        }
        return problem;
    }


public int getNumberOfNodesEvaluated(){
        return counter;}

//calls the BFS algorith , find the solution path and return it
    @Override
    public Solution solve(ISearchable domain){
        if(domain.getStart()==null){return null;}
        ISearchable p=BFS(domain);
        Solution S=new Solution(p.getStart(), p.getGoal());
     //  TheShortestPath(p,S,p.getGoal());
        //for (int i=0;i<S.getSol().size();i++){S.getSol().get(i).setParent(null);}
        System.out.println(S.getSol().size());
        return S;

}

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}

