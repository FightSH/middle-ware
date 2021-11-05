package com.shen.ddd.domain.rule.service;

import com.shen.ddd.applications.MallRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("mallRuleService")
public class MallRuleServiceImpl implements MallRuleService {

    @Resource
    private EngineFilter ruleEngineHandle;


    @Override
    public Object process(Object matter) {
        return null;
    }
}
