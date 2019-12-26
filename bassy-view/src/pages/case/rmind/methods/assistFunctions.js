import md5 from "md5";

export const handlePropagation = event => {
    event.stopPropagation();
};

export const deepCopy = input => { // 简单的递归深拷贝，只考虑 context 中 state 的复制，因此没有处理 function 等
    if (input instanceof Object) {
        if (Array.isArray(input)) {
            return input.map(deepCopy);
        }
        let output = {};
        Object.entries(input).forEach(([key, value]) => {
            output[key] = deepCopy(value);
        });
        return output;
    }
    return input;

};

export const nodeCopy = (input) => { // 简单的递归深拷贝，只考虑 context 中 state 的复制，因此没有处理 function 等
    if (input instanceof Object) {
        if (Array.isArray(input)) {
            return input.map(deepCopy);
        }
        let output = {};
        Object.entries(input).forEach(([key, value]) => {
            output[key] = deepCopy(value);
        });
        return output;
    }
    return input;
};

export const caseNodeCopy = (input) => { // 简单的递归深拷贝，只考虑 context 中 state 的复制，因此没有处理 function 等
    if (input instanceof Object) {
        if (Array.isArray(input)) {
            return input.map(caseNodeCopy);
        }
        let output = {};
        Object.entries(input).forEach(([key, value]) => {
            if (key === "id") {
                output[key] = md5('' + Date.now() + Math.random());
                return;
            }
            if (key === "caseId" || key === "stepId") {
                output[key] = null;
                return;
            }
            output[key] = caseNodeCopy(value);
        });
        return output;
    }
    return input;
};

export const stepNodeCopy = (input) => { // 简单的递归深拷贝，只考虑 context 中 state 的复制，因此没有处理 function 等
    if (input instanceof Object) {
        if (Array.isArray(input)) {
            return input.map(stepNodeCopy);
        }
        let output = {};
        Object.entries(input).forEach(([key, value]) => {
            if (key === "id") {
                output[key] = md5('' + Date.now() + Math.random());
                return;
            }
            if (key === "stepId") {
                output[key] = null;
                return;
            }
            output[key] = stepNodeCopy(value);
        });
        return output;
    }
    return input;
};

export const findNode = (node, search_id) => {
    if (node.id === search_id) {
        return node;
    }
    return node.children.map(child => findNode(child, search_id)).find(item => item);
};

export const changeSubNode = (node, sub_node_type, info) => {
    if (node.type === sub_node_type) {
        Object.assign(node, info);
    }
    node.children.map(child => changeSubNode(child, sub_node_type, info));
};

export const setShowChildrenTrue = node => {
    node.showChildren = true;
    node.children.forEach(setShowChildrenTrue);
};

export const downloadFile = (url, filename) => {
    const link = document.createElement('a');
    link.href = url;
    link.download = filename;
    link.click();
};

export const moveCaseNode = (node, productId, moduleId, target_id) => {
    Object.assign(node, {parentId: target_id, productId, moduleId, hasEdited: true});
    node.children.map(n => moveCaseNode(n, productId, moduleId));
};

export const moveCaseStepNode = (node, productId, moduleId, caseId, version, target_id) => {
    Object.assign(node, {parentId: target_id, productId, moduleId, caseId, version, hasEdited: true});
    node.children.map(n => moveCaseStepNode(n, productId, moduleId, caseId, version));
};

