package controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlChecker {
    String isTheSiteUp = "site is up";
    String isTheSiteDown = "site is down";
    String isInvalidUrl = "invalid URL";
    @GetMapping("/test_site")
    public String getUrlStatusMessage(@RequestParam String url) throws IOException{
        
        // int urlResponse;
        URL urlObject = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int urlResults = (conn.getResponseCode()/100);
        if(urlResults != 2 || urlResults != 3){
            return isTheSiteDown;
        }else if(urlResults == 2 || urlResults == 3){
            return isTheSiteUp;
        }else{
            return isInvalidUrl;
        }
    }
}
