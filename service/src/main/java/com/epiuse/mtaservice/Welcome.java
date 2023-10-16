package com.epiuse.mtaservice;

public class Welcome {
    
    private String name;

    public Welcome(String name)
    {
        this.name = name;
    }

    public String getContent() {
        return name;
    }

}