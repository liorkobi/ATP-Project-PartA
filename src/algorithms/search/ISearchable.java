package algorithms.search;

import java.util.ArrayList;

/**
 * declaration of the methods that every Searchable problem must implement
 */
public interface ISearchable {

     AState getStart() ;

     AState getGoal() ;

     ArrayList<AState> getAllPossibleStates(AState m) ;
//it is must  in order to keep generic programing
     ISearchable clone() ;

    }