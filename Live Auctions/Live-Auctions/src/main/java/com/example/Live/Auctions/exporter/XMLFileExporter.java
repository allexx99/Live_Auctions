package com.example.Live.Auctions.exporter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;

public class XMLFileExporter implements FileExporter {

    @Override
    public String exportData(Object object) {
        String xmlContent = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(object, sw);

            xmlContent = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return xmlContent;
    }
}
