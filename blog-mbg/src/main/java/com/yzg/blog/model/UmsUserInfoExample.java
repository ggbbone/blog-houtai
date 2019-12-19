package com.yzg.blog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UmsUserInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UmsUserInfoExample() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNull() {
            addCriterion("avatar is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("avatar is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarEqualTo(String value) {
            addCriterion("avatar =", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(String value) {
            addCriterion("avatar <>", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(String value) {
            addCriterion("avatar >", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("avatar >=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(String value) {
            addCriterion("avatar <", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(String value) {
            addCriterion("avatar <=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLike(String value) {
            addCriterion("avatar like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(String value) {
            addCriterion("avatar not like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarIn(List<String> values) {
            addCriterion("avatar in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<String> values) {
            addCriterion("avatar not in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(String value1, String value2) {
            addCriterion("avatar between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(String value1, String value2) {
            addCriterion("avatar not between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andBlogAddressIsNull() {
            addCriterion("blog_address is null");
            return (Criteria) this;
        }

        public Criteria andBlogAddressIsNotNull() {
            addCriterion("blog_address is not null");
            return (Criteria) this;
        }

        public Criteria andBlogAddressEqualTo(String value) {
            addCriterion("blog_address =", value, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andBlogAddressNotEqualTo(String value) {
            addCriterion("blog_address <>", value, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andBlogAddressGreaterThan(String value) {
            addCriterion("blog_address >", value, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andBlogAddressGreaterThanOrEqualTo(String value) {
            addCriterion("blog_address >=", value, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andBlogAddressLessThan(String value) {
            addCriterion("blog_address <", value, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andBlogAddressLessThanOrEqualTo(String value) {
            addCriterion("blog_address <=", value, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andBlogAddressLike(String value) {
            addCriterion("blog_address like", value, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andBlogAddressNotLike(String value) {
            addCriterion("blog_address not like", value, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andBlogAddressIn(List<String> values) {
            addCriterion("blog_address in", values, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andBlogAddressNotIn(List<String> values) {
            addCriterion("blog_address not in", values, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andBlogAddressBetween(String value1, String value2) {
            addCriterion("blog_address between", value1, value2, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andBlogAddressNotBetween(String value1, String value2) {
            addCriterion("blog_address not between", value1, value2, "blogAddress");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountIsNull() {
            addCriterion("collected_entries_count is null");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountIsNotNull() {
            addCriterion("collected_entries_count is not null");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountEqualTo(Integer value) {
            addCriterion("collected_entries_count =", value, "collectedEntriesCount");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountNotEqualTo(Integer value) {
            addCriterion("collected_entries_count <>", value, "collectedEntriesCount");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountGreaterThan(Integer value) {
            addCriterion("collected_entries_count >", value, "collectedEntriesCount");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("collected_entries_count >=", value, "collectedEntriesCount");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountLessThan(Integer value) {
            addCriterion("collected_entries_count <", value, "collectedEntriesCount");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountLessThanOrEqualTo(Integer value) {
            addCriterion("collected_entries_count <=", value, "collectedEntriesCount");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountIn(List<Integer> values) {
            addCriterion("collected_entries_count in", values, "collectedEntriesCount");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountNotIn(List<Integer> values) {
            addCriterion("collected_entries_count not in", values, "collectedEntriesCount");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountBetween(Integer value1, Integer value2) {
            addCriterion("collected_entries_count between", value1, value2, "collectedEntriesCount");
            return (Criteria) this;
        }

        public Criteria andCollectedEntriesCountNotBetween(Integer value1, Integer value2) {
            addCriterion("collected_entries_count not between", value1, value2, "collectedEntriesCount");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountIsNull() {
            addCriterion("collections_count is null");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountIsNotNull() {
            addCriterion("collections_count is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountEqualTo(Integer value) {
            addCriterion("collections_count =", value, "collectionsCount");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountNotEqualTo(Integer value) {
            addCriterion("collections_count <>", value, "collectionsCount");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountGreaterThan(Integer value) {
            addCriterion("collections_count >", value, "collectionsCount");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("collections_count >=", value, "collectionsCount");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountLessThan(Integer value) {
            addCriterion("collections_count <", value, "collectionsCount");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountLessThanOrEqualTo(Integer value) {
            addCriterion("collections_count <=", value, "collectionsCount");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountIn(List<Integer> values) {
            addCriterion("collections_count in", values, "collectionsCount");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountNotIn(List<Integer> values) {
            addCriterion("collections_count not in", values, "collectionsCount");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountBetween(Integer value1, Integer value2) {
            addCriterion("collections_count between", value1, value2, "collectionsCount");
            return (Criteria) this;
        }

        public Criteria andCollectionsCountNotBetween(Integer value1, Integer value2) {
            addCriterion("collections_count not between", value1, value2, "collectionsCount");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNull() {
            addCriterion("created_date is null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIsNotNull() {
            addCriterion("created_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedDateEqualTo(Date value) {
            addCriterion("created_date =", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotEqualTo(Date value) {
            addCriterion("created_date <>", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThan(Date value) {
            addCriterion("created_date >", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("created_date >=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThan(Date value) {
            addCriterion("created_date <", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateLessThanOrEqualTo(Date value) {
            addCriterion("created_date <=", value, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateIn(List<Date> values) {
            addCriterion("created_date in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotIn(List<Date> values) {
            addCriterion("created_date not in", values, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateBetween(Date value1, Date value2) {
            addCriterion("created_date between", value1, value2, "createdDate");
            return (Criteria) this;
        }

        public Criteria andCreatedDateNotBetween(Date value1, Date value2) {
            addCriterion("created_date not between", value1, value2, "createdDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateIsNull() {
            addCriterion("updated_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateIsNotNull() {
            addCriterion("updated_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateEqualTo(Date value) {
            addCriterion("updated_date =", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotEqualTo(Date value) {
            addCriterion("updated_date <>", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateGreaterThan(Date value) {
            addCriterion("updated_date >", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_date >=", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateLessThan(Date value) {
            addCriterion("updated_date <", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateLessThanOrEqualTo(Date value) {
            addCriterion("updated_date <=", value, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateIn(List<Date> values) {
            addCriterion("updated_date in", values, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotIn(List<Date> values) {
            addCriterion("updated_date not in", values, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateBetween(Date value1, Date value2) {
            addCriterion("updated_date between", value1, value2, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andUpdatedDateNotBetween(Date value1, Date value2) {
            addCriterion("updated_date not between", value1, value2, "updatedDate");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountIsNull() {
            addCriterion("followees_count is null");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountIsNotNull() {
            addCriterion("followees_count is not null");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountEqualTo(Integer value) {
            addCriterion("followees_count =", value, "followeesCount");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountNotEqualTo(Integer value) {
            addCriterion("followees_count <>", value, "followeesCount");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountGreaterThan(Integer value) {
            addCriterion("followees_count >", value, "followeesCount");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("followees_count >=", value, "followeesCount");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountLessThan(Integer value) {
            addCriterion("followees_count <", value, "followeesCount");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountLessThanOrEqualTo(Integer value) {
            addCriterion("followees_count <=", value, "followeesCount");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountIn(List<Integer> values) {
            addCriterion("followees_count in", values, "followeesCount");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountNotIn(List<Integer> values) {
            addCriterion("followees_count not in", values, "followeesCount");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountBetween(Integer value1, Integer value2) {
            addCriterion("followees_count between", value1, value2, "followeesCount");
            return (Criteria) this;
        }

        public Criteria andFolloweesCountNotBetween(Integer value1, Integer value2) {
            addCriterion("followees_count not between", value1, value2, "followeesCount");
            return (Criteria) this;
        }

        public Criteria andFollowersCountIsNull() {
            addCriterion("followers_count is null");
            return (Criteria) this;
        }

        public Criteria andFollowersCountIsNotNull() {
            addCriterion("followers_count is not null");
            return (Criteria) this;
        }

        public Criteria andFollowersCountEqualTo(Integer value) {
            addCriterion("followers_count =", value, "followersCount");
            return (Criteria) this;
        }

        public Criteria andFollowersCountNotEqualTo(Integer value) {
            addCriterion("followers_count <>", value, "followersCount");
            return (Criteria) this;
        }

        public Criteria andFollowersCountGreaterThan(Integer value) {
            addCriterion("followers_count >", value, "followersCount");
            return (Criteria) this;
        }

        public Criteria andFollowersCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("followers_count >=", value, "followersCount");
            return (Criteria) this;
        }

        public Criteria andFollowersCountLessThan(Integer value) {
            addCriterion("followers_count <", value, "followersCount");
            return (Criteria) this;
        }

        public Criteria andFollowersCountLessThanOrEqualTo(Integer value) {
            addCriterion("followers_count <=", value, "followersCount");
            return (Criteria) this;
        }

        public Criteria andFollowersCountIn(List<Integer> values) {
            addCriterion("followers_count in", values, "followersCount");
            return (Criteria) this;
        }

        public Criteria andFollowersCountNotIn(List<Integer> values) {
            addCriterion("followers_count not in", values, "followersCount");
            return (Criteria) this;
        }

        public Criteria andFollowersCountBetween(Integer value1, Integer value2) {
            addCriterion("followers_count between", value1, value2, "followersCount");
            return (Criteria) this;
        }

        public Criteria andFollowersCountNotBetween(Integer value1, Integer value2) {
            addCriterion("followers_count not between", value1, value2, "followersCount");
            return (Criteria) this;
        }

        public Criteria andOutlineIsNull() {
            addCriterion("outline is null");
            return (Criteria) this;
        }

        public Criteria andOutlineIsNotNull() {
            addCriterion("outline is not null");
            return (Criteria) this;
        }

        public Criteria andOutlineEqualTo(String value) {
            addCriterion("outline =", value, "outline");
            return (Criteria) this;
        }

        public Criteria andOutlineNotEqualTo(String value) {
            addCriterion("outline <>", value, "outline");
            return (Criteria) this;
        }

        public Criteria andOutlineGreaterThan(String value) {
            addCriterion("outline >", value, "outline");
            return (Criteria) this;
        }

        public Criteria andOutlineGreaterThanOrEqualTo(String value) {
            addCriterion("outline >=", value, "outline");
            return (Criteria) this;
        }

        public Criteria andOutlineLessThan(String value) {
            addCriterion("outline <", value, "outline");
            return (Criteria) this;
        }

        public Criteria andOutlineLessThanOrEqualTo(String value) {
            addCriterion("outline <=", value, "outline");
            return (Criteria) this;
        }

        public Criteria andOutlineLike(String value) {
            addCriterion("outline like", value, "outline");
            return (Criteria) this;
        }

        public Criteria andOutlineNotLike(String value) {
            addCriterion("outline not like", value, "outline");
            return (Criteria) this;
        }

        public Criteria andOutlineIn(List<String> values) {
            addCriterion("outline in", values, "outline");
            return (Criteria) this;
        }

        public Criteria andOutlineNotIn(List<String> values) {
            addCriterion("outline not in", values, "outline");
            return (Criteria) this;
        }

        public Criteria andOutlineBetween(String value1, String value2) {
            addCriterion("outline between", value1, value2, "outline");
            return (Criteria) this;
        }

        public Criteria andOutlineNotBetween(String value1, String value2) {
            addCriterion("outline not between", value1, value2, "outline");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateIsNull() {
            addCriterion("last_login_date is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateIsNotNull() {
            addCriterion("last_login_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEqualTo(Date value) {
            addCriterion("last_login_date =", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotEqualTo(Date value) {
            addCriterion("last_login_date <>", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateGreaterThan(Date value) {
            addCriterion("last_login_date >", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("last_login_date >=", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateLessThan(Date value) {
            addCriterion("last_login_date <", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateLessThanOrEqualTo(Date value) {
            addCriterion("last_login_date <=", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateIn(List<Date> values) {
            addCriterion("last_login_date in", values, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotIn(List<Date> values) {
            addCriterion("last_login_date not in", values, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBetween(Date value1, Date value2) {
            addCriterion("last_login_date between", value1, value2, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotBetween(Date value1, Date value2) {
            addCriterion("last_login_date not between", value1, value2, "lastLoginDate");
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