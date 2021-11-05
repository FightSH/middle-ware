package com.shen.ddd.domain.rule.service.engine.impl;

import com.shen.ddd.domain.rule.model.aggregates.TreeRuleRich;
import com.shen.ddd.domain.rule.model.vo.DecisionMatter;
import com.shen.ddd.domain.rule.model.vo.EngineResult;
import com.shen.ddd.domain.rule.model.vo.TreeNodeInfo;
import com.shen.ddd.domain.rule.repo.IRuleRepository;
import com.shen.ddd.domain.rule.service.engine.EngineBase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 决策引擎的具体实现主要有以下三方面
 * ·获取决策规则树，每一棵决策树都有自己的决策树ID，按需获取需要执行的决策树。
 * ·决策节点，这一步是在一棵规则二叉树中从根节点开始不停地向下执行，直至计算出最终的结果，这也是在组合模式中使用的设计模式。
 * ·返回决策结果，这里的每一步都有统一的包装。
 */
@Service("ruleEngineHandle")
public class RuleEngineHandle extends EngineBase {

    @Resource
    private IRuleRepository ruleRepository;

    @Override
    public EngineResult process(DecisionMatter matter) throws Exception {
        //决策规则树
        TreeRuleRich treeRuleRich = ruleRepository.queryTreeRuleRich(matter.getTreeId());
        if (null == treeRuleRich) throw new RuntimeException("Tree Rule is null!");
        //决策节点
        TreeNodeInfo treeNodeInfo = engineDecisionMaker(treeRuleRich, matter);
        //决策结果
        return new EngineResult(matter.getUserId(), treeNodeInfo.getTreeId(), treeNodeInfo.getTreeNodeId(), treeNodeInfo.getNodeValue());
    }

}