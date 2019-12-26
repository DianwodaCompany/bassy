package com.dianwoda.test.bassy.web.controller;

import com.dianwoda.test.bassy.api.StaffService;
import com.dianwoda.test.bassy.common.constants.TestCaseConstant;
import com.dianwoda.test.bassy.common.domain.dto.BassyPagination;
import com.dianwoda.test.bassy.common.domain.dto.testcase.*;
import com.dianwoda.test.bassy.common.domain.vo.testcase.*;
import com.dianwoda.test.bassy.common.exception.BassyException;
import com.dianwoda.test.bassy.common.exception.BusinessException;
import com.dianwoda.test.bassy.common.exception.error.BassyError;
import com.dianwoda.test.bassy.common.result.Pagination;
import com.dianwoda.test.bassy.db.dao.ProductMapper;
import com.dianwoda.test.bassy.db.dao.ProductModuleMapper;
import com.dianwoda.test.bassy.db.entity.*;
import com.dianwoda.test.bassy.service.TestCaseService;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.dianwoda.test.bassy.common.constants.TestCaseConstant.*;


/**
 * @author zcp
 * @date 2019/5/15 16:02
 */
@RestController
@RequestMapping("/case")
public class TestCaseController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestCaseService testCaseService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductModuleMapper productModuleMapper;

    @Autowired
    private StaffService staffService;


    @ApiOperation(value = "分页获取测试用例列表", notes = "")
    @RequestMapping(value = "/pagelist", method = RequestMethod.POST)
    public Pagination<BaseCaseVO> getPagedCase(@RequestBody TestCaseParamDTO paramDTO) {
        List<BaseCaseVO> baseCaseVOList = new ArrayList<>();
        Pagination<BaseCase> page = testCaseService.getPagedBaseCase(paramDTO);
        List<BaseCase> baseCases = page.getList();
        for (BaseCase baseCase : baseCases) {
            baseCaseVOList.add(this.baseCase2baseCaseVo(baseCase));
        }
        Pagination<BaseCaseVO> pagination = new Pagination<>();
        pagination.setCurrentPage(paramDTO.getPageNum());
        pagination.setPageSize(paramDTO.getPageSize());
        pagination.setList(baseCaseVOList);
        pagination.setTotalCount(page.getTotalCount());
        return pagination;
    }

    /**
     * baseCase处理转换为vo
     *
     * @param baseCase
     * @return
     */
    private BaseCaseVO baseCase2baseCaseVo(BaseCase baseCase) {
        BaseCaseVO baseCaseVO = new BaseCaseVO();
        BeanUtils.copyProperties(baseCase, baseCaseVO);
        String product = productMapper.selectByPrimaryKey(baseCase.getProduct()).getName();
        ProductModule productModule = new ProductModule();
        productModule.setId(baseCase.getModule());
        String module = testCaseService.getModuleAllLayerName(productModule);
        List<BaseCaseLabelDTO> baseCaseLabels = testCaseService.getCaseLabel(baseCase.getId());
        List<String> baseLabel = new ArrayList<>();
        List<String> systemLabel = new ArrayList<>();
        for (BaseCaseLabelDTO baseCaseLabel : baseCaseLabels) {
            if (baseCaseLabel.getType().equals("base")) {
                baseLabel.add(baseCaseLabel.getLabelName());
            } else {
                systemLabel.add(baseCaseLabel.getLabelName());
            }
        }
        baseCaseVO.setProductName(product);
        baseCaseVO.setModuleName(module);
        baseCaseVO.setBaseLabel(baseLabel);
        baseCaseVO.setSystemLabel(systemLabel);
        if (baseCase.getParentCase() != null) {
            Byte baseVersion = testCaseService.getBaseCaseDetail(baseCase.getParentCase()).getVersion();
            baseCaseVO.setBaseVersion(baseVersion);
        }
        if (baseCase.getLastEditedBy() != null) {
            baseCaseVO.setLastEditedBy(staffService.getStaffInfo(baseCase.getLastEditedBy()).getName());
        }
        return baseCaseVO;
    }

    @ApiOperation(value = "获取基础测试用例列表", notes = "")
    @RequestMapping(value = "/xmind/list", method = RequestMethod.POST)
    public TestCaseXmindNodeVO getTasks(@RequestBody TestCaseParamDTO paramDTO) {
        //构建根节点
        TestCaseXmindNodeVO rootNode = new TestCaseXmindNodeVO();
        List<TestCaseXmindNodeVO> productNodes = new ArrayList<>();
        GetBaseCaseDTO getBaseCaseDTO = new GetBaseCaseDTO(paramDTO);
        getBaseCaseDTO.setOrderBy("id asc");
        List<Integer> subModules = testCaseService.getSubModule(getBaseCaseDTO);
        getBaseCaseDTO.setSubModules(subModules);
        List<BaseCase> baseCases = testCaseService.getBaseCase(getBaseCaseDTO);
        //为了前端展示效果，暂时定为查询最多200条
        if (baseCases.size() > 200) {
            throw new BusinessException("查询结果太多，请缩小查询范围！");
        }
        rootNode.setId(TestCaseConstant.ROOT_NODE_ID);
        rootNode.setText(TestCaseConstant.ROOT_NODE_TEXT);
        rootNode.setType(TestCaseConstant.ROOT);
        rootNode.setRequireId(paramDTO.getRequireId());
        rootNode.setShowChildren(true);
        rootNode.setChildren(productNodes);

        //对用例集合进行产品分类
        Map<Integer, List<BaseCase>> productSort = baseCases.stream().collect(Collectors.groupingBy(BaseCase::getProduct));
        productSort.forEach((product, productCases) -> {
            //构建产品节点
            TestCaseXmindNodeVO productNodeVO = new TestCaseXmindNodeVO();
            String productId = UUID.randomUUID().toString();
            productNodeVO.setId(productId);
            productNodeVO.setParentId(TestCaseConstant.ROOT_NODE_ID);
            productNodeVO.setProductId(product);
            productNodeVO.setRequireId(paramDTO.getRequireId());
            productNodeVO.setType(TestCaseConstant.PRODUCT);
            productNodeVO.setShowChildren(true);
            String productName = productMapper.selectByPrimaryKey(product).getName();
            productNodeVO.setText(productName);
            List<TestCaseXmindNodeVO> oneLevelModuleNodes = new ArrayList<>();
            productNodeVO.setChildren(oneLevelModuleNodes);
            //对用例进行模块分类
            Map<Integer, List<BaseCase>> moduleSort = productCases.stream().collect(Collectors.groupingBy(BaseCase::getModule));
            moduleSort.forEach((module, moduleCases) -> {
                ProductModule productModule = productModuleMapper.selectByPrimaryKey(module);
                //构建模块节点
                TestCaseXmindNodeVO lastModuleVO = addProductModuleNew(productNodeVO, productModule);
                List<TestCaseXmindNodeVO> testCaseVOList = new ArrayList<>();
                lastModuleVO.setChildren(testCaseVOList);
                //构建测试用例节点
                addTestCase2Module(moduleCases, testCaseVOList, lastModuleVO);
            });

            productNodes.add(productNodeVO);
        });
        return rootNode;
    }

    /**
     * 向产品添加模块
     *
     * @param parentModule  产品模块节点
     * @param productModule 产品模块信息
     * @return 返回最后一级模块节点
     */
    private TestCaseXmindNodeVO addProductModuleNew(TestCaseXmindNodeVO parentModule, ProductModule productModule) {
        String[] modulePath = productModule.getPath().split(",");
        List<TestCaseXmindNodeVO> subNodeModules = Optional.ofNullable(parentModule.getChildren()).orElse(new ArrayList<>());
        //添加第一层模块节点
        if (productModule.getPath().isEmpty()) {
            return addSubNodeModule(subNodeModules, parentModule, productModule);
        }
        for (String mp : modulePath) {
            subNodeModules = Optional.ofNullable(parentModule.getChildren()).orElse(new ArrayList<>());
            //检验是否已经存在当前模块节点，如果存在的话获取该节点，继续向后循环
            Optional<TestCaseXmindNodeVO> subNodeModule1 = subNodeModules.stream().filter(sub ->
                    mp.equals(sub.getModuleId().toString())
            ).findFirst();
            if (subNodeModule1.isPresent()) {
                parentModule = subNodeModule1.get();
                continue;
            }
            ProductModule productModule1 = productModuleMapper.selectByPrimaryKey(Integer.valueOf(mp));
            TestCaseXmindNodeVO subNodeModule = buildProductModuleNode(PRODUCT_MODULE, parentModule, productModule1);
            subNodeModules.add(subNodeModule);
            parentModule = subNodeModule;
        }
        //添加最后一层节点
        return addSubNodeModule(parentModule.getChildren(), parentModule, productModule);
    }

    /**
     * 向父节点添加子模块节点
     *
     * @param subNodeModules 父节点所有子节点
     * @param parentModule   父节点信息
     * @param productModule1 子节点信息
     * @return 返回新添加的子节点
     */
    private TestCaseXmindNodeVO addSubNodeModule(List<TestCaseXmindNodeVO> subNodeModules, TestCaseXmindNodeVO parentModule, ProductModule productModule1) {
        TestCaseXmindNodeVO subNodeModule = buildProductModuleNode(PRODUCT_MODULE, parentModule, productModule1);
        subNodeModules.add(subNodeModule);
        return subNodeModule;
    }

    /**
     * 向模块节点添加用例节点
     *
     * @param moduleCases    需要添加的用例
     * @param testCaseVOList 用添加到的节点
     */
    private void addTestCase2Module(List<BaseCase> moduleCases, List<TestCaseXmindNodeVO> testCaseVOList, TestCaseXmindNodeVO moduleNode) {
        moduleCases.forEach(baseCase -> {
            //测试用例根节点
            TestCaseXmindNodeVO testCaseNodeVO = buildCaseNode(CASE, baseCase, moduleNode);
            testCaseNodeVO.setShowChildren(false);
            //前置条件
            TestCaseXmindNodeVO testCasePreconditionNodeVO = buildCaseNode(CASE_PRECONDITION, baseCase, testCaseNodeVO);
            //用例等级
            TestCaseXmindNodeVO testCasePriNodeVO = buildCaseNode(CASE_PRI, baseCase, testCaseNodeVO);
            //用例步骤根节点
            TestCaseXmindNodeVO testCaseStepNodeVO = buildCaseNode(CASE_STEP, baseCase, testCaseNodeVO);
            List<TestCaseXmindNodeVO> testCaseStepInfoVO = new ArrayList<>();
            //用例步骤详情
            List<BaseCaseStep> baseCaseSteps = testCaseService.getBaseCaseStep(baseCase.getId());
            baseCaseSteps.forEach(baseCaseStep -> {
                //用例步骤描述
                TestCaseXmindNodeVO testCaseStepInfoNodeVO = buildCaseStepNode(CASE_STEP_DESCRIBE, baseCase, baseCaseStep, testCaseStepNodeVO);
                testCaseStepInfoNodeVO.setShowChildren(true);
                List<TestCaseXmindNodeVO> testCaseVerifies = new ArrayList<>();
                //db校验节点
                if (baseCaseStep.getExpectDb() != null && !baseCaseStep.getExpectDb().isEmpty()) {
                    TestCaseXmindNodeVO testCaseDBExceptVO = buildCaseStepNode(EXCEPT_DB, baseCase, baseCaseStep, testCaseStepInfoNodeVO);
                    testCaseVerifies.add(testCaseDBExceptVO);
                }
                //ui校验节点
                if (baseCaseStep.getExpectUi() != null && !baseCaseStep.getExpectUi().isEmpty()) {
                    TestCaseXmindNodeVO testCaseUIExceptVO = buildCaseStepNode(EXCEPT_UI, baseCase, baseCaseStep, testCaseStepInfoNodeVO);
                    testCaseVerifies.add(testCaseUIExceptVO);
                }
                //response校验节点
                if (baseCaseStep.getExpectResponse() != null && !baseCaseStep.getExpectResponse().isEmpty()) {
                    TestCaseXmindNodeVO testCaseResponseExceptVO = buildCaseStepNode(EXCEPT_RESPONSE, baseCase, baseCaseStep, testCaseStepInfoNodeVO);
                    testCaseVerifies.add(testCaseResponseExceptVO);
                }
                //other校验节点
                if (baseCaseStep.getExpectOther() != null && !baseCaseStep.getExpectOther().isEmpty()) {
                    TestCaseXmindNodeVO testCaseOtherExceptVO = buildCaseStepNode(EXCEPT_OTHER, baseCase, baseCaseStep, testCaseStepInfoNodeVO);
                    testCaseVerifies.add(testCaseOtherExceptVO);
                }
                testCaseStepInfoNodeVO.setChildren(testCaseVerifies);
                testCaseStepInfoVO.add(testCaseStepInfoNodeVO);
            });
            testCaseStepNodeVO.setChildren(testCaseStepInfoVO);

            //用例标签
            TestCaseXmindNodeVO testCaseLabelNode = buildCaseNode(CASE_LABEL, baseCase, testCaseNodeVO);
            List<TestCaseXmindNodeVO> testCaseLabels = new ArrayList<>();
            testCaseLabelNode.setChildren(testCaseLabels);
            List<BaseCaseLabelDTO> labels = testCaseService.getCaseLabel(baseCase.getId());
            StringBuilder systemLabel = new StringBuilder();
            StringBuilder baseLabel = new StringBuilder();
            ArrayList<BaseCaseLabelDTO> systemLabels = new ArrayList<>();
            ArrayList<BaseCaseLabelDTO> baseLabels = new ArrayList<>();
            labels.forEach(label -> {
                if (CASE_LABEL_SYSTEM.equals(label.getType())) {
                    systemLabels.add(label);
                    systemLabel.append(label.getLabelName()).append(",");
                } else {
                    baseLabels.add(label);
                    baseLabel.append(label.getLabelName()).append(",");
                }
            });
            //系统标签
            if (systemLabel.length() > 0) {
                TestCaseXmindNodeVO systemLabelNode = buildCaseLabelNode(CASE_LABEL_SYSTEM, baseCase, testCaseLabelNode);
                systemLabelNode.setLabels(systemLabels);
                systemLabelNode.setText(systemLabel.toString().substring(0, systemLabel.length() - 1));
                testCaseLabels.add(systemLabelNode);
            }
            //基础标签
            if (baseLabel.length() > 0) {
                TestCaseXmindNodeVO baseLabelNode = buildCaseLabelNode(CASE_LABEL_BASE, baseCase, testCaseLabelNode);
                baseLabelNode.setLabels(baseLabels);
                baseLabelNode.setText(baseLabel.toString().substring(0, baseLabel.length() - 1));
                testCaseLabels.add(baseLabelNode);
            }

            List<TestCaseXmindNodeVO> testCaseInfo = new ArrayList<>();
            testCaseInfo.add(testCasePreconditionNodeVO);
            testCaseInfo.add(testCasePriNodeVO);
            testCaseInfo.add(testCaseStepNodeVO);
            testCaseInfo.add(testCaseLabelNode);
            testCaseNodeVO.setChildren(testCaseInfo);

            testCaseVOList.add(testCaseNodeVO);
        });
    }

    /**
     * 构建产品模块节点
     *
     * @param nodeType       节点类型
     * @param parentModule   父节点
     * @param productModule1 模块信息
     * @return 新模块节点
     */
    private TestCaseXmindNodeVO buildProductModuleNode(String nodeType, TestCaseXmindNodeVO parentModule, ProductModule productModule1) {
        TestCaseXmindNodeVO subNodeModule = new TestCaseXmindNodeVO();
        subNodeModule.setId(UUID.randomUUID().toString());
        subNodeModule.setParentId(UUID.randomUUID().toString());
        subNodeModule.setProductId(parentModule.getProductId());
        subNodeModule.setModuleId(productModule1.getId());
        subNodeModule.setRequireId(parentModule.getRequireId());
        subNodeModule.setType(nodeType);
        subNodeModule.setShowChildren(true);
        subNodeModule.setText(productModule1.getName());
        subNodeModule.setChildren(new ArrayList<>());
        return subNodeModule;
    }

    /**
     * 构建测试用例基本信息节点
     *
     * @param nodeType 节点类型
     * @param baseCase 用例基本信息
     * @return 信息节点
     */
    private TestCaseXmindNodeVO buildCaseNode(String nodeType, BaseCase baseCase, TestCaseXmindNodeVO parentNode) {
        TestCaseXmindNodeVO node = new TestCaseXmindNodeVO();
        node.setId(UUID.randomUUID().toString());
        node.setParentId(parentNode.getId());
        node.setProductId(baseCase.getProduct());
        node.setModuleId(baseCase.getModule());
        node.setCaseId(baseCase.getId());
        node.setRequireId(baseCase.getRequire());
        node.setType(nodeType);
        if (CASE.equals(nodeType)) {
            node.setText(baseCase.getTitle());
            node.setPass(baseCase.getExecuteStatus());
        } else if (CASE_PRECONDITION.equals(nodeType)) {
            node.setText(baseCase.getPrecondition());
        } else if (CASE_PRI.equals(nodeType)) {
            node.setText(baseCase.getPri().toString());
        } else if (CASE_STEP.equals(nodeType)) {
            node.setText(CASE_STEP_CN);
            node.setShowChildren(true);
        } else if (CASE_LABEL.equals(nodeType)) {
            node.setText(CASE_LABEL_CN);
            node.setShowChildren(true);
        } else {
            node.setText("");
        }
        node.setVersion(baseCase.getVersion());
        node.setShowChildren(true);
        node.setChildren(new ArrayList<>());
        return node;
    }

    /**
     * 构建测试用例步骤节点信息
     *
     * @param nodeType     节点类型
     * @param baseCase     测试用例基本信息
     * @param baseCaseStep 步骤信息
     * @return 信息节点
     */
    private TestCaseXmindNodeVO buildCaseStepNode(String nodeType, BaseCase baseCase, BaseCaseStep baseCaseStep, TestCaseXmindNodeVO parentNode) {
        TestCaseXmindNodeVO node = new TestCaseXmindNodeVO();
        node.setId(UUID.randomUUID().toString());
        node.setParentId(parentNode.getId());
        node.setCaseId(baseCaseStep.getCaseId());
        node.setProductId(parentNode.getProductId());
        node.setModuleId(parentNode.getModuleId());
        node.setRequireId(baseCase.getRequire());
        node.setStepId(baseCaseStep.getId());
        node.setStepStepId(baseCaseStep.getStepId());
        node.setVersion(baseCase.getVersion());
        node.setType(nodeType);
        if (EXCEPT_DB.equals(nodeType)) {
            node.setText(baseCaseStep.getExpectDb());
        } else if (EXCEPT_UI.equals(nodeType)) {
            node.setText(baseCaseStep.getExpectUi());
        } else if (EXCEPT_RESPONSE.equals(nodeType)) {
            node.setText(baseCaseStep.getExpectResponse());
        } else if (EXCEPT_OTHER.equals(nodeType)) {
            node.setText(baseCaseStep.getExpectOther());
        } else if (CASE_STEP_DESCRIBE.equals(nodeType)) {
            node.setText(baseCaseStep.getDesc());
            node.setPass(baseCaseStep.getExecuteStatus());
        } else {
            node.setText("");
        }
        node.setShowChildren(true);
        node.setChildren(new ArrayList<>());
        return node;
    }

    private TestCaseXmindNodeVO buildCaseLabelNode(String nodeType, BaseCase baseCase, TestCaseXmindNodeVO parentNode) {
        TestCaseXmindNodeVO node = new TestCaseXmindNodeVO();
        node.setId(UUID.randomUUID().toString());
        node.setParentId(parentNode.getId());
        node.setCaseId(baseCase.getId());
        node.setProductId(baseCase.getProduct());
        node.setModuleId(baseCase.getModule());
        node.setRequireId(baseCase.getRequire());
        node.setVersion(baseCase.getVersion());
        node.setType(nodeType);
        node.setText("");
        node.setShowChildren(true);
        node.setChildren(new ArrayList<>());
        return node;
    }


    @ApiOperation(value = "通过XMind更新测试用例", notes = "")
    @RequestMapping(value = "/xmind/update", method = RequestMethod.POST)
    public XmindUpdateResultVO updateTasks(@RequestBody TestCaseXmindNodeVO testCaseXmindNodeVO) {
        return testCaseService.updateTestCaseByXMind(testCaseXmindNodeVO);
    }

    @ApiOperation(value = "通过XMind删除测试用例", notes = "")
    @RequestMapping(value = "/xmind/delete", method = RequestMethod.POST)
    public void deleteTasks(@RequestBody XMindDeleteDTO xMindDeleteDTO) {
        testCaseService.deleteTestCaseByXMind(xMindDeleteDTO);
    }

    @ApiOperation(value = "获取测试用例详情", notes = "")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public BaseCaseDetailVO getTestCaseDetail(@RequestBody TestCaseParamDTO paramDTO) {
        BaseCaseDetailVO baseCaseDetailVO = new BaseCaseDetailVO();
        BaseCase baseCase = testCaseService.getBaseCaseDetail(paramDTO.getId());
        BeanUtils.copyProperties(baseCase, baseCaseDetailVO);
        List<BaseCaseStep> baseCaseSteps = testCaseService.getBaseCaseStep(paramDTO.getId());
        List<BaseCaseStepDTO> baseCaseStepDTOS = new ArrayList<>();
        BeanUtils.copyProperties(baseCaseSteps, baseCaseStepDTOS);
        baseCaseDetailVO.setStep(baseCaseStepDTOS);
        List<BaseCaseLabelDTO> baseCaseLabels = testCaseService.getCaseLabel(paramDTO.getId());
        baseCaseDetailVO.setLabel(baseCaseLabels);
        return baseCaseDetailVO;
    }

    @ApiOperation(value = "更新用例信息", notes = "")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ConflictCaseDTO updateTestCase(@RequestBody UpdateBaseCaseDTO updateBaseCaseDTO) {
        if (BASE_CASE.equals(updateBaseCaseDTO.getFamily())) {
            updateBaseCaseDTO.setVersion((byte) (updateBaseCaseDTO.getVersion() + 1));
        }
        updateBaseCaseDTO.setIsMerge(false);
        return testCaseService.updateTestCase(updateBaseCaseDTO);
    }

    @ApiOperation(value = "新增用例", notes = "")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Integer addTestCase(@RequestBody UpdateBaseCaseDTO updateBaseCaseDTO) {
        updateBaseCaseDTO.setVersion((byte) 1);
        return testCaseService.addTestCase(updateBaseCaseDTO);
    }

    @ApiOperation(value = "复制用例", notes = "")
    @RequestMapping(value = "/copy", method = RequestMethod.POST)
    public Integer copyTestCase(@RequestBody UpdateBaseCaseDTO updateBaseCaseDTO) {
        return testCaseService.copyBaseCase(updateBaseCaseDTO.getId());
    }

    @ApiOperation(value = "删除用例", notes = "")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Boolean deleteTestCase(@RequestBody UpdateBaseCaseDTO updateBaseCaseDTO) {
        return testCaseService.deleteBaseCase(updateBaseCaseDTO.getId());
    }

    @ApiOperation(value = "获取产品所有模块", notes = "")
    @RequestMapping(value = "/module/list", method = RequestMethod.POST)
    public List<ProductModule> getModuleList(@RequestBody ProductModuleParamDTO paramDTO) {
        List<ProductModule> productModuleList = testCaseService.getProductModule(paramDTO);
        if (paramDTO.isAllLayer()) {
            productModuleList.forEach(productModule -> {
                productModule.setName(
                        testCaseService.getModuleAllLayerName(productModule)
                );
            });
            return productModuleList;
        }
        return filterNextModule(productModuleList, paramDTO);
    }

    private List<ProductModule> filterNextModule(List<ProductModule> productModuleList, ProductModuleParamDTO productModuleParamDTO) {
        return productModuleList.stream().filter(productModule -> {
            String[] modulePath = productModule.getPath().split(",");
            return isNextModule(modulePath, productModuleParamDTO);
        }).collect(Collectors.toList());
    }

    private boolean isNextModule(String[] modulePath, ProductModuleParamDTO productModuleParamDTO) {

        if (productModuleParamDTO.getProductId() != null && productModuleParamDTO.getModuleId() == null) {
            return modulePath.length == 1 && modulePath[0].isEmpty();
        }
        return modulePath[modulePath.length - 1].equals(productModuleParamDTO.getModuleId().toString());
    }

    @ApiOperation(value = "获取所有的产品", notes = "")
    @RequestMapping(value = "/product/list", method = RequestMethod.POST)
    public List<Product> getProductList(@RequestBody ProductModuleParamDTO paramDTO) {
        return testCaseService.getProduct(paramDTO);
    }


    @ApiOperation(value = "导入用例", notes = "")
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public TestCaseImportVO importTestCase(@RequestParam("file") MultipartFile uploadFiles,
                                           @RequestParam("lastEditedBy") String lastEditedBy) throws IOException {

        HSSFWorkbook book = new HSSFWorkbook(uploadFiles.getInputStream());
        HSSFSheet sheet = book.getSheetAt(0);
        return testCaseService.importTestCase(sheet, lastEditedBy);
    }

    @ApiOperation(value = "获取所有的产品及模块", notes = "")
    @RequestMapping(value = "/product/module/tree", method = RequestMethod.POST)
    public List<ProductModuleTreeNodeVO> getProductModuleTree() {
        ProductModuleParamDTO paramDTO = new ProductModuleParamDTO();
        List<Product> allProduct = testCaseService.getProduct(paramDTO);
        List<ProductModuleTreeNodeVO> products = new ArrayList<>();

        allProduct.forEach(product -> {
            ProductModuleTreeNodeVO productModuleTreeNodeVO = new ProductModuleTreeNodeVO();
            productModuleTreeNodeVO.setId(product.getId() + "_");
            productModuleTreeNodeVO.setProductId(product.getId());
            productModuleTreeNodeVO.setName(staffService.getStaffInfo(product.getName()).getName());
            productModuleTreeNodeVO.setName(product.getName());
            productModuleTreeNodeVO.setDefender(product.getDefender());
            products.add(productModuleTreeNodeVO);
            List<ProductModuleTreeNodeVO> modules = new ArrayList<>();
            productModuleTreeNodeVO.setChildren(modules);
            ProductModuleParamDTO productModuleParamDTO = new ProductModuleParamDTO();
            productModuleParamDTO.setProductId(product.getId());
            List<ProductModule> productModuleList = testCaseService.getProductModule(productModuleParamDTO);
            List<ProductModule> firstModules = filterNextModule(productModuleList, productModuleParamDTO);
            addModule(modules, firstModules);
        });
        return products;
    }

    private void addModule(List<ProductModuleTreeNodeVO> modules, List<ProductModule> productModuleList) {
        productModuleList.forEach(productModule -> {
            ProductModuleTreeNodeVO productModuleTreeNodeVO = new ProductModuleTreeNodeVO();
            productModuleTreeNodeVO.setId(productModule.getRoot() + "_" + productModule.getId());
            productModuleTreeNodeVO.setProductId(productModule.getRoot());
            productModuleTreeNodeVO.setModuleId(productModule.getId());
            productModuleTreeNodeVO.setPath(productModule.getPath());
            productModuleTreeNodeVO.setName(staffService.getStaffInfo(productModule.getName()).getName());
            productModuleTreeNodeVO.setDefender(productModule.getDefender());
            List<ProductModuleTreeNodeVO> subModules = new ArrayList<>();
            productModuleTreeNodeVO.setChildren(subModules);
            modules.add(productModuleTreeNodeVO);
            List<ProductModule> productModules = searchSubModules(productModule);
            addModule(subModules, productModules);
        });
    }

    private List<ProductModule> searchSubModules(ProductModule productModule) {
        ProductModuleParamDTO productModuleParamDTO = new ProductModuleParamDTO();
        productModuleParamDTO.setModuleId(productModule.getId());
        List<ProductModule> productModuleList = testCaseService.getProductModule(productModuleParamDTO);
        return filterNextModule(productModuleList, productModuleParamDTO);
    }

    @ApiOperation(value = "操作产品及模块", notes = "")
    @RequestMapping(value = "/product/module/operate", method = RequestMethod.POST)
    public void operateProductModule(@RequestBody ProductModuleParamDTO paramDTO) {
        if (TARGET_PRODUCT_MODULE.equals(paramDTO.getTarget())) {
            testCaseService.operateProductModule(paramDTO);
            return;
        }
        testCaseService.operateProduct(paramDTO);
    }

    @ApiOperation(value = "获取产品子模块", notes = "")
    @RequestMapping(value = "/product/module/sub", method = RequestMethod.POST)
    public List<ProductSubModuleVO> getProductSubModule(@RequestBody ProductModuleParamDTO paramDTO) {
        if (paramDTO.getProductId() == null && paramDTO.getModuleId() == null) {
            throw new BassyException(BassyError.PARAM_ERROR.getCode(), BassyError.PARAM_ERROR.getMessage());
        }
        List<ProductSubModuleVO> productSubModuleVOList = new ArrayList<>();
        StringBuilder parentModuleName = new StringBuilder();
        ProductModule parentModule = new ProductModule();

        List<ProductModule> moduleList = testCaseService.getProductModule(paramDTO);
        Product product = testCaseService.getProduct(paramDTO).get(0);
        String productName = product.getName();
        parentModuleName.append(productName).append("/");
        if (paramDTO.getModuleId() != null) {
            parentModule = productModuleMapper.selectByPrimaryKey(paramDTO.getModuleId());
            parentModule.setName(
                    testCaseService.getModuleAllLayerName(parentModule)
            );
            parentModuleName.append(parentModule.getName());
        }

        Integer parentModuleId = parentModule.getId();
        List<ProductModule> subModuleList = filterNextModule(moduleList, paramDTO);

        //最后一级模块也要有返回值
        if (subModuleList.size() == 0) {
            ProductSubModuleVO productSubModuleVO = new ProductSubModuleVO();
            productSubModuleVO.setParentModuleId(parentModuleId);
            productSubModuleVO.setParentModuleName(parentModuleName.toString());
            productSubModuleVOList.add(productSubModuleVO);
        }
        subModuleList.forEach(productModule -> {
            ProductSubModuleVO productSubModuleVO = new ProductSubModuleVO();
            productSubModuleVO.setParentModuleId(parentModuleId);
            productSubModuleVO.setParentModuleName(parentModuleName.toString());
            productSubModuleVO.setModuleId(productModule.getId());
            productSubModuleVO.setModuleName(productModule.getName());
            productSubModuleVO.setModuleDefender(productModule.getDefender());
            productSubModuleVOList.add(productSubModuleVO);
        });
        return productSubModuleVOList;
    }

    @ApiOperation(value = "获取基础标签列表", notes = "")
    @RequestMapping(value = "/label/list", method = RequestMethod.POST)
    public BassyPagination<LabelInfo> getLabelList(@RequestBody LabelInfoParamDTO paramDTO) {
        if (paramDTO.getKeyWords() != null && paramDTO.getKeyWords().isEmpty()) {
            return new BassyPagination<>();
        }
        return testCaseService.getLabels(paramDTO);
    }

    @ApiOperation(value = "更新标签", notes = "")
    @RequestMapping(value = "/label/update", method = RequestMethod.POST)
    public Boolean updateLabel(@RequestBody LabelInfoParamDTO paramDTO) {
        return testCaseService.updateLabel(paramDTO);
    }

    @ApiOperation(value = "更新标签", notes = "")
    @RequestMapping(value = "/label/delete", method = RequestMethod.POST)
    public Boolean deleteLabel(@RequestBody LabelInfoParamDTO paramDTO) {
        return testCaseService.deleteLabel(paramDTO);
    }

    @ApiOperation(value = "获取项目用例首页列表", notes = "")
    @RequestMapping(value = "/programCase", method = RequestMethod.POST)
    public Pagination<ProCaseIndexVO> getProCaseIndex(@RequestBody ProCaseIndexParamDTO dto) {
        Pagination<ProCaseIndexVO> proCaseIndexVOPagination = testCaseService.getProCaseIndex(dto);
        return proCaseIndexVOPagination;
    }

    @ApiOperation(value = "拉去基线用例", notes = "")
    @RequestMapping(value = "/program/require/pull", method = RequestMethod.POST)
    public List<ConflictCaseDTO> pullBaseCase(@RequestBody MergeTestCaseParamDTO dto) {
        if (dto.getRequireId() == null && dto.getProgramCaseId() == null) {
            throw new BassyException(BassyError.PARAM_ERROR.getCode(), BassyError.PARAM_ERROR.getMessage());
        } else if (dto.getRequireId() != null) {
            return testCaseService.batchPull(dto);
        } else {
            List<ConflictCaseDTO> conflictCaseDTOS = new ArrayList<>();
            ConflictCaseDTO conflictCaseDTO = testCaseService.pullCase(dto.getProgramCaseId());
            if (conflictCaseDTO != null) {
                conflictCaseDTOS.add(conflictCaseDTO);
            }
            return conflictCaseDTOS;
        }
    }

    @ApiOperation(value = "推送基线用例", notes = "")
    @RequestMapping(value = "/program/require/push", method = RequestMethod.POST)
    public List<ConflictCaseDTO> pushBaseCase(@RequestBody MergeTestCaseParamDTO dto) {
        if (dto.getRequireId() == null && dto.getProgramCaseId() == null) {
            throw new BassyException(BassyError.PARAM_ERROR.getCode(), BassyError.PARAM_ERROR.getMessage());
        } else if (dto.getRequireId() != null) {
            return testCaseService.batchPush(dto);
        } else {
            List<ConflictCaseDTO> conflictCaseDTOS = new ArrayList<>();
            ConflictCaseDTO conflictCaseDTO = testCaseService.pushCase(dto.getProgramCaseId());
            if (conflictCaseDTO != null) {
                conflictCaseDTOS.add(conflictCaseDTO);
            }
            return conflictCaseDTOS;
        }
    }

    @ApiOperation(value = "冲突用例处理", notes = "")
    @RequestMapping(value = "/program/conflict/solve", method = RequestMethod.POST)
    public void solveConflict(@RequestBody SolveCaseResultDTO solveResult) {
        testCaseService.solveConflictCase(solveResult);
    }

    @ApiOperation(value = "基线用例导入到项目用例", notes = "")
    @RequestMapping(value = "/program/importfrombase", method = RequestMethod.POST)
    public int importFromBaseCase(@RequestBody ImportFromBaseParamDTO importFromBaseParam) {
        return testCaseService.importFromBaseCase(importFromBaseParam.getBaseIds(),
                importFromBaseParam.getLastEditedBy(),
                importFromBaseParam.getRequire());
    }

    @ApiOperation(value = "自动生成系统标签", notes = "")
    @RequestMapping(value = "/program/genSysLabel", method = RequestMethod.POST)
    public List<String> autoGenSysLabel(@RequestBody String content) {
        return testCaseService.autoGenSysLabel(content);
    }
}
