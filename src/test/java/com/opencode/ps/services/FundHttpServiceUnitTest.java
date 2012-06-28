package com.opencode.ps.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FundHttpServiceUnitTest {
    private FundHttpService httpService;

    @Before
    public void onSetUp() {
        FundReader fundReader = new FundReaderImpl();
        httpService = new FundHttpService(fundReader);
    }

    @Test
    public void testFundServiceReturnsValueFromXmlUsingXpathExpression() {
        String data = httpService.showFundDetail("1");
        Assert.assertEquals("ABERDEEN=8.91", data);
    }

    @Test
    public void testFundServiceShowAllDisplaysAllAvailableFundNodes() {
        String data = httpService.showAllFunds();
        Assert.assertTrue(data.contains("ABERDEEN"));
        Assert.assertTrue(data.contains("PERPAUS"));
    }
}
