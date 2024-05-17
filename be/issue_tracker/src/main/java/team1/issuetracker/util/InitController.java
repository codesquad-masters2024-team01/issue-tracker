package team1.issuetracker.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
public class InitController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/reset")
    public void executeSqlScript() {
        String ddl = "/sql/ddl.sql";
        String data = "/sql/data.sql";

        runScript(ddl);
        runScript(data);
    }

    public void runScript(String scriptPath) {
        try {
            Resource resource = new ClassPathResource(scriptPath);
            InputStream inputStream = resource.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().isEmpty() && !line.startsWith("--")) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                    if (line.endsWith(";")) {
                        String sqlStatement = stringBuilder.toString().trim();
                        jdbcTemplate.execute(sqlStatement);
                        stringBuilder.setLength(0); // Clear the StringBuilder for the next statement
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
