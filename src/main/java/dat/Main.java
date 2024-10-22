package dat;


import dat.config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        try {
            // Brug denne linje til at initialisere EntityManagerFactory og køre det i development mode
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

            // Alternativt kan du bruge denne linje til at teste mod test-databasen (Testcontainers)
            // EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();

            if (emf != null) {
                System.out.println("EntityManagerFactory created successfully!");
            } else {
                System.out.println("Failed to create EntityManagerFactory.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}