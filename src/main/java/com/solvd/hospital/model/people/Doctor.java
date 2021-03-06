package com.solvd.hospital.model.people;

import com.solvd.hospital.model.equipmentAndMachinery.Equipment;
import com.solvd.hospital.model.exception.ItemNotFoundException;
import com.solvd.hospital.model.exception.PersonNotInDatabaseException;
import com.solvd.hospital.model.hospital.Hospital;
import com.solvd.hospital.model.medicine.Medicine;
import com.solvd.hospital.model.room.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Doctor extends Employee implements IPrescribe, IAskFor, IGive {

    private static final Logger log = LogManager.getLogger(Doctor.class);


    private String type;
    private Equipment equipmentInUse;


    public Doctor() {
        super();
        this.type = "no specialization";
    }

    public Doctor(int ID) {
        super(ID);
        this.type = "no specialization";
    }


    public Doctor(int ID, String type) {
        super(ID);
        this.type = type;
    }


    public Doctor(int ID, String type, Equipment equipmentInUse) {
        super(ID);
        this.type = type;
        this.equipmentInUse = equipmentInUse;
    }

    public Doctor(String name, String surname, int ID, Hospital assignedHospital) {
        super(name, surname, ID, assignedHospital);
    }

    public Doctor(String name, String surname, int ID, Hospital assignedHospital, Room locationRoom, String type, Equipment equipmentInUse) {
        super(name, surname, ID, assignedHospital, locationRoom);
        this.type = type;
        this.equipmentInUse = equipmentInUse;
    }


    public void performSurgery() {
        log.info("Surgery finished");
    }


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;

    }

    public Equipment getEquipmentInUse() {
        return equipmentInUse;
    }

    public void setEquipmentInUse(Equipment equipmentInUse) {
        this.equipmentInUse = equipmentInUse;
    }


    public void treatPatient(Patient patient) {

        patient.setIllness("none");
        patient.getMedicalHistory().add("Cured");

    }

    public void diagnosePatient(Patient patient) {
        Scanner input = new Scanner(System.in);
        log.info("What is the patients illness?\n");
        String illness = input.nextLine();
        patient.setIllness(illness);
        patient.getMedicalHistory().add(illness);
        log.info("Patient 3 has been diagnosed with: " + illness);

    }

    @Override
    public void askForItem(Employee employee) {


        if ((employee instanceof Nurse) && (((Nurse) employee).getEquipmentInUse() != null)) {
            this.setEquipmentInUse(((Nurse) employee).give());
        } else {
            throw new ItemNotFoundException("This employee has no items");
        }

    }


    public List<String> askForMedicalHistory(Employee employee, Patient patient) {

        if (employee instanceof Receptionist) {

            if (((Receptionist) employee).getPatientMedicalHistory(patient) != null) {
                return ((Receptionist) employee).getPatientMedicalHistory(patient);
            } else {
                throw new PersonNotInDatabaseException("This patient is not registered in the database");
            }
        }
        return null;
    }


    public Equipment give() {
        if (this.equipmentInUse == null) {
            throw new ItemNotFoundException("This employee has no items right now");
        }
        Equipment equip = this.equipmentInUse;
        this.setEquipmentInUse(null);
        return equip;
    }

    @Override
    public void prescribe(Medicine medicine, Patient patient) {
        patient.setPrescribedMedicine(medicine);
    }

    @Override
    public String toString() {
        if (this.equipmentInUse != null) {
            return super.toString() +
                    ",  Type='" + type + '\'' + ",  Using: " + equipmentInUse.getType() +
                    "\n";
        } else {
            return super.toString() + ",  Type='" + type + '\'' + ",  Using: Nothing " + "\n";

        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(type, doctor.type) && Objects.equals(equipmentInUse, doctor.equipmentInUse);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + (type != null ? type.hashCode() : 0) + (equipmentInUse != null ? equipmentInUse.hashCode() : 0);
    }
}
