package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.Controller;
import dat.daos.impl.LoanOfferDAO;
import dat.dtos.BankDTO;
import dat.dtos.LoanOfferDTO;
import dat.entities.LoanOffer;
import dat.exception.Message;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.Set;
import java.util.stream.Collectors;


public class LoanOfferController implements Controller {
    private final LoanOfferDAO dao;

    public LoanOfferController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.dao = LoanOfferDAO.getInstance(emf);
    }

    @Override
    public void getById(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam( "id"));
        LoanOfferDTO loanOfferDTO = dao.getById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found"));
        ctx.res().setStatus(200);
        ctx.json( loanOfferDTO,LoanOfferDTO.class);

    }

    @Override
    public void create(Context ctx) {
        try{
            LoanOfferDTO loanOfferDTO = ctx.bodyAsClass(LoanOfferDTO.class);
            LoanOfferDTO loanOfferDTO1 = dao.create(loanOfferDTO);
            ctx.res().setStatus(201, "LoanOffer is sucessfully created");
            ctx.json(loanOfferDTO1, LoanOfferDTO.class);

        }catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanOffer cannot be created"));
        }


    }

    @Override
    public void update(Context ctx) {
        try{
            Long id= Long.parseLong(ctx.pathParam("id"));
            LoanOfferDTO loanOfferDTO = ctx.bodyAsClass(LoanOfferDTO.class);
            LoanOfferDTO existingLoanOffer = dao.getById(id)
                    .orElseThrow(() -> new RuntimeException("LoanOffer not found"));
            dao.update(loanOfferDTO);
            ctx.res().setStatus(200);
            ctx.json(LoanOfferDTO.class);
        }catch (Exception e) {
            ctx.status(400);
            ctx.json(new Message(400, "LoanOffer cannot be updated"));
        }

    }

    @Override
    public void delete(Context ctx) {
        try{
            Long id = Long.parseLong(ctx.pathParam("id"));
            LoanOfferDTO loanOfferDTO = dao.getById(id)
                    .orElseThrow(() -> new RuntimeException("LoanOffer not found"));
            dao.delete(id);
            ctx.res().setStatus(204);
        }catch (Exception e){
            ctx.status(400);
            ctx.json(new Message(400, "LoanOffer cannot be deleted"));
        }

    }

    @Override
    public void getAll(Context ctx) {
        try {
            Set<LoanOfferDTO> loanOffer = dao.getAll();
            ctx.status(200);
            ctx.json( loanOffer,LoanOfferDTO.class);

        } catch (Exception e) {
            ctx.status(400);
            ctx.json(new Message(400, "Cant get All Offers"));
        }
    }
}
