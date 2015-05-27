package ro.agitman.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexandruG on 5/27/2015.
 */
@Generated("org.jsonschema2pojo")
public class FBValidateTokenResponse {

    @SerializedName("app_id")
    @Expose
    private Integer appId;
    @Expose
    private String application;
    @SerializedName("expires_at")
    @Expose
    private Integer expiresAt;
    @SerializedName("is_valid")
    @Expose
    private Boolean isValid;
    @SerializedName("issued_at")
    @Expose
    private Integer issuedAt;
    @Expose
    private Metadata metadata;
    @Expose
    private List<String> scopes = new ArrayList<String>();
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    /**
     * @return The appId
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId The app_id
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * @return The application
     */
    public String getApplication() {
        return application;
    }

    /**
     * @param application The application
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * @return The expiresAt
     */
    public Integer getExpiresAt() {
        return expiresAt;
    }

    /**
     * @param expiresAt The expires_at
     */
    public void setExpiresAt(Integer expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * @return The isValid
     */
    public Boolean getIsValid() {
        return isValid;
    }

    /**
     * @param isValid The is_valid
     */
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    /**
     * @return The issuedAt
     */
    public Integer getIssuedAt() {
        return issuedAt;
    }

    /**
     * @param issuedAt The issued_at
     */
    public void setIssuedAt(Integer issuedAt) {
        this.issuedAt = issuedAt;
    }

    /**
     * @return The metadata
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * @param metadata The metadata
     */
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    /**
     * @return The scopes
     */
    public List<String> getScopes() {
        return scopes;
    }

    /**
     * @param scopes The scopes
     */
    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    /**
     * @return The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}

@Generated("org.jsonschema2pojo")
class Metadata {

    @Expose
    private String sso;

    /**
     * @return The sso
     */
    public String getSso() {
        return sso;
    }

    /**
     * @param sso The sso
     */
    public void setSso(String sso) {
        this.sso = sso;
    }


}
