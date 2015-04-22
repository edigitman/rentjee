package ro.agitman.pub;

import org.primefaces.context.RequestContext;
import ro.agitman.AbstractMB;
import ro.agitman.dto.DotariEnum;
import ro.agitman.model.Advert;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by edi on 4/20/2015.
 */
@ManagedBean
@SessionScoped
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
    }

    public List<DotariEnum> dotari(){
        return dotari;
    }

    public boolean hasDeposit(){
        if(selected!=null){
            return !new BigDecimal("0.00").equals( selected.getValue().getDeposit());
        }

        return false ;
    }

    public Advert getSelected() {
        return selected;
    }

    public void setSelected(Advert selected) {
        this.selected = selected;
    }
}
