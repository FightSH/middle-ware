package com.shen.ddd.infrastructure.dao;

import com.shen.ddd.infrastructure.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RuleTreeDao {

    RuleTree queryRuleTreeByTreeId(Long id);

    RuleTree queryTreeSummaryInfo(Long treeId);

}
