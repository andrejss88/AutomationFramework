package services.impl;

import model.Technology;
import services.TechnologyDataService;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * A quick and dirty implementation using List, so no DataBases and actual persistence
 * The list will always be reset and any POSTed entries will be lost after server restart or app redeployment
 */
public class ListTechnologyDataService implements TechnologyDataService {

    private List<Technology> technologyList;

    public ListTechnologyDataService(){
        technologyList = new ArrayList<>(Arrays.asList(
                new Technology("Java", "A cross-platform OOP language"),
                new Technology("C#", "A .NET OOP language")
        ));
    }

    @Override
    public Technology getTechnology(String name) {
            return technologyList.stream()
                    .filter( t -> t.getName().equals(name))
                    .findFirst().orElse(null);
    }

    @Override
    public List<Technology> getTechnologyList() {
        return technologyList;
    }

    @Override
    public void addTechnology(Technology technology){
        technologyList.add(technology);
    }

    @Override
    public Response deleteTechnology(String name) {

        Optional<Technology> tech = technologyList.stream()
                .filter(t -> t.getName().equals(name))
                .findFirst();

        if (tech.isPresent()){
            technologyList.remove(tech.get());
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
