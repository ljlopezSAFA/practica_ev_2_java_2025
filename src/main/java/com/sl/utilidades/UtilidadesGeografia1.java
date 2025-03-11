package com.sl.utilidades;

import com.sl.modelos.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UtilidadesGeografia1 {



    public static List<Pais> ejercicio1(List<Pais> paises) {

        return paises
                .stream()
                .filter(p-> !p.getPaisesVecinos().isEmpty()
                        && p.getComunidadesAutonomas().size()>2)
                .toList();
    }


    public static Map<Continente, Integer> ejercicio2(List<Continente> continentes) {
        return continentes
                .stream()
                .collect(Collectors.toMap(c->c , c-> c.getPaises().size()));
    }


    public List<Ciudad> ejercicio3(List<Ciudad> ciudades, List<Pais> paises) {

        return ciudades
                .stream()
                .filter(c-> paises
                        .stream()
                        .map(p->p.getCapital())
                        .toList().contains(c))
                .collect(Collectors.toSet())
                .stream()
                .toList();
    }



    public static Map<Pais,Long> ejercicio4(List<Pais> paises){
        return paises
                .stream()
                .collect(Collectors.toMap(
                        p->p,
                        p-> p.getComunidadesAutonomas()
                                .stream()
                                .flatMap(c->c.getCiudades().stream())
                                .mapToLong(c->c.getPoblacion())
                                .sum()));
    }



    public static boolean ejercicio5(InformePais informePais, Pais pais) {
        return pais.equals(informePais.getPais())
                &&
                informePais.getTotalHabitantesPais().equals(pais.getComunidadesAutonomas()
                        .stream()
                        .flatMap(c->c.getCiudades().stream())
                        .mapToLong(c-> c.getPoblacion())
                        .sum())
                &&
                informePais.getTotalComunidadesAutonomasPais().equals(pais.getComunidadesAutonomas().size())
                &&
                informePais.getTotalCiudadesPais().equals(pais.getComunidadesAutonomas()
                        .stream()
                        .mapToInt(c->c.getCiudades().size()).sum());

    }



    public static InformeDetalladoPais ejercicio6(Pais pais) {
        InformeDetalladoPais informe = new InformeDetalladoPais();

        informe.setExtensionComunidades(pais
                .getComunidadesAutonomas()
                .stream()
                .collect(Collectors.toMap(c->c, c-> c.getCiudades().stream().mapToLong(p->p.getExtension()).sum())));

        informe.setPoblacionComunidades(pais
                .getComunidadesAutonomas()
                .stream()
                .collect(Collectors.toMap(c->c, c-> c.getCiudades().stream().mapToLong(p->p.getPoblacion()).sum())));

        informe.setComunidadMasGrande(pais.getComunidadesAutonomas()
                .stream().max(Comparator.comparingLong(c->c.getCiudades()
                        .stream().mapToLong(p->p.getExtension()).sum())).orElse(null));

        informe.setComunidadMasPoblada(pais.getComunidadesAutonomas()
                .stream().max(Comparator.comparingLong(c->c.getCiudades()
                        .stream().mapToLong(p->p.getPoblacion()).sum())).orElse(null));

        informe.setComunidadDeLaCapital(pais.getComunidadesAutonomas().stream()
                .filter(c-> c.getCiudades().contains(pais.getCapital()))
                .findFirst().orElse(null));


        return informe;
    }


}
