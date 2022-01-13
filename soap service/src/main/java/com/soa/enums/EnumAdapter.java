package com.soa.enums;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EnumAdapter extends XmlAdapter<String, MovieGenre> {

    @Override
    public MovieGenre unmarshal(String value) throws Exception {
        return MovieGenre.valueOf(value);
    }

    @Override
    public String marshal(MovieGenre value) throws Exception {
        return value.toString();
    }
}