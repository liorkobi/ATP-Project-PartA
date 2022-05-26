package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithms{
    public BreadthFirstSearch() {
        this.VQ = new LinkedList<AState>();
    }

    public ISearchable BFS(ISearchable problemC) {

        ISearchable problem=problemC.clone();
        AState Start=problem.getStart();
        Start.setColor(AState.color.gray);
        VQ.add(Start);
        while (!VQ.isEmpty()){
            AState u=VQ.poll();
            ArrayList<AState> uAdj=problem.getAllPossibleStates(u);
                for (int i = 0; i < uAdj.size(); i++) {
                    AState v = uAdj.get(i);
                    if (v.getColor() == AState.color.white) {
                        v.setColor(AState.color.gray);
                        v.setParent(u);
                        VQ.add(v);
                        counter++;
                    }
                }
            u.setColor(AState.color.black);
            if (u.equals(problem.getGoal())){break;}
        }
        return problem;
    }


public int getNumberOfNodesEvaluated(){
        return counter;}


    @Override
    public Solution solve(ISearchable domain){
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

