package com.demo.backend.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {

    private final Map<String, Feature> featureMap = new ConcurrentHashMap<>();

    @ReadOperation
    public Map<String, Feature> featureEndpoint() {
        featureMap.put("User", new Feature(true));
        return featureMap;
    }

    @ReadOperation
    public Feature features(@Selector String name) {
        return featureMap.get(name);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Feature {
        private Boolean isEnabled;
    }
}
