package algorithms.mazeGenerators;

public class Position {
    int rowidx;
    int colidx;

    public Position(int rowidx, int colidx) {
        this.rowidx = rowidx;
        this.colidx = colidx;
    }


    public void setRowidx(int rowidx) {
        this.rowidx = rowidx;
    }

    public void setColidx(int colidx) {
        this.colidx = colidx;
    }

    public int getRowIndex() {
        return rowidx;
    }

    public int getColumnIndex() {
        return colidx;
    }

    @Override
    public String toString(){
        return String.format("{%d,%d}",rowidx,colidx);
    }



    //אם יהיה לנו זמן לדדרוס את שווה שווה ולשנות בגט גאול פוסישן
    @Override
    public boolean equals(Object P) {

        // If the object is compared with itself then return true
        if (P == this) {
            return true;
        }


        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(P instanceof Position)) {
            return false;
        }
        Position p = (Position) P;

        // Compare the data members and return accordingly
        return Integer.compare(rowidx,p.rowidx) == 0
                && Integer.compare(colidx,p.colidx) == 0;
    }
}