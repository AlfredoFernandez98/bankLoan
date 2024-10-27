package dat.routes;

import dat.controllers.impl.LoanUserController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class LoanUserRoute {
    private final LoanUserController loanUserController = new LoanUserController();

    protected EndpointGroup getRoutes() {
        return () -> {
            post("/create", loanUserController::create);
            get("/", loanUserController::getAll);
            get("/{id}", loanUserController::getById);
            put("/update/{id}", loanUserController::update);
            delete("/delete/{id}", loanUserController::delete);
        };
    }
}
