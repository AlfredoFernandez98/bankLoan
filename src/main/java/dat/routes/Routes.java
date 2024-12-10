package dat.routes;

import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {
    private final BankRoute bankRoute = new BankRoute();
    private final LoanOfferRoute loanOfferRoute = new LoanOfferRoute();
    private final LoanRequestRoute loanRequestRoute = new LoanRequestRoute();
    private final LoanTypeRoute loanTypeRoute = new LoanTypeRoute();
    private final LoanUserRoute loanUserRoute = new LoanUserRoute();


    public EndpointGroup getRoutes() {
        return () -> {
            path("/banks", bankRoute.getRoutes());
            path("/offers", loanOfferRoute.getRoutes());
            path("/requests", loanRequestRoute.getRoutes());
            path("/types", loanTypeRoute.getRoutes());
            path("/users", loanUserRoute.getRoutes());

        };
    }
}
