import java.util.LinkedList;

public class TransferList {
    private LinkedList<Employee> transferList = null;
    private boolean isTransferListFilled = false;

    public LinkedList<Employee> getTransferList() {
        if (transferList == null) {
            transferList = new LinkedList<>();
        }
        return transferList;
    }

    public boolean isTransferListFilled() {
        return this.isTransferListFilled;
    }

    public void setTransferListFilled(boolean transferListFilled) {
        this.isTransferListFilled = transferListFilled;
    }

}
