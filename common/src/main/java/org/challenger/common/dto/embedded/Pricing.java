package org.challenger.common.dto.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author u.dubok
 * @since 10/9/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pricing {
    private Double retail;
    private Double sale;
    private String currency;
    private LocalDateTime startDate;
}
