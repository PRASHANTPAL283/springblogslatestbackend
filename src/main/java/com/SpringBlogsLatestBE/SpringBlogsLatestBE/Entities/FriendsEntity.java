package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Table(name="friendsModel")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FriendsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int friendId;
    @ManyToOne
    @JoinColumn(name="userId",referencedColumnName = "userId")
    private UserModel friendadded;
    private int myuserId;
    @CreationTimestamp
    private Date date;

}
