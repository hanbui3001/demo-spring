package com.example.Demo_Spring_Boot.repository.specification;

import com.example.Demo_Spring_Boot.model.User;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.jpa.domain.PredicateSpecification;

public class UserSpecification {
    public static PredicateSpecification<User> hasEmail(String email){
        return ((from, criteriaBuilder) -> {
            if(StringUtils.isBlank(email)){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(from.get("email"), email);
        });
    }
    public static PredicateSpecification<User> hasDisplayName(String displayName){
        return ((from, criteriaBuilder) ->  {
            if(StringUtils.isBlank(displayName)){
               return  criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(from.get("displayName")), "%" + displayName.toLowerCase() + "%");

        });
    }
}
