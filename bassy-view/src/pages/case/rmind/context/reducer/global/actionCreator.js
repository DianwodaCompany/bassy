import * as actionTypes from './actionTypes';

export const setTitle = title => ({
    type: actionTypes.SET_TITLE,
    data: {
        title
    }
});
export const setCasePassRate = casePassRate => ({
    type: actionTypes.SET_CASE_PASS_RATE,
    data: {
        casePassRate
    }
});

export const setTheme = theme_index => ({
    type: actionTypes.SET_THEME,
    data: {
        theme_index
    }
});
