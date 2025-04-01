package com.nldk.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name = "parking_spots")
public class ParkingSpot {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "lot_id", nullable = false)
    private ParkingLot lot;

    @Size(max = 50)
    @NotNull
    @Column(name = "spot_number", nullable = false, length = 50)
    private String spotNumber;

    @ColumnDefault("'available'")
    @Lob
    @Column(name = "status")
    private String status;

    @Column(name = "price_per_hour", precision = 10)
    private BigDecimal pricePerHour;

    @ColumnDefault("'motor'")
    @Lob
    @Column(name = "spot_type")
    private String spotType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ParkingLot getLot() {
        return lot;
    }

    public void setLot(ParkingLot lot) {
        this.lot = lot;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getSpotType() {
        return spotType;
    }

    public void setSpotType(String spotType) {
        this.spotType = spotType;
    }

}