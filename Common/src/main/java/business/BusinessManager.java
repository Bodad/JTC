package business;

import data.HealthCheck;

public class BusinessManager implements IHealthCheck, INamedEntity{
    protected final String name;

    public BusinessManager(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
