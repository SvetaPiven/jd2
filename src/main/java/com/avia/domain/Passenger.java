package com.avia.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Passenger {

    private Long idPass;
    private String fullName;
    private String personalId;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;

    public Passenger() {
    }

    public Passenger(Long idPass, String fullName, String personalId, Timestamp created, Timestamp changed, Boolean isDeleted) {
        this.idPass = idPass;
        this.fullName = fullName;
        this.personalId = personalId;
        this.created = created;
        this.changed = changed;
        this.isDeleted = isDeleted;
    }

    public void setIdPass(Long idPass) {
        this.idPass = idPass;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Long getIdPass() {
        return idPass;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(idPass, passenger.idPass) && Objects.equals(fullName, passenger.fullName) && Objects.equals(personalId, passenger.personalId) && Objects.equals(created, passenger.created) && Objects.equals(changed, passenger.changed) && Objects.equals(isDeleted, passenger.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPass, fullName, personalId, created, changed, isDeleted);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "idPass=" + idPass +
                ", fullName='" + fullName + '\'' +
                ", personalId='" + personalId + '\'' +
                ", created=" + created +
                ", changed=" + changed +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
