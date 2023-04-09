package fileio;

import user.AbstractStudentUser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentUserFileWriter implements IFileWriter {

    private List<AbstractStudentUser> students;
    private String fileName;
    private String seperator;

    public StudentUserFileWriter(List<AbstractStudentUser> students, String fileName, String seperator) {
        this.students = students;
        this.fileName = fileName;
        this.seperator = seperator;
    }

    @Override
    public void writeFile() {
        File file = new File(getFileName());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (AbstractStudentUser student : getStudents()) {
                if (getStudents().indexOf(student) != getStudents().size() - 1) {
                    fileWriter.write(student.getUsername() + getSeperator() + student.getPassword() + getSeperator() +
                            student.getLanguage().getName() + getSeperator() + student.getCurrentUnit().getName() + getSeperator() +
                            student.getSolvedQuizzes() + getSeperator() + student.getScore() + "\n");
                }
                else {
                fileWriter.write(student.getUsername() + getSeperator() + student.getPassword() + getSeperator() +
                        student.getLanguage().getName() + getSeperator() + student.getCurrentUnit().getName() + getSeperator() +
                        student.getSolvedQuizzes() + getSeperator() + student.getScore());
                }
                fileWriter.flush();
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AbstractStudentUser> getStudents() {
        return students;
    }

    public void setStudents(List<AbstractStudentUser> students) {
        this.students = students;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSeperator() {
        return seperator;
    }

    public void setSeperator(String seperator) {
        this.seperator = seperator;
    }
}
