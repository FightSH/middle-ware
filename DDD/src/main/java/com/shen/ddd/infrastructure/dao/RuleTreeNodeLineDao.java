package com.shen.ddd.infrastructure.dao;

import com.shen.ddd.infrastructure.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RuleTreeNodeLineDao {

    List<RuleTreeNodeLine> queryRuleTreeNodeLineList(RuleTreeNodeLine req);

    int queryTreeNodeLineCount(Long treeId);

}
