package ge.gov.tsu.studentmanagement.util;

import ge.gov.tsu.studentmanagement.entity.Semester;
import ge.gov.tsu.studentmanagement.entity.Student;
import ge.gov.tsu.studentmanagement.entity.view.StudentSemesterExtended;
import ge.gov.tsu.studentmanagement.entity.view.StudentSubjectRecord;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

public class DocxGenerator {
    static String fontFamily = "Sylfaen";

    /*
    * XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File("create_table.docx"));
        document.write(out);
        out.close();
    * */

    public static XWPFDocument generateStudentRecordDocument(
            Integer academicYear,
            Integer semester,
            String senderUniName,
            String senderUniTel,
            String senderUniEmail,
            String studentName,
            String studentBirthDate,
            Integer gender,
            String studentPassportNumber,
            String receiverUniName,
            String receiverUniTel,
            String receiverUniEmail,
            String receiverUniDepHeadName,
            List<StudentSubjectRecord> students,
            String rectorName) throws IOException {

        XWPFDocument document = new XWPFDocument();

        XWPFParagraph academicYearPar = document.createParagraph();
        XWPFRun tmpRun = academicYearPar.createRun();
        tmpRun.setText("ACADEMIC YEAR: " + academicYear);
        tmpRun.setFontSize(13);
        tmpRun.setBold(true);


        XWPFParagraph semesterPar = document.createParagraph();
        XWPFRun sem = semesterPar.createRun();
        sem.setText("SEMESTER: " + (semester == 1 ? "Fall" : "Autumn"));
        sem.setFontSize(13);
        sem.setBold(true);

        //create table
        XWPFTable table = document.createTable();

        table.setCellMargins(50, 90, 5, 90);
        CTTblWidth uniInfoTableWidth = table.getCTTbl().addNewTblPr().addNewTblW();
        uniInfoTableWidth.setType(STTblWidth.DXA);
        uniInfoTableWidth.setW(BigInteger.valueOf(9072));
        // table.getCTTbl().getTblPr().unsetTblBorders();

        /**
         * გამგზავნი  უნივერსიტეტის სტრიქონი
         * */
        XWPFTableRow universityTableRow = table.getRow(0);
        table.getRow(0).getCell(0).removeParagraph(0);

        XWPFTableCell universityCell = universityTableRow.getCell(0);


        XWPFParagraph sendingUniPar = universityCell.addParagraph();
        XWPFRun sendingUni = sendingUniPar.createRun();
        sendingUni.setText("NAME OF SENDING INSTITUTION: ");
        sendingUni.setFontSize(13);
        sendingUni.setBold(true);

        XWPFRun sendingUni1 = sendingUniPar.createRun();
        sendingUni1.setText(senderUniName + "\t");
        sendingUni1.setFontSize(13);


        XWPFParagraph sendingUniTel = universityCell.addParagraph();
        XWPFRun sendingUniParRun = sendingUniTel.createRun();
        sendingUniParRun.setText("Tel.: " + senderUniTel + "\t \t \t \t" + "Email: " + senderUniEmail);

        /**
         *  სტუდენტის ინფორმაციის სტრიქონი
         */
        XWPFTableRow studentTableRow = table.createRow();
        studentTableRow.getCell(0).removeParagraph(0);
        XWPFTableCell studentCell = studentTableRow.getCell(0);


        XWPFParagraph studentPar = studentCell.addParagraph();
        XWPFRun studnetXwpfRun = studentPar.createRun();
        studnetXwpfRun.setText("NAME OF STUDENT: ");
        studnetXwpfRun.setFontSize(13);
        studnetXwpfRun.setBold(true);

        XWPFRun studentNameXwpfRun = studentPar.createRun();
        studentNameXwpfRun.setText(studentName);
        studentNameXwpfRun.setFontSize(13);


        XWPFParagraph studentDateOfBurth = studentCell.addParagraph();
        XWPFRun studentCellXwpfRun = studentDateOfBurth.createRun();
        studentCellXwpfRun.setText("Date of birth:  " + studentBirthDate + "\t \t \t \t" +
                "Sex: " + (gender == 1 ? "Male" : "Female"));


        XWPFParagraph studentPassportNumberPar = studentCell.addParagraph();
        XWPFRun studentPassportNumberRun = studentPassportNumberPar.createRun();
        studentPassportNumberRun.setText("Passport number: " + studentPassportNumber);


        /**
         *  მიმღები უნივერსიტეტის სტრიქონი
         */
        XWPFTableRow receiverUniTableRow = table.createRow();
        receiverUniTableRow.getCell(0).removeParagraph(0);

        XWPFTableCell receiverUniCell = receiverUniTableRow.getCell(0);

        XWPFParagraph receiverUniNamePar = receiverUniCell.addParagraph();
        XWPFRun receiverUniNameRun = receiverUniNamePar.createRun();
        receiverUniNameRun.setText("NAME OF RECEIVING INSTITUTION: ");
        receiverUniNameRun.setFontSize(13);
        receiverUniNameRun.setBold(true);

        XWPFRun receiverUniNameParRun = receiverUniNamePar.createRun();
        receiverUniNameParRun.setText(receiverUniName + "    ");
        receiverUniNameParRun.setFontSize(13);


        XWPFParagraph receiverUniDepHeadNamePar = receiverUniCell.addParagraph();
        XWPFRun receiverUniDepHeadNameParRun = receiverUniDepHeadNamePar.createRun();
        receiverUniDepHeadNameParRun.setText("Head of  the Department of Foreign Relations: " +
                receiverUniDepHeadName + "\t");


        XWPFParagraph receiverUniTelPar = receiverUniCell.addParagraph();
        XWPFRun receiverUniTelRun = receiverUniTelPar.createRun();
        receiverUniTelRun.setText("Tel.: " + receiverUniTel + "\t \t \t \t" + "Email: " + receiverUniEmail);

        /**
         * სტუდენტის ქულები ცხრილი
         **/
        document.createParagraph();
        document.createParagraph();
        XWPFTable studentGradeTable = document.createTable(1, 4);
        studentGradeTable.setCellMargins(50, 90, 5, 50);

        CTTblWidth gradeTableWidth = studentGradeTable.getCTTbl().addNewTblPr().addNewTblW();
        gradeTableWidth.setType(STTblWidth.DXA);
        gradeTableWidth.setW(BigInteger.valueOf(9072));

        CTTblWidth gradeTableTitleColumnWidth = studentGradeTable.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewTcW();
        gradeTableTitleColumnWidth.setType(STTblWidth.DXA);
        gradeTableTitleColumnWidth.setW(BigInteger.valueOf(5048));


        XWPFTableRow studentGradeTableTitleRow = studentGradeTable.getRow(0);

        XWPFTableCell studentStudentRowCell = studentGradeTableTitleRow.getCell(0);
        studentStudentRowCell.removeParagraph(0);
        studentStudentRowCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

        XWPFParagraph gradeTableStudentNamePar = studentStudentRowCell.addParagraph();
        gradeTableStudentNamePar.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun gradeTableStudentNameParRun = gradeTableStudentNamePar.createRun();
        gradeTableStudentNameParRun.setText("Title of the course unit");
        gradeTableStudentNameParRun.setFontSize(11);
        gradeTableStudentNameParRun.setBold(true);


        XWPFTableCell courseDurationUnit = studentGradeTableTitleRow.getCell(1);
        courseDurationUnit.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
        XWPFRun courseRun = courseDurationUnit.getParagraphs().get(0).createRun();
        courseRun.setBold(true);
        courseRun.setText("Duration of course unit");

        XWPFTableCell gradeNameCell = studentGradeTableTitleRow.getCell(2);
        gradeNameCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
        XWPFRun gradeRun = gradeNameCell.getParagraphs().get(0).createRun();
        gradeRun.setBold(true);
        gradeRun.setText("Grade");


        XWPFTableCell creditCell = studentGradeTableTitleRow.getCell(3);
        creditCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
        XWPFRun creditRun = creditCell.getParagraphs().get(0).createRun();
        creditRun.setBold(true);
        creditRun.setText("ECTS Credit");


        for (StudentSubjectRecord studentTest : students) {
            final Integer[] grade = {0};
            studentTest.getGrades().forEach(gr -> {
                grade[0] += gr.getGrade();
            });
            XWPFTableRow studentGradeTableRow = studentGradeTable.createRow();

            CTTblWidth tblWidth = studentGradeTableRow.getCell(0).getCTTc().addNewTcPr().addNewTcW();
            tblWidth.setType(STTblWidth.DXA);
            tblWidth.setW(BigInteger.valueOf(5048));
            // TODO: 03-Dec-17 ველებია ჩასამატებელი კლასში

            studentGradeTableRow.getCell(0).setText(studentTest.getSubjectName());

            studentGradeTableRow.getCell(1).setText(String.valueOf("todo"));
            studentGradeTableRow.getCell(1).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            studentGradeTableRow.getCell(1).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);


            studentGradeTableRow.getCell(2).setText(String.valueOf(grade[0]));
            studentGradeTableRow.getCell(2).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            studentGradeTableRow.getCell(2).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

            studentGradeTableRow.getCell(3).setText(String.valueOf(studentTest.getCredits()));
            studentGradeTableRow.getCell(3).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            studentGradeTableRow.getCell(3).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        }

        document.createParagraph();


        XWPFParagraph signaturePar = document.createParagraph();
        XWPFRun signatureParRun = signaturePar.createRun();
        signatureParRun.setText("Signature:");

        document.createParagraph();
        document.createParagraph();


        XWPFParagraph rectorPar = document.createParagraph();
        XWPFRun rectorParRun = rectorPar.createRun();
        rectorParRun.setText(rectorName);
        rectorParRun.setBold(true);

        XWPFParagraph rectorTitlePar = document.createParagraph();
        XWPFRun rectorTitleParRun = rectorTitlePar.createRun();
        rectorTitleParRun.setText("Rector");
        rectorTitleParRun.setBold(true);

        return document;

    }

    public static XWPFDocument generateStudentGridData(List<Semester> semester, List<Student> students) throws IOException, XmlException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph docHeadParagraph = document.createParagraph();
        docHeadParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun gradeTableStudentNameParRun = docHeadParagraph.createRun();
        System.out.println(gradeTableStudentNameParRun.getFontFamily());
        String ss = "ორმხრივი ხელშეკრულების ფარგლებში მიღებული გაცვლითი სტუდენტების სია";

        //   gradeTableStudentNameParRun.setText("ორმხრივი ხელშეკრულების ფარგლებში მიღებული გაცვლითი სტუდენტების სია");
        gradeTableStudentNameParRun.setText(ss);
        gradeTableStudentNameParRun.setFontSize(12);
        gradeTableStudentNameParRun.setBold(true);
        gradeTableStudentNameParRun.setFontFamily(fontFamily);


        semester.forEach(s -> {
            XWPFParagraph documentParagraphSemesterList = document.createParagraph();
            documentParagraphSemesterList.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun documentParagraphSemesterListRun = documentParagraphSemesterList.createRun();

            String seasonVal = s.getSeason() == 1 ? " შემოდგომის" : " გაზაფხულის";
            String sem = s.getYear() + " - " + (s.getYear() + 1) + seasonVal + " სემესტრი";

            documentParagraphSemesterListRun.setText(sem);
            documentParagraphSemesterListRun.setFontSize(12);
            documentParagraphSemesterListRun.setBold(true);
            documentParagraphSemesterListRun.setFontFamily(fontFamily);

        });

        /*
        * სტუდენტების ცხრილის გენერაცია
        */

        document.createParagraph();
        document.createParagraph();
        XWPFTable studentDataTable = document.createTable(1, 6);
        studentDataTable.setCellMargins(50, 90, 5, 50);

        CTTblWidth tblWidth = studentDataTable.getCTTbl().addNewTblPr().addNewTblW();
        tblWidth.setType(STTblWidth.DXA);
        tblWidth.setW(BigInteger.valueOf(9999));

        CTTblWidth ctTblWidth = studentDataTable.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewTcW();
        ctTblWidth.setType(STTblWidth.DXA);
        ctTblWidth.setW(BigInteger.valueOf(2048));


        XWPFTableRow studentGradeTableTitleRow = studentDataTable.getRow(0);

        XWPFTableCell studentGradeTableTitleRowCell = studentGradeTableTitleRow.getCell(0);
        studentGradeTableTitleRowCell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(500));
        studentGradeTableTitleRowCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
        XWPFRun tableStudentNameParRun = studentGradeTableTitleRowCell.getParagraphs().get(0).createRun();
        tableStudentNameParRun.setText("N");
        tableStudentNameParRun.setBold(true);
        tableStudentNameParRun.setFontFamily(fontFamily);

        XWPFTableCell nameXwpfTableCell = studentGradeTableTitleRow.getCell(1);
        nameXwpfTableCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
        XWPFRun gradeRun = nameXwpfTableCell.getParagraphs().get(0).createRun();
        gradeRun.setBold(true);
        gradeRun.setText("სახელი,გვარი");
        gradeRun.setFontFamily(fontFamily);


        XWPFTableCell studentPassportXwpfTableCell = studentGradeTableTitleRow.getCell(2);
        studentPassportXwpfTableCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
        XWPFRun xwpfRun = studentPassportXwpfTableCell.getParagraphs().get(0).createRun();
        xwpfRun.setBold(true);
        xwpfRun.setText("პასპორტის ნომერი");
        xwpfRun.setFontFamily(fontFamily);


        XWPFTableCell studentEmailXwpfTableCell = studentGradeTableTitleRow.getCell(3);
        studentEmailXwpfTableCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
        XWPFRun xwpfRun1 = studentEmailXwpfTableCell.getParagraphs().get(0).createRun();
        xwpfRun1.setBold(true);
        xwpfRun1.setText("ელ.ფოსტა");
        xwpfRun1.setFontFamily(fontFamily);


        XWPFTableCell studentHomeUniXwpfTableCell = studentGradeTableTitleRow.getCell(4);
        studentHomeUniXwpfTableCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
        XWPFRun xwpfRun2 = studentHomeUniXwpfTableCell.getParagraphs().get(0).createRun();
        xwpfRun2.setBold(true);
        xwpfRun2.setText("მშობლიური უნივერსიტეტი");
        xwpfRun2.setFontFamily(fontFamily);

        XWPFTableCell studyLevelXwpfTableCell = studentGradeTableTitleRow.getCell(5);
        studyLevelXwpfTableCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
        XWPFRun xwpfRun3 = studyLevelXwpfTableCell.getParagraphs().get(0).createRun();
        xwpfRun3.setBold(true);
        xwpfRun3.setText("სწავლების საფეხური");
        xwpfRun3.setFontFamily(fontFamily);

        int rowNum = 1;

        for (Student studentExtended : students) {

            XWPFTableRow studentDataTableRow = studentDataTable.createRow();

            XWPFTableCell studentDataTableRowCell = studentDataTableRow.getCell(0);
            studentDataTableRowCell.removeParagraph(0);
            studentDataTableRowCell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

            XWPFParagraph xwpfParagraph = studentDataTableRowCell.addParagraph();
            xwpfParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun xwpfRun4 = xwpfParagraph.createRun();
            xwpfRun4.setText(String.valueOf(rowNum
            ));


            XWPFTableCell studentNameDataTableRowCell1 = studentDataTableRow.getCell(1);
            studentNameDataTableRowCell1.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            XWPFRun xwpfRun5 = studentNameDataTableRowCell1.getParagraphs().get(0).createRun();
            xwpfRun5.setText(studentExtended.getGender() == 1 ? "Mr." : "Mrs." + studentExtended.getFirstName() + " " + studentExtended.getLastName() + "(" + studentExtended.getGeorgianFirstName() + " " + studentExtended.getGeorgianFirstName() + ")");
            xwpfRun5.setFontFamily(fontFamily);


            XWPFTableCell studentPassportValXwpfTableCell = studentDataTableRow.getCell(2);
            studentPassportValXwpfTableCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            XWPFRun xwpfRun8 = studentPassportValXwpfTableCell.getParagraphs().get(0).createRun();
            xwpfRun8.setText(studentExtended.getPassportNumber());
            xwpfRun8.setFontFamily(fontFamily);


            XWPFTableCell studentEmailValXwpfTableCell = studentDataTableRow.getCell(3);
            studentEmailValXwpfTableCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            XWPFRun xwpfRun9 = studentEmailValXwpfTableCell.getParagraphs().get(0).createRun();
            xwpfRun9.setText(studentExtended.getEmail());
            xwpfRun9.setFontFamily(fontFamily);

            XWPFTableCell studentUniValXwpfTableCell = studentDataTableRow.getCell(4);
            studentUniValXwpfTableCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            XWPFRun xwpfRun6 = studentUniValXwpfTableCell.getParagraphs().get(0).createRun();
            xwpfRun6.setText(studentExtended.getUniversity().getName() + "(" + studentExtended.getUniversity().getGeorgianName() + ")");
            xwpfRun6.setFontFamily(fontFamily);


            XWPFTableCell studentLevelValXwpfTableCell = studentDataTableRow.getCell(4);
            studentLevelValXwpfTableCell.getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
            XWPFRun xwpfRun7 = studentLevelValXwpfTableCell.getParagraphs().get(0).createRun();
            // TODO: 1/10/2018 degree not presented

            rowNum++;

        }
        return document;

    }

    public static XWPFDocument generateStudentAcceptanceDocument(
            Integer gender,
            String firstName,
            String lastName,
            String birthDate,
            List<Semester> semesters,
            String uniName,
            String countryName,
            String departmentHeadName,
            String tsuName,
            String rector) {


        XWPFDocument document = new XWPFDocument();
        XWPFParagraph docHeadParagraph = document.createParagraph();
        docHeadParagraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun xwpfRun = docHeadParagraph.createRun();
        xwpfRun.setFontSize(12);
        xwpfRun.setFontFamily(fontFamily);

        xwpfRun.setText(departmentHeadName);

        xwpfRun.addCarriageReturn();

        xwpfRun.setText(uniName);

        xwpfRun.addCarriageReturn();

        xwpfRun.setText(countryName);


        document.createParagraph();
        document.createParagraph();
        document.createParagraph();


        XWPFParagraph xwpfParagraph2 = document.createParagraph();
        xwpfParagraph2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun xwpfRun2 = xwpfParagraph2.createRun();
        xwpfRun2.setText("LETTER OF ACCEPTANCE");
        xwpfRun2.setFontSize(12);
        xwpfRun2.setFontFamily(fontFamily);

        document.createParagraph();
        document.createParagraph();
        document.createParagraph();


        XWPFParagraph xwpfParagraph3 = document.createParagraph();
        xwpfParagraph3.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun xwpfRun3 = xwpfParagraph3.createRun();
        xwpfRun3.setFontSize(12);
        xwpfRun3.setFontFamily(fontFamily);

        String bodText = "This is to certify that within the framework of the bilateral agreement between ";
        bodText += tsuName + " and the ";
        bodText += uniName + ", ";
        bodText += gender == 1 ? "Mr. " : "Mrs. " + firstName + " " + lastName + ", ";
        bodText += "born on ";
        bodText += birthDate + ", " + "has been admitted to study  as an exchange student at " + tsuName + " for  the";
        for (int i = 0; i < semesters.size(); i++) {
            Semester sse = semesters.get(i);
            String seasonVal = sse.getSeason() == 1 ? " autumn" : " spring";
            String sem = sse.getYear() + "/" + (sse.getYear() + 1);
            bodText += seasonVal + " semester of the year " + sem;
            if (i != semesters.size() - 1) {
                bodText+=", ";
            }
        }
        bodText += ".";
        xwpfRun3.setText(bodText);

        document.createParagraph();
        document.createParagraph();
        document.createParagraph();

        XWPFParagraph xwpfParagraph4 = document.createParagraph();
        xwpfParagraph4.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun xwpfRun4 = xwpfParagraph4.createRun();
        xwpfRun4.setFontSize(12);
        xwpfRun4.setFontFamily(fontFamily);
        xwpfRun4.setText("Yours sincerely,");

        document.createParagraph();
        document.createParagraph();


        XWPFParagraph xwpfParagraph5 = document.createParagraph();
        xwpfParagraph5.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun xwpfRun5 = xwpfParagraph5.createRun();
        xwpfRun5.setFontSize(12);
        xwpfRun5.setFontFamily(fontFamily);
        xwpfRun5.setText("Dr. " + rector);
        xwpfRun5.addCarriageReturn();
        xwpfRun5.setText("Rector");

        return document;
    }
}
