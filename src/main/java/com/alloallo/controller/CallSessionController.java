package com.alloallo.controller;

import com.alloallo.model.CallSession;
import com.alloallo.service.CallSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calls")
public class CallSessionController {
    @Autowired
    private CallSessionService callSessionService;

    @PostMapping("/initiate")
    public CallSession initiateCall(
            @RequestParam String caller,
            @RequestParam String callee,
            @RequestBody String sdpOffer) {
        return callSessionService.initiateCall(caller, callee, sdpOffer);
    }

    @PostMapping("/answer")
    public CallSession answerCall(
            @RequestParam String caller,
            @RequestParam String callee,
            @RequestBody String sdpAnswer) {
        return callSessionService.answerCall(caller, callee, sdpAnswer);
    }

    @PostMapping("/{callSessionId}/ice-candidate")
    public void addIceCandidate(
            @PathVariable Long callSessionId,
            @RequestBody String iceCandidate) {
        callSessionService.addIceCandidate(callSessionId, iceCandidate);
    }

    @GetMapping("/{callSessionId}")
    public CallSession getCallSession(@PathVariable Long callSessionId) {
        return callSessionService.getCallSession(callSessionId);
    }

    @PostMapping("/{callSessionId}/end")
    public void endCall(@PathVariable Long callSessionId) {
        callSessionService.endCall(callSessionId);
    }
}
