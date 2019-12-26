package com.dianwoda.test.bassy.service.impl;

import com.dianwoda.test.bassy.common.domain.bo.BaseCaseBO;
import com.dianwoda.test.bassy.common.domain.dto.BassyPagination;
import com.dianwoda.test.bassy.common.domain.dto.ProgramParamDTO;
import com.dianwoda.test.bassy.common.domain.dto.testcase.*;
import com.dianwoda.test.bassy.common.domain.vo.testcase.*;
import com.dianwoda.test.bassy.common.exception.BassyException;
import com.dianwoda.test.bassy.common.exception.BusinessException;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.dao.*;
import com.dianwoda.test.bassy.db.entity.*;
import com.dianwoda.test.bassy.service.ProgramService;
import com.dianwoda.test.bassy.service.TestCaseService;
import com.dianwoda.test.bassy.service.factory.ProductModuleFactory;
import com.dianwoda.test.bassy.service.factory.ProductModuleOperate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.dianwoda.test.bassy.common.constants.TestCaseConstant.*;

/**
 * @author zcp
 * @date 2019/5/15 16:21
 */
@Service
public class TestCaseServiceImpl implements TestCaseService {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<ConflictCaseDTO> conflictCases;

    private TestCaseXmindNodeVO testCaseXmindNodeVO;

    private XMindTestCaseEditedInfoDTO editedInfoDTO;


    @Autowired
    private BaseCaseMapper baseCaseMapper;

    @Autowired
    private BaseCaseMapperExt baseCaseMapperExt;

    @Autowired
    private BaseCaseStepMapper baseCaseStepMapper;

    @Autowired
    private BaseCaseStepMapperExt baseCaseStepMapperExt;

    @Autowired
    private BaseCaseLabelMapper baseCaseLabelMapper;

    @Autowired
    private LabelInfoMapper labelInfoMapper;

    @Autowired
    private ProductModuleMapper productModuleMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BaseCaseLabelMapperExt baseCaseLabelMapperExt;

    @Autowired
    private LabelInfoMapperExt labelInfoMapperExt;

    @Autowired
    private TestCaseTransform testCaseTransform;

    @Autowired
    private ProductModuleFactory factory;

    @Autowired
    private ProgramService programService;


    @Override
    public List<Integer> getSubModule(GetBaseCaseDTO paramDTO) {
        List<Integer> modules = new ArrayList<>();
        if (paramDTO.testCaseParamDTO.getModule() != null) {
            ProductModuleExample productModuleExample = new ProductModuleExample();
            ProductModuleExample.Criteria criteria0 = productModuleExample.createCriteria();
            criteria0.andPathLike("%," + paramDTO.testCaseParamDTO.getModule() + ",%");
            criteria0.andDeletedEqualTo(PRODUCT_MODULE_ENABLE);
            ProductModuleExample.Criteria criteria1 = productModuleExample.createCriteria();
            criteria1.andPathLike(paramDTO.testCaseParamDTO.getModule() + ",%");
            criteria1.andDeletedEqualTo(PRODUCT_MODULE_ENABLE);
            ProductModuleExample.Criteria criteria2 = productModuleExample.createCriteria();
            criteria2.andPathLike("%," + paramDTO.testCaseParamDTO.getModule());
            criteria2.andDeletedEqualTo(PRODUCT_MODULE_ENABLE);
            ProductModuleExample.Criteria criteria3 = productModuleExample.createCriteria();
            criteria3.andPathEqualTo(paramDTO.testCaseParamDTO.getModule().toString());
            criteria3.andDeletedEqualTo(PRODUCT_MODULE_ENABLE);
            ProductModuleExample.Criteria criteria4 = productModuleExample.createCriteria();
            criteria4.andIdEqualTo(paramDTO.testCaseParamDTO.getModule());
            criteria4.andDeletedEqualTo(PRODUCT_MODULE_ENABLE);
            productModuleExample.or(criteria0);
            productModuleExample.or(criteria1);
            productModuleExample.or(criteria2);
            productModuleExample.or(criteria3);
            productModuleExample.or(criteria4);
            List<ProductModule> productModules = productModuleMapper.selectByExample(productModuleExample);
            productModules.forEach(productModule -> {
                modules.add(productModule.getId());
            });
        }

        return modules;
    }

    @Override
    public List<BaseCase> getBaseCase(GetBaseCaseDTO paramDTO) {
        BaseCaseExample baseCaseExample = new BaseCaseExample();
        BaseCaseExample.Criteria criteria = baseCaseExample.createCriteria();
        if (paramDTO.testCaseParamDTO.getId() != null) {
            criteria.andIdEqualTo(paramDTO.testCaseParamDTO.getId());
        }
        if (paramDTO.testCaseParamDTO.getTitle() != null && !paramDTO.testCaseParamDTO.getTitle().isEmpty()) {
            criteria.andTitleLike("%" + paramDTO.testCaseParamDTO.getTitle() + "%");
        }
        if (paramDTO.testCaseParamDTO.getProduct() != null) {
            criteria.andProductEqualTo(paramDTO.testCaseParamDTO.getProduct());
        }
        if (paramDTO.testCaseParamDTO.getModule() != null) {
            criteria.andModuleIn(paramDTO.getSubModules());
        }
        if (paramDTO.testCaseParamDTO.getFamily() != null) {
            criteria.andFamilyEqualTo(paramDTO.testCaseParamDTO.getFamily());
        }
        if (paramDTO.testCaseParamDTO.getType() != null) {
            criteria.andTypeEqualTo(paramDTO.testCaseParamDTO.getType());
        }
        if (paramDTO.testCaseParamDTO.getRequireId() != null) {
            criteria.andRequireEqualTo(paramDTO.testCaseParamDTO.getRequireId());
        }
        if (paramDTO.testCaseParamDTO.getLastEditedBy() != null) {
            criteria.andLastEditedByEqualTo(paramDTO.testCaseParamDTO.getLastEditedBy());
        }
        if (paramDTO.testCaseParamDTO.getLabel() != null && paramDTO.testCaseParamDTO.getLabel().size() > 0) {
            ArrayList<LabelInfoDTO> labels = paramDTO.testCaseParamDTO.getLabel();
            criteria.andIdIn(findCasesByLabel(labels));
        }
        if (paramDTO.testCaseParamDTO.getNotPass() != null) {
            List<Byte> otherStatus = new ArrayList<>();
            otherStatus.add(CASE_EXECUTE_PASS);
            otherStatus.add(CASE_EXECUTE_BLOCK);
            otherStatus.add(CASE_EXECUTE_DOUBT);
            otherStatus.add(CASE_UNEXECUTE);
            List<Byte> notPassStatus = new ArrayList<>();
            notPassStatus.add(CASE_EXECUTE_BLOCK);
            notPassStatus.add(CASE_EXECUTE_DOUBT);
            notPassStatus.add(CASE_UNEXECUTE);
            criteria.andExecuteStatusIn(paramDTO.testCaseParamDTO.getNotPass() ? notPassStatus : otherStatus);
        }
        criteria.andStatusEqualTo("1");
        baseCaseExample.setOrderByClause(paramDTO.getOrderBy());
        return baseCaseMapper.selectByExampleWithBLOBs(baseCaseExample);
    }

    @Override
    public Pagination<BaseCase> getPagedBaseCase(TestCaseParamDTO paramDTO) {
        GetBaseCaseDTO getBaseCaseDTO = new GetBaseCaseDTO(paramDTO);
        getBaseCaseDTO.setOrderBy("last_edited_date desc");
        List<Integer> subModules = getSubModule(getBaseCaseDTO);
        getBaseCaseDTO.setSubModules(subModules);
        Pagination<BaseCase> pagination = new Pagination<>();
        PageHelper.startPage(paramDTO.getPageNum(), paramDTO.getPageSize(), true);
        List<BaseCase> lists = this.getBaseCase(getBaseCaseDTO);
        PageInfo<BaseCase> pageInfo = new PageInfo<>(lists);
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(lists);
        pagination.setTotalCount(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    /**
     * 查询使用某标签的测试用例
     *
     * @param labels 标签id
     * @return 测试用例集合
     */
    private ArrayList<Integer> findCasesByLabel(ArrayList<LabelInfoDTO> labels) {
        ArrayList<Integer> caseIds = new ArrayList<>();
        for (LabelInfoDTO labelInfo : labels) {
            BaseCaseLabelExample example = new BaseCaseLabelExample();
            BaseCaseLabelExample.Criteria criteria1 = example.createCriteria();
            criteria1.andLabelIdEqualTo(labelInfo.getId());
            List<BaseCaseLabel> baseCaseLabels = baseCaseLabelMapper.selectByExample(example);
            for (BaseCaseLabel baseCaseLabel : baseCaseLabels) {
                if (caseIds.contains(baseCaseLabel.getCaseId())) continue;
                caseIds.add(baseCaseLabel.getCaseId());
            }
        }
        if (caseIds.isEmpty()) {
            caseIds.add(-1);
        }
        return caseIds;
    }

    @Override
    public BaseCase getBaseCaseDetail(Integer caseId) {
        return baseCaseMapper.selectByPrimaryKey(caseId);
    }

    @Override
    public Integer copyBaseCase(Integer caseId) {
        BaseCase baseCase = this.getBaseCaseDetail(caseId);
        List<BaseCaseStep> baseCaseSteps = this.getBaseCaseStep(caseId);
        List<BaseCaseLabel> labels = this.getBaseCaseLabel(caseId);
        baseCase.setId(null);
        baseCase.setVersion((byte) 1);
        baseCaseMapperExt.insert(baseCase);
        for (BaseCaseLabel baseCaseLabel : labels) {
            baseCaseLabel.setId(null);
            baseCaseLabel.setCaseId(baseCase.getId());
            baseCaseLabelMapper.insert(baseCaseLabel);
        }
        for (BaseCaseStep baseCaseStep : baseCaseSteps) {
            baseCaseStep.setId(null);
            baseCaseStep.setCaseId(baseCase.getId());
            baseCaseStepMapper.insert(baseCaseStep);
        }
        return baseCase.getId();
    }

    @Override
    public Boolean deleteBaseCase(Integer caseId) {
        //处理引用过的项目用例
        BaseCaseExample example = new BaseCaseExample();
        BaseCaseExample.Criteria criteria = example.createCriteria();
        criteria.andParentCaseEqualTo(caseId);
        baseCaseMapper.selectByExample(example).forEach(baseCase -> {
            baseCase.setParentCase(null);
            baseCase.setPushed(CASE_UNPUSHED);
            baseCase.setEdited(CASE_EDITED);
            baseCaseMapper.updateByPrimaryKey(baseCase);
        });
        //删除用例相关
        BaseCaseLabelExample labelExample = new BaseCaseLabelExample();
        BaseCaseLabelExample.Criteria labelCriteria = labelExample.createCriteria();
        labelCriteria.andCaseIdEqualTo(caseId);
        baseCaseLabelMapper.deleteByExample(labelExample);
        BaseCaseStepExample baseCaseStepExample = new BaseCaseStepExample();
        BaseCaseStepExample.Criteria baseCaseStepCriteria = baseCaseStepExample.createCriteria();
        baseCaseStepCriteria.andCaseIdEqualTo(caseId);
        baseCaseStepMapper.deleteByExample(baseCaseStepExample);
        //对用例的操作
        return baseCaseMapper.deleteByPrimaryKey(caseId) > 0;
    }

    @Override
    public List<BaseCaseStep> getBaseCaseStep(Integer caseId) {
        BaseCaseStepExample example = new BaseCaseStepExample();
        BaseCaseStepExample.Criteria criteria = example.createCriteria();
        criteria.andCaseIdEqualTo(caseId);
        List<BaseCaseStep> baseCaseSteps = baseCaseStepMapper.selectByExample(example);
        baseCaseSteps.sort(Comparator.comparing(BaseCaseStep::getStepId));
        return baseCaseSteps;
    }

    @Override
    public List<BaseCaseLabel> getBaseCaseLabel(Integer caseId) {
        BaseCaseLabelExample example = new BaseCaseLabelExample();
        BaseCaseLabelExample.Criteria criteria = example.createCriteria();
        criteria.andCaseIdEqualTo(caseId);
        return baseCaseLabelMapper.selectByExample(example);
    }

    @Override
    public List<BaseCaseLabelDTO> getCaseLabel(Integer caseId) {
        return baseCaseLabelMapperExt.getTestCaseLabelInfo(caseId);
    }


    @Override
    public ConflictCaseDTO updateTestCase(UpdateBaseCaseDTO updateBaseCaseDTO) {
        //对用例的版本号处理
        BaseCase baseCase = new BaseCase();
        BeanUtils.copyProperties(updateBaseCaseDTO, baseCase);
        baseCase.setExecuteStatus(updateBaseCaseDTO.getPass());
        ConflictCaseDTO conflictCaseDTO = compareTestCaseVersion(updateBaseCaseDTO);
        if (conflictCaseDTO != null) return conflictCaseDTO;
        //对用例标签的而操作
        BaseCaseLabelExample labelExample = new BaseCaseLabelExample();
        BaseCaseLabelExample.Criteria labelCriteria = labelExample.createCriteria();
        labelCriteria.andCaseIdEqualTo(updateBaseCaseDTO.getId());
        baseCaseLabelMapper.deleteByExample(labelExample);
        addCaseLabel(updateBaseCaseDTO);
        //对用例在步骤的操作(简单粗暴，删掉原先的，全部新增)
        BaseCaseStepExample baseCaseStepExample = new BaseCaseStepExample();
        BaseCaseStepExample.Criteria baseCaseStepCriteria = baseCaseStepExample.createCriteria();
        baseCaseStepCriteria.andCaseIdEqualTo(updateBaseCaseDTO.getId());
        baseCaseStepMapper.deleteByExample(baseCaseStepExample);
        for (BaseCaseStepDTO baseCaseStepDTO : updateBaseCaseDTO.getStep()) {
            baseCaseStepDTO.setId(null);
            BaseCaseStep baseCaseStep = new BaseCaseStep();
            BeanUtils.copyProperties(baseCaseStepDTO, baseCaseStep);
            baseCaseStepMapper.insert(baseCaseStep);
        }
        //基线用例更新升级版本，项目用例不更新
        if (PROGRAM_CASE.equals(baseCase.getFamily())) {
            if (updateBaseCaseDTO.getIsMerge()) {
                baseCase.setEdited(CASE_UNEDITED);
                baseCase.setPushed(CASE_PUSHED);
            } else {
                baseCase.setEdited(CASE_EDITED);
                baseCase.setPushed(CASE_UNPUSHED);
            }
        }
        baseCaseMapper.updateByPrimaryKeySelective(baseCase);
        return null;
    }

    @Override
    public Integer addTestCase(UpdateBaseCaseDTO updateBaseCaseDTO) {
        //对用例的操作
        BaseCase baseCase = new BaseCase();
        BeanUtils.copyProperties(updateBaseCaseDTO, baseCase);
        baseCase.setStatus(ENABLE);
        baseCase.setRequire(updateBaseCaseDTO.getRequireId());
        if (PROGRAM_CASE.equals(baseCase.getFamily())) {
            baseCase.setExecuteStatus(CASE_UNEXECUTE);
            baseCase.setEdited(CASE_EDITED);
            baseCase.setPushed(CASE_UNPUSHED);
        }
        baseCaseMapperExt.insert(baseCase);
        updateBaseCaseDTO.setId(baseCase.getId());
        //添加标签
        addCaseLabel(updateBaseCaseDTO);
        //添加用例步骤
        for (BaseCaseStepDTO baseCaseStepDTO : updateBaseCaseDTO.getStep()) {
            baseCaseStepDTO.setCaseId(baseCase.getId());
            baseCaseStepDTO.setId(null);
            BaseCaseStep baseCaseStep = new BaseCaseStep();
            BeanUtils.copyProperties(baseCaseStepDTO, baseCaseStep);
            baseCaseStepMapper.insert(baseCaseStep);
        }
        return baseCase.getId();
    }

    private void addCaseLabel(UpdateBaseCaseDTO updateBaseCaseDTO) {
        for (BaseCaseLabelDTO baseCaseLabeldto : updateBaseCaseDTO.getLabel()) {
            BaseCaseLabel baseCaseLabel = new BaseCaseLabel();
            baseCaseLabel.setId(null);
            baseCaseLabel.setLabelId(baseCaseLabeldto.getLabelId());
            baseCaseLabel.setCaseId(updateBaseCaseDTO.getId());
            baseCaseLabel.setCreator(updateBaseCaseDTO.getLastEditedBy());
            baseCaseLabel.setCreatorTm(updateBaseCaseDTO.getLastEditedDate());
            //新标签
            if (baseCaseLabeldto.getLabelId() == null) {
                LabelInfo labelInfo = new LabelInfo();
                labelInfo.setName(baseCaseLabeldto.getLabelName());
                labelInfo.setType(SYSTEM_LABEL);
                labelInfo.setProduct(updateBaseCaseDTO.getProduct());
                labelInfo.setCitations(1);
                labelInfoMapperExt.insert(labelInfo);
                baseCaseLabel.setLabelId(labelInfo.getId());
            }
            baseCaseLabelMapper.insert(baseCaseLabel);
        }
    }

    @Override
    public List<ProductModule> getProductModule(ProductModuleParamDTO productModuleParamDTO) {
        ProductModuleExample example = new ProductModuleExample();
        ProductModuleExample.Criteria criteria = example.createCriteria();
        if (productModuleParamDTO.getProductId() != null) {
            criteria.andRootEqualTo(productModuleParamDTO.getProductId());
        }
        if (productModuleParamDTO.getModuleId() != null) {
            criteria.andPathLike("%" + productModuleParamDTO.getModuleId() + "%");
        }
        if (productModuleParamDTO.getKeyWords() != null) {
            criteria.andNameLike("%" + productModuleParamDTO.getKeyWords() + "%");
        }
        criteria.andDeletedEqualTo(PRODUCT_MODULE_ENABLE);
        return productModuleMapper.selectByExample(example);
    }

    @Override
    public String getModuleAllLayerName(ProductModule productModule) {
        productModule = productModuleMapper.selectByPrimaryKey(productModule.getId());
        if (productModule.getPath().isEmpty()) {
            return productModule.getName();
        }
        String[] modulePath = productModule.getPath().split(",");
        StringBuilder completeName = new StringBuilder();
        for (String moduleId : modulePath) {
            ProductModule pm = productModuleMapper.selectByPrimaryKey(Integer.valueOf(moduleId));
            completeName.append(pm.getName()).append("/");
        }
        return completeName.append(productModule.getName()).toString();
    }

    @Override
    public List<Product> getProduct(ProductModuleParamDTO paramDTO) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(PRODUCT_ENABLE);
        if (paramDTO.getKeyWords() != null) {
            criteria.andNameLike("%" + paramDTO.getKeyWords() + "%");
        }
        if (paramDTO.getProductId() != null) {
            criteria.andIdEqualTo(paramDTO.getProductId());
        }
        return productMapper.selectByExample(example);
    }


    @Override
    public void operateProduct(ProductModuleParamDTO paramDTO) {
        ProductModuleOperate productModuleOperate = factory.getProductModuleOperate(paramDTO.getOperate());
        productModuleOperate.operateProduct(paramDTO);
    }

    @Override
    public void operateProductModule(ProductModuleParamDTO paramDTO) {
        ProductModuleOperate productModuleOperate = factory.getProductModuleOperate(paramDTO.getOperate());
        productModuleOperate.operateModule(paramDTO);
    }

    @Override
    public TestCaseImportVO importTestCase(HSSFSheet sheet, String lastEditedBy) throws IOException {
        TestCaseImportVO testCaseImportVO = new TestCaseImportVO();
        List<Object[]> failCases = new ArrayList<>();
        sheet.rowIterator().forEachRemaining(row -> {
            if (row.getRowNum() <= 0) return;
            try {
                testCaseTransform.execute(row, lastEditedBy);
            } catch (BassyException e) {
                if (!e.getCode().equals("3001")) throw e;
                buildFailCases(row, failCases);
            }
        });
        testCaseImportVO.setFailCases(failCases);
        return testCaseImportVO;
    }

    /**
     * 导入失败用例集合
     *
     * @param row
     * @param failCases
     */
    private void buildFailCases(Row row, List<Object[]> failCases) {
        Object[] failCase = new Object[row.getLastCellNum()];
        row.cellIterator().forEachRemaining(cell -> {
            if (cell.getCellType() == CellType.FORMULA) {
                failCase[cell.getColumnIndex()] = cell.getCellFormula();
            } else if (cell.getCellType() == CellType.NUMERIC) {
                failCase[cell.getColumnIndex()] = cell.getNumericCellValue();
            } else if (cell.getCellType() == CellType.STRING) {
                failCase[cell.getColumnIndex()] = cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.BOOLEAN) {
                failCase[cell.getColumnIndex()] = cell.getBooleanCellValue();
            } else if (cell.getCellType() == CellType.ERROR) {
                failCase[cell.getColumnIndex()] = cell.getErrorCellValue();
            } else if (cell.getCellType() == CellType.BLANK) {
                failCase[cell.getColumnIndex()] = "";
            }
        });
        failCases.add(failCase);
    }

    @Override
    public XmindUpdateResultVO updateTestCaseByXMind(TestCaseXmindNodeVO node) {
        conflictCases = new ArrayList<>();
        testCaseXmindNodeVO = node;
        editedInfoDTO = new XMindTestCaseEditedInfoDTO();
        XmindUpdateResultVO xmindUpdateResultVO = new XmindUpdateResultVO();
        xmindUpdateResultVO.setConflictCases(conflictCases);
        xmindUpdateResultVO.setTestCaseXmindNodeVO(testCaseXmindNodeVO);
        handelNode(testCaseXmindNodeVO);
        return xmindUpdateResultVO;
    }

    private void handelNode(TestCaseXmindNodeVO testCaseXmindNodeVO) {
        testCaseXmindNodeVO.getChildren().forEach(node -> {

            if (CASE.equals(node.getType())) {
                editedInfoDTO.setCaseId(node.getCaseId());
                editedInfoDTO.setVersion(node.getVersion());
                editedInfoDTO.setIsEdited(false);
                editedInfoDTO.setHasUpVersion(false);
                editedInfoDTO.setIsBaseCase(node.getRequireId() == null);
            }
            if (isNewCase(node)) {
                xMindNode2BaseCase(node);
                return;
            }
            if (isNewCaseStep(node)) {
                editedInfoDTO.setIsEdited(true);
                xMindNode2BaseCaseStep(node);
                return;
            }
            if (node.isHasEdited()) {
                editedInfoDTO.setIsEdited(true);
                //有版本冲突，结束该用例编辑
                if (verifyCaseVersion(node)) return;
                switch (node.getType()) {
                    case CASE:
                    case CASE_PRECONDITION:
                    case CASE_PRI:
                        updateTestCase(node);
                        break;
                    case CASE_STEP_DESCRIBE:
                    case EXCEPT_DB:
                    case EXCEPT_RESPONSE:
                    case EXCEPT_OTHER:
                    case EXCEPT_UI:
                        updateTestCaseStep(node);
                        break;
                    case CASE_LABEL_SYSTEM:
                    case CASE_LABEL_BASE:
                        updateTestCaseLabel(node);
                        break;
                }
            }
            handelNode(node);

            if (CASE.equals(node.getType()) && editedInfoDTO.getIsEdited() && editedInfoDTO.getIsBaseCase()) {
                updateCaseVersion(node);
                updateXMindNodeVersion(node);
                editedInfoDTO = new XMindTestCaseEditedInfoDTO();
            }
        });
    }

    private void updateCaseVersion(TestCaseXmindNodeVO node) {
        BaseCase baseCase = new BaseCase();
        baseCase.setId(node.getCaseId());
        baseCase.setVersion((byte) (node.getVersion() + 1));
        baseCaseMapper.updateByPrimaryKeySelective(baseCase);
        node.setVersion((byte) (node.getVersion() + 1));
    }

    private void updateXMindNodeVersion(TestCaseXmindNodeVO node) {
        node.getChildren().forEach(subNode -> {
            subNode.setVersion(node.getVersion());
            updateXMindNodeVersion(subNode);
        });
    }


    private boolean isNewCase(TestCaseXmindNodeVO node) {
        return CASE.equals(node.getType()) && node.getCaseId() == null;
    }

    private boolean isNewCaseStep(TestCaseXmindNodeVO node) {
        return CASE_STEP_DESCRIBE.equals(node.getType()) && node.getStepId() == null;
    }

    private Boolean verifyCaseVersion(TestCaseXmindNodeVO node) {
        BaseCase baseCase = new BaseCase();
        baseCase.setId(node.getCaseId());
        baseCase.setVersion(node.getVersion());
        BaseCase oldBaseCase = baseCaseMapper.selectByPrimaryKey(baseCase.getId());
        if (oldBaseCase.getVersion() > baseCase.getVersion()) {
            BaseCaseDetailVO newBaseCaseDetailVO = buildTestCaseDetail(node);
            ConflictCaseDTO conflictCaseDTO = new ConflictCaseDTO();
            conflictCaseDTO.setBaseCase(buildCaseDetail(oldBaseCase));
            conflictCaseDTO.setProgramCase(newBaseCaseDetailVO);
            conflictCaseDTO.setCaseTitle(newBaseCaseDetailVO.getTitle());
            conflictCaseDTO.setCaseId(newBaseCaseDetailVO.getId());
            conflictCases.add(conflictCaseDTO);
            return true;
        }
        return false;
    }

    private BaseCaseDetailVO buildTestCaseDetail(TestCaseXmindNodeVO node) {
        if (!CASE.equals(node.getType())) {
            getCaseNode(testCaseXmindNodeVO, node.getCaseId());
            node = caseNode;
        }
        ;
        BaseCaseDetailVO baseCaseDetailVO = new BaseCaseDetailVO();
        baseCaseDetailVO.setId(node.getCaseId());
        baseCaseDetailVO.setProduct(node.getProductId());
        baseCaseDetailVO.setModule(node.getModuleId());
        baseCaseDetailVO.setTitle(node.getText());
        baseCaseDetailVO.setType(node.getType());
        baseCaseDetailVO.setVersion(node.getVersion());
        baseCaseDetailVO.setPass(node.getPass());
        node.getChildren().forEach(subNode -> {
            if (CASE_PRECONDITION.equals(subNode.getType())) {
                baseCaseDetailVO.setPrecondition(subNode.getText());
            }
            ;
            if (CASE_PRI.equals(subNode.getType())) {
                baseCaseDetailVO.setPri(Byte.valueOf(subNode.getText()));
            }
            ;
            if (CASE_STEP.equals(subNode.getType())) {
                List<BaseCaseStepDTO> baseCaseSteps = new ArrayList<>();
                baseCaseDetailVO.setStep(baseCaseSteps);
                subNode.getChildren().forEach(step -> {
                    BaseCaseStepDTO baseCaseStepDTO = new BaseCaseStepDTO();
                    baseCaseStepDTO.setId(step.getStepId());
                    baseCaseStepDTO.setStepId(step.getStepStepId());
                    baseCaseStepDTO.setCaseId(step.getCaseId());
                    baseCaseStepDTO.setDesc(step.getText());
                    baseCaseStepDTO.setExecuteStatus(step.getPass());
                    step.getChildren().forEach(except -> {
                        if (EXCEPT_DB.equals(except.getType())) {
                            baseCaseStepDTO.setExpectDb(except.getText());
                        }
                        if (EXCEPT_UI.equals(except.getType())) {
                            baseCaseStepDTO.setExpectUi(except.getText());
                        }
                        if (EXCEPT_RESPONSE.equals(except.getType())) {
                            baseCaseStepDTO.setExpectResponse(except.getText());
                        }
                        if (EXCEPT_OTHER.equals(except.getType())) {
                            baseCaseStepDTO.setExpectOther(except.getText());
                        }
                    });
                    baseCaseSteps.add(baseCaseStepDTO);
                });
            }
            ;
        });
        baseCaseDetailVO.setLabel(new ArrayList<>());
        return baseCaseDetailVO;
    }

    private TestCaseXmindNodeVO caseNode = new TestCaseXmindNodeVO();

    private synchronized void getCaseNode(TestCaseXmindNodeVO testCaseXmindNodeVO, Integer caseId) {
        testCaseXmindNodeVO.getChildren().forEach(node -> {
            if (CASE.equals(node.getType()) && caseId.equals(node.getCaseId())) {
                caseNode = node;
                return;
            }
            if (node.getChildren().size() > 0) {
                getCaseNode(node, caseId);
            }
        });
    }

    private void updateTestCase(TestCaseXmindNodeVO node) {
        BaseCase baseCase = new BaseCase();
        baseCase.setId(node.getCaseId());
        baseCase.setProduct(node.getProductId());
        baseCase.setModule(node.getModuleId());
        baseCase.setId(node.getCaseId());
        baseCase.setLastEditedBy(node.getLastEditedBy());
        baseCase.setLastEditedDate(new Date());
        baseCase.setExecuteStatus(node.getPass());
        if (CASE.equals(node.getType()) && node.isHasEdited()) {
            baseCase.setTitle(node.getText());
        }
        if (CASE_PRI.equals(node.getType()) && node.isHasEdited()) {
            baseCase.setPri(Byte.valueOf(node.getText()));
        }
        if (CASE_PRECONDITION.equals(node.getType()) && node.isHasEdited()) {
            baseCase.setPrecondition(node.getText());
        }

        //项目用例更新编辑状态
        if (node.getRequireId() != null) {
            baseCase.setEdited(CASE_EDITED);
            baseCase.setPushed(CASE_UNPUSHED);
        }
        baseCaseMapper.updateByPrimaryKeySelective(baseCase);
        node.setHasEdited(false);
    }

    private void updateTestCaseStep(TestCaseXmindNodeVO node) {
        if (!node.isHasEdited()) {
            return;
        }
        BaseCaseStep baseCaseStep = new BaseCaseStep();
        baseCaseStep.setId(node.getStepId());
        baseCaseStep.setCaseId(node.getCaseId());
        if (CASE_STEP_DESCRIBE.equals(node.getType())) {
            baseCaseStep.setDesc(node.getText());
        }
        if (EXCEPT_DB.equals(node.getType())) {
            baseCaseStep.setExpectDb(node.getText());
        }
        if (EXCEPT_UI.equals(node.getType())) {
            baseCaseStep.setExpectUi(node.getText());
        }
        if (EXCEPT_RESPONSE.equals(node.getType())) {
            baseCaseStep.setExpectResponse(node.getText());
        }
        if (EXCEPT_OTHER.equals(node.getType())) {
            baseCaseStep.setExpectOther(node.getText());
        }
        baseCaseStep.setExecuteStatus(node.getPass());
        baseCaseStepMapper.updateByPrimaryKeySelective(baseCaseStep);

        //基础用例进行版本升级
        BaseCase baseCase = new BaseCase();
        baseCase.setId(node.getCaseId());
        baseCase.setLastEditedBy(node.getLastEditedBy());
        baseCase.setLastEditedDate(new Date());
        //项目用例更新编辑状态
        if (node.getRequireId() != null) {
            baseCase.setEdited(CASE_EDITED);
            baseCase.setPushed(CASE_UNPUSHED);
        }
        baseCaseMapper.updateByPrimaryKeySelective(baseCase);
        node.setHasEdited(false);
    }

    private void updateTestCaseLabel(TestCaseXmindNodeVO node) {
        //全部删除重新添加
        baseCaseLabelMapperExt.deleteByLabelType(node.getCaseId(), node.getType());
        insertTestCaseLabel(node);
    }

    private void insertTestCaseLabel(TestCaseXmindNodeVO node) {
        if (CASE_LABEL_BASE.equals(node.getType())) {
            node.getLabels().forEach(label -> {
                BaseCaseLabel baseCaseLabel = new BaseCaseLabel();
                baseCaseLabel.setLabelId(label.getLabelId());
                baseCaseLabel.setCaseId(node.getCaseId());
                baseCaseLabel.setCreator(node.getLastEditedBy());
                baseCaseLabel.setCreatorTm(new Date());
                baseCaseLabelMapper.insertSelective(baseCaseLabel);
            });
            node.setHasEdited(false);
            return;
        }

        if (CASE_LABEL_SYSTEM.equals(node.getType())) {
            String[] labels = node.getText().split(",");
            for (String labelName : labels) {
                LabelInfo labelInfo = new LabelInfo();
                labelInfo.setName(labelName);
                labelInfo.setCitations(1);
                labelInfo.setProduct(node.getProductId());
                labelInfo.setType(SYSTEM_LABEL);
                labelInfoMapperExt.insert(labelInfo);

                BaseCaseLabel baseCaseLabel = new BaseCaseLabel();
                baseCaseLabel.setLabelId(labelInfo.getId());
                baseCaseLabel.setCaseId(node.getCaseId());
                baseCaseLabel.setCreator(node.getLastEditedBy());
                baseCaseLabel.setCreatorTm(new Date());
                baseCaseLabelMapper.insertSelective(baseCaseLabel);
            }
            node.setHasEdited(false);
        }
    }

    /**
     * 数据库内的版本号小于等于当前版本号，可以更新
     * 大于当前版本号，不能更新
     *
     * @return
     */
    private ConflictCaseDTO compareTestCaseVersion(UpdateBaseCaseDTO updateBaseCaseDTO) {
        BaseCase oldBaseCase = baseCaseMapper.selectByPrimaryKey(updateBaseCaseDTO.getId());
        if (oldBaseCase.getVersion() <= updateBaseCaseDTO.getVersion()) {
            return null;
        }
        BaseCaseDetailVO newBaseCaseDetailVO = new BaseCaseDetailVO();
        BeanUtils.copyProperties(updateBaseCaseDTO, newBaseCaseDetailVO);
        ConflictCaseDTO conflictCaseDTO = new ConflictCaseDTO();
        conflictCaseDTO.setBaseCase(newBaseCaseDetailVO);
        conflictCaseDTO.setProgramCase(buildCaseDetail(oldBaseCase));
        conflictCaseDTO.setCaseTitle(updateBaseCaseDTO.getTitle());
        conflictCaseDTO.setCaseId(updateBaseCaseDTO.getId());
        return conflictCaseDTO;
    }

    private void xMindNode2BaseCase(TestCaseXmindNodeVO node) {
        BaseCase baseCase = new BaseCase();
        baseCase.setTitle(node.getText());
        baseCase.setStatus(ENABLE);
        baseCase.setType(BUSINESS_CASE);
        baseCase.setVersion((byte) 1);
        baseCase.setProduct(node.getProductId());
        baseCase.setModule(node.getModuleId());
        baseCase.setRequire(node.getRequireId());
        baseCase.setFamily(node.getRequireId() == null ? BASE_CASE : PROGRAM_CASE);
        baseCase.setLastEditedBy(node.getLastEditedBy());
        baseCase.setLastEditedDate(new Date());
        baseCase.setExecuteStatus(node.getPass());
        List<TestCaseXmindNodeVO> baseCaseInfoNodes = node.getChildren();
        List<TestCaseXmindNodeVO> baseCaseStepNodes = new ArrayList<>();
        TestCaseXmindNodeVO labelNode = new TestCaseXmindNodeVO();
        for (TestCaseXmindNodeVO subNode : baseCaseInfoNodes) {
            switch (subNode.getType()) {
                case CASE_PRECONDITION:
                    baseCase.setPrecondition(subNode.getText());
                    break;
                case CASE_PRI:
                    baseCase.setPri(Byte.valueOf(subNode.getText()));
                    break;
                case CASE_LABEL:
                    labelNode = subNode;
                    break;
                case CASE_STEP:
                    baseCaseStepNodes = subNode.getChildren();
                    break;
            }
        }
        baseCaseMapperExt.insert(baseCase);
        node.setCaseId(baseCase.getId());
        node.setVersion(baseCase.getVersion());
        node.setHasEdited(false);
        for (TestCaseXmindNodeVO subNode : baseCaseInfoNodes) {
            subNode.setCaseId(baseCase.getId());
            subNode.setVersion(baseCase.getVersion());
            subNode.setHasEdited(false);
        }
        insertTestCaseSteps(baseCaseStepNodes, baseCase);
        labelNode.setCaseId(baseCase.getId());
        insertTestCaseLabels(labelNode);
    }

    private void insertTestCaseSteps(List<TestCaseXmindNodeVO> baseCaseNodes, BaseCase baseCase) {
        baseCaseNodes.forEach(baseCaseNode -> {
            baseCaseNode.setCaseId(baseCase.getId());
            baseCaseNode.setVersion(baseCase.getVersion());
            xMindNode2BaseCaseStep(baseCaseNode);
        });
    }

    private void xMindNode2BaseCaseStep(TestCaseXmindNodeVO node) {
        if (!CASE_STEP_DESCRIBE.equals(node.getType())) return;
        BaseCaseStep step = new BaseCaseStep();
        step.setCaseId(node.getCaseId());
        step.setDesc(node.getText());
        step.setStepId(System.currentTimeMillis());
        step.setExecuteStatus(node.getPass());
        node.getChildren().forEach(except -> {
            switch (except.getType()) {
                case EXCEPT_DB:
                    step.setExpectDb(except.getText());
                    break;
                case EXCEPT_RESPONSE:
                    step.setExpectResponse(except.getText());
                    break;
                case EXCEPT_OTHER:
                    step.setExpectOther(except.getText());
                    break;
                case EXCEPT_UI:
                    step.setExpectUi(except.getText());
                    break;
            }
        });
        baseCaseStepMapperExt.insert(step);
        node.setCaseId(node.getCaseId());
        node.setStepId(step.getId());
        node.setHasEdited(false);
        node.getChildren().forEach(except -> {
            except.setCaseId(node.getCaseId());
            except.setStepId(step.getId());
            except.setVersion(node.getVersion());
            except.setHasEdited(false);
        });
    }

    private void insertTestCaseLabels(TestCaseXmindNodeVO node) {
        node.getChildren().forEach(subNode -> {
            subNode.setCaseId(node.getCaseId());
            insertTestCaseLabel(subNode);
        });
    }

    @Override
    public void deleteTestCaseByXMind(XMindDeleteDTO xMindDeleteDTO) {
        if (isDeleteVerify(xMindDeleteDTO)) {
            BaseCaseStep baseCaseStep = new BaseCaseStep();
            baseCaseStep.setId(xMindDeleteDTO.getStepId());
            if (EXCEPT_DB.equals(xMindDeleteDTO.getVerifyType())) {
                baseCaseStep.setExpectDb("");
            }
            if (EXCEPT_UI.equals(xMindDeleteDTO.getVerifyType())) {
                baseCaseStep.setExpectUi("");
            }
            if (EXCEPT_RESPONSE.equals(xMindDeleteDTO.getVerifyType())) {
                baseCaseStep.setExpectResponse("");
            }
            if (EXCEPT_OTHER.equals(xMindDeleteDTO.getVerifyType())) {
                baseCaseStep.setExpectOther("");
            }
            baseCaseStepMapper.updateByPrimaryKeySelective(baseCaseStep);
            BaseCase baseCase = new BaseCase();
            baseCase.setId(xMindDeleteDTO.getCaseId());
            baseCase.setEdited(CASE_EDITED);
            baseCase.setPushed(CASE_UNPUSHED);
            baseCaseMapper.updateByPrimaryKeySelective(baseCase);
            return;
        }
        if (isDeleteLabel(xMindDeleteDTO)) {
            baseCaseLabelMapperExt.deleteByLabelType(xMindDeleteDTO.getCaseId(), xMindDeleteDTO.getLabelType());
            return;
        }
        if (isDeleteStep(xMindDeleteDTO)) {
            baseCaseStepMapper.deleteByPrimaryKey(xMindDeleteDTO.getStepId());
            BaseCase baseCase = new BaseCase();
            baseCase.setId(xMindDeleteDTO.getCaseId());
            baseCase.setEdited(CASE_EDITED);
            baseCase.setPushed(CASE_UNPUSHED);
            baseCaseMapper.updateByPrimaryKeySelective(baseCase);
            return;
        }
        if (isDeleteCase(xMindDeleteDTO)) {
            int num = baseCaseMapper.deleteByPrimaryKey(xMindDeleteDTO.getCaseId());
            if (num == 0) {
                throw new BusinessException("删除测试用例失败");
            }
            BaseCaseStepExample stepExample = new BaseCaseStepExample();
            BaseCaseStepExample.Criteria criteria = stepExample.createCriteria();
            criteria.andCaseIdEqualTo(xMindDeleteDTO.getCaseId());
            baseCaseStepMapper.deleteByExample(stepExample);
            BaseCaseLabelExample labelExample = new BaseCaseLabelExample();
            BaseCaseLabelExample.Criteria criteria1 = labelExample.createCriteria();
            criteria1.andCaseIdEqualTo(xMindDeleteDTO.getCaseId());
            baseCaseLabelMapper.deleteByExample(labelExample);
            return;
        }
    }

    private Boolean isDeleteVerify(XMindDeleteDTO xMindDeleteDTO) {
        return xMindDeleteDTO.getCaseId() != null && xMindDeleteDTO.getStepId() != null
                && xMindDeleteDTO.getVerifyType() != null;
    }

    private Boolean isDeleteStep(XMindDeleteDTO xMindDeleteDTO) {
        return xMindDeleteDTO.getCaseId() != null && xMindDeleteDTO.getStepId() != null;
    }

    private Boolean isDeleteLabel(XMindDeleteDTO xMindDeleteDTO) {
        return xMindDeleteDTO.getCaseId() != null && xMindDeleteDTO.getLabelType() != null;
    }

    private Boolean isDeleteCase(XMindDeleteDTO xMindDeleteDTO) {
        return xMindDeleteDTO.getCaseId() != null;
    }

    @Override
    public BassyPagination<LabelInfo> getLabels(LabelInfoParamDTO labelInfo) {
        BassyPagination<LabelInfo> pagination = new BassyPagination<>();
        LabelInfoExample example = new LabelInfoExample();
        LabelInfoExample.Criteria criteria = example.createCriteria();

        if (labelInfo.getName() != null) {
            criteria.andNameLike("%" + labelInfo.getName() + "%");
        }
        if (labelInfo.getType() != null) {
            criteria.andTypeEqualTo(labelInfo.getType());
        }
        if (labelInfo.getKeyWords() != null) {
            criteria.andNameLike("%" + labelInfo.getKeyWords() + "%");
        }
        List<LabelInfo> labelInfos = labelInfoMapper.selectByExample(example);

        PageInfo<LabelInfo> pageInfo = new PageInfo<>(labelInfos);
        pagination.setCurrent(labelInfo.getCurrent());
        pagination.setPageSize(labelInfo.getPageSize());
        pagination.setList(labelInfos);
        pagination.setTotal(((Long) pageInfo.getTotal()).intValue());
        return pagination;
    }

    @Override
    public boolean updateLabel(LabelInfoParamDTO labelInfoParamDTO) {
        LabelInfo labelInfo = new LabelInfo();
        BeanUtils.copyProperties(labelInfoParamDTO, labelInfo);
        if (labelInfoParamDTO.getId() != null) {
            return labelInfoMapper.updateByPrimaryKeySelective(labelInfo) > 0;
        } else {
            labelInfo.setCitations(0);
            return labelInfoMapper.insertSelective(labelInfo) > 0;
        }
    }

    @Override
    public boolean deleteLabel(LabelInfoParamDTO labelInfoParamDTO) {
        BaseCaseLabelExample example = new BaseCaseLabelExample();
        BaseCaseLabelExample.Criteria criteria = example.createCriteria();
        criteria.andLabelIdEqualTo(labelInfoParamDTO.getId());
        baseCaseLabelMapper.deleteByExample(example);
        return labelInfoMapper.deleteByPrimaryKey(labelInfoParamDTO.getId()) > 0;
    }

    @Override
    public Pagination<ProCaseIndexVO> getProCaseIndex(ProCaseIndexParamDTO dto) {
        Pagination<ProCaseIndexVO> proCaseIndexVOPagination = new Pagination<>();
        List<ProCaseIndexVO> vos = new ArrayList<>();
        ProgramParamDTO paramDTO = new ProgramParamDTO();
        paramDTO.setName(dto.getKeyword());
        paramDTO.setPageNum(dto.getPageNum());
        paramDTO.setPageSize(dto.getPageSize());
        List<Program> programList;
        Pagination<Program> programs;
        if (dto.getStatus().equals("unpush")) {
            programs = programService.listUnpushedCaseProgram(paramDTO);
            programList = programs.getList();

        } else if (dto.getStatus().equals("end")) {
            programs = programService.listAllpushedCaseProgram(paramDTO);
            programList = programs.getList();
        } else {
            String[] status = {"processing", "init", "scheduled"}; // TODO: 2019/12/25 不要使用魔法值
            programs = programService.getProgramByStatus(paramDTO, status);
            programList = programs.getList();
        }
        for (Program program : programList) {
            ProCaseIndexVO caseIndexVO = new ProCaseIndexVO();
            caseIndexVO.setId(program.getId());
            caseIndexVO.setProgramName(program.getProgramName());
            caseIndexVO.setCreateTm(program.getCreateTm());
            caseIndexVO.setStartTime(program.getStartTime());
            caseIndexVO.setEndTime(program.getEndTime());
            List<ProCaseRequireInfoVO> requireInfoVOS = new ArrayList<>();
            List<ProgramRequire> requires = programService.getProgramRequire(program.getId());
            for (ProgramRequire r : requires) {
                ProCaseRequireInfoVO requireInfoVO = new ProCaseRequireInfoVO();
                Integer requireId = r.getRequireId();
                requireInfoVO.setRequireId(requireId);
                requireInfoVO.setRequireName(r.getRequireName());
                requireInfoVO.setRunPercent(this.getRunPercent(requireId));
                requireInfoVO.setPassPercent(this.getPassPercent(requireId));
                BaseCaseExample baseCaseExample = new BaseCaseExample();
                BaseCaseExample.Criteria criteria = baseCaseExample.createCriteria();
                criteria.andRequireEqualTo(requireId);
                criteria.andStatusEqualTo("1");
                Integer caseCount = (int) baseCaseMapper.countByExample(baseCaseExample);
                criteria.andPushedEqualTo((byte) 0);
                requireInfoVO.setTotalCase(caseCount);
                Integer unpushCount = (int) baseCaseMapper.countByExample(baseCaseExample);
                requireInfoVO.setUnpushCase(unpushCount);
                requireInfoVOS.add(requireInfoVO);
            }
            caseIndexVO.setRequireInfoVOList(requireInfoVOS);
            vos.add(caseIndexVO);
        }
        proCaseIndexVOPagination.setCurrentPage(dto.getPageNum());
        proCaseIndexVOPagination.setPageSize(dto.getPageSize());
        proCaseIndexVOPagination.setList(vos);
        proCaseIndexVOPagination.setTotalCount(programs.getTotalCount());
        return proCaseIndexVOPagination;
    }

    @Override
    public Float getRunPercent(Integer requireId) {
        BaseCaseExample baseCaseExample = new BaseCaseExample();
        BaseCaseExample.Criteria criteria = baseCaseExample.createCriteria();
        criteria.andRequireEqualTo(requireId);
        criteria.andStatusEqualTo("1");
        long total = baseCaseMapper.countByExample(baseCaseExample);
        if (total == 0) {
            return null;
        }
        criteria.andExecuteStatusNotEqualTo((byte) 0);
        //已执行用例数除以总用例数
        long run = baseCaseMapper.countByExample(baseCaseExample);
        return ((float) Math.round(run * 1.0f / total * 100) / 100);
    }

    @Override
    public Float getPassPercent(Integer requireId) {
        BaseCaseExample baseCaseExample = new BaseCaseExample();
        BaseCaseExample.Criteria criteria = baseCaseExample.createCriteria();
        criteria.andRequireEqualTo(requireId);
        criteria.andStatusEqualTo("1");
        long total = baseCaseMapper.countByExample(baseCaseExample);
        if (total == 0) {
            return null;
        }
        criteria.andExecuteStatusEqualTo((byte) 1);
        //通过的用例数除以总用例数
        long pass = baseCaseMapper.countByExample(baseCaseExample);
        return ((float) Math.round(pass * 1.0f / total * 100) / 100);
    }

    @Override
    public List<ConflictCaseDTO> batchPush(MergeTestCaseParamDTO mergeTestCaseParamDTO) {
        List<ConflictCaseDTO> conflictCaseDTOS = new ArrayList<>();
        List<BaseCase> programCases = getCases(mergeTestCaseParamDTO);
        programCases.forEach(pc -> {
            ConflictCaseDTO conflictCaseDTO = pushCase(pc.getId());
            if (conflictCaseDTO != null) {
                conflictCaseDTOS.add(conflictCaseDTO);
            }
        });
        return conflictCaseDTOS;
    }

    @Override
    public ConflictCaseDTO pushCase(Integer programCaseId) {
        BaseCase programCase = baseCaseMapper.selectByPrimaryKey(programCaseId);
        if (CASE_UNEDITED.equals(programCase.getEdited())) return null;
        if (programCase.getParentCase() != null) {
            BaseCase baseCase = baseCaseMapper.selectByPrimaryKey(programCase.getParentCase());
            if (baseCase.getVersion().compareTo(programCase.getVersion()) > 0) {
                ConflictCaseDTO conflictCaseDTO = new ConflictCaseDTO();
                conflictCaseDTO.setBaseCase(buildCaseDetail(baseCase));
                conflictCaseDTO.setProgramCase(buildCaseDetail(programCase));
                conflictCaseDTO.setCaseTitle(programCase.getTitle());
                conflictCaseDTO.setCaseId(programCase.getId());
                return conflictCaseDTO;
            }
            programCase.setVersion((byte) (programCase.getVersion() + 1));
        }

        //更新基线用例
        UpdateBaseCaseDTO targetCase = new UpdateBaseCaseDTO();
        targetCase.setId(programCase.getParentCase());
        targetCase.setFamily(BASE_CASE);
        mergeCaseUpdate(programCase, targetCase);

        //更新项目用例
        programCase.setEdited(CASE_UNEDITED);
        programCase.setPushed(CASE_PUSHED);
        baseCaseMapper.updateByPrimaryKeySelective(programCase);
        return null;
    }

    @Override
    public List<ConflictCaseDTO> batchPull(MergeTestCaseParamDTO mergeTestCaseParamDTO) {
        List<ConflictCaseDTO> conflictCaseDTOS = new ArrayList<>();
        List<BaseCase> programCases = getCases(mergeTestCaseParamDTO);
        programCases.forEach(pc -> {
            ConflictCaseDTO conflictCaseDTO = pullCase(pc.getId());
            if (conflictCaseDTO != null) {
                conflictCaseDTOS.add(conflictCaseDTO);
            }
        });
        return conflictCaseDTOS;
    }

    @Override
    public ConflictCaseDTO pullCase(Integer programCaseId) {
        BaseCase programCase = baseCaseMapper.selectByPrimaryKey(programCaseId);
        if (programCase.getParentCase() == null) return null;
        BaseCase baseCase = baseCaseMapper.selectByPrimaryKey(programCase.getParentCase());
        if (baseCase.getVersion().compareTo(programCase.getVersion()) == 0) return null;
        if (CASE_EDITED.equals(programCase.getEdited())) {
            ConflictCaseDTO conflictCaseDTO = new ConflictCaseDTO();
            conflictCaseDTO.setCaseId(programCase.getId());
            conflictCaseDTO.setCaseTitle(programCase.getTitle());
            conflictCaseDTO.setBaseCase(buildCaseDetail(baseCase));
            conflictCaseDTO.setProgramCase(buildCaseDetail(programCase));
            return conflictCaseDTO;
        }
        //更新项目用例
        UpdateBaseCaseDTO targetCase = new UpdateBaseCaseDTO();
        targetCase.setId(programCaseId);
        targetCase.setFamily(PROGRAM_CASE);
        mergeCaseUpdate(baseCase, targetCase);
        return null;
    }

    private void mergeCaseUpdate(BaseCase sourceCase, UpdateBaseCaseDTO targetCase) {
        BaseCaseBO baseCaseBO = new BaseCaseBO();
        BeanUtils.copyProperties(sourceCase, baseCaseBO, "id");
        BeanUtils.copyProperties(baseCaseBO, targetCase, "id");
        List<BaseCaseStep> steps = this.getBaseCaseStep(sourceCase.getId());
        List<BaseCaseLabelDTO> labels = this.getCaseLabel(sourceCase.getId());
        List<BaseCaseStepDTO> baseCaseStepDTOS = new ArrayList<>();
        BeanUtils.copyProperties(labels, baseCaseStepDTOS);
        targetCase.setStep(baseCaseStepDTOS);
        targetCase.setLabel(labels);
        targetCase.setIsMerge(true);
        if (targetCase.getId() != null) {
            steps.forEach(step -> step.setCaseId(targetCase.getId()));
            labels.forEach(label -> label.setCaseId(targetCase.getId()));
            updateTestCase(targetCase);
        } else {
            steps.forEach(step -> step.setCaseId(null));
            labels.forEach(label -> label.setCaseId(null));
            targetCase.setFamily(BASE_CASE);
            Integer caseId = addTestCase(targetCase);
            //更新项目用例的基线用例Id
            BaseCase programCase = new BaseCase();
            programCase.setId(sourceCase.getId());
            programCase.setParentCase(caseId);
            baseCaseMapper.updateByPrimaryKeySelective(programCase);
        }
    }

    private BaseCaseDetailVO buildCaseDetail(BaseCase baseCase) {
        BaseCaseDetailVO baseCaseDetailVO = new BaseCaseDetailVO();
        List<BaseCaseStep> baseCaseSteps = getBaseCaseStep(baseCase.getId());
        List<BaseCaseStepDTO> baseCaseStepDTOS = new ArrayList<>();
        BeanUtils.copyProperties(baseCaseSteps, baseCaseStepDTOS);
        baseCaseDetailVO.setStep(baseCaseStepDTOS);
        List<BaseCaseLabelDTO> baseCaseLabels = getCaseLabel(baseCase.getId());
        baseCaseDetailVO.setLabel(baseCaseLabels);
        BeanUtils.copyProperties(baseCase, baseCaseDetailVO);
        baseCaseDetailVO.setPass(baseCase.getExecuteStatus());
        return baseCaseDetailVO;
    }

    private List<BaseCase> getCases(MergeTestCaseParamDTO dto) {
        BaseCaseExample baseCaseExample = new BaseCaseExample();
        BaseCaseExample.Criteria criteria = baseCaseExample.createCriteria();
        criteria.andRequireEqualTo(dto.getRequireId());
        if (dto.getLastEditedBy() != null) {
            criteria.andLastEditedByEqualTo(dto.getLastEditedBy());
        }
        if (dto.getNotPass() != null) {
            List<Byte> otherStatus = new ArrayList<>();
            otherStatus.add(CASE_EXECUTE_PASS);
            otherStatus.add(CASE_EXECUTE_BLOCK);
            otherStatus.add(CASE_EXECUTE_DOUBT);
            otherStatus.add(CASE_UNEXECUTE);
            List<Byte> notPassStatus = new ArrayList<>();
            notPassStatus.add(CASE_EXECUTE_BLOCK);
            notPassStatus.add(CASE_EXECUTE_DOUBT);
            notPassStatus.add(CASE_UNEXECUTE);
            criteria.andExecuteStatusIn(dto.getNotPass() ? notPassStatus : otherStatus);
        }
        criteria.andStatusEqualTo(ENABLE);
        return baseCaseMapper.selectByExample(baseCaseExample);
    }

    @Override
    public void solveConflictCase(SolveCaseResultDTO solveResult) {
        //更新基线用例
        UpdateBaseCaseDTO updateBaseCase = new UpdateBaseCaseDTO();
        BeanUtils.copyProperties(solveResult, updateBaseCase);
        updateBaseCase.setId(solveResult.getBaseCaseId());
        updateBaseCase.setVersion((byte) (solveResult.getVersion() + 1));
        updateBaseCase.getLabel().forEach(label -> label.setCaseId(solveResult.getBaseCaseId()));
        updateBaseCase.getStep().forEach(step -> step.setCaseId(solveResult.getBaseCaseId()));
        updateBaseCase.setFamily(BASE_CASE);
        updateBaseCase.setIsMerge(true);
        updateTestCase(updateBaseCase);

        //更新项目用例
        if (solveResult.getProgramCaseId() == null) return;
        UpdateBaseCaseDTO updateProgramCase = new UpdateBaseCaseDTO();
        BeanUtils.copyProperties(solveResult, updateProgramCase);
        updateProgramCase.setId(solveResult.getProgramCaseId());
        updateProgramCase.setVersion((byte) (solveResult.getVersion() + 1));
        updateProgramCase.getLabel().forEach(label -> label.setCaseId(solveResult.getProgramCaseId()));
        updateProgramCase.getStep().forEach(step -> step.setCaseId(solveResult.getProgramCaseId()));
        updateProgramCase.setFamily(PROGRAM_CASE);
        updateProgramCase.setIsMerge(true);
        updateTestCase(updateProgramCase);
    }


    @Override
    public int importFromBaseCase(List<Integer> baseIds, String lastEditedBy, Integer require) {
        int importCount = 0;
        Date lastEditedDate = new Date();
        BaseCase baseCase = new BaseCase();
        baseCase.setRequire(require);
        baseCase.setLastEditedBy(lastEditedBy);
        baseCase.setLastEditedDate(lastEditedDate);
        for (Integer id : baseIds) {
            BaseCaseExample example = new BaseCaseExample();
            BaseCaseExample.Criteria criteria = example.createCriteria();
            criteria.andRequireEqualTo(require);
            criteria.andParentCaseEqualTo(id);
            if (baseCaseMapper.selectByExample(example).size() > 0) {
                logger.info("需求{}，基线用例{}已导入过，跳过", require, id);
                continue;
            }
            baseCase.setParentCase(id);
            importCount += baseCaseMapperExt.insertFromBaseCase(baseCase);
            Integer newCaseId = baseCase.getId();
            baseCaseLabelMapperExt.insertFromBaseLabel(id, newCaseId, lastEditedBy, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastEditedDate));
            baseCaseStepMapperExt.insertFromBaseStep(id, newCaseId);
        }
        return importCount;
    }

    @Override
    public List<String> autoGenSysLabel(String content) {
        return new ArrayList<>();
    }

}
