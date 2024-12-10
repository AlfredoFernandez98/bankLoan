package dat.routes;

import dat.controllers.impl.BankController;
import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;


import static io.javalin.apibuilder.ApiBuilder.*;

public class BankRoute {
    private final BankController bankController = new BankController();

    protected EndpointGroup getRoutes() {
        return () -> {
            post("/create", bankController::create, Role.ADMIN);
            get("/", bankController::getAll,Role.ADMIN);
            get("/{id}", bankController::getById,Role.ADMIN);
            put("/update/{id}", bankController::update, Role.ADMIN);
            delete("/delete/{id}", bankController::delete,Role.ADMIN);
        };
    }

}
