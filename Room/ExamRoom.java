package Room;

import EquipmentAndMachinery.Equipment;
import EquipmentAndMachinery.Machinery;
import People.Employee;

import java.util.List;
import java.util.Objects;

public class ExamRoom extends Room {


    private Machinery assignedMachinery;

    public ExamRoom() {
        super();
        this.assignedMachinery = null;
    }

    public ExamRoom(int roomID) {
        super(roomID);
        this.assignedMachinery = null;
    }

    public ExamRoom(Machinery assignedMachinery) {
        super();
        this.assignedMachinery = assignedMachinery;
    }

    public ExamRoom(List<Employee> assignedEmployee, int roomID, Machinery assignedMachinery) {
        super(assignedEmployee, roomID);
        this.assignedMachinery = assignedMachinery;
    }


    public Machinery getAssignedMachinery() {
        return assignedMachinery;
    }

    public void setAssignedMachinery(Machinery assignedMachinery) {
        this.assignedMachinery = assignedMachinery;

    }

    @Override
    public String toString() {
        return "ExamRoom{" +
                "assignedMachinery=" + assignedMachinery +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExamRoom examRoom = (ExamRoom) o;
        return Objects.equals(assignedMachinery, examRoom.assignedMachinery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), assignedMachinery);
    }
}
