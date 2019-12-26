import {useContext} from 'react';
import {context} from '../context';
import * as mindmapAction from '../context/reducer/mindmap/actionCreator';
import * as nodeStatusAction from '../context/reducer/nodeStatus/actionCreator.js';
import {clearHistory} from '../context/reducer/history/actionCreator';
import md5 from 'md5';

const useMindmap = () => {
    const {mindmap: {dispatch: mDispatch}, nodeStatus: {dispatch: nDispatch}, history: {dispatch: hDispatch}} = useContext(context);
    return {
        toggleChildren: (node_id, bool) => {
            mDispatch(mindmapAction.toggleChildren(node_id, bool));
        },
        addChild: node_id => {
            const new_node_id = md5('' + Date.now() + Math.random());
            mDispatch(mindmapAction.toggleChildren(node_id, true));
            mDispatch(mindmapAction.addChild(node_id, new_node_id));
            nDispatch(nodeStatusAction.setEdit(new_node_id));
        },
        addSibling: (node_id, parent_id) => {
            const new_node_id = md5('' + Date.now() + Math.random());
            mDispatch(mindmapAction.addSibling(node_id, parent_id, new_node_id));
            nDispatch(nodeStatusAction.setEdit(new_node_id));
        },
        moveNode: (node_id, target_id, parent_id, is_sibling, product_id, module_id, case_id, type, version) => {
            mDispatch(mindmapAction.moveNode(node_id, target_id, parent_id, is_sibling, product_id, module_id, case_id, type, version));
            nDispatch(nodeStatusAction.setSelect(node_id));
        },
        editNode: node_id => {
            nDispatch(nodeStatusAction.setEdit(node_id));
        },
        changeText: (node_id, text, staffCode) => {
            mDispatch(mindmapAction.changeText(node_id, text, staffCode));
        },
        selectNode: (node_id, select_by_click) => {
            nDispatch(nodeStatusAction.setSelect(node_id, select_by_click));
        },
        deleteNode: (node_id, parent_id) => {
            mDispatch(mindmapAction.deleteNode(node_id, parent_id));
            nDispatch(nodeStatusAction.setSelect(parent_id));
        },
        copyNode: (node_id, parent_id, node_type) => {
            const new_node_id = md5('' + Date.now() + Math.random());
            mDispatch(mindmapAction.copyNode(node_id, parent_id, new_node_id, node_type));
            nDispatch(nodeStatusAction.setSelect(new_node_id));
        },
        clearNodeStatus: () => {
            nDispatch(nodeStatusAction.clearAll());
        },
        setMindmap: (mindmap, is_new_map) => {
            if (is_new_map) {
                hDispatch(clearHistory());
            }
            mDispatch(mindmapAction.setMindmap(mindmap));
        },
        expandAll: node_id => {
            mDispatch(mindmapAction.expandAll(node_id));
            nDispatch(nodeStatusAction.setSelect(node_id));
        },
        addProduct: (node_id, require_id, product_id, product_name) => {
            const new_node_id = md5('' + Date.now() + Math.random());
            mDispatch(mindmapAction.toggleChildren(node_id, true));
            mDispatch(mindmapAction.addProduct(node_id, new_node_id, require_id, product_id, product_name));
            nDispatch(nodeStatusAction.setSelect(new_node_id));
        },
        addProductModule: (node_id, product_id, require_id, module_id, module_name) => {
            const new_node_id = md5('' + Date.now() + Math.random());
            mDispatch(mindmapAction.toggleChildren(node_id, true));
            mDispatch(mindmapAction.addProductModule(node_id, new_node_id, product_id, require_id, module_id, module_name));
            nDispatch(nodeStatusAction.setSelect(new_node_id));
        },
        addCase: (node_id, module_id, product_id, require_id, staffCode) => {
            const new_node_id = md5('' + Date.now() + Math.random());
            mDispatch(mindmapAction.toggleChildren(node_id, true));
            mDispatch(mindmapAction.addCase(node_id, new_node_id, product_id, module_id, require_id, staffCode));
            nDispatch(nodeStatusAction.setEdit(new_node_id));
        },
        addCaseStep: (node_id, module_id, product_id, require_id, case_id, version, staffCode) => {
            const new_node_id = md5('' + Date.now() + Math.random());
            mDispatch(mindmapAction.toggleChildren(node_id, true));
            mDispatch(mindmapAction.addCaseStep(node_id, new_node_id, module_id, product_id, require_id, case_id, version, staffCode));
            nDispatch(nodeStatusAction.setEdit(new_node_id));
        },
        addCaseLabel: (node_id, module_id, product_id, require_id, case_id, version, staffCode) => {
            const new_node_id = md5('' + Date.now() + Math.random());
            mDispatch(mindmapAction.toggleChildren(node_id, true));
            mDispatch(mindmapAction.addCaseLabel(node_id, module_id, product_id, require_id, case_id, version, staffCode));
            nDispatch(nodeStatusAction.setEdit(new_node_id));
        },
        addCaseVerify: (node_id, module_id, product_id, require_id, case_id, step_id, verify_type, version, staffCode) => {
            const new_node_id = md5('' + Date.now() + Math.random());
            mDispatch(mindmapAction.toggleChildren(node_id, true));
            mDispatch(mindmapAction.addCaseVerify(node_id, new_node_id, module_id, product_id, require_id, case_id,
                step_id, verify_type, version, staffCode));
            nDispatch(nodeStatusAction.setEdit(new_node_id));
        },
        addCaseAppointLabel: (node_id, module_id, product_id, require_id, case_id, label_type, version, staffCode) => {
            const new_node_id = md5('' + Date.now() + Math.random());
            mDispatch(mindmapAction.toggleChildren(node_id, true));
            mDispatch(mindmapAction.addCaseAppointLabel(node_id, new_node_id, module_id, product_id, require_id, case_id,
                label_type, version, staffCode));
            nDispatch(nodeStatusAction.setEdit(new_node_id));
        },
        changeCasePassStatus: (node_id, pass) => {
            mDispatch(mindmapAction.changeCasePassStatus(node_id, pass));
        },
        changeCaseLabel: (node_id, labels) => {
            mDispatch(mindmapAction.changeCaseLabel(node_id, labels));
        },
        // moveCase: (node_id, target_id, parent_id, is_sibling, product_id, module_id, case_id, type) => {
        //     mDispatch(mindmapAction.moveNode(node_id, target_id, parent_id, is_sibling, product_id, module_id, case_id, type));
        //     nDispatch(nodeStatusAction.setSelect(node_id));
        // },
        // moveCaseStep: (node_id, target_id, parent_id, is_sibling, product_id, module_id, case_id, type) => {
        //     mDispatch(mindmapAction.moveNode(node_id, target_id, parent_id, is_sibling, product_id, module_id, case_id, type));
        //     nDispatch(nodeStatusAction.setSelect(node_id));
        // },
    }
};

export default useMindmap;
