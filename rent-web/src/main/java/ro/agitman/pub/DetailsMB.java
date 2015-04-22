package ro.agitman.pub;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;
import org.primefaces.context.RequestContext;
import ro.agitman.AbstractMB;
import ro.agitman.dto.DotariEnum;
import ro.agitman.dto.DotariEnumCmp;
import ro.agitman.model.Advert;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by edi on 4/20/2015.
 */
@ManagedBean
@SessionScoped
@URLMappings(mappings = {
        @URLMapping(id = "details", pattern = "details", viewId = "/pages/details.jsf")
})
public class DetailsMB extends AbstractMB{

    private Advert selected;
    private List<DotariEnum> dotari;

    public void onRowSelect() throws NoSuchFieldException, IllegalAccessException {
        buildDotari();
        redirect("/pages/details");
        RequestContext.getCurrentInstance().update(":details");
    }

    private void buildDotari() throws NoSuchFieldException, IllegalAccessException {
        dotari = new LinkedList<>();
        long dot = selected.getDotari();
        System.out.println(Long.toBinaryString(dot));
        if (dot != 0) {
            for (DotariEnum d : DotariEnum.values()) {
                if ((dot & d.getVal()) != 0) {
                    dotari.add(d);
                }
            }
        }
        Collections.sort(dotari, new DotariEnumCmp());
    }

    public List<DotariEnum> dotari(){
        return dotari;
    }

    public boolean hasDeposit() {
        return selected != null && !new BigDecimal("0.00").equals(selected.getValue().getDeposit());
    }

    public Advert getSelected() {
        return selected;
    }

    public void setSelected(Advert selected) {
        this.selected = selected;
    }
}
