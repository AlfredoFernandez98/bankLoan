package dat.routes;

import dat.controllers.impl.LoanOfferController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class LoanOfferRoute {

    private final LoanOfferController loanOfferController = new LoanOfferController();

    protected EndpointGroup getRoutes() {
        return () -> {
            post("/create", loanOfferController::create);
            get("/", loanOfferController::getAll);
            get("/{id}", loanOfferController::getById);
            put("/update/{id}", loanOfferController::update);
            delete("/delete/{id}", loanOfferController::delete);
        };
    }
}
