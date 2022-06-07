package Server;

import algorithms.search.BestFirstSearch;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * Singleton class of Configuration file
 */
public class Configurations {
    private static Configurations configurations = new Configurations();
    Properties prop;

    private Configurations() {
        try {
            OutputStream output = new FileOutputStream("resources/config.properties");
            prop = new Properties();
            int num = Runtime.getRuntime().availableProcessors()*2;
            prop.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
            prop.setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
            prop.setProperty("threadPoolSize", String.valueOf(num));
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public String getval(String key){
        try{
            InputStream input = new FileInputStream("config.properties");
            Properties prop = new Properties();
            prop.load(input);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    return prop.getProperty(key);}



    public static Configurations getInstance() {
        return configurations;
    }

    public void setProperty(String key , String val) {

        try{
            prop.setProperty(key, val);
            OutputStream newP = new FileOutputStream("resources/config.properties");
            prop.store(newP, null);

        }catch (IOException ex2) {
            ex2.printStackTrace();
        }
        }



}
