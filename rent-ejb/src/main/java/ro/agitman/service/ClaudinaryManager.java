package ro.agitman.service;

import com.cloudinary.Cloudinary;

import javax.ejb.Singleton;

/**
 * Created by edi on 4/18/2015.
 */
@Singleton
public class ClaudinaryManager {

    private final Cloudinary claudinary= new Cloudinary(Cloudinary.asMap(
            "cloud_name", "dl2g1uqtc",
            "api_key", "471616466767527",
            "api_secret", "o1mDIm4IWTBVEg1j2sfpXi020X8"));

    public Cloudinary getClaudinary() {
        return claudinary;
    }
}
