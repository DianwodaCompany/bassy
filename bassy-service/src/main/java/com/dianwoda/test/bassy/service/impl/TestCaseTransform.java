package com.dianwoda.test.bassy.service.impl;

import com.dianwoda.test.bassy.common.constants.TestCaseConstant;
import com.dianwoda.test.bassy.common.enums.TestCaseTypeEn;
import com.dianwoda.test.bassy.common.exception.BassyException;
import com.dianwoda.test.bassy.common.exception.error.BassyError;
import com.dianwoda.test.bassy.db.dao.*;
import com.dianwoda.test.bassy.db.entity.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author zcp
 * @date 2019/6/3 14:34
 */
@Component
public class TestCaseTransform {


    @Autowired
    private BaseCaseMapper baseCaseMapper;

    @Autowired
    private BaseCaseMapperExt baseCaseMapperExt;

    @Autowired
    private LabelInfoMapper labelInfoMapper;

    @Autowired
    private ProductModuleMapper productModuleMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private LabelInfoMapperExt labelInfoMapperExt;

    @Autowired
    private BaseCaseLabelMapper baseCaseLabelMapper;

    @Autowired
    private BaseCaseStepMapper baseCaseStepMapper;

    public void execute(Row row, String lastEditedBy) {
        BaseCase baseCase = new BaseCase();
        baseCase.setLastEditedBy(lastEditedBy);
        baseCase.setLastEditedDate(new Date());
        CaseInfo caseInfo = new CaseInfo();

        init(row, baseCase, caseInfo);

        updateTestCase(baseCase, caseInfo);

        updateTestCaseLabel(baseCase, caseInfo);

        updateTestCaseStep(baseCase, caseInfo);
    }

    private void init(Row row, BaseCase baseCase, CaseInfo caseInfo) {
        row.cellIterator().forEachRemaining(cell -> {
            switch (cell.getColumnIndex()) {
                case 0:
                    baseCase.setId((int) cell.getNumericCellValue());
                    break;
                case 1:
                    getTestCaseProduct(cell, caseInfo);
                    baseCase.setProduct(caseInfo.getCaseProduct());
                    break;
                case 2:
                    getTestCaseModule(cell, caseInfo);
                    baseCase.setModule(caseInfo.getCaseModule());
                    break;
                case 3:
                    baseCase.setTitle(cell.getStringCellValue());
                    break;
                case 4:
                    baseCase.setType(String.valueOf(TestCaseTypeEn.toEnum(cell.getStringCellValue()).getCode()));
                    break;
                case 5:
                    baseCase.setPri((byte) cell.getNumericCellValue());
                    break;
                case 6:
                    baseCase.setPrecondition(cell.getStringCellValue());
                    break;
                case 7:
                    Collections.addAll(caseInfo.getLabels(), cell.getStringCellValue().split(","));
                    break;
                case 8:
                    Collections.addAll(caseInfo.getSteps(), cell.getStringCellValue().split(";"));
                    break;
                case 9:
                    Collections.addAll(caseInfo.getExceptDB(), cell.getStringCellValue().split(";"));
                    break;
                case 10:
                    Collections.addAll(caseInfo.getExceptUI(), cell.getStringCellValue().split(";"));
                    break;
                case 11:
                    Collections.addAll(caseInfo.getExceptResponse(), cell.getStringCellValue().split(";"));
                    break;
                case 12:
                    Collections.addAll(caseInfo.getExceptOther(), cell.getStringCellValue().split(";"));
                    break;
                case 13:
                    baseCase.setVersion((byte) cell.getNumericCellValue());
                    break;
            }
        });
        if (baseCase.getId() != null) {
            caseInfo.setNewCase(false);
        }
    }

    private void updateTestCase(BaseCase baseCase, CaseInfo caseInfo) {
        baseCase.setStatus("1");
        if (caseInfo.isNewCase()) {
            baseCase.setVersion((byte) 1);
            baseCaseMapperExt.insert(baseCase);
        } else {
            BaseCase oldBaseCase = baseCaseMapper.selectByPrimaryKey(baseCase.getId());
            if (baseCase.getVersion() <= oldBaseCase.getVersion()) {
                throw new BassyException(BassyError.TEST_CASE_VERSION_ERROR.getCode(),
                        BassyError.TEST_CASE_VERSION_ERROR.getMessage());
            }
            baseCaseMapper.updateByPrimaryKey(baseCase);
        }
    }

    private void getTestCaseProduct(Cell cell, CaseInfo caseInfo) {
        ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andNameEqualTo(cell.getStringCellValue());
        List<Product> products = productMapper.selectByExample(productExample);
        caseInfo.setCaseProduct(products.get(0).getId());
    }

    private void getTestCaseModule(Cell cell, CaseInfo caseInfo) {
        String[] modules = cell.getStringCellValue().split("/");
        ProductModuleExample productModuleExample = new ProductModuleExample();
        ProductModuleExample.Criteria criteria1 = productModuleExample.createCriteria();
        criteria1.andNameEqualTo(modules[modules.length - 1]);
        List<ProductModule> productModules = productModuleMapper.selectByExample(productModuleExample);
        caseInfo.setCaseModule(productModules.get(0).getId());
    }

    private void updateTestCaseLabel(BaseCase baseCase, CaseInfo caseInfo) {
        if (!caseInfo.isNewCase()) {
            BaseCaseLabelExample example = new BaseCaseLabelExample();
            BaseCaseLabelExample.Criteria criteria = example.createCriteria();
            criteria.andCaseIdEqualTo(baseCase.getId());
            baseCaseLabelMapper.deleteByExample(example);
        }
        caseInfo.getLabels().forEach(label -> {
            LabelInfoExample labelInfoExample = new LabelInfoExample();
            LabelInfoExample.Criteria criteria2 = labelInfoExample.createCriteria();
            criteria2.andNameEqualTo(label);
            List<LabelInfo> labelInfos = labelInfoMapper.selectByExample(labelInfoExample);
            BaseCaseLabel baseCaseLabel = new BaseCaseLabel();
            if (labelInfos.size() > 0) {
                baseCaseLabel.setLabelId(labelInfos.get(0).getId());
            } else {
                LabelInfo labelInfo = new LabelInfo();
                labelInfo.setName(label);
                labelInfo.setType(TestCaseConstant.SYSTEM_LABEL);
                labelInfo.setProduct(caseInfo.getCaseProduct());
                labelInfo.setCitations(1);
                labelInfoMapperExt.insert(labelInfo);
                baseCaseLabel.setLabelId(labelInfo.getId());
            }
            baseCaseLabel.setCaseId(baseCase.getId());
            baseCaseLabelMapper.insertSelective(baseCaseLabel);
        });
    }

    private void updateTestCaseStep(BaseCase baseCase, CaseInfo caseInfo) {
        if (!caseInfo.isNewCase()) {
            BaseCaseStepExample example = new BaseCaseStepExample();
            BaseCaseStepExample.Criteria criteria = example.createCriteria();
            criteria.andCaseIdEqualTo(baseCase.getId());
            baseCaseStepMapper.deleteByExample(example);
        }
        for (short i = 0; i < caseInfo.getSteps().size(); i++) {
            BaseCaseStep baseCaseStep = new BaseCaseStep();
            baseCaseStep.setCaseId(baseCase.getId());
            baseCaseStep.setStepId((long) i);
            baseCaseStep.setDesc(caseInfo.getSteps().get(i));
            if (caseInfo.getExceptDB().size() > i) {
                baseCaseStep.setExpectDb(caseInfo.getExceptDB().get(i));
            }
            if (caseInfo.getExceptUI().size() > i) {
                baseCaseStep.setExpectUi(caseInfo.getExceptUI().get(i));
            }
            if (caseInfo.getExceptResponse().size() > i) {
                baseCaseStep.setExpectResponse(caseInfo.getExceptResponse().get(i));
            }
            if (caseInfo.getExceptOther().size() > i) {
                baseCaseStep.setExpectOther(caseInfo.getExceptOther().get(i));
            }
            baseCaseStepMapper.insertSelective(baseCaseStep);
        }
    }

    static class CaseInfo {
        private boolean newCase = true;
        private Integer caseProduct = -1;
        private Integer caseModule = -1;
        private List<String> labels = new ArrayList<>();
        private List<String> steps = new ArrayList<>();
        private List<String> exceptDB = new ArrayList<>();
        private List<String> exceptUI = new ArrayList<>();
        private List<String> exceptResponse = new ArrayList<>();
        private List<String> exceptOther = new ArrayList<>();

        public boolean isNewCase() {
            return newCase;
        }

        public void setNewCase(boolean newCase) {
            this.newCase = newCase;
        }

        public Integer getCaseProduct() {
            return caseProduct;
        }

        public void setCaseProduct(Integer caseProduct) {
            this.caseProduct = caseProduct;
        }

        public Integer getCaseModule() {
            return caseModule;
        }

        public void setCaseModule(Integer caseModule) {
            this.caseModule = caseModule;
        }

        public List<String> getLabels() {
            return labels;
        }

        public void setLabels(List<String> labels) {
            this.labels = labels;
        }

        public List<String> getSteps() {
            return steps;
        }

        public void setSteps(List<String> steps) {
            this.steps = steps;
        }

        public List<String> getExceptDB() {
            return exceptDB;
        }

        public void setExceptDB(List<String> exceptDB) {
            this.exceptDB = exceptDB;
        }

        public List<String> getExceptUI() {
            return exceptUI;
        }

        public void setExceptUI(List<String> exceptUI) {
            this.exceptUI = exceptUI;
        }

        public List<String> getExceptResponse() {
            return exceptResponse;
        }

        public void setExceptResponse(List<String> exceptResponse) {
            this.exceptResponse = exceptResponse;
        }

        public List<String> getExceptOther() {
            return exceptOther;
        }

        public void setExceptOther(List<String> exceptOther) {
            this.exceptOther = exceptOther;
        }
    }
}
