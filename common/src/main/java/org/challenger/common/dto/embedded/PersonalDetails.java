package org.challenger.common.dto.embedded;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author u.dubok
 * @since 10/9/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDetails {
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirthday;
}
