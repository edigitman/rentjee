package ro.agitman.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({@NamedQuery(name = User.FIND_BY_EMAIL, query = "select u from User u where u.email = :email"),
        @NamedQuery(name = User.FIND_BY_TOKEN, query = "select u from User u where u.regToken = :token"),
        @NamedQuery(name = User.FIND_BY_EMAIL_TOKEN, query = "select u from User u where u.regToken = :token and u.email = :email")
})

public class User extends AbstractModel {

    public static final String FIND_BY_EMAIL = "User.findUserByEmail";
    public static final String FIND_BY_TOKEN = "User.findUserByToken";
    public static final String FIND_BY_EMAIL_TOKEN = "User.findUserByEmailAndToken";

    private Long id;
    private String email;
    private String password;
    private String name;
    private String role;
    private String phone;
    private String phone2;
    private String phone3;
    private String regToken;
    private boolean confirmedBl;
    private NetUser netUser;
    private Address = address;
    private Boolean agency;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getRegToken() {
        return regToken;
    }

    public void setRegToken(String regToken) {
        this.regToken = regToken;
    }

    @Basic
    @Column(name = "confirmed_BL")
    public boolean isConfirmedBl() {
        return confirmedBl;
    }

    public void setConfirmedBl(boolean confirmedBl) {
        this.confirmedBl = confirmedBl;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "net_user_id")
    public NetUser getNetUser() {
        return netUser;
    }

    public void setNetUser(NetUser netUser) {
        this.netUser = netUser;
    }

    public Boolean isAgency(){
        return this.agency;
    }

    public void setAgency(Boolean agency){
        this.agency = agency;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    public Address getAddress(){
        return this.Address;
    }

    public void setAddress(Address address){
        this.address = address;
    }
}
