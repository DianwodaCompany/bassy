package com.dianwoda.test.bassy.service.impl;

import com.alibaba.fastjson.JSON;
import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.dao.*;
import com.dianwoda.test.bassy.db.entity.*;
import com.dianwoda.test.bassy.common.domain.dto.BBSParamDTO;
import com.dianwoda.test.bassy.common.domain.vo.BBSLogVO;
import com.dianwoda.test.bassy.common.domain.vo.BBSVO;
import com.dianwoda.test.bassy.common.domain.vo.BBSWeeklyVO;
import com.dianwoda.test.bassy.service.BBSService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.dianwoda.test.bassy.common.constants.DingdingConstant.SEND_LINK_MSG_URL;
import static io.restassured.RestAssured.given;

/**
 * Created by LiuJJ on 2019/5/5.
 */
@Service
public class BBSServiceImpl implements BBSService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private StaffService staffService;

    @Autowired
    BBSMapperExt bbsMapperExt;

    @Autowired
    BBSMapper bbsMapper;

    @Autowired
    BBSTagMapper bbsTagMapper;

    @Autowired
    BBSLogMapper bbsLogMapper;

    @Autowired
    BBSLogMapperExt bbsLogMapperExt;

    @Autowired
    BBSRelateMapperExt bbsRelateMapperExt;

    @Autowired
    BBSRelateMapper bbsRelateMapper;

    @Autowired
    BBSWeeklyMapper bbsWeeklyMapper;

    @Override
    public Pagination<BBSVO> getBBSPosts(BBSParamDTO paramDTO) {
        Pagination<BBSVO> pagination = new Pagination<>();
        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        List<BBSVO> bbsListVo = new ArrayList<>();
        List<Map<String, Object>> bbsList = bbsMapperExt.selectByCondition(paramDTO.getType(), paramDTO.getAuthorCode(), paramDTO.getKeyword(), paramDTO.getSort(), paramDTO.getNoteType());
        for (Map<String, Object> bbs : bbsList) {
            BBSVO bbsVo = new BBSVO();
            bbsVo.setId(Integer.parseInt(bbs.get("id").toString()));
            bbsVo.setType(Byte.valueOf(bbs.get("type") == null ? "" : bbs.get("type").toString()));
            bbsVo.setTitle(bbs.get("title") == null ? "" : bbs.get("title").toString());
            bbsVo.setAuthorCode(bbs.get("author_code") == null ? "" : bbs.get("author_code").toString());
            bbsVo.setAuthorName(bbs.get("author_name") == null ? "" : bbs.get("author_name").toString());
            bbsVo.setContent(bbs.get("content") == null ? "" : bbs.get("content").toString());
            bbsVo.setCreateTm((Date) bbs.get("create_tm"));
            bbsVo.setModifyTm((Date) bbs.get("modify_tm"));
            bbsVo.setLike(Short.valueOf(bbs.get("like") == null ? "0" : bbs.get("like").toString()));
            bbsVo.setTag(bbs.get("tags") == null ? new String[]{} : bbs.get("tags").toString().split(","));
            boolean likeStatus = bbsMapperExt.likeStatus(paramDTO.getStaffCode(), bbs.get("id").toString()) % 2 > 0;
            bbsVo.setLikeStatus(likeStatus);
            bbsListVo.add(bbsVo);
        }
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(bbsList);
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(bbsListVo);
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    @Override
    public Boolean editBBS(BBSParamDTO paramDTO) {
        BBS bbs = new BBS();
        boolean insertTagResult = true;
        boolean insetLogResult = true;
        boolean insertRelateResult = true;
        BeanUtils.copyProperties(paramDTO, bbs);
        if (paramDTO.getId() == null) {
            //新增文章
            bbs.setCreateTm(new Date());
            bbs.setModifyTm(new Date());
            boolean insertResult = bbsMapper.insertSelective(bbs) > 0;
            if (insertResult) {
                Integer id = bbs.getId();
                for (Object tag : paramDTO.getTag()) {
                    BBSTag bbsTag = new BBSTag();
                    bbsTag.setBbsId(id);
                    bbsTag.setTag(tag.toString());
                    bbsTag.setCreateTm(new Date());
                    bbsTag.setModifyTm(new Date());
                    insertTagResult = insertTagResult && bbsTagMapper.insert(bbsTag) > 0;
                }
                if (paramDTO.getRelateBbs() != null) {
                    for (Map<String, Object> relateBbs : paramDTO.getRelateBbs()) {
                        BBSRelate bbsRelate = new BBSRelate();
                        bbsRelate.setBbsId(id);
                        bbsRelate.setRelateId(Integer.parseInt(relateBbs.get("id").toString()));
                        bbsRelate.setCreateTm(new Date());
                        bbsRelate.setModifyTm(new Date());
                        insertRelateResult = insertRelateResult && bbsRelateMapper.insert(bbsRelate) > 0;
                    }
                }
                BBSLog bbsLog = new BBSLog();
                bbsLog.setStaffCode(paramDTO.getStaffCode());
                bbsLog.setBbsId(id);
                bbsLog.setOperation((short) 1);
                bbsLog.setInsTm(new Date());
                insetLogResult = insetLogResult && bbsLogMapper.insert(bbsLog) > 0;
            }
            if (insertResult && insertTagResult && insetLogResult) {
                if (paramDTO.getType() == 30) {
                    String authorName = staffService.getStaffInfo(paramDTO.getStaffCode()).getName();
                    studyDingRemind(paramDTO.getWeek(), authorName, paramDTO.getTitle(), paramDTO.getContent());
                }
            }
            return insertResult && insertTagResult && insetLogResult && insertRelateResult;

        } else {
            //编辑文章
            bbs.setModifyTm(new Date());
            logger.info("删除已有标签");
            BBSTagExample example = new BBSTagExample();
            BBSTagExample.Criteria criteria = example.createCriteria();
            criteria.andBbsIdEqualTo(paramDTO.getId());
            bbsTagMapper.deleteByExample(example);
            //删除已有关联文章
            BBSRelateExample exampleR = new BBSRelateExample();
            BBSRelateExample.Criteria criteriaR = exampleR.createCriteria();
            criteriaR.andBbsIdEqualTo(paramDTO.getId());
            bbsRelateMapper.deleteByExample(exampleR);

            boolean updateResult = bbsMapper.updateByPrimaryKeySelective(bbs) > 0;
            if (updateResult) {
                for (Object tag : paramDTO.getTag()) {
                    BBSTag bbsTag = new BBSTag();
                    bbsTag.setBbsId(paramDTO.getId());
                    bbsTag.setTag(tag.toString());
                    bbsTag.setCreateTm(new Date());
                    bbsTag.setModifyTm(new Date());
                    insertTagResult = insertTagResult && bbsTagMapper.insert(bbsTag) > 0;
                }
                if (paramDTO.getRelateBbs() != null) {
                    for (Map<String, Object> relateBbs : paramDTO.getRelateBbs()) {
                        BBSRelate bbsRelate = new BBSRelate();
                        bbsRelate.setBbsId(paramDTO.getId());
                        bbsRelate.setRelateId(Integer.parseInt(relateBbs.get("id").toString()));
                        bbsRelate.setCreateTm(new Date());
                        bbsRelate.setModifyTm(new Date());
                        insertRelateResult = insertRelateResult && bbsRelateMapper.insert(bbsRelate) > 0;
                    }
                }

                BBSLog bbsLog = new BBSLog();
                bbsLog.setStaffCode(paramDTO.getStaffCode());
                bbsLog.setBbsId(paramDTO.getId());
                bbsLog.setOperation((short) 2);
                bbsLog.setInsTm(new Date());
                insetLogResult = insetLogResult && bbsLogMapper.insert(bbsLog) > 0;
            }
            return updateResult && insertTagResult && insetLogResult && insertRelateResult;
        }

    }

    @Override
    public BBSVO getBBSById(int id, BBSParamDTO dto) {
        BBSVO vo = new BBSVO();
        BBS bs = bbsMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(bs, vo);
        vo.setFiles(bs.getFiles() == null ? new Object[]{} : JSON.parseArray(bs.getFiles(), Object.class).toArray());
        BBSTagExample example = new BBSTagExample();
        BBSTagExample.Criteria criteria = example.createCriteria();
        criteria.andBbsIdEqualTo(id);
        List<BBSTag> tags = bbsTagMapper.selectByExample(example);
        List<String> tmp = new ArrayList();
        for (BBSTag tag : tags) {
            tmp.add(tag.getTag());
        }
        vo.setTag(tmp.toArray(new String[0]));
        boolean likeStatus = bbsMapperExt.likeStatus(dto.getStaffCode(), String.valueOf(id)) % 2 > 0;
        vo.setLikeStatus(likeStatus);
        vo.setRelateBbs(bbsRelateMapperExt.getRelateBbsByid(String.valueOf(id)));
        return vo;
    }


    @Override
    public BBSWeeklyVO getWeeklyById(int id, BBSParamDTO dto) {
        BBSWeeklyVO vo = new BBSWeeklyVO();
        BBS bbs = bbsMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(bbs, vo);

        BBSWeeklyExample bbsWeeklyExample = new BBSWeeklyExample();
        BBSWeeklyExample.Criteria criteria = bbsWeeklyExample.createCriteria();
        criteria.andWeeklyIdEqualTo(id);
        List<BBSWeekly> bbsWeeklies = bbsWeeklyMapper.selectByExample(bbsWeeklyExample);
        List<Integer> bbsIds = new ArrayList<>();
        if (bbsWeeklies != null) {
            for (BBSWeekly bbsWeekly : bbsWeeklies) {
                bbsIds.add(bbsWeekly.getBbsId());
            }
        }

        List<Map<String, Object>> weeklyBBSList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Map<String, Object> m = new HashMap<>();
            BBSExample bbsExample = new BBSExample();
            BBSExample.Criteria criteria1 = bbsExample.createCriteria();
            criteria1.andIdIn(bbsIds);
            criteria1.andNoteTypeEqualTo((byte) i);
            List<BBS> bbsList = bbsMapper.selectByExample(bbsExample);
            if (bbsList.size() != 0) {
                switch (i) {
                    case 0:
                        m.put("noteType", "业务经验");
                        m.put("bbsList", bbsList);
                        break;
                    case 1:
                        m.put("noteType", "测试经验");
                        m.put("bbsList", bbsList);
                        break;
                    case 2:
                        m.put("noteType", "工具技巧");
                        m.put("bbsList", bbsList);
                        break;
                    case 3:
                        m.put("noteType", "其他");
                        m.put("bbsList", bbsList);
                        break;
                }
                weeklyBBSList.add(m);
            }
        }
        vo.setBbsList(weeklyBBSList);

        boolean likeStatus = bbsMapperExt.likeStatus(dto.getStaffCode(), String.valueOf(id)) % 2 > 0;
        vo.setLikeStatus(likeStatus);
        return vo;
    }


    @Override
    public List<BBSVO> countPersCtrb() {
        List<BBSVO> bbsListVo = new ArrayList<>();
        List<Map<String, Object>> persCtrbCount = bbsMapperExt.countPersCtrb();
        for (Map<String, Object> bbs : persCtrbCount) {
            BBSVO bbsVo = new BBSVO();
            bbsVo.setAuthorCode(bbs.get("author_code") == null ? "" : bbs.get("author_code").toString());
            bbsVo.setAuthorName(bbs.get("author_name") == null ? "" : bbs.get("author_name").toString());
            bbsVo.setCtrbNum(Integer.parseInt(bbs.get("ctrbNum").toString()));
            bbsListVo.add(bbsVo);
        }
        return bbsListVo;
    }

    @Override
    public List<BBSLogVO> getBBSLog() {
        List<BBSLogVO> bbsLogListVo = new ArrayList<>();
        List<Map<String, Object>> bbsLogList = bbsLogMapperExt.getBBSLog();
        for (Map<String, Object> bbsLog : bbsLogList) {
            BBSLogVO bbsLogVo = new BBSLogVO();
            bbsLogVo.setId(Integer.parseInt(bbsLog.get("id").toString()));
            bbsLogVo.setStaffCode(bbsLog.get("staff_code").toString());
            bbsLogVo.setStaffName(bbsLog.get("staff_code") == null ? "" :
                    staffService.getStaffInfo(bbsLog.get("staff_code").toString()).getName());
            bbsLogVo.setBbsId(Integer.parseInt(bbsLog.get("bbs_id").toString()));
            bbsLogVo.setTitle(bbsLog.get("title") == null ? "" : bbsLog.get("title").toString());
            bbsLogVo.setType(bbsLog.get("type") == null ? (byte) 0 : (byte) bbsLog.get("type"));
            bbsLogVo.setOperation(Short.parseShort(bbsLog.get("operation").toString()));
            bbsLogVo.setInsTm((Date) bbsLog.get("ins_tm"));
            bbsLogListVo.add(bbsLogVo);
        }
        return bbsLogListVo;
    }

    @Override
    public Boolean likeBBS(BBSParamDTO paramDTO) {
        boolean updateResult = bbsMapperExt.updateLike(paramDTO.getId(), paramDTO.isLikeStatus()) > 0;
        BBSLog bbsLog = new BBSLog();
        bbsLog.setStaffCode(paramDTO.getStaffCode());
        bbsLog.setBbsId(paramDTO.getId());
        bbsLog.setOperation((short) (paramDTO.isLikeStatus() ? 4 : 3));
        bbsLog.setInsTm(new Date());
        boolean insetLogResult = bbsLogMapper.insert(bbsLog) > 0;
        return updateResult && insetLogResult;
    }

    @Override
    public List<BBSVO> countStudyTimes(BBSParamDTO paramDTO) {
        List<BBSVO> bbsListVo = new ArrayList<>();
        List<Map<String, Object>> studyTimesCount = bbsMapperExt.countStudyTimes(paramDTO.getWeek());
        for (Map<String, Object> bbs : studyTimesCount) {
            BBSVO bbsVo = new BBSVO();
            bbsVo.setAuthorCode(bbs.get("author_code") == null ? "" : bbs.get("author_code").toString());
            bbsVo.setAuthorName(bbs.get("author_name") == null ? "" : bbs.get("author_name").toString());
            bbsVo.setStudyTimes(bbs.get("count") == null ? 0 : Integer.parseInt(bbs.get("count").toString()));
            bbsVo.setStudyPeopleNum(bbs.get("people_num") == null ? 0 : Integer.parseInt(bbs.get("people_num").toString()));
            bbsListVo.add(bbsVo);
        }
        return bbsListVo;
    }

    @Override
    public boolean createWeekly() {
        boolean insertWeeklyResult = true;
        boolean insertBBSWeeklyResult = true;

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);//设置星期一为一周开始的第一天
        calendar.setTimeInMillis(System.currentTimeMillis());//获得当前的时间戳
        int weekYear = calendar.get(Calendar.YEAR);//获得当前的年
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);//获得当前日期属于今年的第几周

        BBSExample bbsExample = new BBSExample();
        BBSExample.Criteria criteria = bbsExample.createCriteria();
        criteria.andWeekEqualTo(String.valueOf(weekOfYear));
        criteria.andTypeEqualTo((byte) 40);
        bbsExample.setOrderByClause("note_type");
        List<BBS> weeklyBBSList = bbsMapper.selectByExample(bbsExample);

        if (weeklyBBSList.isEmpty()) {
            logger.info("本周没有记一笔文章，无法生成周刊");
        } else {
            BBS bbsWeekly = new BBS();
            bbsWeekly.setType((byte) 50);
            bbsWeekly.setTitle(weekYear + "年第" + weekOfYear + "周测试沉淀周刊");
            bbsWeekly.setAuthorName("系统");
            bbsWeekly.setCreateTm(new Date());
            bbsWeekly.setModifyTm(new Date());
            bbsWeekly.setLike((short) 0);
            bbsWeekly.setWeek(String.valueOf(weekOfYear));
            bbsWeekly.setContent(weeklyBBSList.get(0).getContent());
            insertBBSWeeklyResult = bbsMapper.insertSelective(bbsWeekly) > 0;
            if (insertBBSWeeklyResult) {
                Integer weeklyId = bbsWeekly.getId();
                for (BBS bbs : weeklyBBSList) {
                    BBSWeekly Weekly = new BBSWeekly();
                    Weekly.setBbsId(bbs.getId());
                    Weekly.setWeeklyId(weeklyId);
                    Weekly.setNoteType(bbs.getNoteType());
                    Weekly.setWeek(bbs.getWeek());
                    Weekly.setCreateTm(new Date());
                    insertWeeklyResult = insertWeeklyResult && bbsWeeklyMapper.insert(Weekly) > 0;
                }
            }

        }
        return insertWeeklyResult && insertBBSWeeklyResult;

    }


    private void studyDingRemind(String week, String authorName, String title, String content) {

        String text = JSON.toJSONString("### 第 " + week + " 周学习打卡 \n  >" + authorName + "  【打卡】了 \n > 《" + title + "》 \n\n > " + content + " \n\n > ###### [点击查看](http://www.baidu.com)");
        logger.info("text>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>:    " + text);
        String body = "{\n" +
                "     \"msgtype\": \"markdown\",\n" +
                "     \"markdown\": {\n" +
                "                    \"title\":\"第 " + week + " 周学习打卡啦~~\",\n" +
                "                    \"text\": " + text +
                "                   },\n" +
                "     \"at\": {\n" +
                "        \"atMobiles\": [], \n" +
                "        \"isAtAll\": true\n" +
                "     }\n" +
                " }";
        logger.info("钉钉通知{}", body);
        Response response = given().contentType(ContentType.JSON).body(body).when().post(SEND_LINK_MSG_URL);
        logger.info("钉钉通知结果{}", response.asString());
    }
}


