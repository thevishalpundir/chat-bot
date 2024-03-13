package com.pilog.url.redirector.repository;


import com.pilog.url.redirector.model.ApiDetails;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiDetailsRepository extends JpaRepository<ApiDetails, Long> {

    public Optional<ApiDetails> findByApiName(String apiName);
}
