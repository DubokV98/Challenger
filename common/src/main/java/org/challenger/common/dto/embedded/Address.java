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
public class Address {
    private String name;
    private String street;
    private String state;
    private String zip;
}
