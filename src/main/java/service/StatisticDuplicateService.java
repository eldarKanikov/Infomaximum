package main.java.service;

import main.java.model.Address;

import java.util.HashMap;
import java.util.Map;


public class StatisticDuplicateService {

    private Map<Address, Integer> statistics;

    public StatisticDuplicateService() {
        this.statistics = new HashMap<>();
    }


    public boolean collectAndReturnIsDuplicated(Address address){
        if (statistics.containsKey(address)){
            statistics.put(address, statistics.get(address) + 1);
            return true;
        } else {
            statistics.put(address, 1);
            return false;
        }
    }

    public void printStatistics() {
        String format = "%-60s %-6s";
        System.out.format(format, "Address", "Count of duplicates");
        System.out.println();
        statistics
                .entrySet()
                .parallelStream()
                .filter(i -> i.getValue() > 1)
                .forEach(s -> {
                    Address key = s.getKey();
                    System.out.format(format, key.getCity() + ", " + key.getStreet() + ", " + key.getHouse() + ", " + key.getFloor(), s.getValue());
                    System.out.println();
                });
        System.out.println();
    }
}
