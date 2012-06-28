package com.opencode.ps.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Path("/services")
public class FundHttpService {

    private FundReader fundReader;

    @Autowired
    public FundHttpService(FundReader fundReader) {
        this.fundReader = fundReader;
    }

    @GET
    @Path("/show/{id}")
    @Produces(value = "text/plain")
    public String showFundDetail(@PathParam("id") String id) {
        String data = "";
        data = fundReader.getFundDetail(id);
        return data;
    }

    @GET
    @Path("/showall")
    @Produces(value = "text/plain")
    public String showAllFunds() {
        String data = "";
        data = fundReader.getAllFunds();
        return data;
    }

}
