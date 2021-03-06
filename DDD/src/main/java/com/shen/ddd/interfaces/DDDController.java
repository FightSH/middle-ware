package com.shen.ddd.interfaces;

import com.alibaba.fastjson.JSON;
import com.shen.ddd.applications.MallRuleService;
import com.shen.ddd.applications.MallTreeService;
import com.shen.ddd.domain.rule.model.vo.DecisionMatter;
import com.shen.ddd.domain.rule.model.vo.EngineResult;
import com.shen.ddd.domain.tree.model.aggregates.TreeCollect;
import com.shen.ddd.interfaces.dto.DecisionMatterDTO;
import com.shen.ddd.interfaces.dto.TreeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 接口层的实现主要包括以下三点：包装应用接口对外提供 API；外部传输对象采用 DTO 类，主要为了避免内部类被污染（不断迭代的需求会在类中增加很多字段）；
 * 目前依然提供的是HTTP 服务，如果提供的是RPC服务，则需要对外提供可引用jar。
 */
@Slf4j
@Controller
public class DDDController {
    @Resource
    private MallTreeService mallTreeService;
    @Resource
    private MallRuleService mallRuleService;

    /**
     * 测试接口：http://localhost:8080/api/tree/queryTreeSummaryInfo
     * 请求参数：{"treeId":10001}
     */
    @RequestMapping(path = "/api/tree/queryTreeSummaryInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity queryTreeSummaryInfo(@RequestBody TreeDTO request) {
        String reqStr = JSON.toJSONString(request);
        try {
            log.info("查询规则树信息{}Begin req：{}", request.getTreeId(), reqStr);
            TreeCollect treeCollect = mallTreeService.queryTreeSummaryInfo(request.getTreeId());
            log.info("查询规则树信息{}End res：{}", request.getTreeId(), JSON.toJSON(treeCollect));
            return new ResponseEntity<>(treeCollect, HttpStatus.OK);
        } catch (Exception e) {
            log.error("查询规则树信息{}Error req：{}", request.getTreeId(), reqStr, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    /**
     * 测试接口：http://localhost:8080/api/tree/decisionRuleTree
     * 请求参数：{"treeId":10001,"userId":"xiaofuge","valMap":{"gender":"man","age":"25"}}
     */
    @RequestMapping(path = "/api/tree/decisionRuleTree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity decisionRuleTree(@RequestBody DecisionMatterDTO request) {
        String reqStr = JSON.toJSONString(request);
        try {
            log.info("规则树行为信息决策{}Begin req：{}", request.getTreeId(), reqStr);
            DecisionMatter decisionMatter = new DecisionMatter();
            decisionMatter.setTreeId(request.getTreeId());
            decisionMatter.setUserId(request.getUserId());
            decisionMatter.setValMap(request.getValMap());
            EngineResult engineResult = mallRuleService.process(decisionMatter);
            log.info("规则树行为信息决策{}End res：{}", request.getTreeId(), JSON.toJSON(engineResult));
            return new ResponseEntity<>(engineResult, HttpStatus.OK);
        } catch (Exception e) {
            log.error("规则树行为信息决策{}Error req：{}", request.getTreeId(), reqStr, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
}
