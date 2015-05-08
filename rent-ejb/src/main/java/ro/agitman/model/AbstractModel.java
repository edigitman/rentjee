package ro.agitman.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Edi on 11/12/2014.
 */
public abstract class AbstractModel implements Serializable{

    public abstract Long getId();

    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @inheritDoc
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!this.getClass().isInstance(obj)) {
            return false;
        }
        AbstractModel po = (AbstractModel) obj;
        EqualsBuilder builder = new EqualsBuilder();
        if (getId() != null && po.getId() != null) {
            builder.append(getId(), po.getId());
        }
        return builder.isEquals();
    }

    /**
     * @inheritDoc
     */
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        if (getId() != null) {
            builder.append(getId());
        }
        return builder.toHashCode();
    }

    /**
     * @inheritDoc
     */
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.append("id", getId());
        return builder.toString();

    }

}
