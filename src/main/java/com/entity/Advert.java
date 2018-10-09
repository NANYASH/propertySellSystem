package com.entity;


import com.entity.enums.Currency;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ADVERT2")
@EqualsAndHashCode(exclude = {"id","title","description","price","currency",
"availableFromDate","availableToDate","author","property"})
@ToString(exclude = {"id","title","description","price","currency",
"availableFromDate","availableToDate","author","property"})
public class Advert {

    @SequenceGenerator(name = "ADVERT2_SEQ", sequenceName = "ADVERT2_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADVERT2_SEQ")
    @Id
    @Column(name = "ID")
    @Getter @Setter
    private Long id;

    @Column(name = "TITLE")
    @Getter @Setter
    private String title;

    @Column(name = "DESCRIPTION")
    @Getter @Setter
    private String description;

    @Column(name = "PRICE")
    @Getter @Setter
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY")
    @Getter @Setter
    private Currency currency;

    @Column(name = "AVAILABLE_FROM_DATE")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Getter @Setter
    private Date availableFromDate;


    @Column(name = "AVAILABLE_TO_DATE")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Getter @Setter
    private Date availableToDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "ID_USER")
    @Getter @Setter
    private User author;

    @OneToOne(targetEntity = Property.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PROPERTY")
    @Getter @Setter
    private Property property;
}
