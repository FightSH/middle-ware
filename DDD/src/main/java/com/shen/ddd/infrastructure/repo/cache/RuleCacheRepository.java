package com.shen.ddd.infrastructure.repo.cache;

import com.shen.ddd.domain.rule.model.aggregates.TreeRuleRich;
import com.shen.ddd.domain.rule.repo.IRuleRepository;
import com.shen.ddd.infrastructure.util.CacheUtil;
import org.springframework.stereotype.Repository;


@Repository("ruleCacheRepository")
public class RuleCacheRepository implements IRuleRepository {

    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {
        return (TreeRuleRich) CacheUtil.cacheMap.get(treeId);
    }

}
