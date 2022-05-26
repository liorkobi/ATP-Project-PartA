package algorithms.search;

import java.util.*;

public abstract class ASearchingAlgorithms implements ISearchingAlgorithm {
    protected Queue<AState> VQ;
    protected int counter;

    public ASearchingAlgorithms() {
        this.counter = 0;
    }

    protected void TheShortestPath(ISearchable p,Solution S,AState curr){
        if (curr==p.getStart()) {
            S.setSol(p.getStart());
        }
        else {
            if (curr.getParent()!=null){
                S.setSol(curr);
                TheShortestPath(p,S,curr.getParent());}
        }
    }



}
