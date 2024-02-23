package com.yi.spring.repository;


import com.yi.spring.entity.Dinning;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class DinningSpecifications {

    public static Specification<Dinning> hasId(Long id) {
        return (root, query, builder) -> id == null ? null : builder.equal(root.get("id"), id);
    }

    public static Specification<Dinning> hasName(String name) {
        return (root, query, builder) -> name == null ? null : builder.equal(root.get("name"), name);
    }

    public static Specification<Dinning> hasAddresses(List<String> addresses) {
        return (root, query, builder) -> addresses == null ? null : root.get("address").in(addresses);
    }

    public static Specification<Dinning> hasPhone(String phone) {
        return (root, query, builder) -> phone == null ? null : builder.equal(root.get("phone"), phone);
    }
}



class YourService {

    private final DinningRepository dinningRepository;

    public YourService(DinningRepository dinningRepository) {
        this.dinningRepository = dinningRepository;
    }

    public List<Dinning> findByConditions(Long id, String name, List<String> addresses, String phone) {
        Specification<Dinning> spec = Specification
                .where(DinningSpecifications.hasId(id))
                .and(DinningSpecifications.hasName(name))
                .and(DinningSpecifications.hasAddresses(addresses))
                .and(DinningSpecifications.hasPhone(phone));

        return dinningRepository.findAll(spec);
    }
}