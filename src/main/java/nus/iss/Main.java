package nus.iss;

import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import nus.iss.resource.CoinCalculatorResource;
import nus.iss.service.CoinCalculatorService;

public class Main extends Application<MainConfiguration> {

    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public void initialize(Bootstrap<MainConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

    @Override
    public void run(MainConfiguration mainConfiguration, Environment environment) {
        CoinCalculatorService coinCalculatorService = new CoinCalculatorService();
        environment.jersey().register(new CoinCalculatorResource(coinCalculatorService));
    }
}
