package nus.iss.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import nus.iss.api.CoinCalculatorRequest;
import nus.iss.service.CoinCalculatorService;

@Path("coin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CoinCalculatorResource {

    private CoinCalculatorService cService;

    public CoinCalculatorResource(CoinCalculatorService service) {
        this.cService = service;
    }

    @POST
    @Path("calculate")
    public Response calculateCoins(@Valid CoinCalculatorRequest request) {
        List<Double> calculatedCoins =
                cService.calculateCoins(request.getTarget(), request.getCoinDenominations());
        return Response.ok(calculatedCoins).build();
    }
}
