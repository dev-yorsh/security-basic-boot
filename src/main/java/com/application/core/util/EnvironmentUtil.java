package com.application.core.util;

import com.application.core.exception.http.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class EnvironmentUtil {

    @Autowired
    Environment environment;
    private String port;
    private String hostname;

    public String getPort() {
        if (port == null) port = environment.getProperty("local.server.port");
        return port;
    }

    public String getHostname() {
        try {
            if (hostname == null) hostname = InetAddress.getLocalHost().getHostAddress();
            return hostname;
        } catch (UnknownHostException ex) {
            throw new BusinessException(ex.getCause().toString());
        }
    }

    public String getServerUrl() {
        return "http://" + getHostname() + ":" + getPort();
    }
}
