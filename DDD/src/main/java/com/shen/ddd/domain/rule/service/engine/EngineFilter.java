package com.shen.ddd.domain.rule.service.engine;

import com.shen.ddd.domain.rule.model.vo.DecisionMatter;
import com.shen.ddd.domain.rule.model.vo.EngineResult;

/**
 * 接口 EngineFilter 主要定义了这棵决策树的统一入口。另外，在实现 EngineFilter的类中，会包装一些通用信息，简化外部的调用
 */
public interface EngineFilter {
    EngineResult process(final DecisionMatter matter) throws Exception;
}
