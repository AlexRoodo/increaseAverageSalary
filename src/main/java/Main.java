import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String sourceFilePath = "C:\\Users\\aturchenkov\\sourceFile.xlsx";
        SourceExcelSheet sourceExcelSheet = new SourceExcelSheet();
        sourceExcelSheet.readFromExcel(sourceFilePath);
    }
}
