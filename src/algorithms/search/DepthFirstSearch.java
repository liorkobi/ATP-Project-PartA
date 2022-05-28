//package algorithms.search;
//
//import java.util.*;
//
//public class DepthFirstSearch extends ASearchingAlgorithms{
//    Stack<AState> stack;
//
//    public DepthFirstSearch(){
//         stack = new Stack<>();
//    }
//    public ISearchable DFS(ISearchable problemC){
//        ISearchable problem=problemC.clone();
//        AState c= problem.getStart();
//        // push the source node into the stack
//        stack.push(c);
//
//        // loop till stack is empty
//        while (!stack.empty())
//        {
//            // Pop a vertex from the stack
//            c = stack.pop();
//
//            // if the vertex is already discovered yet, ignore it
//            if (c.getColor() == AState.color.gray) {
//                continue;
//            }
//            ArrayList<AState> uAdj=problem.getAllPossibleStates(c);
//
//            // we will reach here if the popped vertex `v` is not discovered yet;
//            // print `v` and process its undiscovered adjacent nodes into the stack
//            c.setColor(AState.color.gray);
//            counter++;
//
//
//            // do for every edge (v, u)
//            for (int i = uAdj.size() - 1; i >= 0; i--)
//            {
//                AState u = uAdj.get(i);
//                if (u.getColor() == AState.color.white) {
//                    u.setParent(c);
//
//                    stack.push(u);
//
//                }
//            }
//            c.setColor(AState.color.black);
//            if (c.equals(problem.getGoal())){break;}
//        }
//        return problem;}
//
//
//
//
//
//
//    @Override
//    public Solution solve(ISearchable domain) {
//        ISearchable p = DFS(domain);
//        Solution S = new Solution();
//        TheShortestPath(p, S, p.getGoal());
//        return S;
//    }
//
//    @Override
//    public String getName() {
//        return "DepthFirstSearch";
//    }
//
//    @Override
//    public int getNumberOfNodesEvaluated() {
//        return counter;
//    }
//
//}

package algorithms.search;
import java.util.Stack;
import java.util.LinkedList;

public class DepthFirstSearch extends ASearchingAlgorithm
{
    /**
     *
     * @return String
     */
    public  String  getName()
    {
        String str="DepthFirstSearch";
        return str;
    }


    Stack<AState> stack = new Stack<AState>();
    LinkedList<AState> visited = new LinkedList<>();

    /**
     * This function solve the maze with DepthFirstSearch
     and sending tp the super solve.
     * @param search
     * @return Solution
     */
    public Solution solve(ISearchable search)
    {
        if (search == null)
            return null;

        AState startVert = search.getStart();
        AState endVert = search.getGoal();
        visited.add(startVert);
        adj=search.getAllPossibleStates(startVert);
        for(int i=adj.size()-1 ;i>=0;i--)
        {
            adj.get(i).setCamefrom(startVert);
            stack.push((adj.get(i)));
        }

        while (!stack.isEmpty())
        {

            AState out=stack.pop();
            visited.add(out);//pop the top element and insert to visited
            LinkedList<AState> templist = new LinkedList<>();
            templist=search.getAllPossibleStates(out);
            for(int i=templist.size()-1;i>=0;i--)
            {
                templist.get(i).setCamefrom(out);
                stack.push(templist.get(i));

            }
        }
        search.fixfunc();
        this.setvisitednodes(visited.size());//number of node the algo gave


        for(int i=0;i<visited.size();i++)
        {
            if(endVert.toString().equals(visited.get(i).toString())){
                AState last =  visited.get(i);
                Solution s1=new Solution();
                s1.setLast(last);
                s1.setStart(startVert);
                return s1;
            }

        }
        return null;
    }
}