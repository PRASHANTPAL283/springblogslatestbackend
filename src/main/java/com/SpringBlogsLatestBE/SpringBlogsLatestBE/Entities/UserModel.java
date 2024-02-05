package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="myusertable")
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @NotBlank(message = "username should not ne blank")
    private String username;
    @Email(message = "valid email must be used")
    private String email;

    private String password;

    private long phoneNumber;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "roleId"))
    private List<UserRoles> userRolesList;
}
