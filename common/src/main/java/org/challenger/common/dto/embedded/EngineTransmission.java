package org.challenger.common.dto.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author u.dubok
 * @since 10/9/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngineTransmission {
    private String engineType;
    private String displacement;
    private String maxTorque;
    private Integer maxCylinders;
    private String coolingSystem;
    private Integer valvePerCylinder;
    private String fuelSupply;
    private String gearBox;

    private Double mileage;
    private String fuelType;
    private String maxPower;
}
