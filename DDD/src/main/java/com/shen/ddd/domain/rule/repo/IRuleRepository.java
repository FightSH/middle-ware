package com.shen.ddd.domain.rule.repo;

import com.shen.ddd.domain.rule.model.aggregates.TreeRuleRich;

/**
 *
 */
public interface IRuleRepository {

    TreeRuleRich queryTreeRuleRich(Long treeId);

}
