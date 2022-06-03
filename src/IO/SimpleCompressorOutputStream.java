package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class SimpleCompressorOutputStream extends OutputStream {
    OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {

    }

    @Override
    public void write(byte[] b) throws IOException {

        int idx = b[b.length - 1];
        int counter = 0;
        byte[] temp = new byte[b.length];
        temp[b.length - 1] = (byte) idx;
        if (idx >= 0) System.arraycopy(b, 0, temp, 0, idx - 1);
        boolean bool = true;
        if (b[idx] == 1) {
            temp[idx] = (byte) 0;
            bool = false;
            idx += 1;}
        for (int i = b[b.length - 1]; i < b.length-1; i++) {
            //current byte equals to previous byte
            if ((b[i] == 0 && bool) || (b[i] == 1 && !bool)) {
                counter++;
            }
            //prev was 1 and curr is 0
            else if (b[i] == 0 && !bool) {
                if (counter <= 255) {
                    temp[idx] = (byte) counter;
                    idx++;
                } else {
                    while (counter >= 255) {
                        temp[idx] = (byte) 255;
                        counter -= 255;
                        idx++;
                    }
                }
                counter = 1;
                //preparing to next iteration
                bool = true;
            }
            //prev was 0 curr is 1
            else if (b[i] == 1 && bool) {
                if (counter <= 255) {
                    temp[idx] = (byte) counter;
                    idx++;
                } else {
                    while (counter >= 255) {
                        temp[idx] = (byte) 255;
                        counter -= 255;
                        idx++;
                    }
                }
                counter = 1;
                //preparing to next iteration
                bool = false;
            }
        }
        if(temp[idx]==0){
        temp[idx]=(byte)counter;}
        else {idx++;temp[idx]=(byte)counter;}
        out.write(removeBuffer(temp, idx));
    }

    private byte[] removeBuffer( byte[] b, int i){
        byte[] Final = new byte[i+2];
        System.arraycopy(b, 0, Final, 0, i+1 );
        Final[i+1] = b[b.length - 1];
        return Final;
    }


}