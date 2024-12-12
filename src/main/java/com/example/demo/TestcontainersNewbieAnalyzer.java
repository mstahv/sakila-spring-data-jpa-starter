package com.example.demo;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * This class is a custom failure analyzer that provides a more user-friendly error message when the application fails
 * to start due to a missing database connection configuration. Registered in spring.factories.
 */
public class TestcontainersNewbieAnalyzer extends AbstractFailureAnalyzer<BeanCreationException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, BeanCreationException cause) {
        if (rootFailure.getMessage().contains("entityManagerFactory")) {
            return new FailureAnalysis("""
                DB connection not configured for the production environment!
                """,
                """
                During testing and development, you should most likely start the TestDemoApplication class from the
                test sources. It automatically starts a Docker container with a DB configured for testing and 
                development purposes.
                
                If you are not using an IDE, for some weird reason, start the application with the following command:
                
                    mvn spring-boot:test-run
                """, cause);

        }
        return null;
    }
}
