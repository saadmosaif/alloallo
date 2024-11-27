package com.alloallo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CallSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caller;  // Username of the caller
    private String callee;  // Username of the callee
    private String sdpOffer;
    private String sdpAnswer;
    private String status;  // "initiated", "answered", "ended"

    @ElementCollection
    private List<String> iceCandidates = new ArrayList<>(); // ICE candidates
}
