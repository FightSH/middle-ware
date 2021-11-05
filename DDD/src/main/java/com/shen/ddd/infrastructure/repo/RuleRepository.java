package com.shen.ddd.infrastructure.repo;

import com.shen.ddd.domain.rule.model.aggregates.TreeRuleRich;
import com.shen.ddd.domain.rule.repo.IRuleRepository;
import com.shen.ddd.infrastructure.repo.cache.RuleCacheRepository;
import com.shen.ddd.infrastructure.repo.mysql.RuleMysqlRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 主要时数据库的操纵
 */
@Repository("ruleRepository")
public class RuleRepository implements IRuleRepository {

    @Resource(name = "ruleMysqlRepository")
    private RuleMysqlRepository ruleMysqlRepository;
    @Resource(name = "ruleCacheRepository")
    private RuleCacheRepository ruleCacheRepository;

    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {
        TreeRuleRich treeRuleRich = ruleCacheRepository.queryTreeRuleRich(treeId);
        if (null != treeRuleRich) return treeRuleRich;
        return ruleMysqlRepository.queryTreeRuleRich(treeId);
    }

}
