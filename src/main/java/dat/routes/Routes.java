package dat.routes;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {
    private final BankRoute bankRoute = new BankRoute();
    private final LoanOfferRoute loanOfferRoute = new LoanOfferRoute();


    public EndpointGroup getRoutes() {
        return () -> {
            path("/banks", bankRoute.getRoutes());
            path("offers", loanOfferRoute.getRoutes());
        };
    }
}
