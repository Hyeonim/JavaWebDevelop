package com.yi.spring.controller;

import com.yi.spring.entity.Dinning;
import com.yi.spring.repository.DinningRepository;
import com.yi.spring.service.DinningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/*")
public class MapController {

    @Autowired
    DinningRepository dinningRepository;

    private DinningService dinningService;

    public MapController(DinningService dinningService) {
        this.dinningService = dinningService;
    }


    @GetMapping("view")
    public String map(Model model) {
        List<Dinning> list = dinningService.findAll();

        System.out.println(list.get(1));


//        for (Dinning diningRest : list) {
//            System.out.println("Latitude: " + diningRest.getRestLatitude() + ", Longitude: " + diningRest.getRestLongitude() + "가게 이름" + diningRest.getRestName());
//        }

        List<Dinning> respList = new ArrayList<>();
        for (Dinning diningRest : list) {
            Dinning elem = new Dinning();
            elem.setRestLatitude(diningRest.getRestLatitude());
            elem.setRestLongitude(diningRest.getRestLongitude());
            elem.setRestName(diningRest.getRestName());
            respList.add(elem);
        }


        model.addAttribute("list", respList);

        return "map";
    }

    @GetMapping("manyReview")
    public String manyReview(@RequestParam(name = "keyword") String restName,
                             Model model) {
        // List로 캐스팅하여 반환된 데이터를 처리

        System.out.println("Controller method called with keyword: " + restName);


        if(restName == null || restName.isEmpty()) {
            List<Dinning> list = dinningRepository.findAllWithTotalReviewsOrderByTotalReviewsDesc();
            model.addAttribute("list", list);

        }
        else {
            List<Dinning> list2 = dinningRepository.findAllWithTotalReviewsOrderByTotalReviewsDesc(restName);
            model.addAttribute("list", list2);

        }


        return "search"; // 뷰 이름 반환
    }

}