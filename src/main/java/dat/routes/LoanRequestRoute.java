package dat.routes;

import dat.controllers.impl.LoanRequestController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class LoanRequestRoute {
    private final LoanRequestController loanRequestController= new LoanRequestController();

    protected EndpointGroup getRoutes() {
        return () -> {
            post("/create", loanRequestController::create);
            get("/", loanRequestController::getAll);
            get("/{id}", loanRequestController::getById);
            put("/update/{id}", loanRequestController::update);
            delete("/delete/{id}", loanRequestController::delete);
        };
    }
}
