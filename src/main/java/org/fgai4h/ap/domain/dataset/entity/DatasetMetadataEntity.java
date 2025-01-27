package org.fgai4h.ap.domain.dataset.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fgai4h.ap.domain.user.entity.UserEntity;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="metadata")
public class DatasetMetadataEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    
    private UUID metadataUUID;

    private String datCollectionAuthorsAgency;
    private String dataAcceptanceStandardsCompliance;
    private String dataAcquisitionSensingDeviceType;
    private String dataAcquisitionSensingModality;
    private String dataAnnotationProcessTool;
    private String dataAssumptionsConstraintsDependencies;
    private String dataBiasAndVarianceMinimization;
    private String dataCollectionFundingAgency;
    private String dataCollectionPeriod;
    private String dataCollectionPlace;
    private String dataDimension;
    private String dataExclusionCriteria;
    @OneToOne
    private UserEntity dataOwner;
    private String dataPreprocessingTechniques;
    private String dataPrivacyDeIdentificationProtocol;
    private String dataRegistryURL;
    private String dataResolutionPrecision;
    private String dataSafetySecurityProtocol;
    private String dataSampleSize;
    private String dataSamplingRate;
    private String dataSource;
    private String dataType;
    private String dataUpdateVersion;
    private String trainTuningEvalDatasetPartitioningRatio;
    private String version;
}
