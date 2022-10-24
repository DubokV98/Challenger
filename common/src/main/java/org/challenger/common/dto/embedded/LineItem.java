package org.challenger.common.dto.embedded;

import lombok.Data;

/**
 * @author u.dubok
 * @since 10/24/2022
 */
@Data
public class LineItem {
    private String id;
    private String token;
    private String model;
    private Integer year;
    private Integer quantity;
    private Pricing pricing;
}
