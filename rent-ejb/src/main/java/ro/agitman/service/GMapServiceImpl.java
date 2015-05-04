package ro.agitman.service;

import com.google.gson.Gson;
import ro.agitman.dto.GoogleGeoCodeResponse;
import ro.agitman.facade.GMapService;
import ro.agitman.model.Address;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

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

        String jsonResult = "";
        try {
            URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + strAdress + "&sensor=false");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                jsonResult += inputLine;
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (!"".equals(jsonResult)) {
            GoogleGeoCodeResponse response = gson.fromJson(jsonResult, GoogleGeoCodeResponse.class);
            double lat = Double.parseDouble(response.results[0].geometry.location.lat);
            double lng = Double.parseDouble(response.results[0].geometry.location.lng);

            address.setLat(lat);
            address.setLng(lng);
        }
    }
}
