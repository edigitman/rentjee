package ro.agitman.model;

import java.util.Date;

/**
 * Created by Edi on 11/12/2014.
 */
public abstract class AbstractModel {

    public abstract Long getId();

    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
