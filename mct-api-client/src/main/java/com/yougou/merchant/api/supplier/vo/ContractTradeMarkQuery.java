/*
 * 类名 com.yougou.merchant.api.supplier.vo.ContractTradeMarkQuery
 * 
 * 日期  Tue Jun 23 13:25:28 CST 2015
 * 
 * 版权声明Copyright (C) 2013 YouGou Information Technology Co.,Ltd 
 * All Rights Reserved. 
 * 
 * The software for the YouGou technology development, without the 
 * company's written consent, and any other individuals and 
 * organizations shall not be used, Copying, Modify or distribute 
 * the software.
 * 
 */
package com.yougou.merchant.api.supplier.vo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class ContractTradeMarkQuery {
    /**
     * This field was generated by YouGou MyBatis Generator.
     * This field corresponds to the database table tbl_sp_supplier_contract_trademark
     *
     * @yougougenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by YouGou MyBatis Generator.
     * This field corresponds to the database table tbl_sp_supplier_contract_trademark
     *
     * @yougougenerated
     */
    protected String oderProperty;

    /**
     * This field was generated by YouGou MyBatis Generator.
     * This field corresponds to the database table tbl_sp_supplier_contract_trademark
     *
     * @yougougenerated
     */
    protected String oderType;

    /**
     * This field was generated by YouGou MyBatis Generator.
     * This field corresponds to the database table tbl_sp_supplier_contract_trademark
     *
     * @yougougenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by YouGou MyBatis Generator.
     * This field corresponds to the database table tbl_sp_supplier_contract_trademark
     *
     * @yougougenerated
     */
    protected List<Criteria> oredCriteria;

    public ContractTradeMarkQuery() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        if (StringUtils.isEmpty(orderByClause) && StringUtils.isNotEmpty(this.getOderProperty()) && StringUtils.isNotEmpty(this.getOderType())){
            return MessageFormat.format("{0} {1}", this.getOderProperty(),this.getOderType());
        }
        return orderByClause;
    }

    public void setOderProperty(String oderProperty) {
        this.oderProperty = oderProperty;
    }

    public String getOderProperty() {
        return oderProperty;
    }

    public void setOderType(String oderType) {
        this.oderType = oderType;
    }

    public String getOderType() {
        return oderType;
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

    /**
     * This class was generated by YouGou MyBatis Generator.
     * This class corresponds to the database table tbl_sp_supplier_contract_trademark
     *
     * @yougougenerated
     */
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

        public Criteria setLimitValue(int value1, int value2) {
            if (value2 == 0 )
              throw new RuntimeException("value2 is 0");
            addCriterion("limit",value1,value2,"null");
            return (Criteria) this;
        }

        public Criteria getLimitValue() {
            return (Criteria)criteria;
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
            criteria.add(new Criterion(condition, value, property));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andContractIdIsNull() {
            addCriterion("contract_id is null");
            return (Criteria) this;
        }

        public Criteria andContractIdIsNotNull() {
            addCriterion("contract_id is not null");
            return (Criteria) this;
        }

        public Criteria andContractIdEqualTo(String value) {
            addCriterion("contract_id =", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotEqualTo(String value) {
            addCriterion("contract_id <>", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdGreaterThan(String value) {
            addCriterion("contract_id >", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdGreaterThanOrEqualTo(String value) {
            addCriterion("contract_id >=", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdLessThan(String value) {
            addCriterion("contract_id <", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdLessThanOrEqualTo(String value) {
            addCriterion("contract_id <=", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdLike(String value) {
            addCriterion("contract_id like", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotLike(String value) {
            addCriterion("contract_id not like", value, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdIn(List<String> values) {
            addCriterion("contract_id in", values, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotIn(List<String> values) {
            addCriterion("contract_id not in", values, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdBetween(String value1, String value2) {
            addCriterion("contract_id between", value1, value2, "contractId");
            return (Criteria) this;
        }

        public Criteria andContractIdNotBetween(String value1, String value2) {
            addCriterion("contract_id not between", value1, value2, "contractId");
            return (Criteria) this;
        }

        public Criteria andTrademarkIsNull() {
            addCriterion("trademark is null");
            return (Criteria) this;
        }

        public Criteria andTrademarkIsNotNull() {
            addCriterion("trademark is not null");
            return (Criteria) this;
        }

        public Criteria andTrademarkEqualTo(String value) {
            addCriterion("trademark =", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkNotEqualTo(String value) {
            addCriterion("trademark <>", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkGreaterThan(String value) {
            addCriterion("trademark >", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkGreaterThanOrEqualTo(String value) {
            addCriterion("trademark >=", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkLessThan(String value) {
            addCriterion("trademark <", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkLessThanOrEqualTo(String value) {
            addCriterion("trademark <=", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkLike(String value) {
            addCriterion("trademark like", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkNotLike(String value) {
            addCriterion("trademark not like", value, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkIn(List<String> values) {
            addCriterion("trademark in", values, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkNotIn(List<String> values) {
            addCriterion("trademark not in", values, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkBetween(String value1, String value2) {
            addCriterion("trademark between", value1, value2, "trademark");
            return (Criteria) this;
        }

        public Criteria andTrademarkNotBetween(String value1, String value2) {
            addCriterion("trademark not between", value1, value2, "trademark");
            return (Criteria) this;
        }

        public Criteria andAuthorizerIsNull() {
            addCriterion("authorizer is null");
            return (Criteria) this;
        }

        public Criteria andAuthorizerIsNotNull() {
            addCriterion("authorizer is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorizerEqualTo(String value) {
            addCriterion("authorizer =", value, "authorizer");
            return (Criteria) this;
        }

        public Criteria andAuthorizerNotEqualTo(String value) {
            addCriterion("authorizer <>", value, "authorizer");
            return (Criteria) this;
        }

        public Criteria andAuthorizerGreaterThan(String value) {
            addCriterion("authorizer >", value, "authorizer");
            return (Criteria) this;
        }

        public Criteria andAuthorizerGreaterThanOrEqualTo(String value) {
            addCriterion("authorizer >=", value, "authorizer");
            return (Criteria) this;
        }

        public Criteria andAuthorizerLessThan(String value) {
            addCriterion("authorizer <", value, "authorizer");
            return (Criteria) this;
        }

        public Criteria andAuthorizerLessThanOrEqualTo(String value) {
            addCriterion("authorizer <=", value, "authorizer");
            return (Criteria) this;
        }

        public Criteria andAuthorizerLike(String value) {
            addCriterion("authorizer like", value, "authorizer");
            return (Criteria) this;
        }

        public Criteria andAuthorizerNotLike(String value) {
            addCriterion("authorizer not like", value, "authorizer");
            return (Criteria) this;
        }

        public Criteria andAuthorizerIn(List<String> values) {
            addCriterion("authorizer in", values, "authorizer");
            return (Criteria) this;
        }

        public Criteria andAuthorizerNotIn(List<String> values) {
            addCriterion("authorizer not in", values, "authorizer");
            return (Criteria) this;
        }

        public Criteria andAuthorizerBetween(String value1, String value2) {
            addCriterion("authorizer between", value1, value2, "authorizer");
            return (Criteria) this;
        }

        public Criteria andAuthorizerNotBetween(String value1, String value2) {
            addCriterion("authorizer not between", value1, value2, "authorizer");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkIsNull() {
            addCriterion("registered_trademark is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkIsNotNull() {
            addCriterion("registered_trademark is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkEqualTo(String value) {
            addCriterion("registered_trademark =", value, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkNotEqualTo(String value) {
            addCriterion("registered_trademark <>", value, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkGreaterThan(String value) {
            addCriterion("registered_trademark >", value, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkGreaterThanOrEqualTo(String value) {
            addCriterion("registered_trademark >=", value, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkLessThan(String value) {
            addCriterion("registered_trademark <", value, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkLessThanOrEqualTo(String value) {
            addCriterion("registered_trademark <=", value, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkLike(String value) {
            addCriterion("registered_trademark like", value, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkNotLike(String value) {
            addCriterion("registered_trademark not like", value, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkIn(List<String> values) {
            addCriterion("registered_trademark in", values, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkNotIn(List<String> values) {
            addCriterion("registered_trademark not in", values, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkBetween(String value1, String value2) {
            addCriterion("registered_trademark between", value1, value2, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredTrademarkNotBetween(String value1, String value2) {
            addCriterion("registered_trademark not between", value1, value2, "registeredTrademark");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateIsNull() {
            addCriterion("registered_start_date is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateIsNotNull() {
            addCriterion("registered_start_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateEqualTo(Date value) {
            addCriterionForJDBCDate("registered_start_date =", value, "registeredStartDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("registered_start_date <>", value, "registeredStartDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateGreaterThan(Date value) {
            addCriterionForJDBCDate("registered_start_date >", value, "registeredStartDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registered_start_date >=", value, "registeredStartDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateLessThan(Date value) {
            addCriterionForJDBCDate("registered_start_date <", value, "registeredStartDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registered_start_date <=", value, "registeredStartDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateIn(List<Date> values) {
            addCriterionForJDBCDate("registered_start_date in", values, "registeredStartDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("registered_start_date not in", values, "registeredStartDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registered_start_date between", value1, value2, "registeredStartDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredStartDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registered_start_date not between", value1, value2, "registeredStartDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateIsNull() {
            addCriterion("registered_end_date is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateIsNotNull() {
            addCriterion("registered_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("registered_end_date =", value, "registeredEndDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("registered_end_date <>", value, "registeredEndDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("registered_end_date >", value, "registeredEndDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registered_end_date >=", value, "registeredEndDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateLessThan(Date value) {
            addCriterionForJDBCDate("registered_end_date <", value, "registeredEndDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("registered_end_date <=", value, "registeredEndDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("registered_end_date in", values, "registeredEndDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("registered_end_date not in", values, "registeredEndDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registered_end_date between", value1, value2, "registeredEndDate");
            return (Criteria) this;
        }

        public Criteria andRegisteredEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("registered_end_date not between", value1, value2, "registeredEndDate");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by YouGou MyBatis Generator.
     * This class corresponds to the database table tbl_sp_supplier_contract_trademark
     *
     * @yougougenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by YouGou MyBatis Generator.
     * This class corresponds to the database table tbl_sp_supplier_contract_trademark
     *
     * @yougougenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean limitValue;

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

        public boolean isLimitValue() {
            return limitValue;
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
            if("limit".equals(condition))
              this.limitValue = true;
            else
              this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}