package com.alloallo.service;

import com.alloallo.model.CallSession;
import com.alloallo.repository.CallSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallSessionService {
    @Autowired
    private CallSessionRepository callSessionRepository;

    public CallSession initiateCall(String caller, String callee, String sdpOffer) {
        CallSession callSession = new CallSession();
        callSession.setCaller(caller);
        callSession.setCallee(callee);
        callSession.setSdpOffer(sdpOffer);
        callSession.setStatus("initiated");
        return callSessionRepository.save(callSession);
    }

    public CallSession answerCall(String caller, String callee, String sdpAnswer) {
        CallSession callSession = callSessionRepository.findByCallerAndCallee(caller, callee);
        if (callSession == null) {
            throw new RuntimeException("Call session not found");
        }
        callSession.setSdpAnswer(sdpAnswer);
        callSession.setStatus("answered");
        return callSessionRepository.save(callSession);
    }

    public void addIceCandidate(Long callSessionId, String iceCandidate) {
        CallSession callSession = callSessionRepository.findById(callSessionId)
                .orElseThrow(() -> new RuntimeException("Call session not found"));
        callSession.getIceCandidates().add(iceCandidate);
        callSessionRepository.save(callSession);
    }

    public CallSession getCallSession(Long callSessionId) {
        return callSessionRepository.findById(callSessionId)
                .orElseThrow(() -> new RuntimeException("Call session not found"));
    }

    public void endCall(Long callSessionId) {
        CallSession callSession = callSessionRepository.findById(callSessionId)
                .orElseThrow(() -> new RuntimeException("Call session not found"));
        callSession.setStatus("ended");
        callSessionRepository.save(callSession);
    }
}
