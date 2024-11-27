package com.alloallo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alloallo.model.CallSession;

@Repository
public interface CallSessionRepository extends JpaRepository<CallSession, Long> {
    CallSession findByCallerAndCallee(String caller, String callee);
}
