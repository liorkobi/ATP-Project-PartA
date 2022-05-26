package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithms{

    public DepthFirstSearch(){

    }




    public ISearchable DFS(ISearchable problemC){
        ISearchable problem=problemC.clone();

        return DFS_REC(problem,problem.getStart());
         }
    private ISearchable  DFS_REC(ISearchable problem,AState c){

        if (c.equals(problem.getGoal())){return problem;}
//        else {
            c.setColor(AState.color.gray);
            ArrayList<AState> uAdj = problem.getAllPossibleStates(c);

            for (int i = 0; i < uAdj.size(); i++) {
                if (uAdj.get(i).getColor() == AState.color.white) {
                    uAdj.get(i).setParent(c);
                    counter++;
                    DFS_REC(problem, uAdj.get(i));
                }
                c.setColor(AState.color.black);

            }
//        return problem;}}
//
        }
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
        ISearchable p = DFS(domain);
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
