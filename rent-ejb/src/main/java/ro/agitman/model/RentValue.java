package ro.agitman.model;

import ro.agitman.dto.CurrencyEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rt_value")
public class RentValue extends AbstractModel {

    private Long id;
    private BigDecimal value;
    private BigDecimal deposit;
    private Boolean negotiable;
    private CurrencyEnum currency;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Boolean getNegotiable() {
        return negotiable;
    }

    public void setNegotiable(Boolean negotiable) {
        this.negotiable = negotiable;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

}
