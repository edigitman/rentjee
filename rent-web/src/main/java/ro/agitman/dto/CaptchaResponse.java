package ro.agitman.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by AlexandruG on 4/22/2015.
 */
public class CaptchaResponse implements Serializable {

    @SerializedName("error-codes")
    private String[] errorCodes;

    private String success;

    public String[] getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(String[] errorCodes) {
        this.errorCodes = errorCodes;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ClassPojo [error-codes = " + errorCodes + ", success = " + success + "]";
    }

}
