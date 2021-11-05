package com.shen.ddd.domain.rule.service;

import com.shen.ddd.applications.MallRuleService;
import com.shen.ddd.domain.rule.model.vo.DecisionMatter;
import com.shen.ddd.domain.rule.model.vo.EngineResult;
import com.shen.ddd.domain.rule.service.engine.EngineFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("mallRuleService")
public class MallRuleServiceImpl implements MallRuleService {

    @Resource
    private EngineFilter ruleEngineHandle;


    @Override
    public EngineResult process(DecisionMatter matter) {
        try {
            return ruleEngineHandle.process(matter);
        } catch (Exception e) {
            log.error("决策引擎执行失败", e);
            return new EngineResult(false);
        }
    }
}
