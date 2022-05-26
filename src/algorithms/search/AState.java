package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.zip.Inflater;
public abstract class AState {
    public static enum color {white, gray, black}

    color Color;
    AState Parent;
    int cost;

    public AState() {
        Parent = null;
        cost = 0;
        Color = color.white;
    }

    public color getColor() {
        return Color;
    }

    public void setColor(color color) {
        Color = color;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    //the AState's ancestor
    public AState getParent() {
        return Parent;
    }

    public void setParent(AState parent) {
        Parent = parent;
    }

    public String toString() {
        return String.format("MY color : %s", this.Color);
    }

//    public int compareCost(AState state) {
//        return Integer.compare(this.cost, state.getCost());
//    }
}

//    @Override
//    public boolean equals(Object A) {
//
//        // If the object is compared with itself then return true
//        if (A == this) {
//            return true;
//        }
//
//
//        /* Check if o is an instance of Complex or not
//          "null instanceof [type]" also returns false */
//        if (!(A instanceof AState)) {
//            return false;
//        }
//        AState a = (AState) A;
//if (Color.equals(((AState) A).Color) && Parent.equals())
//    }
//
//    }


