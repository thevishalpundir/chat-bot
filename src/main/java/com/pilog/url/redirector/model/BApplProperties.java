package com.pilog.url.redirector.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "B_APPL_PROPERTIES")
@Data
public class BApplProperties {
    @Id
    @Column(name = "AUDIT_ID", length = 100)
    private String auditId;

    @Column(name = "KEYNAME", length = 50)
    private String keyName;

    @Column(name = "PROCESS_VALUE", length = 4000)
    private String processValue;

    @Column(name = "HEADER", length = 1000)
    private String header;

    @Column(name = "COMP_ID", length = 20)
    private String compId;
}
