import static org.junit.jupiter.api.Assertions.assertEquals;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import java.util.List;
import nus.iss.api.CoinCalculatorRequest;
import nus.iss.resource.CoinCalculatorResource;
import nus.iss.service.CoinCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CoinCalculatorResourceTest {

    private static final ResourceExtension EXT =
            ResourceExtension.builder()
                    .addResource(new CoinCalculatorResource(new CoinCalculatorService()))
                    .build();

    @Test
    void validRequest() {
        CoinCalculatorRequest request = new CoinCalculatorRequest();
        request.setTarget(100.0);
        request.setCoinDenominations(List.of(0.1, 0.5));

        Response response = EXT.target("/coin/calculate").request().post(Entity.json(request));

        assertEquals(200, response.getStatus());
    }

    @Test
    void invalidRequest_TargetAbove10000() {
        CoinCalculatorRequest request = new CoinCalculatorRequest();
        request.setTarget(10001.0);
        request.setCoinDenominations(List.of(0.1, 0.5));

        Response response = EXT.target("/coin/calculate").request().post(Entity.json(request));

        assertEquals(422, response.getStatus());
    }

    @Test
    void invalidRequest_TargetBelow0() {
        CoinCalculatorRequest request = new CoinCalculatorRequest();
        request.setTarget(-1.0);
        request.setCoinDenominations(List.of(0.1, 0.5));

        Response response = EXT.target("/coin/calculate").request().post(Entity.json(request));

        assertEquals(422, response.getStatus());
    }

    @Test
    void invalidRequest_invalidDenomination() {
        CoinCalculatorRequest request = new CoinCalculatorRequest();
        request.setTarget(100.0);
        request.setCoinDenominations(List.of(0.3, 0.5));

        Response response = EXT.target("/coin/calculate").request().post(Entity.json(request));

        assertEquals(422, response.getStatus());
    }
}
