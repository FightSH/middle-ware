package com.shen.ddd.domain.rule.service.logic.impl;

import com.shen.ddd.domain.rule.model.vo.DecisionMatter;
import com.shen.ddd.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Service;

@Service("userGenderFilter")
public class UserGenderFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatter decisionMatter) {
        return decisionMatter.getValMap().get("gender").toString();
    }
    
}
