import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String sourceFilePath = "C:\\Users\\aturchenkov\\sourceFile.xlsx";
        String resultFilePath = "C:\\Users\\aturchenkov\\resultfile.xlsx";

        TransferCandidates transferCandidates = new TransferCandidates();
        SourceExcelSheet sourceExcelSheet = new SourceExcelSheet();
        sourceExcelSheet.readFromExcel(sourceFilePath, transferCandidates.getDepartmentHashMap());

        transferCandidates.searchForCandidate();
        transferCandidates.filterCandidate();

        ResultExcelSheet resultExcelSheet = new ResultExcelSheet();
        resultExcelSheet.saveResultToFile(resultFilePath, transferCandidates.resultTransferCandidatesList);
    }
}
