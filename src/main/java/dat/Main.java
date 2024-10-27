package dat;


import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.config.PopulateDB;
import jakarta.persistence.EntityManagerFactory;


public class Main {
    public static void main(String[] args) {
        ApplicationConfig.startServer(7070);

        PopulateDB.main(args);

//        try{
//            EntityManagerFactory emf= HibernateConfig.getEntityManagerFactory();
//        }catch (Exception e){
//            e.printStackTrace();
//        }


    }
}