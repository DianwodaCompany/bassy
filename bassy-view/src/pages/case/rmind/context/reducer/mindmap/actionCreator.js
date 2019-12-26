import * as actionTypes from './actionTypes';
import {
    CASE,
    CASE_LABEL, CASE_LABEL_BASE, CASE_LABEL_SYSTEM,
    CASE_PRECONDITION,
    CASE_PRI,
    CASE_STEP,
    CASE_STEP_DESCRIBE,
    EXCEPT_DB,
    EXCEPT_OTHER,
    EXCEPT_RESPONSE,
    EXCEPT_UI,
    NEW_NODE_TEXT, PRODUCT,
    PRODUCT_MODULE
} from '../../../statics/refer';
import md5 from "md5";

export const toggleChildren = (node_id, bool) => ({
    type: actionTypes.TOGGLE_CHILDREN,
    data: {
        node_id,
        node: {
            showChildren: bool
        }
    }
});

export const addChild = (node_id, new_node_id) => ({
    type: actionTypes.ADD_CHILD,
    data: {
        node_id,
        node: {
            id: new_node_id,
            text: NEW_NODE_TEXT,
            showChildren: true,
            children: []
        }
    }
});

export const addCase = (node_id, new_node_id, product_id, module_id, require_id, staffCode) => {

    const step_id = md5('' + Date.now() + Math.random());
    const step_describe_id = md5('' + Date.now() + Math.random());
    let node;
    node = {
        type: actionTypes.ADD_CHILD,
        data: {
            node_id,
            node: {
                id: new_node_id,
                parentId: node_id,
                text: "用例名称",
                type: CASE,
                pass: 0,
                productId: product_id,
                moduleId: module_id,
                requireId: require_id,
                lastEditedBy: staffCode,
                showChildren: true,
                hasEdited: true,
                children: [
                    {
                        id: md5('' + Date.now() + Math.random()),
                        parentId: new_node_id,
                        text: "前置条件",
                        type: CASE_PRECONDITION,
                        productId: product_id,
                        moduleId: module_id,
                        requireId: require_id,
                        lastEditedBy: staffCode,
                        showChildren: true,
                        hasEdited: true,
                        children: []
                    }, {
                        id: md5('' + Date.now() + Math.random()),
                        parentId: new_node_id,
                        text: "3",
                        type: CASE_PRI,
                        productId: product_id,
                        moduleId: module_id,
                        requireId: require_id,
                        lastEditedBy: staffCode,
                        showChildren: true,
                        hasEdited: true,
                        children: []
                    }, {
                        id: step_id,
                        parentId: new_node_id,
                        text: "步骤",
                        type: CASE_STEP,
                        productId: product_id,
                        moduleId: module_id,
                        requireId: require_id,
                        lastEditedBy: staffCode,
                        showChildren: true,
                        hasEdited: true,
                        children: [
                            {
                                id: step_describe_id,
                                parentId: step_id,
                                text: "步骤描述",
                                type: CASE_STEP_DESCRIBE,
                                pass: 0,
                                productId: product_id,
                                moduleId: module_id,
                                requireId: require_id,
                                lastEditedBy: staffCode,
                                showChildren: true,
                                hasEdited: true,
                                children: [
                                    {
                                        id: md5('' + Date.now() + Math.random()),
                                        parentId: step_describe_id,
                                        text: "DB校验",
                                        type: EXCEPT_DB,
                                        productId: product_id,
                                        moduleId: module_id,
                                        requireId: require_id,
                                        lastEditedBy: staffCode,
                                        showChildren: true,
                                        hasEdited: true,
                                        children: []
                                    }, {
                                        id: md5('' + Date.now() + Math.random()),
                                        parentId: step_describe_id,
                                        text: "UI校验",
                                        type: EXCEPT_UI,
                                        productId: product_id,
                                        moduleId: module_id,
                                        requireId: require_id,
                                        lastEditedBy: staffCode,
                                        showChildren: true,
                                        hasEdited: true,
                                        children: []
                                    }, {
                                        id: md5('' + Date.now() + Math.random()),
                                        parentId: step_describe_id,
                                        text: "API校验",
                                        type: EXCEPT_RESPONSE,
                                        productId: product_id,
                                        moduleId: module_id,
                                        requireId: require_id,
                                        lastEditedBy: staffCode,
                                        showChildren: true,
                                        hasEdited: true,
                                        children: []
                                    }, {
                                        id: md5('' + Date.now() + Math.random()),
                                        parentId: step_describe_id,
                                        text: "其他校验",
                                        type: EXCEPT_OTHER,
                                        productId: product_id,
                                        moduleId: module_id,
                                        requireId: require_id,
                                        lastEditedBy: staffCode,
                                        showChildren: true,
                                        hasEdited: true,
                                        children: []
                                    },
                                ]
                            },
                        ]
                    }, {
                        id: md5('' + Date.now() + Math.random()),
                        parentId: new_node_id,
                        text: "标签",
                        type: CASE_LABEL,
                        productId: product_id,
                        moduleId: module_id,
                        requireId: require_id,
                        lastEditedBy: staffCode,
                        showChildren: true,
                        hasEdited: true,
                        children: []
                    },
                ]
            }
        }
    };
    return node;
};
export const addCaseStep = (node_id, new_node_id, module_id, product_id, require_id, case_id, version, staffCode) => {
    let node;
    node = {
        type: actionTypes.ADD_CHILD,
        data: {
            node_id,
            node: {
                id: new_node_id,
                parentId: node_id,
                text: "步骤描述",
                type: CASE_STEP_DESCRIBE,
                pass: 0,
                caseId: case_id,
                productId: product_id,
                moduleId: module_id,
                requireId: require_id,
                lastEditedBy: staffCode,
                showChildren: true,
                hasEdited: true,
                version: version,
                children: [
                    {
                        id: md5('' + Date.now() + Math.random()),
                        parentId: new_node_id,
                        text: "DB校验",
                        type: EXCEPT_DB,
                        caseId: case_id,
                        productId: product_id,
                        moduleId: module_id,
                        requireId: require_id,
                        lastEditedBy: staffCode,
                        showChildren: true,
                        hasEdited: true,
                        version: version,
                        children: []
                    }, {
                        id: md5('' + Date.now() + Math.random()),
                        parentId: new_node_id,
                        text: "UI校验",
                        type: EXCEPT_UI,
                        caseId: case_id,
                        productId: product_id,
                        moduleId: module_id,
                        requireId: require_id,
                        lastEditedBy: staffCode,
                        showChildren: true,
                        hasEdited: true,
                        version: version,
                        children: []
                    }, {
                        id: md5('' + Date.now() + Math.random()),
                        parentId: new_node_id,
                        text: "API校验",
                        type: EXCEPT_RESPONSE,
                        caseId: case_id,
                        productId: product_id,
                        moduleId: module_id,
                        requireId: require_id,
                        lastEditedBy: staffCode,
                        showChildren: true,
                        hasEdited: true,
                        version: version,
                        children: []
                    }, {
                        id: md5('' + Date.now() + Math.random()),
                        parentId: new_node_id,
                        text: "其他校验",
                        type: EXCEPT_OTHER,
                        caseId: case_id,
                        productId: product_id,
                        moduleId: module_id,
                        requireId: require_id,
                        lastEditedBy: staffCode,
                        showChildren: true,
                        hasEdited: true,
                        version: version,
                        children: []
                    },
                ]
            },
        }
    };
    return node
};
export const addCaseLabel = (node_id, module_id, product_id, require_id, case_id, version, staffCode) => {
    const case_node_id = md5('' + Date.now() + Math.random());
    let node;
    node = {
        type: actionTypes.ADD_CHILD,
        data: {
            node_id,
            node: {
                id: case_node_id,
                parentId: node_id,
                text: "标签",
                type: CASE_LABEL,
                pass: 0,
                caseId: case_id,
                productId: product_id,
                moduleId: module_id,
                requireId: require_id,
                lastEditedBy: staffCode,
                showChildren: true,
                hasEdited: true,
                version: version,
                children: [
                    {
                        id: md5('' + Date.now() + Math.random()),
                        parentId: case_node_id,
                        text: "",
                        type: CASE_LABEL_SYSTEM,
                        caseId: case_id,
                        productId: product_id,
                        moduleId: module_id,
                        requireId: require_id,
                        labels: [],
                        lastEditedBy: staffCode,
                        showChildren: true,
                        hasEdited: true,
                        version: version,
                        children: []
                    }, {
                        id: md5('' + Date.now() + Math.random()),
                        parentId: case_node_id,
                        text: "",
                        type: CASE_LABEL_BASE,
                        caseId: case_id,
                        productId: product_id,
                        moduleId: module_id,
                        requireId: require_id,
                        labels: [],
                        lastEditedBy: staffCode,
                        showChildren: true,
                        hasEdited: true,
                        version: version,
                        children: []
                    }
                ]
            },
        }
    };
    return node
};
export const addCaseVerify = (node_id, new_node_id, module_id, product_id, require_id, case_id, step_id, verify_type, version, staffCode) => ({
    type: actionTypes.ADD_CHILD,
    data: {
        node_id,
        node: {
            id: new_node_id,
            stepId: step_id,
            text: "",
            type: verify_type,
            pass: 0,
            caseId: case_id,
            productId: product_id,
            moduleId: module_id,
            requireId: require_id,
            lastEditedBy: staffCode,
            showChildren: true,
            hasEdited: true,
            version: version,
            children: []
        }
    }
});
export const addCaseAppointLabel = (node_id, new_node_id, module_id, product_id, require_id, case_id, label_type, version, staffCode) => ({
    type: actionTypes.ADD_CHILD,
    data: {
        node_id,
        node: {
            id: new_node_id,
            stepId: null,
            text: "",
            type: label_type,
            pass: 0,
            caseId: case_id,
            productId: product_id,
            moduleId: module_id,
            requireId: require_id,
            labels: [],
            lastEditedBy: staffCode,
            showChildren: true,
            hasEdited: true,
            version: version,
            children: []
        }
    }
});
export const addProductModule = (node_id, new_node_id, product_id, require_id, moduleId, moduleName) => ({
    type: actionTypes.ADD_CHILD,
    data: {
        node_id,
        node: {
            id: new_node_id,
            parentId: node_id,
            text: moduleName,
            productId: product_id,
            requireId: require_id,
            moduleId: moduleId,
            type: PRODUCT_MODULE,
            showChildren: true,
            children: []
        }
    }
});
export const addProduct = (node_id, new_node_id, require_id, product_id, product_name) => ({
    type: actionTypes.ADD_CHILD,
    data: {
        node_id,
        node: {
            id: new_node_id,
            parentId: node_id,
            text: product_name,
            requireId: require_id,
            productId: product_id,
            type: PRODUCT,
            showChildren: true,
            children: []
        }
    }
});

export const addSibling = (node_id, parent_id, new_node_id) => ({
    type: actionTypes.ADD_SIBLING,
    data: {
        node_id,
        parent_id,
        node: {
            id: new_node_id,
            text: NEW_NODE_TEXT,
            showChildren: true,
            children: []
        }
    }
});

export const moveNode = (node_id, target_id, parent_id, is_sibling, product_id, module_id, case_id, type, version) => ({
    type: actionTypes.MOVE_NODE,
    data: {
        node_id,
        target_id,
        parent_id,
        is_sibling,
        product_id,
        module_id,
        case_id,
        type,
        version,
        hasEdited: true
    }
});

export const changeText = (node_id, text, staffCode) => ({
    type: actionTypes.CHANGE_TEXT,
    data: {
        node_id,
        node: {
            text,
            lastEditedBy: staffCode,
            hasEdited: true
        }
    }
});

export const changeCasePassStatus = (node_id, pass) => ({
    type: actionTypes.CHANGE_CASE_PASS,
    data: {
        node_id,
        node: {
            pass,
            hasEdited: true
        }
    }
});

export const changeCaseLabel = (node_id, labels) => ({
    type: actionTypes.CHANGE_CASE_LABEL,
    data: {
        node_id,
        node: {
            labels,
            hasEdited: true
        }
    }
});

export const deleteNode = (node_id, parent_id) => ({
    type: actionTypes.DELETE_NODE,
    data: {
        node_id,
        parent_id
    }
});

export const expandAll = node_id => ({
    type: actionTypes.EXPAND_ALL,
    data: {
        node_id
    }
});

export const setMindmap = mindmap => ({
    type: actionTypes.SET_MINDMAP,
    data: {
        mindmap
    }
});

export const copyNode = (node_id, parent_id, new_node_id, node_type) => ({
    type: actionTypes.COPY_NODE,
    data: {
        node_id,
        parent_id,
        node_type,
        node: {
            id: new_node_id
        }
    }
});
