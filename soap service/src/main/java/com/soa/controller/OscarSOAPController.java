package com.soa.controller;

import com.soa.enums.MovieGenre;
import com.soa.exception.GenreDoesNotExistException;
import com.soa.service.OscarService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@WebService(endpointInterface = "com.soa.controller.OscarSOAPService")
public class OscarSOAPController implements OscarSOAPService {

    private OscarService oscarService;

    public OscarSOAPController() {
        oscarService = lookupRemoteOscarService();
    }

    @WebMethod
    public String ping() {
        return "i am alive";
    }

    public String increaseOscarsWhereDurationIsGreater(@WebParam(name = "Duration") Long duration,
                                                       @WebParam(name = "Oscars") Integer oscars) {
        oscarService.increaseOscars(duration, oscars);
        return "oscars were increased";
    }

    @WebMethod
    public String reallocateOscars(@WebParam(name = "FromGenre") String from,
                                   @WebParam(name = "ToGenre") String to) throws GenreDoesNotExistException {
        try {
            MovieGenre fromGenre = MovieGenre.valueOf(from);
            MovieGenre toGenre = MovieGenre.valueOf(to);
            oscarService.reallocateOscars(fromGenre, toGenre);
            return "oscars were reallocated";
        } catch (IllegalArgumentException e) {
            throw new GenreDoesNotExistException("Genre does not exist: " + e.getMessage());
        }
    }

    private OscarService lookupRemoteOscarService() {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            final Context context = new InitialContext(jndiProperties);
            String workspace = "java:global";
            String moduleName = "soa-lab3-ejb";
            String ejbName = "OscarServiceImpl";
            return (OscarService) context.lookup(workspace + "/" + moduleName + "/" + ejbName);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

}