package com.dianwoda.test.bassy.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssetLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AssetLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAssetIdIsNull() {
            addCriterion("asset_id is null");
            return (Criteria) this;
        }

        public Criteria andAssetIdIsNotNull() {
            addCriterion("asset_id is not null");
            return (Criteria) this;
        }

        public Criteria andAssetIdEqualTo(Integer value) {
            addCriterion("asset_id =", value, "assetId");
            return (Criteria) this;
        }

        public Criteria andAssetIdNotEqualTo(Integer value) {
            addCriterion("asset_id <>", value, "assetId");
            return (Criteria) this;
        }

        public Criteria andAssetIdGreaterThan(Integer value) {
            addCriterion("asset_id >", value, "assetId");
            return (Criteria) this;
        }

        public Criteria andAssetIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("asset_id >=", value, "assetId");
            return (Criteria) this;
        }

        public Criteria andAssetIdLessThan(Integer value) {
            addCriterion("asset_id <", value, "assetId");
            return (Criteria) this;
        }

        public Criteria andAssetIdLessThanOrEqualTo(Integer value) {
            addCriterion("asset_id <=", value, "assetId");
            return (Criteria) this;
        }

        public Criteria andAssetIdIn(List<Integer> values) {
            addCriterion("asset_id in", values, "assetId");
            return (Criteria) this;
        }

        public Criteria andAssetIdNotIn(List<Integer> values) {
            addCriterion("asset_id not in", values, "assetId");
            return (Criteria) this;
        }

        public Criteria andAssetIdBetween(Integer value1, Integer value2) {
            addCriterion("asset_id between", value1, value2, "assetId");
            return (Criteria) this;
        }

        public Criteria andAssetIdNotBetween(Integer value1, Integer value2) {
            addCriterion("asset_id not between", value1, value2, "assetId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andBorrowerIsNull() {
            addCriterion("borrower is null");
            return (Criteria) this;
        }

        public Criteria andBorrowerIsNotNull() {
            addCriterion("borrower is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowerEqualTo(String value) {
            addCriterion("borrower =", value, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowerNotEqualTo(String value) {
            addCriterion("borrower <>", value, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowerGreaterThan(String value) {
            addCriterion("borrower >", value, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowerGreaterThanOrEqualTo(String value) {
            addCriterion("borrower >=", value, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowerLessThan(String value) {
            addCriterion("borrower <", value, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowerLessThanOrEqualTo(String value) {
            addCriterion("borrower <=", value, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowerLike(String value) {
            addCriterion("borrower like", value, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowerNotLike(String value) {
            addCriterion("borrower not like", value, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowerIn(List<String> values) {
            addCriterion("borrower in", values, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowerNotIn(List<String> values) {
            addCriterion("borrower not in", values, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowerBetween(String value1, String value2) {
            addCriterion("borrower between", value1, value2, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowerNotBetween(String value1, String value2) {
            addCriterion("borrower not between", value1, value2, "borrower");
            return (Criteria) this;
        }

        public Criteria andBorrowTmIsNull() {
            addCriterion("borrow_tm is null");
            return (Criteria) this;
        }

        public Criteria andBorrowTmIsNotNull() {
            addCriterion("borrow_tm is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowTmEqualTo(Date value) {
            addCriterion("borrow_tm =", value, "borrowTm");
            return (Criteria) this;
        }

        public Criteria andBorrowTmNotEqualTo(Date value) {
            addCriterion("borrow_tm <>", value, "borrowTm");
            return (Criteria) this;
        }

        public Criteria andBorrowTmGreaterThan(Date value) {
            addCriterion("borrow_tm >", value, "borrowTm");
            return (Criteria) this;
        }

        public Criteria andBorrowTmGreaterThanOrEqualTo(Date value) {
            addCriterion("borrow_tm >=", value, "borrowTm");
            return (Criteria) this;
        }

        public Criteria andBorrowTmLessThan(Date value) {
            addCriterion("borrow_tm <", value, "borrowTm");
            return (Criteria) this;
        }

        public Criteria andBorrowTmLessThanOrEqualTo(Date value) {
            addCriterion("borrow_tm <=", value, "borrowTm");
            return (Criteria) this;
        }

        public Criteria andBorrowTmIn(List<Date> values) {
            addCriterion("borrow_tm in", values, "borrowTm");
            return (Criteria) this;
        }

        public Criteria andBorrowTmNotIn(List<Date> values) {
            addCriterion("borrow_tm not in", values, "borrowTm");
            return (Criteria) this;
        }

        public Criteria andBorrowTmBetween(Date value1, Date value2) {
            addCriterion("borrow_tm between", value1, value2, "borrowTm");
            return (Criteria) this;
        }

        public Criteria andBorrowTmNotBetween(Date value1, Date value2) {
            addCriterion("borrow_tm not between", value1, value2, "borrowTm");
            return (Criteria) this;
        }

        public Criteria andReturnTmIsNull() {
            addCriterion("return_tm is null");
            return (Criteria) this;
        }

        public Criteria andReturnTmIsNotNull() {
            addCriterion("return_tm is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTmEqualTo(Date value) {
            addCriterion("return_tm =", value, "returnTm");
            return (Criteria) this;
        }

        public Criteria andReturnTmNotEqualTo(Date value) {
            addCriterion("return_tm <>", value, "returnTm");
            return (Criteria) this;
        }

        public Criteria andReturnTmGreaterThan(Date value) {
            addCriterion("return_tm >", value, "returnTm");
            return (Criteria) this;
        }

        public Criteria andReturnTmGreaterThanOrEqualTo(Date value) {
            addCriterion("return_tm >=", value, "returnTm");
            return (Criteria) this;
        }

        public Criteria andReturnTmLessThan(Date value) {
            addCriterion("return_tm <", value, "returnTm");
            return (Criteria) this;
        }

        public Criteria andReturnTmLessThanOrEqualTo(Date value) {
            addCriterion("return_tm <=", value, "returnTm");
            return (Criteria) this;
        }

        public Criteria andReturnTmIn(List<Date> values) {
            addCriterion("return_tm in", values, "returnTm");
            return (Criteria) this;
        }

        public Criteria andReturnTmNotIn(List<Date> values) {
            addCriterion("return_tm not in", values, "returnTm");
            return (Criteria) this;
        }

        public Criteria andReturnTmBetween(Date value1, Date value2) {
            addCriterion("return_tm between", value1, value2, "returnTm");
            return (Criteria) this;
        }

        public Criteria andReturnTmNotBetween(Date value1, Date value2) {
            addCriterion("return_tm not between", value1, value2, "returnTm");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifyTmIsNull() {
            addCriterion("modify_tm is null");
            return (Criteria) this;
        }

        public Criteria andModifyTmIsNotNull() {
            addCriterion("modify_tm is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTmEqualTo(Date value) {
            addCriterion("modify_tm =", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotEqualTo(Date value) {
            addCriterion("modify_tm <>", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmGreaterThan(Date value) {
            addCriterion("modify_tm >", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_tm >=", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmLessThan(Date value) {
            addCriterion("modify_tm <", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmLessThanOrEqualTo(Date value) {
            addCriterion("modify_tm <=", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmIn(List<Date> values) {
            addCriterion("modify_tm in", values, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotIn(List<Date> values) {
            addCriterion("modify_tm not in", values, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmBetween(Date value1, Date value2) {
            addCriterion("modify_tm between", value1, value2, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotBetween(Date value1, Date value2) {
            addCriterion("modify_tm not between", value1, value2, "modifyTm");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}