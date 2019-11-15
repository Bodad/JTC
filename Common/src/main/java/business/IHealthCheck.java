package business;

import data.HealthCheck;

public interface IHealthCheck extends INamedEntity {
    default HealthCheck getHealthCheck() {
        return new HealthCheck(this);
    }
}
