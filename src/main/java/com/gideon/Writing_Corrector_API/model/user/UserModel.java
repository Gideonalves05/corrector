
package com.gideon.Writing_Corrector_API.model.user;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class UserModel {

    @Id
    private String id;

    private String firstName;

    private String lastName;


    private String email;

    private String password;
}
