package ro.agitman.pub;

import com.google.gson.Gson;
import com.ocpsoft.pretty.PrettyContext;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ro.agitman.AbstractMB;
import ro.agitman.dto.CaptchaResponse;
import ro.agitman.dto.FBValidateTokenResponse;
import ro.agitman.model.Advert;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.*;
import java.util.*;

/**
 * Created by AlexandruG on 5/27/2015.
 */
@ManagedBean(name = "netLogin")
@ViewScoped
@URLMappings(mappings = {
        @URLMapping(id = "callbackF", pattern = "/callback/f/#{netLogin.callcback}", viewId = "/pages/loginNet.xhtml?faces-redirect=true"),
        @URLMapping(id = "callbackG", pattern = "/callback/g/#{netLogin.callcback}", viewId = "/pages/loginNet.xhtml?faces-redirect=true"),
        @URLMapping(id = "callbackT", pattern = "/callback/t/#{netLogin.callcback}", viewId = "/pages/loginNet.xhtml?faces-redirect=true")
})
public class NetLoginMB extends AbstractMB {

    private String callcback;

    @URLAction(onPostback = false)
    public void init() {
        String viewId = PrettyContext.getCurrentInstance().getCurrentMapping().getId();

        if ("callbackF".equals(viewId)) {
           facebookFlow();
        }

        if ("callbackG".equals(viewId)) {

        }
    }

    private void facebookFlow(){
        //todo get token
        String token = splitQuery(callcback).get("token").get(0);
        FBValidateTokenResponse response = sendFacebookTokenValidation(token);
        response.getIsValid();


    }

    // HTTP GET request
    private FBValidateTokenResponse sendFacebookTokenValidation(String token) {
        StringBuilder url = new StringBuilder("http://graph.facebook.com/debug_token?");
        url.append("access_token=").append("1078929275454685|tqQ_MlAGX2aMEHNqTJ0nIBx-DkU");
        url.append("&input_token=").append(token);

        try {
            URL obj = new URL(url.toString());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            if (con.getResponseCode() != 200) {
                return null;
            }
            FBValidateTokenResponse objParsed;
            try (InputStreamReader isr = new InputStreamReader(con.getInputStream())) {
                final Gson gson = new Gson();
                final BufferedReader reader = new BufferedReader(isr);
                objParsed = gson.fromJson(reader, FBValidateTokenResponse.class);
            }
            return objParsed;
        }catch (IOException e){
            return null;
        }
    }

    private Map<String, List<String>> splitQuery(String url) {
        final Map<String, List<String>> queryPairs = new LinkedHashMap<>();
        final String[] pairs = url.split("&");
        try {
            for (String pair : pairs) {
                final int idx = pair.indexOf("=");
                final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
                if (!queryPairs.containsKey(key)) {
                    queryPairs.put(key, new LinkedList<String>());
                }
                final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
                queryPairs.get(key).add(value);
            }
        }catch (UnsupportedEncodingException e){
            return queryPairs;
        }
        return queryPairs;
    }


    public String getCallcback() {
        return callcback;
    }

    public void setCallcback(String callcback) {
        this.callcback = callcback;
    }


}
