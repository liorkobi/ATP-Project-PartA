package algorithms.search;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class DepthFirstSearch extends BreadthFirstSearch{




    public DepthFirstSearch() {
     this.VQ= new Stack<AState>();
    }
    @Override
    public String getName() {
        return "DepthFirstSearch";
    }

}
