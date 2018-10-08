package com.entity;


import com.entity.enums.Currency;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ADVERT2")
public class Advert {
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private Currency currency;
    private LocalDateTime availableFromDate;
    private LocalDateTime availableToDate;
    private User author;
    private Property property;

    @SequenceGenerator(name = "ADVERT2_SEQ", sequenceName = "ADVERT2_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADVERT2_SEQ")
    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "PRICE")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "CURRENCY")
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Column(name = "AVAILABLE_FROM_DATE")
    public LocalDateTime getAvailableFromDate() {
        return availableFromDate;
    }

    public void setAvailableFromDate(LocalDateTime availableFromDate) {
        this.availableFromDate = availableFromDate;
    }

    @Column(name = "AVAILABLE_TO_DATE")
    public LocalDateTime getAvailableToDate() {
        return availableToDate;
    }

    public void setAvailableToDate(LocalDateTime availableToDate) {
        this.availableToDate = availableToDate;
    }

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @OneToOne(targetEntity = Property.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PROPERTY")
    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
