package com.example.measures.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mseId;

    @Column(name = "mseDateAndHour",nullable = false)
    private String mseDateAndHour;
    @Column(name = "mseObservation",nullable = true)
    private String mseObservation;
    @Column(name = "msePhotography",nullable = false)
    private String msePhotography;

    @Column(name = "mseStatus",nullable = false, length = 8)
    @Enumerated(value = EnumType.STRING)
    private Status mseStatus;

    @Column(name = "mseUserWhoSentId",nullable = false)
    private long mseUserWhoSendId;
    @Column(name = "msePlantWhereSentId",nullable = false)
    private long msePlantWhereSendId;

    @Column(name = "mseChlorineAmount",nullable = false)
    private String mseChlorineAmount;
    @Column(name = "mseFluorineAmount",nullable = false)
    private String mseFluorineAmount;
    @Column(name = "msePhAmount",nullable = false)
    private String msePhAmount;
    @Column(name = "mseSedimentAmount",nullable = true)
    private String mseSedimentAmount;
    @Column(name = "mseMagnesiumAmount",nullable = true)
    private String mseMagnesiumAmount;
    @Column(name = "mseIronAmount",nullable = true)
    private String mseIronAmount;

}
