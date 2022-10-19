package org.challenger.common.dto.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author u.dubok
 * @since 10/13/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotorcycleForOrder {
    private String id;
    private String token;
    private String model;
    private Integer year;
    private Integer quantity;
    private Pricing pricing;
}
