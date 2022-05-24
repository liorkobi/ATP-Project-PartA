package algorithms.search;

import java.util.ArrayList;
import java.util.Optional;

public interface ISearchable {
    //add funcs from Smaze

    public AState getStart() ;

    public AState getGoal() ;

    public ArrayList<AState> getAllPossibleStates(AState m) ;
    public SearchableMaze clone() ;

    }