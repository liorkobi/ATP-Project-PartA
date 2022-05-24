package algorithms.maze3D;

import algorithms.mazeGenerators.Position;

public class Position3D {
        int rowidx;
        int colidx;
        int depidx;

        public Position3D(int rowidx, int colidx,int depidx) {
            this.rowidx = rowidx;
            this.colidx = colidx;
            this.depidx = depidx;
        }


        public void setRowidx(int rowidx) {
            this.rowidx = rowidx;
        }

        public void setColidx(int colidx) {
            this.colidx = colidx;
        }


    public int getDepthIndex() {return depidx;}

    public void setDepidx(int depidx) {this.depidx = depidx;}

    public int getRowIndex() {return rowidx;}

        public int getColumnIndex() {
            return colidx;
        }

        @Override
        public String toString(){
            return String.format("{%d,%d,%d}",depidx,rowidx,colidx);
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
            if (!(P instanceof Position3D)) {
                return false;
            }
            Position3D p = (Position3D) P;

            // Compare the data members and return accordingly
            return Integer.compare(rowidx,p.rowidx) == 0
                    && Integer.compare(colidx,p.colidx) == 0
                    && Integer.compare(depidx,p.depidx) == 0
                    ;
        }
    }

