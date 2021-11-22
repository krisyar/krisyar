package com.testing;

import static org.slf4j.LoggerFactory.getLogger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Value;


/**
 * A very basic Hello World controller which returns the hostname.
 *
 * @author kim
 *
 */
@RestController
public class HelloWorldController {

    @Autowired
	private static final Logger LOG = getLogger(HelloWorldController.class.getName());
    public static final String MESSAGE_KEY = "Service-Name";	
	@Value("${spring.application.name}")
    private String appname;
    //public static final String HOSTNAME_KEY = "hostname";
   //public static final String IP_KEY = "ip";

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> helloWorld() throws UnknownHostException {
        return getResponse();
    }

    private Map<String, String> getResponse() throws UnknownHostException {
        String host = InetAddress.getLocalHost().getHostName();
        String ip = InetAddress.getLocalHost().getHostAddress();		
        Map<String, String> response = new HashMap<>();
		System.out.println("***********" + appname);
        response.put(MESSAGE_KEY, appname);
        //response.put(HOSTNAME_KEY, host);
        //response.put(IP_KEY, ip);
        LOG.info("Returning {}", response);
        return response;
    }

}
