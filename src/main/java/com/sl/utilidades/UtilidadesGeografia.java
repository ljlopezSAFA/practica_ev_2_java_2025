package com.sl.utilidades;

import com.sl.modelos.*;

import java.util.*;
import java.util.stream.Collectors;

public class UtilidadesGeografia {


    public static List<Pais> ejercicio1(List<Pais> paises) {

        List<Pais> solucion = new ArrayList<>();

        for(Pais p: paises){
            if(!p.getPaisesVecinos().isEmpty()
                    && p.getComunidadesAutonomas().size() > 2){
                    solucion.add(p);
            }
        }
        return solucion;
    }


    public static Map<Continente, Integer> ejercicio2(List<Continente> continentes) {


        Map<Continente, Integer> solicion = new HashMap<>();

        for(Continente c: continentes){
            solicion.put(c, c.getPaises().size());
        }

        return solicion;
    }


    public List<Ciudad> ejercicio3(List<Ciudad> ciudades, List<Pais> paises) {

        Set<Ciudad> capitales = new HashSet<>();

        for(Ciudad c:ciudades){
            for(Pais p : paises){
                if(c.equals(p.getCapital())){
                        capitales.add(c);
                }
            }
        }
        return new ArrayList<>(capitales);
    }



    public static Map<Pais,Long> ejercicio4(List<Pais> paises){

        Map<Pais,Long> solucion = new HashMap<>();

        for(Pais p: paises){

            Long poblacionTotal = 0L;

            for(ComunidadAutonoma ca: p.getComunidadesAutonomas()){
                for(Ciudad c: ca.getCiudades()){
                    poblacionTotal+= c.getPoblacion();
                }
            }

            solucion.put(p,poblacionTotal);

        }


        return solucion;
    }



    public static boolean ejercicio5(InformePais informePais, Pais pais) {


        boolean condicion1 = false;
        boolean condicion2 = false;
        boolean condicion3 = false;
        boolean condicion4 = false;



        if(informePais.getPais().equals(pais)){
           condicion1 = true;
        }

        Long poblacionTotal = 0L;

        for(ComunidadAutonoma ca: pais.getComunidadesAutonomas()){
            for(Ciudad c: ca.getCiudades()){
                poblacionTotal+= c.getPoblacion();
            }
        }

        if(poblacionTotal.equals(informePais.getTotalHabitantesPais())){
            condicion2= true;
        }



        if(informePais.getTotalComunidadesAutonomasPais()
                == pais.getComunidadesAutonomas().size()){
            condicion3 = true;
        }


        Integer totalCiudades = 0;

        for(ComunidadAutonoma c: pais.getComunidadesAutonomas()){
            totalCiudades += c.getCiudades().size();
        }

        if(totalCiudades.equals(informePais.getTotalCiudadesPais())){
            condicion4 = true;
        }

        return condicion1 && condicion2 && condicion3 && condicion4;

    }



    public static InformeDetalladoPais ejercicio6(Pais pais) {

        InformeDetalladoPais informeDetalladoPais = new InformeDetalladoPais();

        //EXTENSION COMUNIDADES
        Map<ComunidadAutonoma, Long> mapaExtensiones = new HashMap<>();

        //EXTENSION COMUNIDADES
        Map<ComunidadAutonoma, Long> mapaPoblacion = new HashMap<>();

        //CAPITAL
        ComunidadAutonoma comunidadCapital = null;

        for(ComunidadAutonoma ca: pais.getComunidadesAutonomas()){
            Long extentionComunidad = 0L;
            Long poblacionComunidad = 0L;

            for(Ciudad c: ca.getCiudades()){
                extentionComunidad+= c.getExtension();
                poblacionComunidad+= c.getPoblacion();
            }

            mapaExtensiones.put(ca, extentionComunidad);
            mapaPoblacion.put(ca, poblacionComunidad);

        }

        informeDetalladoPais.setExtensionComunidades(mapaExtensiones);
        informeDetalladoPais.setPoblacionComunidades(mapaPoblacion);



        ComunidadAutonoma masPoblada = null;
        Long poblacionTotal = 0L;

        ComunidadAutonoma masGrande = null;
        Long extension = 0L;

        for(ComunidadAutonoma ca: mapaPoblacion.keySet()){
            if(masPoblada == null ||  poblacionTotal<  mapaPoblacion.get(ca)){
                masPoblada = ca;
                poblacionTotal = mapaPoblacion.get(ca);

            }

            if(masGrande == null ||  extension<  mapaExtensiones.get(ca)){
                masGrande = ca;
                extension = mapaExtensiones.get(ca);
            }
        }

        informeDetalladoPais.setComunidadMasGrande(masGrande);
        informeDetalladoPais.setComunidadMasPoblada(masPoblada);





        for(ComunidadAutonoma ca: pais.getComunidadesAutonomas()){
            if(ca.getCiudades().contains(pais.getCapital())){
                comunidadCapital = ca;
            }

        }

        informeDetalladoPais.setComunidadDeLaCapital(comunidadCapital);




        return informeDetalladoPais;
    }


}
