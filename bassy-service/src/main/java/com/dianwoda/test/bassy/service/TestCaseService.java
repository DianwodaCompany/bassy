package com.dianwoda.test.bassy.service;


import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.entity.*;
import com.dianwoda.test.bassy.common.domain.dto.BassyPagination;
import com.dianwoda.test.bassy.common.domain.dto.testcase.*;
import com.dianwoda.test.bassy.common.domain.vo.testcase.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.io.IOException;
import java.util.List;

/**
 * @author zcp
 * @date 2019/5/15 16:16
 */
public interface TestCaseService {

    /**
     * 获取用例
     * @param paramDTO
     * @return
     */
    List<BaseCase> getBaseCase(GetBaseCaseDTO paramDTO);


    /**
     * 获取子模块
     * @param paramDTO
     * @return
     */
    List<Integer> getSubModule(GetBaseCaseDTO paramDTO);

    /**
     * 分页获取用例
     * @param paramDTO
     * @return
     */
    Pagination<BaseCase> getPagedBaseCase(TestCaseParamDTO paramDTO);

    /**
     * 获取用例详情
     *
     * @param caseId
     * @return
     */
    BaseCase getBaseCaseDetail(Integer caseId);

    /**
     * 新增测试用例
     *
     * @param updateBaseCaseDTO
     * @return
     */
    Integer addTestCase(UpdateBaseCaseDTO updateBaseCaseDTO);

    /**
     * 复制用例
     *
     * @param caseId
     * @return
     */
    Integer copyBaseCase(Integer caseId);

    /**
     * 删除用例
     *
     * @param caseId
     * @return
     */
    Boolean deleteBaseCase(Integer caseId);

    /**
     * 获取用例步骤
     *
     * @param caseId
     * @return
     */
    List<BaseCaseStep> getBaseCaseStep(Integer caseId);

    /**
     * 获取基础用例标签
     *
     * @param caseId
     * @return
     */
    List<BaseCaseLabel> getBaseCaseLabel(Integer caseId);

    /**
     * 获取用例标签
     *
     * @param caseId
     * @return
     */
    List<BaseCaseLabelDTO> getCaseLabel(Integer caseId);


    /**
     * 更新测试用例
     *
     * @param updateBaseCaseDTO
     * @return
     */
    ConflictCaseDTO updateTestCase(UpdateBaseCaseDTO updateBaseCaseDTO);

    /**
     * 获取产品的模块
     *
     * @param productModuleParamDTO
     * @return
     */
    List<ProductModule> getProductModule(ProductModuleParamDTO productModuleParamDTO);

    /**
     * 获取模块完整层级名称
     *
     * @param productModule
     * @return
     */
    String getModuleAllLayerName(ProductModule productModule);

    /**
     * keyWords 关键字查询
     * productId 通过产品id查询
     * 获取产品列表
     *
     * @return
     */
    List<Product> getProduct(ProductModuleParamDTO paramDTO);


    /**
     * 处理产品
     *
     * @param paramDTO
     */
    void operateProduct(ProductModuleParamDTO paramDTO);

    /**
     * 处理产品模块
     *
     * @param paramDTO
     */
    void operateProductModule(ProductModuleParamDTO paramDTO);

    /**
     * 导入用例
     */
    TestCaseImportVO importTestCase(HSSFSheet sheet, String lastEditedBy) throws IOException;

    /**
     * 通过xmind更新测试用例
     *
     * @return
     */
    XmindUpdateResultVO updateTestCaseByXMind(TestCaseXmindNodeVO testCaseXmindNodeVO);

    /**
     * 通过xMind删除用例或用例步骤
     *
     * @param xMindDeleteDTO caseId 和 stepId 必填一个
     */
    void deleteTestCaseByXMind(XMindDeleteDTO xMindDeleteDTO);


    /**
     * 获取标签list
     *
     * @return
     */
    BassyPagination<LabelInfo> getLabels(LabelInfoParamDTO labelInfoParamDTO);

    /**
     * 更新标签  id  非 null 更新，
     * id 是 null 新增
     *
     * @return
     */
    boolean updateLabel(LabelInfoParamDTO labelInfoParamDTO);

    /**
     * 删除标签 id 不能为 null
     *
     * @return
     */
    boolean deleteLabel(LabelInfoParamDTO labelInfoParamDTO);

    /**
     * 项目用例首页
     * @param dto
     * @return
     */
    Pagination<ProCaseIndexVO> getProCaseIndex(ProCaseIndexParamDTO dto);

    /**
     * 用例执行率
     * @param requireId
     * @return
     */
    Float getRunPercent(Integer requireId);

    /**
     * 用例通过率
     * @param requireId
     * @return
     */
    Float getPassPercent(Integer requireId);


    /**
     * 批量推送测试用例
     * @param mergeTestCaseParamDTO  需求
     * @return 冲突用例
     */
    List<ConflictCaseDTO> batchPush(MergeTestCaseParamDTO mergeTestCaseParamDTO);

    /**
     * 批量拉取测试用例
     * @param mergeTestCaseParamDTO  需求id必传
     * @return 冲突用例
     */
    List<ConflictCaseDTO> batchPull(MergeTestCaseParamDTO mergeTestCaseParamDTO);

    /**
     * 单个推送测试用例
     * @param programCaseId 项目测试用例id 必传
     * @return 冲突结果
     */
    ConflictCaseDTO pushCase(Integer programCaseId);

    /**
     * 单个拉取测试用例
     * @param programCaseId 项目测试用例id 必传
     * @return 冲突结果
     */
    ConflictCaseDTO pullCase(Integer programCaseId);

    /**
     * 冲突处理结果
     * @param solveResult 基线用例id  项目用例id 必传
     */
    void solveConflictCase(SolveCaseResultDTO solveResult);

    /**
     * 从基线用例导入到项目用例
     * @param baseIds
     * @return
     */
    int importFromBaseCase(List<Integer> baseIds, String lastEditedBy, Integer require);

    /**
     * 自动生成系统标签
     * @param content
     * @return
     */
    List<String> autoGenSysLabel(String content);
}
