package org.challenger.common.dto.embedded;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class PaymentMethods {
    private String name;
    private String secretCode;
    private String number;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime expirationDate;
}
