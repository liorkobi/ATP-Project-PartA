package algorithms.search;

import java.util.*;

public class DepthFirstSearch extends ASearchingAlgorithms{
    Stack<AState> stack;

    public DepthFirstSearch(){
         stack = new Stack<>();
    }

    /**
     *implementation of DFS algorithm using stack - in order to preserve the right order of evaluated nodes
     * we push them to the stack reversely.
     */
    public ISearchable DFS(ISearchable problemC){
        if(problemC.getStart()==null){return null;}

        ISearchable problem=problemC.clone();
        AState c= problem.getStart();

        // push the source node into the stack
        stack.push(c);

        // loop till stack is empty
        while (!stack.empty()){

            // Pop a state from the stack
            c = stack.pop();

            if (c.equals(problem.getGoal())){c.setColor(AState.color.gray);
                return problem;}
            // if the state is already discovered ignore it
            if (c.getColor() == AState.color.white) {
                c.setColor(AState.color.gray);
                counter++;
                ArrayList<AState> uAdj=problem.getAllPossibleStates(c);
            for (int i = uAdj.size() - 1; i >= 0; i--){
                AState u = uAdj.get(i);
                if (u.getColor() == AState.color.white) {
                    u.setParent(c);
                    stack.push(u);
                }
            }
            c.setColor(AState.color.black);
        }}
        return problem;}


//calls the DFS algorith , find the solution path and return it

    @Override
    public Solution solve(ISearchable domain) {
        if(domain.getStart()==null){return null;}
        ISearchable p = DFS(domain);
        Solution S = new Solution(domain.getStart(), domain.getGoal());
   //     TheShortestPath(p, S, p.getGoal());
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