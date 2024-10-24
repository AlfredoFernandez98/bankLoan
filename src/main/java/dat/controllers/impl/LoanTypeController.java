package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.Controller;
import dat.daos.impl.LoanTypeDAO;
import dat.dtos.LoanTypeDTO;
import dat.exception.Message;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.Set;

public class LoanTypeController implements Controller {
    private final LoanTypeDAO dao;

    public LoanTypeController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.dao = LoanTypeDAO.getInstance(emf);
    }

    @Override
    public void getById(Context ctx) {
        try{
            Long id= Long.parseLong(ctx.pathParam("id"));
            LoanTypeDTO loanType = dao.getById(id)
                    .orElseThrow(() -> new RuntimeException("LoanType not found"));
        }catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanType cannot be found"));
        }

    }

    @Override
    public void create(Context ctx) {
        try{
            LoanTypeDTO loanTypeDTO=ctx.bodyAsClass(LoanTypeDTO.class);
            LoanTypeDTO loanTypeDTO1=dao.create(loanTypeDTO);
            ctx.res().setStatus(201, "LoanType is sucessfully created");
            ctx.json(loanTypeDTO1, LoanTypeDTO.class);
        }
        catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanType cannot be created"));
        }

    }

    @Override
    public void update(Context ctx) {
        try{
            Long id= Long.parseLong(ctx.pathParam("id"));
            LoanTypeDTO loanTypeDTO= ctx.bodyAsClass(LoanTypeDTO.class);
            loanTypeDTO.setId(id);
            dao.update(loanTypeDTO);
            ctx.res().setStatus(200, "LoanType is sucessfully updated");
            ctx.json(loanTypeDTO, LoanTypeDTO.class);
        }catch (Exception e) {
            ctx.status(400);
            ctx.json(new Message(400, "LoanType cannot be updated"));
        }

    }

    @Override
    public void delete(Context ctx) {
        try{
            Long id= Long.parseLong(ctx.pathParam("id"));
            dao.delete(id);
            ctx.res().setStatus(200, "LoanType is sucessfully deleted");
        }catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanType cannot be deleted"));
        }

    }

    @Override
    public void getAll(Context ctx) {
        try{
            Set<LoanTypeDTO> loanTypeDTO1=dao.getAll();
            ctx.res().setStatus(200, "LoanType is sucessfully created");
            ctx.json(loanTypeDTO1, LoanTypeDTO.class);
        }catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanType cannot be created"));

    }
    }
}
