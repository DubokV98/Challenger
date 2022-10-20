package org.challenger.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.challenger.common.dto.embedded.Address;
import org.challenger.common.dto.embedded.Contact;
import org.challenger.common.dto.embedded.PaymentMethods;
import org.challenger.common.dto.embedded.PersonalDetails;
import org.challenger.common.mapper.MappedDto;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/12/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements MappedDto {
    private String id;

    private String username;
    private String password;

    private PersonalDetails personalDetails;
    private Contact contact;

    private List<Address> addresses;
    private List<PaymentMethods> paymentMethods;
}
