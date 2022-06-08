package Server;

import algorithms.search.BestFirstSearch;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * Singleton class of Configuration file
 */
public class Configurations {
    private static Configurations configurations=null;
    Properties prop;

    /**
     * constractor
     * create a configuration file and initialize it with default values.
     */
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

    /**
     * with given key these method returns its value from the config file
     * @param key
     * @return
     */
    public String getval(String key){
        try{
            InputStream input = new FileInputStream("resources/config.properties");
            Properties prop = new Properties();
            prop.load(input);
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop.getProperty(key);}


    /**
     * keep this class as singleton
     * @return
     */
    public static Configurations getInstance() {
        if( configurations==null){
            configurations=new Configurations();
        }
        return configurations;
    }

    /**
     * these method allows the user to edit the configuration file with values of its own
     * must be corresponding to the strategies checks
     * @param key
     * @param val
     */
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