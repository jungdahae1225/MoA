package com.moaserver.moa.service.cash;

import com.moaserver.moa.repository.CashRepository;
import com.moaserver.moa.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CashService {

    private final CashRepository cashRepository;


}
