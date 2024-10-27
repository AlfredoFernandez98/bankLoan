package dat.routes;

import dat.controllers.impl.BankController;
import io.javalin.apibuilder.EndpointGroup;


import static io.javalin.apibuilder.ApiBuilder.*;

public class BankRoute {
    private final BankController bankController = new BankController();

    protected EndpointGroup getRoutes() {
        return () -> {
            post("/create", bankController::create);
            get("/", bankController::getAll);
            get("/{id}", bankController::getById);
            put("/update/{id}", bankController::update);
            delete("/delete/{id}", bankController::delete);
        };
    }

}
