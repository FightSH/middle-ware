package com.shen.ddd.domain.tree.repository;

import com.shen.ddd.domain.tree.model.vo.TreeInfo;
import com.shen.ddd.domain.tree.model.vo.TreeRulePoint;

import java.util.List;


public interface ITreeRepository {

    TreeInfo queryTreeInfo(Long treeId);

    List<TreeRulePoint> queryTreeRulePointList(Long treeId);

}
