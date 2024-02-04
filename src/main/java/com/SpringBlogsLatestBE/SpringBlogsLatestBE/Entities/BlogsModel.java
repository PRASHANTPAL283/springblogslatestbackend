package com.SpringBlogsLatestBE.SpringBlogsLatestBE.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Blogs model")
@Entity
public class BlogsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    private int blogId;
    @NotNull(message = "subject should not be null")
    @NotBlank(message = "subject should not be blank")
    private String subject;
    @NotBlank(message = "description should be blank")
    @NotNull(message = "description should not be null")
    private String description;
    private String imageUrl;
    private int imageId;
}
