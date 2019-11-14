package com.company;

import com.fabio.models.Composicao;
import com.fabio.models.Elemento;
import com.fabio.models.MaoDeObra;
import com.fabio.models.Material;
import com.fabio.utils.Json;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JSONArray jArray = new JSONArray(readLineByLineJava8("/home/lukefrozz/IdeaProjects/fabio/data.json"));
//        System.out.println(jArray.toList().get(0));
        JSONObject json = new JSONObject();
        new Json().getElementos(jArray).forEach((elemento -> System.out.println(elemento.subTotal())));
    }

    private static String readLineByLineJava8(String filePath)
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }

    private static void legacyTest() {
        MaoDeObra pedreiro = new MaoDeObra(1, "Pedreiro", (float) 0.5, "Horas", 30);
        MaoDeObra servente = new MaoDeObra(2, "Servente", 1, "Horas", 18);
        Material cimento = new Material(3, "Cimento", (float) 0.1, "Sacos", 30);
        Material areia = new Material(4, "Areia", 5, "Kg", 20);
        Material cal = new Material(5, "Cal", 1, "Kg", 9);

        List<Elemento> elementosArgamassa = new ArrayList<>();
        elementosArgamassa.add(pedreiro);
        elementosArgamassa.add(servente);
        elementosArgamassa.add(cimento);
        elementosArgamassa.add(areia);
        elementosArgamassa.add(cal);

        Composicao argamassa = new Composicao(6, "Argamassa", 3, "m³", elementosArgamassa);
        List<Elemento> elementosParede = new ArrayList<Elemento>();
        elementosParede.add(pedreiro);
        elementosParede.add(servente);
        Material tijolo = new Material(7, "Tijolo 6 furos", 10, "unid.", (float) 0.34);
        elementosParede.add(tijolo);
        elementosParede.add(argamassa);
        Composicao parede = new Composicao(8, "Parede", 3, "m²", elementosParede);
        System.out.println(argamassa.subTotal());
        System.out.println(parede.subTotal());
    }
}
