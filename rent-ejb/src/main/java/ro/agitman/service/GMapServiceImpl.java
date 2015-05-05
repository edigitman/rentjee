package ro.agitman.service;

import com.google.gson.Gson;
import ro.agitman.dto.GoogleGeoCodeResponse;
import ro.agitman.facade.GMapService;
import ro.agitman.model.Address;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by AlexandruG on 5/4/2015.
 */
@Stateless
public class GMapServiceImpl implements GMapService {

    @Override
    public void findForAddress(Address address) {
        Gson gson = new Gson();

        String strAdress = address.getCity().getName() + " ";
        strAdress = strAdress + address.getStreet() + " ";
        strAdress = strAdress + address.getNr() + ", RO";

        String jsonResult = null;
        try {
            jsonResult = sendGet(strAdress);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (jsonResult != null) {
            GoogleGeoCodeResponse response = gson.fromJson(jsonResult, GoogleGeoCodeResponse.class);

            address.setLat(new BigDecimal(response.results[0].geometry.location.lat));
            address.setLng(new BigDecimal(response.results[0].geometry.location.lng));
        }
    }

    // HTTP GET request
    private String sendGet(String address) throws IOException {
        address = URLEncoder.encode(address, "UTF-8");
        String url = "http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=false";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        if(con.getResponseCode() != 200){
            return null;
        }

        InputStreamReader isr = new InputStreamReader(con.getInputStream());

        BufferedReader in = new BufferedReader(isr);
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        isr.close();

        return response.toString();
    }

}
