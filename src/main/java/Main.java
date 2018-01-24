import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String sourceFilePath = "C:\\Users\\aturchenkov\\sourceFile.xlsx";
        String resultFilePath = "C:\\Users\\aturchenkov\\resultfile.xlsx";

        SourceExcelSheet sourceExcelSheet = new SourceExcelSheet();
        sourceExcelSheet.readFromExcel(sourceFilePath);

        TransferCandidates transferCandidates = new TransferCandidates();
        transferCandidates.searchForCandidate(sourceExcelSheet);
        transferCandidates.filterCandidate(sourceExcelSheet);

        ResultExcelSheet resultExcelSheet = new ResultExcelSheet();
        resultExcelSheet.saveResultToFile(resultFilePath, transferCandidates.resultTransferCandidatesList);
    }

}
