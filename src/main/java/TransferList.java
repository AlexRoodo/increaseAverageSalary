import java.util.LinkedList;

public class TransferList {
    private LinkedList<Employee> transferList = null;

    public LinkedList<Employee> getTransferList() {
        if (transferList == null) {
            transferList = new LinkedList<>();
        }
        return transferList;
    }
}
