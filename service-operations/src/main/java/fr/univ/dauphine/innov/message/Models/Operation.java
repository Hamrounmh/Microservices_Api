package fr.univ.dauphine.innov.message.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Operation {
    private String MonnaieSource;
    private String MonnaieDestination;
    private double taux;
    private double montant;
    private Long id_transaction;

    public Operation() {

    }

    public void setId_transaction(Long id_transaction) {
        this.id_transaction = id_transaction;
    }

    public Operation(double taux, Long id_transaction) {
        this.taux = taux;
        this.id_transaction = id_transaction;
    }

    public String getMonnaieSource() {
        return MonnaieSource;
    }

    public void setMonnaieSource(String monnaieSource) {
        MonnaieSource = monnaieSource;
    }

    public String getMonnaieDestination() {
        return MonnaieDestination;
    }

    public void setMonnaieDestination(String monnaieDestination) {
        MonnaieDestination = monnaieDestination;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    @Id
    public Long getId_transaction() {
        return id_transaction;
    }
}
