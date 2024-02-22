package org.repository;

import org.entity.Mossa;

import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MossaRepository implements Repository{

    HashMap<String, Mossa> mosse = new HashMap<String, Mossa>();


    @Override
    public void read() {
        String csvFile = "Dataset/Elenco_Mosse_V2.csv";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                mosse.put(data[1], new Mossa(data[1],
                        data[2],
                        data[3],
                        Integer.parseInt(data[6]),//potenza
                        Integer.parseInt(data[7]),
                        Integer.parseInt(data[8])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //costruttore
    public MossaRepository(){
        read();
    }

    public HashMap<String, Mossa> getMosse() {
        return mosse;
    }

    public Mossa getMossa(String nome){
        return mosse.get(nome);
    }
}
