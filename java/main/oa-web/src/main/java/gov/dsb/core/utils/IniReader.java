package gov.dsb.core.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class IniReader {

    protected HashMap<String, Properties> sections = new HashMap<String, Properties>();
    private transient String currentSection;
    private transient Properties current;

    public IniReader(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        read(reader);
        reader.close();
    }

    protected void read(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            parseLine(line);
        }
    }

    protected void parseLine(String line) {
        line = line.trim();
        if (line.matches("\\[.*\\]")) {
            currentSection = line.replaceFirst("\\[(.*)\\]", "$1");
            current = new Properties();
            sections.put(currentSection, current);
        } else if (line.matches(".*=.*")) {
            if (current != null) {
                int i = line.indexOf('=');
                String name = line.substring(0, i);
                String value = line.substring(i + 1);
                current.setProperty(name, value);
            }
        }
    }

    public String getValue(String section, String name) {
        Properties p = (Properties) sections.get(section);

        if (p == null) {
            return null;
        }

        String value = p.getProperty(name);
        return value;
    }

    public int getSectionCount() {
        return sections.size();
    }

    public Set<String> getSectionNames() {
        return sections.keySet();
    }

    public static void main(String[] args) throws IOException {
        IniReader iniReader = new IniReader("E:\\work\\data-analyzer\\trunk\\config\\config.ini");

    }
}
