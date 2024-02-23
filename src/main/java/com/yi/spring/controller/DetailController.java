package com.yi.spring.controller;

import com.yi.spring.entity.*;
import com.yi.spring.repository.DinningRepository;
import com.yi.spring.repository.ImgTableRepository;
import com.yi.spring.repository.ReviewRepository;
import com.yi.spring.service.EventService;
import com.yi.spring.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/*")
public class DetailController {

    @Autowired
    DinningRepository dinningRepository;

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ImgTableRepository imageTableRepository;

    @Autowired
    MenuService menuService;

    @Autowired
    EventService eventService;

    @GetMapping("/detail")
    public void getDinningByRestNo(@RequestParam Long restNo, Model model) {


        Optional<Dinning> dinningOptional = dinningRepository.findById((restNo));

        List<Review> list = reviewRepository.findByRestNo(new Dinning(Math.toIntExact(restNo)));

        double sum = list.stream().mapToDouble(Review::getRevScore).sum() * 10;

        double result = sum /list.size();

        dinningOptional.ifPresent(dinning -> model.addAttribute("dinning", dinning));

        model.addAttribute("list",list);
        model.addAttribute("reg", result);

        List<Menu> menuList =  menuService.getMenusByRestNo(Math.toIntExact(restNo));
        model.addAttribute("menuList", menuList);

        List<Event> eventList =  eventService.findByRestNo(dinningOptional.get());
        model.addAttribute("eventList", eventList);
//        return "detail";

    }

    @PostMapping("/reviewAdd")
    public void reviewAdd(@RequestParam MultipartFile file, Review review) {
        if (file.isEmpty()) {
            reviewRepository.save(review);
        } else {
            try {
                byte[] revImg = file.getBytes();
                review.setRevImg(revImg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        reviewRepository.save(review);
    }

    @GetMapping("reviewTest")
    public void reviewTest(){
//         processFilesInFolder( "C:/Users/lenovo/Documents/jjjjjjjh/imgs/6734cd4eeb84e676/가게사진2/가게 사진1/" );
    }
//    public void processFilesInFolder(String folderPath) {
//
//
//        File folder = new File(folderPath);
//        File[] files = folder.listFiles();
//
//        if (files != null) {
//            for (File file : files) {
//                // 파일 처리 작업 수행
//                System.out.println("Processing file: " + file.getName());
//                try {
//                    byte[] revImg = StreamUtils.copyToByteArray(new FileInputStream(file));
//
//
//                    if ( false ) {
//                    Review review = new Review();
//                    review.setRevContent("이미지 업로드 테스트");
//                    review.setRevScore(50);
//                    review.setRevWriteTime(LocalTime.now().toString());
//                    review.setRevImg(revImg);
//                    reviewRepository.save(review);
//                    }
//                    else
//                    {
//                        Matcher matcher = Pattern.compile(
//                                "([0-9]{3})"
//                        ).matcher( file.getName() );
//                        matcher.find();
//                        int restNo = Integer.parseInt(   matcher.group(1) );
//
//
//                        Dinning dinning = dinningRepository.findById(Long.valueOf(restNo)).orElse(null );
////                        dinning.setRestNo( restNo );
//                        dinning.setRestImg( revImg );
//                        System.out.println( dinning );
//                        dinningRepository.save( dinning );
//                    }
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        } else {
//            System.err.println("Folder is empty or does not exist: " + folderPath);
//        }
//    }


    @PostMapping("/ownerRestImgAdd")
    public void ownerRestImgAdd(@RequestParam MultipartFile file, Dinning dinning) {
        Optional<Dinning> dbObject = dinningRepository.findById((long) dinning.getRestNo() );
        if (dbObject.isEmpty())
            return;
        if (file.isEmpty())
            return;
        try {
            byte[] resImg = file.getBytes();
            dbObject.get().setRestImg( dinning.getRestImgMan().setRestImg(imageTableRepository, ImageFrom.REST, resImg) );
            dinningRepository.save(dbObject.get());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
