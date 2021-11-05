package com.shen.ddd.infrastructure.dao;

import com.shen.ddd.infrastructure.po.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RuleTreeNodeDao {

    List<RuleTreeNode> queryRuleTreeNodeList(Long treeId);

    int queryTreeNodeCount(Long treeId);

    List<RuleTreeNode> queryTreeRulePoint(Long treeId);

}
