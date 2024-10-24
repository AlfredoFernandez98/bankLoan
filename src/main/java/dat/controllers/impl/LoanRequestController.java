package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.Controller;
import dat.daos.impl.LoanOfferDAO;
import dat.daos.impl.LoanRequestDAO;
import dat.dtos.LoanRequestDTO;
import dat.entities.LoanRequest;
import dat.exception.Message;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.Set;

public class LoanRequestController implements Controller {

    private final LoanRequestDAO dao;

    public LoanRequestController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.dao=LoanRequestDAO.getInstance(emf);
    }

    @Override
    public void getById(Context ctx) {
        try{
            Long id= Long.parseLong(ctx.pathParam("id"));
            LoanRequestDTO loanRequest = dao.getById(id)
                    .orElseThrow(() -> new RuntimeException("LoanRequest not found"));
        }catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanRequest cannot be found"));
        }

    }

    @Override
    public void create(Context ctx) {
        try {
            LoanRequestDTO loanRequestDTO=ctx.bodyAsClass(LoanRequestDTO.class);
            LoanRequestDTO loanRequestDTO1=dao.create(loanRequestDTO);
            ctx.res().setStatus(201, "LoanRequest is sucessfully created");
            ctx.json(loanRequestDTO1, LoanRequestDTO.class);
        }
        catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanRequest cannot be created"));
        }

    }

    @Override
    public void update(Context ctx) {
        try{
            Long id= Long.parseLong(ctx.pathParam("id"));
            LoanRequestDTO loanRequestDTO= ctx.bodyAsClass(LoanRequestDTO.class);
            loanRequestDTO.setId(id);
            dao.update(loanRequestDTO);
            ctx.res().setStatus(200, "LoanRequest is sucessfully updated");
            ctx.json(loanRequestDTO, LoanRequestDTO.class);
        }catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanRequest cannot be updated"));
        }

    }

    @Override
    public void delete(Context ctx) {

    }

    @Override
    public void getAll(Context ctx) {
        try{
           Set<LoanRequestDTO> loanRequestDTO1=dao.getAll();
            ctx.res().setStatus(200, "LoanRequest is sucessfully created");
            ctx.json(loanRequestDTO1, LoanRequestDTO.class);
        }catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanRequest cannot be created"));
        }

    }
}
