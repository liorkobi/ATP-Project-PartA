package algorithms.search;

import java.util.*;

public abstract class ASearchingAlgorithms implements ISearchingAlgorithm {
    protected Queue<AState> VQ;
    protected int counter;

    public ASearchingAlgorithms() {
        this.counter = 0;
    }



}
