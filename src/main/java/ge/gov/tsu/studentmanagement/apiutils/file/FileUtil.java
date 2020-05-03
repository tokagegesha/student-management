package ge.gov.tsu.studentmanagement.apiutils.file;

import ge.gov.tsu.studentmanagement.apiutils.DateUtil;
import ge.gov.tsu.studentmanagement.entity.Student;
import ge.gov.tsu.studentmanagement.pojo.UploadedFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileUtil {
    public static final String studentDataBaseDirName;
    public static final String studentDataBaseDir;

    static {
        String rootPath = Paths.get(System.getProperty("user.home")).toAbsolutePath().toString();
        rootDir = rootPath + File.separator + "student-management";
        studentDataBaseDirName = "student-data";
        studentDataBaseDir = rootPath + File.separator + "student-management";
    }

    public static String saveFile(String subBaseDir, String uniqueFolder, String folderName, MultipartFile file) {
        try {
            // TODO: 25-Feb-18
            String subBase = createCandidateDocumentBaseDirectory(subBaseDir);
            String uniqueFolderDir = createCandidateUniqueDirectory(subBase, uniqueFolder);
            String cutDirectory = createDirectoryByName(uniqueFolderDir, folderName);
            byte[] bytes = file.getBytes();
            String path = subBase + File.separator + uniqueFolder + File.separator + cutDirectory + File.separator;
            File studentFile = new File(path + file.getOriginalFilename());
            if (studentFile.exists()) {
                studentFile = new File(path + getFileNameWithIndexIfNeeded(path, file.getOriginalFilename()));
            }
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(studentFile));
            stream.write(bytes);
            stream.close();

            return File.separator + subBaseDir + File.separator + uniqueFolder
                    + File.separator + cutDirectory + File.separator + studentFile.getName();

        } catch (Exception e) {
            System.out.println("You failed to upload " + file.getName() + " => " + e.getMessage());
        }
        return null;
    }

    public static List<UploadedFile> saveFiles(List<MultipartFile> files) {
        List<UploadedFile> result = new ArrayList<>();
        String fileDir = getRandomDirectoryName();
        for (int i = 0; i < files.size(); i++) {
            File file = saveFileInDirectory(fileDir, files.get(i).getOriginalFilename(), files.get(i));
            result.add(new UploadedFile(files.get(i).getOriginalFilename(), fileDir + "/" + file.getName()));
        }
        return result;
    }

    public static File saveFileInDirectory(String dir, String name, MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            File studentFile = new File(rootDir + File.separator + dir + File.separator + name);
            if (studentFile.exists()) {
                //System.out.println(candidateFile.getAbsolutePath());
                //System.out.println(getFileNameWithIndexIfNeeded(directory, file.getOriginalFilename()));
                studentFile = new File(rootDir + File.separator + dir + File.separator + getFileNameWithIndexIfNeeded(dir, name));
                //System.out.println(candidateFile.getAbsolutePath());
            }
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(studentFile));
            stream.write(bytes);
            stream.close();

            return studentFile;

        } catch (Exception e) {
            System.out.println("You failed to upload " + file.getName() + " => " + e.getMessage());
        }
        return null;
    }

    private static final String rootDir;

    public static String creteCandidateDirectory() {
        String dirName = DateUtil.getOnlyDate(new Date());
        File userDir = new File(rootDir + File.separator + dirName);
        if (!userDir.exists()) userDir.mkdir();
        return dirName;
    }

    public static String createCandidateDocumentDirectory(String folderName) {
        File dir = new File(rootDir + File.separator + folderName);
        if (!dir.exists())
            dir.mkdir();
        return folderName;
    }


    /*
    * don't touch
    * */
    public static String createCandidateDocumentBaseDirectory(String subBaseDir) {
        File dir = new File(rootDir + File.separator + subBaseDir);
        if (!dir.exists())
            dir.mkdir();
        return dir.getAbsolutePath();
    }

    public static String createCandidateUniqueDirectory(String root, String uniqueName) {
        File dir = new File(root + File.separator + uniqueName);
        if (!dir.exists())
            dir.mkdir();
        return dir.getAbsolutePath();
    }

    public static String createDirectoryByName(String root, String name) {
        File subFolder = new File(root + File.separator + name + File.separator);
        if (!subFolder.exists()) subFolder.mkdir();
        String subPath = subFolder.getAbsolutePath();

        String date = DateUtil.getOnlyDate(new Date());
        String dirName = subPath + File.separator + date;
        File userDir = new File(dirName);
        if (!userDir.exists()) userDir.mkdir();
        return name + File.separator + date;
    }

    
      /*
    * end of don't touch
    * */

    private static String getRandomDirectoryName() {
        Calendar calendar = Calendar.getInstance();
        String dirName = calendar.get(Calendar.YEAR) + "_" + calendar.get(Calendar.MONTH) + "_" + calendar.get(Calendar.DAY_OF_MONTH);
        File dir = new File(rootDir + File.separator + dirName);
        if (!dir.exists()) dir.mkdir();
        return dirName;
    }

    private static String getFileNameWithIndexIfNeeded(String dir, String name) throws Exception {
        String extension = getFileExtension(name);
        String nam = getFileNameWithoutExtension(name);
        int i = 2;
        while (new File(dir + File.separator + nam + "(" + i + ")" + extension).exists()) {
            i++;
        }
        return nam + "(" + i + ")" + extension;
    }

    public static String getFileExtension(String fileName) throws Exception {
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(i);
        } else {
            throw new Exception("Filename doesn't contain extension.");
        }
    }

    public static String getFileNameWithoutExtension(String fileName) throws Exception {
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            return fileName.substring(0, i);
        } else {
            throw new Exception("Filename doesn't contain extension.");
        }
    }

    public static ByteArrayOutputStream createZip(Student studentExtended) throws Exception {

        File cv = new File(rootDir + studentExtended.getCvPath());
        File diploma = new File(rootDir + studentExtended.getDiplomaPath());
        File letterOfNomination = new File(rootDir + studentExtended.getLetterOfNominationPath());
        File learningAgreement = new File(rootDir + studentExtended.getLearningAgreementPath());
        File passport = new File(rootDir + studentExtended.getPassportPath());
        File picture = new File(rootDir + studentExtended.getPicturePath());
        File uniRecord = new File(rootDir + studentExtended.getUniversityRecordPath());
        File proofOfEnglishSkill = new File(rootDir + studentExtended.getProofOfEnglishSkillPath());
        File healthInsurance = new File(rootDir + studentExtended.getHealthInsurancePath());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream out = new ZipOutputStream(baos);

        String sb =
                "First Name: " + studentExtended.getFirstName() + System.lineSeparator() +
                        "Last Name: " + studentExtended.getLastName() + System.lineSeparator() +
                        "Email: " + studentExtended.getEmail() + System.lineSeparator() +
                        "University name: " + studentExtended.getUniversity().getName() + System.lineSeparator();
        //sb.append("Programme degree: "+candidate.getProgrammeDegree()+System.lineSeparator());

        ZipEntry info = new ZipEntry("info-" + studentExtended.getFirstName() + "-" + studentExtended.getLastName() + ".txt");
        out.putNextEntry(info);
        byte[] infoData = sb.getBytes();
        out.write(infoData, 0, infoData.length);
        out.closeEntry();

        if (cv.exists()) {
            ZipEntry cvZip = new ZipEntry("cv-" + cv.getName());
            out.putNextEntry(cvZip);
            byte[] cvData = Files.readAllBytes(cv.toPath());
            out.write(cvData, 0, cvData.length);
            out.closeEntry();
        }

        if (diploma.exists()) {
            ZipEntry educationDecZip = new ZipEntry("diploma-" + diploma.getName());
            out.putNextEntry(educationDecZip);
            byte[] eduDecData = Files.readAllBytes(diploma.toPath());
            out.write(eduDecData, 0, eduDecData.length);
            out.closeEntry();
        }

        if (letterOfNomination.exists()) {
            ZipEntry gradeZip = new ZipEntry("letter-of-nomination-" + letterOfNomination.getName());
            out.putNextEntry(gradeZip);
            byte[] gradeData = Files.readAllBytes(letterOfNomination.toPath());
            out.write(gradeData, 0, gradeData.length);
            out.closeEntry();
        }

        if (learningAgreement.exists()) {
            ZipEntry motLetterZip = new ZipEntry("learning-agreement-" + learningAgreement.getName());
            out.putNextEntry(motLetterZip);
            byte[] motLetterData = Files.readAllBytes(learningAgreement.toPath());
            out.write(motLetterData, 0, motLetterData.length);
            out.closeEntry();
        }

        if (passport.exists()) {
            ZipEntry passportZip = new ZipEntry("passport-" + passport.getName());
            out.putNextEntry(passportZip);
            byte[] passportData = Files.readAllBytes(passport.toPath());
            out.write(passportData, 0, passportData.length);
            out.closeEntry();
        }

        if (picture.exists()) {
            ZipEntry pictureZip = new ZipEntry("picture-" + picture.getName());
            out.putNextEntry(pictureZip);
            byte[] pictureData = Files.readAllBytes(picture.toPath());
            out.write(pictureData, 0, pictureData.length);
            out.closeEntry();
        }

        if (uniRecord.exists()) {
            ZipEntry recLetterZip = new ZipEntry("university-record-" + uniRecord.getName());
            out.putNextEntry(recLetterZip);
            byte[] recLetterData = Files.readAllBytes(uniRecord.toPath());
            out.write(recLetterData, 0, recLetterData.length);
            out.closeEntry();

        }
        if (proofOfEnglishSkill.exists()) {
            ZipEntry statusDocZip = new ZipEntry("proof-of-english-skill-" + proofOfEnglishSkill.getName());
            out.putNextEntry(statusDocZip);
            byte[] statusDocData = Files.readAllBytes(proofOfEnglishSkill.toPath());
            out.write(statusDocData, 0, statusDocData.length);
            out.closeEntry();
        }
        if (healthInsurance.exists()) {
            ZipEntry statusDocZip = new ZipEntry("health-insurance-" + healthInsurance.getName());
            out.putNextEntry(statusDocZip);
            byte[] statusDocData = Files.readAllBytes(healthInsurance.toPath());
            out.write(statusDocData, 0, statusDocData.length);
            out.closeEntry();
        }


        out.close();

        return baos;
    }


    public static byte[] getOriginalFile(String imageUrl) {
        try {
            String fullOriginalPath = getFullPathOfFile(imageUrl);
            File f = new File(fullOriginalPath);

            if (f.exists() && !f.isDirectory()) {
                return Files.readAllBytes(Paths.get(fullOriginalPath));
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getFullPathOfFile(String image) {
        return rootDir + File.separator + image;
    }
}
