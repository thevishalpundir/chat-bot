package com.pilog.url.redirector.model;


import java.util.Date;
import javax.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "API_DETAILS")
public class ApiDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_details_sequence")
    @SequenceGenerator(
            name = "api_details_sequence",
            sequenceName = "API_DETAILS_SEQ",
            allocationSize = 1)
    @Column(name = "API_ID")
    private Long id;

    @Column(name = "API_NAME")
    private String apiName;

    @Column(name = "QUERY_TYPE")
    private String queryType;

    @Column(name = "API_URL")
    private String apiUrl;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;
}
