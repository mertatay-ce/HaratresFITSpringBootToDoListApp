package com.haratres_fit.springboot_todolistapp.repository.specifications.todo;


import com.haratres_fit.springboot_todolistapp.model.entity.TodoItem;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isAllBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.logging.log4j.util.Strings.isBlank;

public class TodoSpecification implements Specification<TodoItem> {

    @Nullable
    private String state;
    @Nullable
    private String title;

    private LocalDateTime created_date;
    private final Sort.Direction sort_type;
    private Logger logger = LoggerFactory.getLogger(TodoSpecification.class);


    public TodoSpecification(String state, String title, LocalDateTime created_date, Sort.Direction sortType) {
        this.state = state;
        this.title = title;
        this.created_date = created_date;
        this.sort_type = sortType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }


    @Override
    public Specification<TodoItem> and(Specification<TodoItem> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<TodoItem> or(Specification<TodoItem> other) {
        return Specification.super.or(other);
    }

    @Override
    public Predicate toPredicate(Root<TodoItem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate statePredicate = statePredicate(root,criteriaBuilder);
        Predicate titlePredicate = titlePredicate(root,criteriaBuilder);


        if (nonNull(statePredicate) || nonNull(titlePredicate)) {
            query.distinct(true);
        }

        Predicate searchPred = null;
        Order createdDateOrder = createdDateOrder(root,criteriaBuilder,sort_type.name());


        logger.info("Title boş mu: {}",isBlank(title));
        logger.info("State boş mu: {}", isBlank(state));


        if (isBlank(title) & isNotBlank(state)){
            searchPred = statePredicate;
        }else if (isBlank(state) & isNotBlank(title)){
            searchPred = titlePredicate;
        }else if (StringUtils.isNotBlank(title) ||StringUtils.isNotBlank(state)) {
            searchPred = criteriaBuilder.and(titlePredicate, statePredicate);
        }


        List<Predicate> predicates = new ArrayList<>();

        ofNullable(searchPred).ifPresent(predicates::add);



        return query.orderBy(createdDateOrder).where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]))).getRestriction();
    }

    private Order createdDateOrder(Root<TodoItem> root, CriteriaBuilder criteriaBuilder,String sorting_type) {
        if (sorting_type.equals(Sort.Direction.ASC.name())){
            return criteriaBuilder.asc(root.get("createdDate"));
        }else{
            return criteriaBuilder.desc(root.get("createdDate"));
        }
    }

    private Predicate equals(CriteriaBuilder criteriaBuilder, Path<Object> state, String state1) {
        return criteriaBuilder.equal(state,state1);
    }

    private Predicate like(CriteriaBuilder criteriaBuilder, Path<String> field, String searchTerm) {
        return criteriaBuilder.like(criteriaBuilder.lower(field),"%" + searchTerm.toLowerCase() + "%");
    }
    private Predicate between(CriteriaBuilder cb, Path<Integer> field, int min, int max) {
        return cb.between(field, min, max);
    }



    private Predicate titlePredicate(Root<TodoItem> root,CriteriaBuilder cb){
        if (isAllBlank(title)) {
            return null;
        }

        return like(cb, root.get("title"), title);
    }

    private Predicate statePredicate(Root<TodoItem> root,CriteriaBuilder cb){
        if (isNull(state)) {
            return null;
        }
        return ofNullable(state)
                .map(item -> equals(cb,root.get("state"),state)).orElse(null);
    }
}
