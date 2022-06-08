package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    @Override
    public void write(int b) throws IOException {

    }

    /**
     * Constractor
     * @param out - an output stream object
     */
    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    /**
     * ovveriding the original Write method in order to compress the Maze
     * @param b   the data - the maze to compress.
     * @throws IOException
     */
    @Override
    public void write(byte[] b) throws IOException { //each 8 "bits" will perform as byte
        int c = 0;
        int idx = b[b.length - 1];
        int size = (int) Math.ceil(b.length/8.0) ;
        size+= b[b.length - 1];
        byte[] tmp = new byte[size];
        tmp[size-1] = (byte) b[b.length - 1];
        System.arraycopy(b, 0, tmp, 0, b[b.length - 1]-1);

        for (int i =  b[b.length - 1]; i < b.length; i = i+8) {
            byte newNum = binTOdec(b, i);
            tmp[idx] = newNum;
            idx++;
        }
        out.write(tmp);
    }

    /**
     * convert binary number to decimal number
     * @param b - byte array
     * @param i - binary number index
     * @return
     */
    private byte binTOdec(byte[] b, int i) {  //  binary number to decimal
        int sum = 0;
        for (int j = 7; j >= 0; j--) {
            if (i+j < b.length) {
                if (b[i + j] == (byte) 1) {
                    sum += Math.pow(2, j);
                }
            }
        }
        return (byte)sum;
    }
}
