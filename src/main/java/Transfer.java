import java.util.LinkedList;

public class Transfer implements Comparable<Transfer>{
    public LinkedList<Employee> employeesToTransfer = new LinkedList<>();
    private Department currentDepartment = null;
    private Department targetDepartment = null;

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
        return targetDepartment;
    }

    public void setTargetDepartment(Department targetDepartment) {
        this.targetDepartment = targetDepartment;
    }


    @Override
    public int compareTo(Transfer o) {
        if (this.currentDepartment.getName().equalsIgnoreCase(o.getCurrentDepartment().getName())) {
            if (this.targetDepartment.getName().equalsIgnoreCase(o.getTargetDepartment()
                    .getName())) {
                if (this.employeesToTransfer.size() == o.getEmployeesToTransfer().size()) {
                    for (int i = 0; i < this.employeesToTransfer.size(); i++) {
                        if (this.employeesToTransfer.get(i)
                                .compareTo(o.getEmployeesToTransfer().get(i)) != 0) {
                            return this.employeesToTransfer.get(i)
                                    .compareTo(o.getEmployeesToTransfer().get(i));
                        }

                    }
                }

                return this.employeesToTransfer.size() - o.getEmployeesToTransfer().size();
            }

            return this.targetDepartment.getName().compareTo(o.getTargetDepartment().getName());
        }
        return 0;
    }
}
