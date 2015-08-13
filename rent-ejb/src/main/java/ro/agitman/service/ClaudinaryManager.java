package ro.agitman.service;

import com.cloudinary.Cloudinary;

import javax.ejb.Singleton;

/**
 * Created by edi on 4/18/2015.
 */
@Singleton
public class ClaudinaryManager {

    private final Cloudinary claudinary= new Cloudinary(Cloudinary.asMap(
            "cloud_name", "null",
            "api_key", "number",
            "api_secret", "secret"));

    public Cloudinary getClaudinary() {
        return claudinary;
    }
}
