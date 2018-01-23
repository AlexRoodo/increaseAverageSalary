public class ConcreteDepartmentCreator extends DepartmentCreator {
    @Override
    public Department createNewDepartment(String departmentName) {
        return new ConcreteDepartment(departmentName);
    }
}
