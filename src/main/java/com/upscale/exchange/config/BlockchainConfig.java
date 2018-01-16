package com.upscale.exchange.config;

import io.github.jhipster.config.JHipsterConstants;
import multichain.command.MultiChainCommand;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.rmi.ServerException;


@Configuration
public class BlockchainConfig {

    private final Logger log = LoggerFactory.getLogger(BlockchainConfig.class);

    private final ApplicationProperties.Blockchain applicationProperties;

    public BlockchainConfig(ApplicationProperties.Blockchain applicationProperties) {
        this.applicationProperties = applicationProperties;
    }


    /**
     * Opens the Multichain connection for use.
     */

    @Bean(initMethod = "start", destroyMethod = "stop")
    @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
    public Object multichainCommand() throws ServerException {
        try {
            Object multichainCommand = new MultiChainCommand(applicationProperties.getHost(),applicationProperties.getPort(),applicationProperties.getUsername(),applicationProperties.getPassword());
            return multichainCommand;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to created object" , e);
        }

    }


}
