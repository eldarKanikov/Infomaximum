package main.java;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SAXParserFactory factory;
        SAXParser saxParser;
        SaxParsingAdress saxParsingAdress = new SaxParsingAdress();
        File file = new File(request("Enter path to file: "));
        if (file.isFile() && file.exists()){
            try {
                factory = SAXParserFactory.newInstance();
                saxParser = factory.newSAXParser();
                saxParser.parse(file, saxParsingAdress);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
            saxParsingAdress.getDuplicateService().printStatistics();
            saxParsingAdress.getFloorService().printStatisticFloor();
        } else {
            System.out.println("File not found");
        }

    }
    private static String request(String description){
        System.out.print(description);
        return new Scanner(System.in).nextLine();
    }
}
