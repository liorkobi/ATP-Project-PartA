package Server;

import algorithms.mazeGenerators.Maze;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class main {

    public static void main(String[] args) {


        String fileP = "\\\\DESKTOP-9HCMTT4\\Users\\liork\\Ipad\\37hgfdsa2-2016.pdf";
        File file = new File(fileP);
        if (!file.exists()) {
System.out.println("lior");        }

        else {System.out.println("kobi");}
    }
}
