import java.util.ArrayList;
import java.util.LinkedList;

public class Transfer {
    private LinkedList<Employee> employeesToTransfer = null;
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

    public Department getDestinationDepartment() {
        return destinationDepartment;
    }

    public void setDestinationDepartment(Department destinationDepartment) {
        this.destinationDepartment = destinationDepartment;
    }

    public void setEmployeesToTransfer(LinkedList<Employee> employeesToTransfer) {
        if (this.employeesToTransfer == null) {
            this.employeesToTransfer = new LinkedList<>(employeesToTransfer);
        }
    }
}
