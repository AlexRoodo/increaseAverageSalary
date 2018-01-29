import java.util.ArrayList;
import java.util.LinkedList;

public class Transfer {
    public LinkedList<Employee> employeesToTransfer = new LinkedList<>();
    private Department currentDepartment = null;
    private Department destinationDepartment = null;

    public LinkedList<Employee> getEmployeesToTransfer() {
        if (employeesToTransfer == null) {
            employeesToTransfer = new LinkedList<>();
        }
        return employeesToTransfer;
    }

    public Department getCurrentDepartment() {
        return currentDepartment;
    }

    public void setCurrentDepartment(Department currentDepartment) {
        this.currentDepartment = currentDepartment;
    }

    public Department getTargetDepartment() {
        return destinationDepartment;
    }

    public void setDestinationDepartment(Department destinationDepartment) {
        this.destinationDepartment = destinationDepartment;
    }

}
