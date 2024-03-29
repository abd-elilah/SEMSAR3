package com.example.semsar3.Controllers;

import com.example.semsar3.entities.Logement;
import com.example.semsar3.repositories.LogementRepository;
import com.example.semsar3.repositories.TypeRepository;
import com.example.semsar3.repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LogementController {

    @Autowired
    LogementRepository logementRepository ;

    @Autowired
    VilleRepository villeRepository;
    @Autowired
    TypeRepository typeRepository;

    @RequestMapping("/find")

    public String  findLogement(Model model  , @RequestParam(value = "page"  , defaultValue = "0" )  int page ,
                                @RequestParam(value = "size" ,defaultValue = "6") int size ,
                                @RequestParam(value = "ville" , defaultValue = "Choisiser") String ville ,
                                @RequestParam(value = "type" , defaultValue = "Choisiser") String type){

        // test coming ville , type variables
        //System.out.println(ville);
        //System.out.println(type);

        model.addAttribute("villes" ,villeRepository.findAll() );
        model.addAttribute("types" , typeRepository.findAll() );
        Page<Logement> logements = logementRepository.findLogementsByVilleNomAndTypeTypeLog(ville , type , PageRequest.of(page,size));
        model.addAttribute("logements" , logements);
        model.addAttribute("ville" , ville);
        model.addAttribute("type" , type);
        model.addAttribute("pages" , new int[logements.getTotalPages()]);
        model.addAttribute("pageCourant" , page);
        return  "resultats";


    }


    @RequestMapping(value = "/detail")
    public String detail(){
        return "result-details";
    }







}
