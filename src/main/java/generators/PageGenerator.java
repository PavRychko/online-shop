package generators;

import java.io.StringWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class PageGenerator {
    private static final String HTML_DIR = "src/main/resources";

    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String fileName) throws SQLException {
        return getPage(fileName, Collections.emptyMap());
    }

    public String getPage(String filename, Map<String, Object> data) throws SQLException {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }


    private PageGenerator() {
        cfg = new Configuration();
    }
}

