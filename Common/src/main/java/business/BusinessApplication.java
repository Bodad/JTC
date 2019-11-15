package business;

import data.HealthCheck;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BusinessApplication implements IHealthCheck, INamedEntity {
    List<BusinessManager> businessManagers = new ArrayList<>();
    protected final String name;

    protected BusinessApplication(String name){
        this.name = name;
    }

    protected void registerBusinessManager(BusinessManager businessManager){
        this.businessManagers.add(businessManager);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public HealthCheck getHealthCheck() {
        HealthCheck healthCheck = new HealthCheck(this);
        healthCheck.children.addAll(businessManagers.stream().map(BusinessManager::getHealthCheck).collect(Collectors.toList()));
        return healthCheck;
    }

    public abstract void run();
}
