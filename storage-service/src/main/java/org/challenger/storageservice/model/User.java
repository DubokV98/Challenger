package org.challenger.storageservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.challenger.common.mapper.MappedEntity;
import org.challenger.common.dto.embedded.Address;
import org.challenger.common.dto.embedded.Contact;
import org.challenger.common.dto.embedded.PaymentMethods;
import org.challenger.common.dto.embedded.PersonalDetails;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author u.dubok
 * @since 10/9/2022
 */
@Document(collection = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements MappedEntity {
    @Id
    private String id;

    @Indexed(name = "username_index", unique = true)
    private String username;
    private String password;

    private PersonalDetails personalDetails;
    private Contact contact;

    private List<Address> addresses;
    private List<PaymentMethods> paymentMethods;
}
