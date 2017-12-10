package services;

import model.Technology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TechnologyDataService {

    private List<Technology> technologyList;

    private static TechnologyDataService serviceInstance = new TechnologyDataService();

    public static TechnologyDataService getInstance(){
        return serviceInstance;
    }

    private TechnologyDataService(){
        technologyList = new ArrayList<>(Arrays.asList(
                new Technology(1, "Java", "A cross-platform OOP language"),
                new Technology(2, "C#", "A .NET OOP language")
        ));


    }

    public List<Technology> getTechnologyList() {
        return technologyList;
    }

    public void addTechnology(Technology technology){
        long newId = technologyList.size() + 1;
        technology.setId(newId);
        technologyList.add(technology);
    }
}
