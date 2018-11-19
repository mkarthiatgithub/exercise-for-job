package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

import org.apache.commons.io.FilenameUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Strategy Pattern
 * Conventionally the Strategy pattern is written by having an interface that is implemented by different classes.
 */
public interface FileService {

    default List<SupportedMIME> getSupportedMIME() {
        return Arrays.asList(SupportedMIME.values());
    }

    List<vehicleDetail> getVechicleDetails(SupportedMIME supportedMIME, String dirName);

    List<FileInformation> getFileInfo(SupportedMIME supportedMIME, String dirName);

    class SupportedFiles {
        private static FileService fileService;
        private final static MimetypesFileTypeMap mimeTypes = new MimetypesFileTypeMap();

        public static List<FileInformation> setListOfSupportedFiles(String directoryName, SupportedMIME supportedExtn) {
            if (!fileService.getSupportedMIME().contains(supportedExtn)) {
                throw new RuntimeException("Non supported Extn");
            }
            List<FileInformation> supportedFiles = new ArrayList<>();
            if (getFiles(directoryName).size() > 0)
                supportedFiles.addAll(getFiles(directoryName));
            return supportedFiles;
        }

         static List<FileInformation> getFiles(String directoryName) {
            List<FileInformation> FileInfoList = new ArrayList<>();
            File directory = new File(directoryName);
            File[] listFiles = directory.listFiles();
            if (listFiles.length == 0) {
                return Collections.EMPTY_LIST;
            }
            for (File file : listFiles) {
                String extension = getExtensionByApacheCommonLib(file.getName());
                FileInformation fileInformation = new FileInformation();
                fileInformation.setFileName(file.getName());
                fileInformation.setExt(extension);
                fileInformation.setFileSize(file.length());
                fileInformation.setExt(extension);
                fileInformation.setMimeType(mimeTypes.getContentType(file));
                FileInfoList.add(fileInformation);
            }
            return FileInfoList;
        }


        static String getExtensionByApacheCommonLib(String filename) {
            return FilenameUtils.getExtension(filename);
        }
    }

}

