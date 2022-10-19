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
public class Features {
    private String frontBrake;
    private String rearBrake;
    private Boolean ledTailLight;
    private String abs;
    private String speedometer;
    private String tripmeter;
    private String tachometer;
    private String odometer;
    private Boolean fuelGauge;
    private Boolean clock;
    private Boolean passengerFootrest;

}
