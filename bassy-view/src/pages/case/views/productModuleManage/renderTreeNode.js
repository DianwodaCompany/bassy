import {Icon, Tooltip, Tree} from "antd";
import React from "react";

const {TreeNode} = Tree;

const RenderTreeNode = (node, clickEditModule, clickDeleteButton) => {
		node.title = (
			<div>
				<span>{node.name + "(" + node.defender + ")"}</span>&nbsp;&nbsp;
				<Tooltip title={"编辑"}><Icon type="edit"
											onClick={() => clickEditModule(node)}/></Tooltip>&nbsp;&nbsp;
				<Tooltip title={"删除"}><Icon type="delete" onClick={clickDeleteButton}/></Tooltip>
			</div>
		);
		// if (node.children) {
		// 	return <TreeNode title={node.title} key={node.key}
		// 					  style={{fontSize: 20}}>
		// 		 {RenderTreeNode(node.children, clickEditModule, clickDeleteButton)}
		// 	 </TreeNode>
		// }
		return (<TreeNode title={node.title} key={node.key} style={{fontSize: 20}}/>)
};

export default RenderTreeNode;
