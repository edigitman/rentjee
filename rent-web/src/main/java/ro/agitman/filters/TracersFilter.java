package ro.agitman.filters;

import ro.agitman.dba.DataAccessService;
import ro.agitman.model.Tracers;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.security.Principal;
import java.util.Date;

/**
 * Created by AlexandruG on 5/4/2015.
 */
@WebFilter("/*")
public class TracersFilter implements Filter {

    @EJB
    private DataAccessService service;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long t0 = System.currentTimeMillis();
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String path = req.getServletPath();
        if (!path.startsWith("/javax.faces.resource")) {
            Tracers t = new Tracers();
            t.setCreateDate(new Date());
            t.setIp(pack(InetAddress.getByName(req.getRemoteAddr()).getAddress()));

            Principal principal = req.getUserPrincipal();
            if (principal != null) {
                t.setUser(principal.getName());
            }
            t.setUrl(buildUrl(req.getServletPath()));

            //TODO cauta ora, cartier si pret

            service.create(t);
        }
        System.out.println("TracersFilter time: " + (System.currentTimeMillis() - t0));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private int buildUrl(String path) {
        if (path.contains("index"))
            return 1;


        return 0;
    }


    private long pack(byte[] bytes) {
        long val = 0;
        for (int i = 0; i < bytes.length; i++) {
            val <<= 8;
            val |= bytes[i] & 0xff;
        }
        return val;
    }

    @Override
    public void destroy() {

    }
}
