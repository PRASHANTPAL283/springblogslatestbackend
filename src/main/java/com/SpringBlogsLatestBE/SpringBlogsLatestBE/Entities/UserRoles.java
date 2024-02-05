package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="userRolesTable")

public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;
    private String roleName;


}
