package com.dianwoda.test.bassy.web.controller;

import com.dianwoda.test.bassy.common.domain.dto.BBSParamDTO;
import com.dianwoda.test.bassy.common.domain.vo.BBSLogVO;
import com.dianwoda.test.bassy.common.domain.vo.BBSVO;
import com.dianwoda.test.bassy.common.domain.vo.BBSWeeklyVO;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.service.BBSService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * Created by LiuJJ on 2019/5/5.
 */
@RestController
@RequestMapping("/bbs")
public class BBSController extends BaseController{

    @Autowired
    BBSService bbsService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "获取BBS帖子", notes = "")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Pagination<BBSVO> getBBSPosts(@RequestBody  BBSParamDTO dto) throws ParseException {
        return bbsService.getBBSPosts(dto);
    }

    @ApiOperation(value = "新增/编辑帖子", notes = "")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Boolean editBBS(@RequestBody BBSParamDTO bbs ) {
        return bbsService.editBBS(bbs);
    }

    @ApiOperation(value = "获取帖子详情", notes = "")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
    public BBSVO getArticleDetail(@PathVariable("id") Integer id, @RequestBody BBSParamDTO dto) {
        return bbsService.getBBSById(id,dto);
    }

    @ApiOperation(value = "获取周刊详情", notes = "")
    @RequestMapping(value = "/weekly/detail/{id}", method = RequestMethod.POST)
    public BBSWeeklyVO getWeeklyDetail(@PathVariable("id") Integer id, @RequestBody BBSParamDTO dto) {
        return bbsService.getWeeklyById(id,dto);
    }

    @ApiOperation(value = "获取帖子个人贡献数", notes = "")
    @RequestMapping(value = "/countPersCtrb", method = RequestMethod.GET)
    public List<BBSVO> getArticleDetail() {
        return bbsService.countPersCtrb();
    }

    @ApiOperation(value = "获取期刊操作记录", notes = "")
    @RequestMapping(value = "/getLog", method = RequestMethod.GET)
    public List<BBSLogVO> getBBSLog() {
        return bbsService.getBBSLog();
    }


    @ApiOperation(value = "期刊赞不赞", notes = "")
    @RequestMapping(value = "/likeBBS", method = RequestMethod.POST)
    public boolean likeBBS(@RequestBody  BBSParamDTO dto) {
        return bbsService.likeBBS(dto);
    }

    @ApiOperation(value = "统计学习打卡", notes = "")
    @RequestMapping(value = "/countStudyTms", method = RequestMethod.POST)
    public  List<BBSVO> countStudyTimes(@RequestBody  BBSParamDTO dto) {
        return bbsService.countStudyTimes(dto);
    }

}



