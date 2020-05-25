package main.java;

import main.java.model.Address;
import main.java.service.StatisticDuplicateService;
import main.java.service.StatisticsFloorService;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParsingAdress extends DefaultHandler {

    private Address address;
    private StatisticDuplicateService duplicateService;
    private StatisticsFloorService floorService;
    private boolean isDuplicated;

    public SaxParsingAdress() {
        duplicateService = new StatisticDuplicateService();
        floorService = new StatisticsFloorService();
    }

    @Override
    public void startDocument(){
        System.out.println("Start parse XML ...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if (qName.equalsIgnoreCase("item")) {
            address = new Address();
            address.setCity(attributes.getValue("city"));
            address.setStreet(attributes.getValue("street"));
            address.setHouse(Integer.parseInt(attributes.getValue("house")));
            address.setFloor(Integer.parseInt(attributes.getValue("floor")));

            isDuplicated = duplicateService.collectAndReturnIsDuplicated(address);
            if (!isDuplicated){
                floorService.collectStatisticsOfFloor(address.getCity(), address.getFloor());
            }
        }
    }

    @Override
    public void endDocument(){
        System.out.println("End parse XML");
    }


    public StatisticDuplicateService getDuplicateService() {
        return duplicateService;
    }

    public StatisticsFloorService getFloorService() {
        return floorService;
    }
}
