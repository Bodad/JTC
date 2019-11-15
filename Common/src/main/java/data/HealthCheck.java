package data;

import business.INamedEntity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HealthCheck {
    public final String name;
    public String status = "Good";
    public List<HealthCheck> children = new ArrayList<>();

    public HealthCheck(INamedEntity namedEntity){
        this.name = namedEntity.getName();
    }

    @Override
    public String toString() {
        return String.format("%s: %s", name, status);
    }
}
