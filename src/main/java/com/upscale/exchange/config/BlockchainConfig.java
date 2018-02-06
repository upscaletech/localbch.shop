package com.upscale.exchange.config;

import io.github.jhipster.config.JHipsterConstants;
import multichain.command.MultiChainCommand;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.rmi.ServerException;


@Configuration
public class BlockchainConfig {

    private final Logger log = LoggerFactory.getLogger(BlockchainConfig.class);

    private final ApplicationProperties applicationProperties;

    private MultiChainCommand multiChainCommand;

    public BlockchainConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }


    /**
     * Opens the Multichain connection for use.
     */

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public Object multichainCommand() throws ServerException {
        try {
            multiChainCommand = new MultiChainCommand(applicationProperties.getBlockchain().getHost(),applicationProperties.getBlockchain().getPort(),applicationProperties.getBlockchain().getUsername(),applicationProperties.getBlockchain().getPassword());
            return multiChainCommand;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to created object" , e);
        }

    }

    public void init() throws Exception {
            log.debug("Starting the connection with multichain", multichainCommand());

    }

    public void destroy() throws Exception {

        log.debug("Stopping the connectiong with multichain");

    }


}
