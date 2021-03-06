package com.solvd.hospital.model.equipmentAndMachinery;

import com.solvd.hospital.model.room.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bed extends Equipment {

    private static final Logger log = LogManager.getLogger(Bed.class);

    private boolean inUse;
    private int inclination;

    public Bed() {
        super();
        this.inUse = false;
        this.inclination = 0;
    }

    public Bed(boolean working, Room locationRoom, int ID, boolean inUse, int inclination) {
        super(working, locationRoom, ID, "Bed");
        this.inUse = inUse;
        if (inclination <= 4)
            this.inclination = inclination;
        else {
            log.info("The inclination provided is invalid. It has been set to 0");
            this.inclination = 0;
        }
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getInclination() {
        return inclination;
    }

    public void setInclination(int inclination) {
        this.inclination = inclination;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bed bed = (Bed) o;
        return super.equals(o) && inUse == bed.inUse && inclination == bed.inclination;
    }

    @Override
    public int hashCode() {

        return super.hashCode() + inclination + ((inUse) ? 1 : 0);
    }
}
