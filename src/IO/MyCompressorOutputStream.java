package IO;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;

    @Override
    public void write(int b) throws IOException {

    }

    public MyCompressorOutputStream(OutputStream out) {
        this.out = out;
    }


    @Override
    public void write(byte[] b) throws IOException { //each 8 "bits" will perform as byte
        int c = 0;
        int idx = 0;
        int size = (int) Math.ceil(b.length/8.0) ;
        byte[] tmp = new byte[size];

        for (int i = 0; i < b.length; i = i+8) {
            byte newNum = binTOdec(b, i);
            tmp[idx] = newNum;
            idx++;
        }
        out.write(tmp);
    }

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
