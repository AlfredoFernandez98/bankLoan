package dat;


import dat.config.ApplicationConfig;
import dat.config.PopulateDB;


public class Main {
    public static void main(String[] args) {
        ApplicationConfig.startServer(7070);

        PopulateDB.main(args);


    }
}