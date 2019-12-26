import {Select} from "antd";
import React from "react";
import useMindmap from "../../../customHooks/useMindmap";

const SystemLabelSelect = ({node_id,labels, value}) => {
    const mindmapHook = useMindmap();
    const handleChangeSystemLabel = label => {
        updateCaseLabel(label);
    };

    const updateCaseLabel = (label) => {
        let labels = "";
        label.map(lab => {
            labels = labels + lab + ","
        });
        labels = labels.substring(0, labels.length - 1);
        mindmapHook.changeText(node_id, labels, window.__data.staffCode);
    };

    return (
        <div id={node_id}>
            <Select
            style={{minWidth: 200, zIndex: 2000, border: 0}}
            size={"small"}
            mode="tags"
            placeholder="请添加系统标签"
            filterOption={false}
            showArrow={false}
            onChange={handleChangeSystemLabel}
            onBlur={(value) => updateCaseLabel(value)}
            defaultValue={value !== "" ? value.split(",") : []}
            getPopupContainer={() => document.getElementById(node_id)}
            >
        </Select>
        </div>
    )
};

export default SystemLabelSelect;