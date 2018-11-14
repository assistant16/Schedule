package studentModule;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentWriter {

    private final String path;

    public StudentWriter(String path) {
        this.path = path;
    }

    public void write(List<Student> students) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            if (students!=null){
                for(Student st: students) {
                    String line = String.format("%s;%s;%s",
                            st.getId(),
                            st.getFirstName(),
                            st.getSecondName());
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }
}
