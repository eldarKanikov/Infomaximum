package main.java.service;

import java.util.HashMap;
import java.util.Map;

public class StatisticsFloorService {
    private Map<String, int[]> cityFloor;
    private StatisticDuplicateService duplicateService;

    public StatisticsFloorService() {
        this.cityFloor = new HashMap<>();
        this.duplicateService = new StatisticDuplicateService();
    }

    public void collectStatisticsOfFloor(String city, int floor){
        if (floor <= 5){
            int[] floors = cityFloor.getOrDefault(city, new int[]{0, 0, 0, 0, 0});
            ++floors[floor - 1];
            cityFloor.put(city, floors);
        }
    }

    public void printStatisticFloor(){
        String format = "%-20s %-6s %-6s %-6s %-6s %-6s";
        System.out.format(format, "City/Floors", "1", "2", "3", "4", "5");
        System.out.println();
        cityFloor
                .entrySet()
                .parallelStream()
                .forEach(s -> {
                    int[] arr = s.getValue();
                    System.out.format(format, s.getKey(), arr[0], arr[1], arr[2], arr[3], arr[4]);
                    System.out.println();
                });
    }
}
