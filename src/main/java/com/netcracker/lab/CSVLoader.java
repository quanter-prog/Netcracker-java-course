package com.netcracker.lab;

import com.netcracker.lab.entities.Division;
import com.netcracker.lab.entities.Person;
import com.netcracker.lab.entities.enums.Gender;
import com.netcracker.lab.repository.IRepository;
import com.netcracker.lab.repository.Repository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {

    private String path;

    CSVLoader(String path) {
        this.path = path;
    }

    public IRepository getRepository() {
        return fillRepository(read());
    }

    /**
     * Данная функция заполняет репозиторий даннымыми из файла.
     * @param list list с информацией из файла
     * @return репозиотрий с людьми из файла
     */
    private Repository fillRepository(List<String[]> list) {
        Repository rep = new Repository();

        for (String[] arr : list) {
            Person person;
            StringBuilder buf = new StringBuilder("");

            for (String s : arr) {
                buf.append(s);
            }

            String line = buf.toString();
            String[] values = line.split(";");

            DateTimeFormatter dtmf = DateTimeFormat.forPattern("DD.MM.YYYY");
            Division division = new Division(values[4]);
            person = new Person(values[1],
                    Integer.parseInt(values[0]),
                    dtmf.parseLocalDate(values[3]),
                    Gender.valueOf(values[2].toUpperCase()),
                    division,
                    BigDecimal.valueOf(Double.parseDouble(values[5])));
            rep.add(person);
        }

        return rep;
    }

    /**
     * Данная функция считывает из файла информацию и записывает её в list.
     * @return list с информацией из файла
     */
    public List<String[]> read() {
        List<String[]> list = new ArrayList<>();
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .build();


            list = csvReader.readAll();
            reader.close();
            csvReader.close();
            return list;
        } catch (Exception ex) {
            ex = new Exception();
        }
        return list;
    }
}