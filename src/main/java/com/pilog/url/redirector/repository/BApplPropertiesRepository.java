package com.pilog.url.redirector.repository;


import com.pilog.url.redirector.model.BApplProperties;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BApplPropertiesRepository extends JpaRepository<BApplProperties, String> {

    Optional<BApplProperties> findByKeyName(String keyName);
}
