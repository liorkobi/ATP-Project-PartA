package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithms{
    public BreadthFirstSearch() {
        this.VQ = new LinkedList<AState>();
    }

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
//                        if (v.equals(problem.getGoal())){counter++;return problem;}}

                    }
                }
            u.setColor(AState.color.black);
        }
        return problem;
    }


public int getNumberOfNodesEvaluated(){
        return counter;}


    @Override
    public Solution solve(ISearchable domain){
        if(domain.getStart()==null){return null;}
        ISearchable p=BFS(domain);
        Solution S=new Solution();
        TheShortestPath(p,S,p.getGoal());
        return S;

}

    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}

