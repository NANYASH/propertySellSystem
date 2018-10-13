package com.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER2")
@EqualsAndHashCode(exclude = {"adverts"})
@ToString(exclude = {"adverts"})
public class User{

    @SequenceGenerator(name = "USER2_SEQ", sequenceName = "USER2_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER2_SEQ")
    @Id
    @Getter@Setter
    private Long id;

    @Column(name = "USERNAME")
    @Getter@Setter
    private String username;

    @Column(name = "PASSWORD")
    @Getter@Setter
    private String password;

    @Column(name = "FIRST_NAME")
    @Getter@Setter
    private String firstName;

    @Column(name = "LAST_NAME")
    @Getter@Setter
    private String lastName;

    @Column(name = "PHONE")
    @Getter@Setter
    private String phone;

    @Column(name = "LOG_IN_STATUS")
    @Getter@Setter
    private Character isLoggedIn;

    @OneToMany(targetEntity = Advert.class, fetch = FetchType.EAGER, mappedBy = "author")
    @Getter@Setter
    private List<Advert> adverts;
}
