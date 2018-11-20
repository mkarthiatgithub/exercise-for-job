package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

import org.apache.commons.io.FilenameUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Strategy Pattern
 * Conventionally the Strategy pattern is written by having an interface that is implemented by different classes.
 */
public interface FileService {

    List<VehicleDetail> getVechicleDetails(String dirName);

    List<FileInformation> getFileInfo(String dirName);

    class SupportedFiles {

        private final static MimetypesFileTypeMap mimeTypes = new MimetypesFileTypeMap();

        static List<FileInformation> getListOfSupportedFiles(String directoryName, SupportedMIME supportedExtn) {
            List<FileInformation> supportedFiles = new ArrayList<>();
            if (getFiles(directoryName, supportedExtn).size() > 0)
                supportedFiles.addAll(getFiles(directoryName, supportedExtn));
            return supportedFiles;
        }

        static List<FileInformation> getFiles(String directoryName, SupportedMIME supportedExtn) {
            List<FileInformation> FileInfoList = new ArrayList<>();
            File directory = new File(directoryName);
            File[] listFiles = directory.listFiles();
            if (listFiles == null) {
                return new ArrayList<>();
            }
            for (File file : listFiles) {
                String extension = getExtensionByApacheCommonLib(file.getName());
                if (!supportedExtn.toString().equalsIgnoreCase(extension)) {
                    continue;
                }
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


