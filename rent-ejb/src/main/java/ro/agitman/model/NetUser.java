package ro.agitman.model;

import ro.agitman.dto.NetTypeEnum;

import javax.persistence.*;

/**
 * Created by AlexandruG on 5/27/2015.
 */
@Entity
@Table(name = "rt_net_user")
public class NetUser extends AbstractModel {

    private Long id;
    private String picUrl;
    private String token;
    private Long tokenExp;
    private NetTypeEnum netTypeEnum;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getTokenExp() {
        return tokenExp;
    }

    public void setTokenExp(Long tokenExp) {
        this.tokenExp = tokenExp;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "net_type")
    public NetTypeEnum getNetTypeEnum() {
        return netTypeEnum;
    }

    public void setNetTypeEnum(NetTypeEnum netTypeEnum) {
        this.netTypeEnum = netTypeEnum;
    }
}
