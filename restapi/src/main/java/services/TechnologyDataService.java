package services;

import model.Technology;

import javax.ws.rs.core.Response;
import java.util.List;

public interface TechnologyDataService {

    Technology getTechnology(String name);

    List<Technology> getTechnologyList();

    void addTechnology(Technology technology);

    Response deleteTechnology(String name);
}
