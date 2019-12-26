import moment from "moment/moment";

const taskInfo = [{
	id: "", taskCode: "", requireId: "", requireRelate: ""
}];

export function getDictValue(dictInfo, type, taskCode) {
	for (let i in dictInfo) {
		const dict = dictInfo[i];
		if (dict.dictGroup === type && dict.dictCode === taskCode) {
			return dict.dictValue
		}
	}
}

export function isAutoTestTask(taskCode) {
	return taskCode.indexOf("AUTO_TEST") > -1;
}

const programInfo = [{
	id: "", programName: ""
}];

export function getProgramName(info = programInfo, programId) {
	for (let i in info) {
		const program = info[i];
		if (program.id === programId) {
			return program.programName
		}
	}
}

export function colSort(a, b, type) {
	switch (type) {
		case "number": {
			let numA = a == null ? 0 : a;
			let numB = b == null ? 0 : b;
			return numA - numB;
		}
		case "date": {
			let dateA = moment(a);
			let dateB = moment(b);
			if (dateA.isBefore(dateB)) {
				return -1
			}
			if (dateA.isAfter(dateB)) {
				return 1
			}
			return 0;
		}
		case "string": {
			if (a > b) {
				return 1
			}
			if (a < b) {
				return -1
			}
			return 0;
		}
	}
}

export function haveAdminAuth(authResourceCodeList) {
	return authResourceCodeList.includes("BASSY_ADMIN");
}

export function haveCommonAuth(authResourceCodeList) {
	return authResourceCodeList.includes("BASSY_COMMON");
}

export function haveLeaderAuth(authResourceCodeList) {
	return authResourceCodeList.includes("BASSY_LEADER");
}

export function haveGuestAuth(authResourceCodeList) {
	return authResourceCodeList.includes("BASSY_GUEST");
}


export function getFailReasonName(failReason = [{code: "", name: ""}], failReasonCode) {
	for (let i in failReason) {
		if (failReason[i].code === failReasonCode) {
			return failReason[i].name;
		}
	}
}

export function getabnormalReasonName(abnormalReasonType = [{
	reasonTeamCode: 0,
	reasonTeamName: "",
	reasonTypeCode: 0,
	reasonTypeName: ""
}], resonTypeCode) {
	for (let i in abnormalReasonType) {
		if (abnormalReasonType[i].reasonTypeCode === resonTypeCode) {
			return abnormalReasonType[i];
		}
	}
}

export function parseStaffName(name) {
	if (name) {
		return /\((\d+)\)/.exec(name)[1];
	}
	return name
}

