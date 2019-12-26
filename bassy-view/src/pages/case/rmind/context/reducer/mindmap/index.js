import * as actionTypes from './actionTypes';
import defaultMindmap from '../../../statics/defaultMindmap';
import {
    caseNodeCopy,
    changeSubNode,
    deepCopy,
    findNode, moveCaseNode, moveCaseStepNode,
    nodeCopy,
    setShowChildrenTrue, stepNodeCopy
} from '../../../methods/assistFunctions';
import {CASE, CASE_STEP_DESCRIBE} from "../../../statics/refer";

export const defaultValue_mindmap = JSON.parse(localStorage.getItem('mindmap')) || defaultMindmap;

export default (mindmap, action) => {
    switch (action.type) {
        case actionTypes.TOGGLE_CHILDREN: {
            const new_mindmap = deepCopy(mindmap),
                node_found = findNode(new_mindmap, action.data.node_id);
            if (node_found.children.length > 0 && node_found !== new_mindmap) {
                Object.assign(node_found, action.data.node);
            }
            return new_mindmap;
        }
        case actionTypes.ADD_CHILD: {
            const new_mindmap = deepCopy(mindmap),
                node_found = findNode(new_mindmap, action.data.node_id);
            node_found.children.push(action.data.node);
            return new_mindmap;
        }
        case actionTypes.ADD_SIBLING: {
            const new_mindmap = deepCopy(mindmap);
            if (action.data.parent_id) {
                const node_found = findNode(new_mindmap, action.data.parent_id);
                const insert_index = node_found.children.findIndex(node => node.id === action.data.node_id) + 1;
                node_found.children.splice(insert_index, 0, action.data.node);
            }
            return new_mindmap;
        }
        case actionTypes.MOVE_NODE: {
            const {product_id, module_id, case_id, type, version, target_id} = action.data;
            const new_mindmap = deepCopy(mindmap),
                parent = findNode(new_mindmap, action.data.parent_id),
                node_index = parent.children.findIndex(node => node.id === action.data.node_id),
                node_copy = parent.children[node_index];
            //替换移动节点的 product_id, module_id, case_id, parent_id
            if (type === CASE) {
                moveCaseNode(node_copy, product_id, module_id, target_id);
            } else if (type === CASE_STEP_DESCRIBE) {
                moveCaseStepNode(node_copy, product_id, module_id, case_id, version, target_id);
            }
            parent.children.splice(node_index, 1);
            if (action.data.is_sibling) {
                const target_index = parent.children.findIndex(node => node.id === action.data.target_id) + 1 || parent.children.length + 1;
                parent.children.splice(target_index - 1, 0, node_copy);
            } else {
                const target_node = findNode(new_mindmap, action.data.target_id);
                target_node.children.push(node_copy);
            }
            return new_mindmap;
        }
        case actionTypes.CHANGE_TEXT: {
            const new_mindmap = deepCopy(mindmap),
                node_found = findNode(new_mindmap, action.data.node_id);
            Object.assign(node_found, action.data.node);
            return new_mindmap;
        }
        case actionTypes.CHANGE_CASE_PASS: {
            const new_mindmap = deepCopy(mindmap),
                node_found = findNode(new_mindmap, action.data.node_id);
            Object.assign(node_found, action.data.node);
            if (node_found.type === "step_describe") {
                let case_pass = 0;
                const step_node = findNode(new_mindmap, node_found.parentId);
                const all_step_describe_node = step_node.children;
                const pass_step_describe_node = all_step_describe_node.filter(node => node.pass === 1);
                case_pass = all_step_describe_node.length === pass_step_describe_node.length ? 1 : 2;
                const case_node = findNode(new_mindmap, step_node.parentId);
                Object.assign(case_node, {pass: case_pass, hasEdited: true});
            } else {
                changeSubNode(node_found, "step_describe", action.data.node);
            }
            return new_mindmap;
        }
        case actionTypes.CHANGE_CASE_LABEL: {
            const new_mindmap = deepCopy(mindmap),
                node_found = findNode(new_mindmap, action.data.node_id);
            Object.assign(node_found, action.data.node);
            return new_mindmap;
        }
        case actionTypes.DELETE_NODE: {
            const new_mindmap = deepCopy(mindmap);
            if (action.data.parent_id) {
                const node_found = findNode(new_mindmap, action.data.parent_id);
                const delete_index = node_found.children.findIndex(node => node.id === action.data.node_id);
                node_found.children.splice(delete_index, 1);
            }
            return new_mindmap;
        }
        case actionTypes.EXPAND_ALL: {
            const new_mindmap = deepCopy(mindmap);
            const node_found = findNode(new_mindmap, action.data.node_id);
            setShowChildrenTrue(node_found);
            return new_mindmap;
        }
        case actionTypes.SET_MINDMAP: {
            return action.data.mindmap;
        }
        case actionTypes.COPY_NODE: {
            const new_mindmap = deepCopy(mindmap);
            if (action.data.parent_id) {
                const node_found = findNode(new_mindmap, action.data.parent_id);
                const node_target = findNode(new_mindmap, action.data.node_id);
                let node_copy = {};
                if (action.data.node_type === CASE) {
                    node_copy = caseNodeCopy(node_target);
                } else if (action.data.node_type === CASE_STEP_DESCRIBE) {
                    node_copy = stepNodeCopy(node_target);
                } else {
                    node_copy = nodeCopy(node_target);
                }
                const node_new = Object.assign({}, node_copy, action.data.node);
                const delete_index = node_found.children.findIndex(node => node.id === action.data.node_id);
                node_found.children.splice(delete_index + 1, 0, node_new);
            }
            return new_mindmap;
        }
        default:
            return mindmap;
    }
};
