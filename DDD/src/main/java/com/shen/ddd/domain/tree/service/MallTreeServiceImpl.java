package com.shen.ddd.domain.tree.service;

import com.shen.ddd.applications.MallTreeService;
import com.shen.ddd.domain.tree.model.aggregates.TreeCollect;
import com.shen.ddd.domain.tree.model.vo.TreeInfo;
import com.shen.ddd.domain.tree.model.vo.TreeRulePoint;
import com.shen.ddd.domain.tree.repository.ITreeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("mallTreeService")
public class MallTreeServiceImpl implements MallTreeService {

    private Logger logger = LoggerFactory.getLogger(MallTreeServiceImpl.class);

    @Resource
    private ITreeRepository treeRepository;

    @Override
    public TreeCollect queryTreeSummaryInfo(Long treeId) {
        TreeInfo treeInfo = treeRepository.queryTreeInfo(treeId);
        List<TreeRulePoint> treeRulePointList = treeRepository.queryTreeRulePointList(treeId);
        // 封装结果
        TreeCollect treeCollect = new TreeCollect();
        treeCollect.setTreeInfo(treeInfo);
        treeCollect.setTreeRulePointList(treeRulePointList);
        return treeCollect;
    }

}
