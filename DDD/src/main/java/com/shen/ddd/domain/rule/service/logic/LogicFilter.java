package com.shen.ddd.domain.rule.service.logic;

import java.util.List;

/**
 * 定义了两个方法: 逻辑决策器filter()和获取决策值matterValue()
 * filter()方法用于过滤决策树的各个行为节点，判断下一执行节点
 * matterValue()方法可以统一获取决策值，解决不同信息下决策值的不同问题
 */
public interface LogicFilter {
    /**
     * 逻辑决策器
     * @param matterValue          决策值
     * @param treeNodeLineInfoList 决策节点
     * @return                     下一个节点Id
     */
    Long filter(String matterValue, List<TreeNodeLineInfo> treeNodeLineInfoList);

    /**
     * 获取决策值
     *
     * @param decisionMatter 决策物料
     * @return               决策值
     */
    String matterValue(DecisionMatter decisionMatter);
}
