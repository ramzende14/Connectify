package com.connectify.connectify10.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialLink {
    @Id
    private Long id;
    private String link;
    private String title;

    @ManyToOne
    private Contact contact;

    @ManyToOne
    private User user; // Added reference to User entity
}
