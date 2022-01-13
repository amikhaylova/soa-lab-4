package com.soa.controller;

import com.soa.exception.GenreDoesNotExistException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface OscarSOAPService {
    @WebMethod
    String ping();

    @WebMethod
    String increaseOscarsWhereDurationIsGreater(@WebParam(name = "Duration") Long duration,
                                                @WebParam(name = "Oscars") Integer oscars);

    @WebMethod
        //@XmlJavaTypeAdapter(EnumAdapter.class)
    String reallocateOscars(@WebParam(name = "FromGenre") String from,
                            @WebParam(name = "ToGenre") String to) throws GenreDoesNotExistException;

}

