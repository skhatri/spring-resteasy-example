package com.opencode.ps.services;

import java.io.InputStream;
import java.io.StringReader;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

@Service
public class FundReaderImpl implements FundReader {

    private static final String DATA_XML = "data.xml";

    @Override
    public String getFundDetail(String id) {
        String data;
        XPath xpath = XPathFactory.newInstance().newXPath();
        try {
            ClassPathResource resource = new ClassPathResource(DATA_XML);
            InputStream stream = resource.getInputStream();
            int available = stream.available();
            byte[] availableBytes = new byte[available];
            stream.read(availableBytes);
            String xmlData = new String(availableBytes, "UTF-8");
            String fundNode = String.format("//fund[id='%s']", id);
            String fundName = xpath.compile(String.format("%s/name", fundNode)).evaluate(
                    new InputSource(new StringReader(xmlData)));
            String rate = xpath.compile(String.format("%s/rate", fundNode)).evaluate(
                    new InputSource(new StringReader(xmlData)));
            data = String.format("%s=%s", fundName, rate);
        } catch (Exception e) {
            data = "error " + e.getMessage();
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getAllFunds() {
        String data;
        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(new ClassPathResource(DATA_XML).getInputStream());
            StringBuilder response = new StringBuilder();
            for (Element fund : (List<Element>) document.getRootElement().getChildren()) {
                String fundName = fund.getChildText("name");
                String rate = fund.getChildText("rate");
                response.append(fundName).append("=").append(rate).append("\n");
            }
            data = response.toString();
        } catch (Exception e) {
            data = "error " + e.getMessage();
        }
        return data;
    }

}
