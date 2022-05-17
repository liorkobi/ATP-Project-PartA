package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    //add funcs from Smaze

    public AState getStart() ;

    public AState getGoal() ;

    public ArrayList<AState> getAllPossibleStates(AState m) ;
    public SearchableMaze clone() ;

    }