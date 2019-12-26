import React, {useState} from 'react';
import {css} from 'emotion';
import useMindmap from '../../../customHooks/useMindmap';
import {handlePropagation} from '../../../methods/assistFunctions';
import ToolButton from '../../ToolButton';
import {
    CASE, CASE_LABEL, CASE_LABEL_BASE, CASE_LABEL_SYSTEM, CASE_PRI,
    CASE_STEP,
    CASE_STEP_DESCRIBE,
    EXCEPT_DB, EXCEPT_OTHER, EXCEPT_RESPONSE,
    EXCEPT_UI,
    PRODUCT,
    PRODUCT_MODULE,
    ROOT
} from "../../../statics/refer";
import {deleteByXmindTestCase, getModuleList, getProductList} from "../../../../../../apis";
import ProductModule from "./productModule"
import Product from "./product"

const Toolbar = ({layer, node, parent}) => {

    const [viewModule, setView] = useState(false);
    const [viewProduct, setProductView] = useState(false);
    const [moduleList, setModuleList] = useState([]);
    const [productList, setProductList] = useState([]);
    const mindmapHook = useMindmap();

    const handleAddChild = () => {
        mindmapHook.addChild(node.id);
    };

    const changeCasePri = (pri) => {
        mindmapHook.changeText(node.id, pri, window.__data.staffCode);
    };

    const handleAddCase = () => {
        mindmapHook.addCase(node.id, node.moduleId, node.productId, node.requireId, window.__data.staffCode);
    };

    const handleAddCaseVerify = (verify_type) => {
        let newExcept = true;
        node.children.map(except => {
            if (except.type === verify_type) {
                newExcept = false;
            }
        });
        if (newExcept) {
            mindmapHook.addCaseVerify(node.id, node.moduleId, node.productId, node.requireId, node.caseId,
                node.stepId, verify_type, node.version, window.__data.staffCode);
        }
    };

    const handleAddProduct = async () => {
        const res = await getProductList({});
        const productList = res.data;
        setProductList(productList);
        setProductView(true);
    };

    const cancelAddProduct = () => {
        setProductView(false);
    };

    const handleAddProductModule = async () => {
        const res =
            await getModuleList({moduleId: node.moduleId, productId: node.productId, allLayer: false});
        const moduleList = res.data;
        setModuleList(moduleList);
        setView(true);
    };

    const cancelAddProductModule = () => {
        setView(false);
    };

    const handleAddCaseStep = () => {
        mindmapHook.addCaseStep(node.id, node.moduleId, node.productId, node.requireId, node.caseId, node.version, window.__data.staffCode);
    };

    const handleAddCaseLabel = () => {
        mindmapHook.addCaseLabel(node.id, node.moduleId, node.productId, node.requireId, node.caseId, node.version, window.__data.staffCode);
    };

    const handleAddCaseAppointLabel = (label_type) => {
        let newLabel = true;
        node.children.map(label => {
            if (label.type === label_type) {
                newLabel = false;
            }
        });
        if (newLabel) {
            mindmapHook.addCaseAppointLabel(node.id, node.moduleId, node.productId, node.requireId, node.caseId,
                label_type, node.version, window.__data.staffCode);
        }
    };

    const handleAddSibling = () => {
        mindmapHook.addSibling(node.id, parent.id);
    };

    const handleDeleteNode = async () => {
        //新节点直接删除，老节点需要跟后端交互后再删除
        if (isNewNode(node)) {
            mindmapHook.deleteNode(node.id, parent.id);
        } else {
            let params = {};
            if (node.type === CASE) {
                params = {caseId: node.caseId}
            }
            if (node.type === CASE_STEP_DESCRIBE) {
                params = {caseId: node.caseId, stepId: node.stepId}
            }
            if (node.type === EXCEPT_DB || node.type === EXCEPT_UI || node.type === EXCEPT_RESPONSE ||
                node.type === EXCEPT_OTHER) {
                params = {caseId: node.caseId, stepId: node.stepId, verifyType: node.type}
            }
            if (node.type === CASE_LABEL_SYSTEM || node.type === CASE_LABEL_BASE) {
                params = {caseId: node.caseId, labelType: node.type}
            }
            const res = await deleteByXmindTestCase(params);
            if (res.errCode === 1) {
                mindmapHook.deleteNode(node.id, parent.id);
            }
        }
    };

    const isNewNode = (node) => {
        //新用例
        if (node.type === CASE && (node.caseId === undefined || node.caseId === null)) return true;
        //新标签(这个判断不准确，但不影响业务)
        if ((node.type === CASE_LABEL_SYSTEM || node.type === CASE_LABEL_BASE) && (node.labels === undefined, node.labels === null)) return true;
        //新步骤
        if (node.type === CASE_STEP_DESCRIBE && (node.stepId === undefined || node.stepId === null)) return true;
        //新校验点
        if ((node.type === EXCEPT_DB || node.type === EXCEPT_UI || node.type === EXCEPT_RESPONSE ||
            node.type === EXCEPT_OTHER) && (node.stepId === undefined || node.stepId === null)) return true;
        return false;
    };

    const handleEditNode = () => {
        mindmapHook.editNode(node.id);
    };
    const setCaseReady = () => {
        mindmapHook.changeCasePassStatus(node.id, 0);
    };
    const setCasePassed = () => {
        mindmapHook.changeCasePassStatus(node.id, 1);
    };
    const setCaseBlock = () => {
        mindmapHook.changeCasePassStatus(node.id, 2);
    };
    const setCaseDoubt = () => {
        mindmapHook.changeCasePassStatus(node.id, 3);
    };

    const handleToggleChildren = () => {
        mindmapHook.toggleChildren(node.id, !node.showChildren);
    };

    const handleCopyCase = () => {
        mindmapHook.copyNode(node.id, parent.id, node.type)
    };

    const handleCopyCaseStep = () => {
        mindmapHook.copyNode(node.id, parent.id, node.type)
    };

    return (<div className={wrapper} onClick={handlePropagation}>
        {
            node.type === ROOT &&
            <ToolButton icon={'apartment'} onClick={handleAddProduct}>添加产品</ToolButton>
        }
        {
            (node.type === PRODUCT_MODULE || node.type === PRODUCT) &&
            <ToolButton icon={'apartment'} onClick={handleAddProductModule}>添加模块</ToolButton>
        }
        {
            (node.type === PRODUCT_MODULE) && layer > 2 &&
            <ToolButton icon={'eye'} onClick={handleAddCase}>添加用例</ToolButton>
        }
        {
            (node.type === CASE) &&
            <ToolButton icon={'eye'} onClick={handleCopyCase}>复制用例</ToolButton>
        }
        {
            (node.type === CASE_STEP) &&
            <ToolButton icon={'robot'} onClick={handleAddCaseStep}>添加步骤</ToolButton>
        }
        {
            (node.type === CASE) &&
            <ToolButton icon={'tags'} onClick={handleAddCaseLabel}>添加标签</ToolButton>
        }
        {
            (node.type === CASE_LABEL) &&
            <ToolButton icon={'build'} onClick={() => handleAddCaseAppointLabel(CASE_LABEL_BASE)}>添加基础标签</ToolButton>
        }
        {
            (node.type === CASE_LABEL) &&
            <ToolButton icon={'setting'}
                        onClick={() => handleAddCaseAppointLabel(CASE_LABEL_SYSTEM)}>添加系统标签</ToolButton>
        }
        {
            (node.type === CASE_STEP_DESCRIBE) &&
            <ToolButton icon={'robot'} onClick={handleCopyCaseStep}>复制步骤</ToolButton>
        }
        {/*<ToolButton icon={'git-commit'} onClick={handleAddChild}>添加子节点</ToolButton>*/}
        {/*<ToolButton icon={'git-fork'} onClick={handleAddSibling} disabled={layer < 1}>添加兄弟节点</ToolButton>*/}
        {(node.type !== CASE_PRI) && layer > 1 &&
        <ToolButton icon={'delete'} onClick={handleDeleteNode}>删除</ToolButton>
        }
        {
            (node.type === ROOT && node.type === PRODUCT && node.type === PRODUCT_MODULE) &&
            <ToolButton icon={'edit'} onClick={handleEditNode}>编辑</ToolButton>
        }
        {
            (layer > 0 && node.children.length > 0) && (node.type !== CASE_STEP_DESCRIBE) &&
            <ToolButton icon={'fullscreen'} onClick={handleToggleChildren}>显隐子节点</ToolButton>
        }
        {
            node.requireId !== null && (node.type === CASE || node.type === CASE_STEP_DESCRIBE) &&
            <ToolButton icon={'check-circle'} onClick={setCasePassed}>通过</ToolButton>
        }
        {
            node.requireId !== null && (node.type === CASE || node.type === CASE_STEP_DESCRIBE) &&
            <ToolButton icon={'close-circle'} onClick={setCaseBlock}>阻塞</ToolButton>
        }
        {
            node.requireId !== null &&  (node.type === CASE || node.type === CASE_STEP_DESCRIBE) &&
            <ToolButton icon={'question-circle'} onClick={setCaseDoubt}>存疑</ToolButton>
        }
        {
            node.requireId !== null && (node.type === CASE || node.type === CASE_STEP_DESCRIBE) &&
            <ToolButton icon={'play-circle'} onClick={setCaseReady}>待测</ToolButton>
        }
        {
            (viewModule) &&
            <ProductModule cancelAddProductModule={cancelAddProductModule} moduleList={moduleList} node={node}/>
        }
        {
            (viewProduct) &&
            <Product cancelAddProduct={cancelAddProduct} productList={productList} node={node}/>
        }
        {
            node.type === CASE_STEP_DESCRIBE &&
            <ToolButton icon={'database'} onClick={() => handleAddCaseVerify(EXCEPT_DB)}>校验DB</ToolButton>

        }
        {
            node.type === CASE_STEP_DESCRIBE &&
            <ToolButton icon={'desktop'} onClick={() => handleAddCaseVerify(EXCEPT_UI)}>校验UI</ToolButton>

        }
        {
            node.type === CASE_STEP_DESCRIBE &&
            <ToolButton icon={'api'} onClick={() => handleAddCaseVerify(EXCEPT_RESPONSE)}>校验RES</ToolButton>

        }
        {
            node.type === CASE_STEP_DESCRIBE &&
            <ToolButton icon={'more'} onClick={() => handleAddCaseVerify(EXCEPT_OTHER)}>校验OT</ToolButton>
        }
        {
            node.type === CASE_PRI &&
            <ToolButton avatar={1} onClick={() => changeCasePri(1)}>1级</ToolButton>
        }
        {
            node.type === CASE_PRI &&
            <ToolButton avatar={2} onClick={() => changeCasePri(2)}>2级</ToolButton>
        }
        {
            node.type === CASE_PRI &&
            <ToolButton avatar={3} onClick={() => changeCasePri(3)}>3级</ToolButton>
        }
    </div>);
};

export default Toolbar;

// CSS
const wrapper = css`
display: flex;
position: absolute;
bottom: calc(100% + 5px);
left:0;
width: max-content;
height: 50px;
padding: 0 8px;
font-size: 20px;
background-color: #ffffff;
border-radius: 10px;
box-shadow: 5px 5px 10px #aaaaaa;
z-index:300;
`;
