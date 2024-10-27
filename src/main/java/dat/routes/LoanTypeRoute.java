package dat.routes;

import dat.controllers.impl.LoanTypeController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class LoanTypeRoute {

    private final LoanTypeController loanTypeController = new LoanTypeController();

    protected EndpointGroup getRoutes() {
        return () -> {
            post("/create", loanTypeController::create);
            get("/", loanTypeController::getAll);
            get("/{id}", loanTypeController::getById);
            put("/update/{id}", loanTypeController::update);
            delete("/delete/{id}", loanTypeController::delete);
        };
    }
}
