package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.Controller;
import dat.daos.impl.LoanUserDAO;
import dat.dtos.LoanUserDTO;
import dat.entities.LoanUser;
import dat.exception.Message;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.Set;

public class LoanUserController implements Controller {
    private final LoanUserDAO dao;

    public LoanUserController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.dao=LoanUserDAO.getInstance(emf);
    }

    @Override
    public void getById(Context ctx) {
        try {
            Long id = Long.parseLong(ctx.pathParam("id"));
            LoanUserDTO loanUserDTO = dao.getById(id)
                    .orElseThrow(() -> new RuntimeException("LoanUser not found"));
            ctx.json(loanUserDTO, LoanUserDTO.class);
        } catch (Exception e) {
            ctx.status(400);
            ctx.json(new Message(400, "LoanUser cannot be found"));
        }
    }

    @Override
    public void create(Context ctx) {
        try{
            LoanUserDTO loanUserDTO=ctx.bodyAsClass(LoanUserDTO.class);
            LoanUserDTO loanUserDTO1=dao.create(loanUserDTO);
            ctx.res().setStatus(201, "LoanUser is sucessfully created");
            ctx.json(loanUserDTO1, LoanUserDTO.class);
        }
        catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanUser cannot be created"));
        }

    }

    @Override
    public void update(Context ctx) {
        try{
            Long id= Long.parseLong(ctx.pathParam("id"));
            LoanUserDTO loanUserDTO= ctx.bodyAsClass(LoanUserDTO.class);
            loanUserDTO.setId(id);
            dao.update(loanUserDTO);
            ctx.res().setStatus(200, "LoanUser is sucessfully updated");
            ctx.json(loanUserDTO, LoanUserDTO.class);
        }catch (Exception e) {
            ctx.status(400);
            ctx.json(new Message(400, "LoanUser cannot be updated"));
        }

    }

    @Override
    public void delete(Context ctx) {
        try{
            Long id= Long.parseLong(ctx.pathParam("id"));
            dao.delete(id);
            ctx.res().setStatus(200, "LoanUser is sucessfully deleted");
        }catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanUser cannot be deleted"));
        }

    }

    @Override
    public void getAll(Context ctx) {
        try{
            Set<LoanUserDTO> loanUserDTO1=dao.getAll();
            ctx.res().setStatus(200, "LoanUser is sucessfully created");
            ctx.json(loanUserDTO1, LoanUserDTO.class);
        }catch (Exception e) {
            ctx.status(400);
            ctx.json(new Message(400, "LoanUser cannot be created"));

        }
    }
}
