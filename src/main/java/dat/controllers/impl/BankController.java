package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.Controller;
import dat.daos.impl.BankDAO;
import dat.dtos.BankDTO;
import dat.entities.Bank;
import dat.exception.Message;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.Set;

public class BankController implements Controller {

    private final BankDAO bankDAO;

    public BankController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.bankDAO = BankDAO.getInstance(emf);

    }


    @Override
    public void getById(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        BankDTO bankDTO = bankDAO.getById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found"));
        ctx.res().setStatus(200);
        ctx.json(bankDTO);

    }

    @Override
    public void create(Context ctx) {
        try{
           BankDTO bankDTO = ctx.bodyAsClass(BankDTO.class);
           BankDTO bankDTO1 = bankDAO.create(bankDTO);
              ctx.res().setStatus(201, "Bank is sucessfully created");
                ctx.json(bankDTO1, BankDTO.class);

        }catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "Bank cannot be created"));
        }

    }

    @Override
    public void update(Context ctx) {
        try {
            // Hent id fra URL
            Long id = Long.parseLong(ctx.pathParam("id"));

            // Hent BankDTO fra request body
            BankDTO bankDTO = ctx.bodyAsClass(BankDTO.class);
            bankDTO.setId(id);
            // Opdater bank i databasen via DAO
            bankDAO.update(bankDTO);

            // Sæt statuskode og returnér opdateret bankDTO som JSON
            ctx.status(200);
            ctx.json(bankDTO);
        } catch (Exception e) {
            // Log undtagelsen for fejlfinding
            e.printStackTrace();  // Dette logger hele fejlen til din konsol/log

            // Returnér en 400-status med en fejlmeddelelse
            ctx.status(400);
            ctx.json(new Message(400, "Bank cannot be updated"));
        }
    }
    @Override
    public void delete(Context ctx) {
        try {
            // Hent ID fra URL
            Long id = Long.parseLong(ctx.pathParam("id"));

            // Tjek om banken eksisterer, ellers kast undtagelse
            BankDTO bankDTO = bankDAO.getById(id).orElseThrow(() -> new IllegalArgumentException("Bank with ID " + id + " not found"));

            // Slet banken fra databasen via DAO
            bankDAO.delete(id);

            // Sæt statuskode 204 (No Content) for succesfuld sletning
            ctx.status(204);
        } catch (IllegalArgumentException e) {
            // Hvis banken ikke blev fundet
            ctx.status(404);
            ctx.json(new Message(404, e.getMessage()));
        } catch (Exception e) {
            // Hvis en anden fejl opstår, log fejlen og returnér en 400 fejl
            e.printStackTrace();
            ctx.status(400);
            ctx.json(new Message(400, "Bank cannot be deleted"));
        }
    }

    @Override
    public void getAll(Context ctx) {
        try{
            Set<BankDTO>banks = bankDAO.getAll();
            ctx.res().setStatus(200, "Bank is sucessfully retrieved");
            ctx.json(banks, BankDTO.class);
        }catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "Bank cannot be retrieved"));
        }

    }
}
