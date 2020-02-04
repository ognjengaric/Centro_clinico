package hermanos.Centro.Clinico.dto;

import hermanos.Centro.Clinico.model.Medicine;
import hermanos.Centro.Clinico.model.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportDTO {

    private String description;

    private String diagnosisCode;
    private String diagnosisDescription;

    private boolean prescriptionCertified;
    private String certifiedByName;
    private List<MedicineDTO> medicines = new ArrayList<>();

    public ReportDTO(){}

    public ReportDTO(Report report){
        this.description = report.getDescription();
        this.diagnosisCode = report.getDiagnosis().getCode();
        this.diagnosisDescription = report.getDiagnosis().getDescription();

        this.prescriptionCertified = report.getPrescription().isCertified();
        this.certifiedByName = report.getPrescription().getByWho().getName();

        for(Medicine medicine: report.getPrescription().getMedicine_list()){
            this.medicines.add(new MedicineDTO(medicine));
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getDiagnosisDescription() {
        return diagnosisDescription;
    }

    public void setDiagnosisDescription(String diagnosisDescription) {
        this.diagnosisDescription = diagnosisDescription;
    }

    public boolean isPrescriptionCertified() {
        return prescriptionCertified;
    }

    public void setPrescriptionCertified(boolean prescriptionCertified) {
        this.prescriptionCertified = prescriptionCertified;
    }

    public String getCertifiedByName() {
        return certifiedByName;
    }

    public void setCertifiedByName(String certifiedByName) {
        this.certifiedByName = certifiedByName;
    }

    public List<MedicineDTO> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<MedicineDTO> medicines) {
        this.medicines = medicines;
    }
}
