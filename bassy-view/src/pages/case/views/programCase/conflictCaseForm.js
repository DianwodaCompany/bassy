import {Col, Icon, Input, List, Row, Select, Tag} from "antd/lib/index";
import React, {useEffect, useReducer} from 'react';
import reducer, {init} from "../../reducer";
import {getModuleList, getProductList} from "../../../../apis";
import {addAllProduct, updateProductModuleList} from "../../actions";

const ConflictCaseForm = ({type = "start", caseInfo, selectCurrentRowData, selectCurrentCaseData}) => {

	const [state, dispatch] = useReducer(reducer, init);
	const {allProduct, productModuleList, caseType} = state;

	useEffect(() => {
			const fetchAllProduct = async () => {
				const productRes = await getProductList({keyWords: ""});
				dispatch(addAllProduct(productRes.data));
				const moduleRes = await getModuleList({allLayer: true});
				dispatch(updateProductModuleList(moduleRes.data));
			};
			fetchAllProduct();
		}, []
	);

	const ArrowOperation = ({type, field, value}) => {
		return <Col span={3} style={{textAlign: "center"}}>
			<Icon type={type} theme="filled" style={{color: "#1890ff", fontSize: 30}}
				  onClick={() => selectCurrentRowData(field, value)}/>
		</Col>
	};

	const CaseVersion = ({type}) => {
		if (type === "end") {
			return <span><b>你的用例版本：</b><a onClick={() => selectCurrentCaseData(caseInfo)}>全部用我</a></span>;
		}
		if (type === "start") {
			return <span><b>基础用例版本：</b><a onClick={() => selectCurrentCaseData(caseInfo)}>全部用我</a></span>;
		}
		return <span><b>最终版本：</b></span>;
	};

	const CaseRowInfo = ({field, fieldName, expand, value}) => {
		return <Row type="flex" justify={type} align="middle" style={{minHeight: 50}}>
			{
				type === "start" && <ArrowOperation type="left-square" field={field} value={value}/>
			}
			<Col span={4}><span>{fieldName}：</span></Col>
			<Col span={14}>{expand}</Col>
			{
				type === "end" && <ArrowOperation type="right-square" field={field} value={value}/>
			}
		</Row>;
	};

	const borderStyle = type.type === "center" ? {
		border: "2px solid lightgray",
		borderTop: "none",
		borderBottom: "none"
	} : {};

	const ProductView = ({productId}) => {
		return <Select value={productId} style={{width: "100%"}} disabled={true}>
			{
				allProduct.map((p) => (
					<Select.Option key={p.id} value={p.id}>{p.name}</Select.Option>
				))
			}
		</Select>
	};

	const ProductModuleView = ({moduleId}) => {
		return <Select value={moduleId} style={{width: "100%"}} disabled={true}>
			{
				productModuleList.map((m) => (
					<Select.Option key={m.id} value={m.id}>{m.name}</Select.Option>
				))
			}
		</Select>
	};

	const CaseTypeView = ({type}) => {
		return <Select value={type} style={{width: "100%"}} disabled={true}>
			{
				caseType.map((t) => (
					<Select.Option key={t.code} value={t.code}>{t.name}</Select.Option>
				))
			}
		</Select>
	};

	const CaseLabel = ({labels}) => {
		return (
			labels.map((label, num) => {
					const isLongLab = label.labelName.length > 5;
					return <Tag key={num + 1}>{isLongLab ? label.labelName.slice(0, 5) + '...' : label.labelName}</Tag>
				}
			)
		)
	};
	const CaseStep = ({steps}) => {
		return <List size={"small"}
					 bordered
					 dataSource={steps}
					 renderItem={item => <List.Item>
						 【步骤】：{item.desc}<br/>
						 【DB】：{item.expectDb}<br/>
						 【UI】：{item.expectUi}<br/>
						 【响应】：{item.expectResponse}<br/>
						 【其他】：{item.expectOther}
					 </List.Item>}
		/>
	};

	const CasePass = ({pass}) => {
		if (pass !== undefined && pass === 0) {
			return <Icon type="question-circle" theme="twoTone" twoToneColor="#eda938"/>;
		}
		if (pass !== undefined && pass === 1) {
			return <Icon type="check-circle" theme="twoTone" twoToneColor="#52c41a"/>;
		}
		if (pass !== undefined && pass === 2) {
			return <Icon type="close-circle" theme="twoTone" twoToneColor="#ff4c26"/>;
		}
		return null;
	};

	return <div style={borderStyle}>
		<CaseVersion type={type}/>
		<CaseRowInfo field={"title"} fieldName="用例标题" value={caseInfo.title}
					 expand={<Input value={caseInfo.title} disabled={true}/>}/>
		<CaseRowInfo field={"product"} fieldName="所属产品" value={caseInfo.product}
					 expand={<ProductView productId={caseInfo.product}/>}/>
		<CaseRowInfo field={"module"} fieldName="所属模块" value={caseInfo.module}
					 expand={<ProductModuleView moduleId={caseInfo.module} disabled={true}/>}/>
		<CaseRowInfo field={"type"} fieldName="用例类型" value={caseInfo.type}
					 expand={<CaseTypeView type={caseInfo.type} disabled={true}/>}/>
		<CaseRowInfo field={"pri"} fieldName="优先级" value={caseInfo.pri}
					 expand={<Input value={caseInfo.pri} disabled={true}/>}/>
		<CaseRowInfo field={"precondition"} fieldName="前置条件" value={caseInfo.precondition}
					 expand={<Input.TextArea value={caseInfo.precondition} disabled={true}/>}/>
		<CaseRowInfo field={"step"} fieldName="步骤" value={caseInfo.step}
					 expand={<CaseStep steps={caseInfo.step}/>}/>
		<CaseRowInfo field={"label"} fieldName="标签" value={caseInfo.label}
					 expand={<CaseLabel labels={caseInfo.label}/>}/>
		{/*<CaseRowInfo field={"pass"} fieldName="通过" value={caseInfo.pass}*/}
		{/*			 expand={<CasePass pass={caseInfo.pass}/>}/>*/}
	</div>
};

export default ConflictCaseForm;
