import {getLabelList} from "../../../../../../apis";
import {Select} from "antd";
import React, {useEffect, useRef, useState} from "react";
import useMindmap from "../../../customHooks/useMindmap";

const BaseLabelSelect = ({node_id, labels, value}) => {
    const self = useRef();
    const [label, setLabel] = useState([]);
    const [baseLabel, setBaseLabel] = useState([]);
    const mindmapHook = useMindmap();

    const updateCaseLabel = (label) => {
        let labelNames = "";
        label.map(lab => {
            labelNames = labelNames + lab + ","
        });
        labelNames = labelNames.substring(0, labelNames.length - 1);
        mindmapHook.changeText(node_id, labelNames, window.__data.staffCode);
    };

    const fetchLabel = async value => {
        setBaseLabel([]);
        const res = await getLabelList({type: "base", keyWords: value});
        setBaseLabel(res.data.list);
    };

    const handleChange = value => {
        let newLabels = [];
        let exit = false;
        label.map(lab => {
            if (lab.labelName === value) {
                exit = true;
            }
        });
        if (!exit) {
            baseLabel.map(lab => {
                if (lab.name === value) {
                    newLabels = [...label, {labelId: lab.id, labelName: lab.name, type: lab.type}];
                }
            })
        }
        setLabel(newLabels);
        mindmapHook.changeCaseLabel(node_id, newLabels);
    };

    const handleClose = removedTag => {
        self.current.focus();
        const newLabels = label.filter(lab => lab.labelName !== removedTag);
        setLabel(newLabels);
        mindmapHook.changeCaseLabel(node_id, newLabels);
    };

    useEffect(() => {
        setLabel(labels);
    }, []);

    return (
        <div id={node_id}>
            <Select
            ref={self}
            style={{minWidth: 200, zIndex: 2000, border: 0}}
            size={"small"}
            mode="multiple"
            placeholder="请添加基础标签"
            filterOption={false}
            showArrow={false}
            onSearch={fetchLabel}
            onSelect={handleChange}
            onDeselect={handleClose}
            onBlur={(value) => updateCaseLabel(value)}
            defaultValue={labels.length === 0 ? [] : value.split(",")}
            getPopupContainer={() => document.getElementById(node_id)}
        >
            {
                baseLabel.map(b => (
                        <Select.Option key={b.id} value={b.name}>{b.name}</Select.Option>
                    )
                )
            }
        </Select>
        </div>
    )
};

export default BaseLabelSelect;