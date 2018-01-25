import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String sourceFilePath = "C:\\Users\\aturchenkov\\sourceFile.xlsx";
        String resultFilePath = "C:\\Users\\aturchenkov\\resultfile.xlsx";

        TransferCandidates transferCandidates = new TransferCandidates();
        SourceExcelSheet sourceExcelSheet = new SourceExcelSheet();
        TransferList transferList = new TransferList();
        ResultExcelSheet resultExcelSheet = new ResultExcelSheet();


        sourceExcelSheet.readFromExcel(sourceFilePath, transferCandidates.getDepartmentHashMap());

        transferCandidates.searchForCandidate(transferList.getTransferList());

        resultExcelSheet.saveResultToFile(resultFilePath, transferList.getTransferList());
    }
}
