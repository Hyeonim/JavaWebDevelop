package com.yi.spring.controller;

import com.yi.spring.entity.Dinning;
import com.yi.spring.entity.Menu;
import com.yi.spring.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menuMining")
public class MenuTextMining {
    @Autowired
    MenuRepository menuRepository;
    @GetMapping("/")
    public String mining(Model model){


        List<Menu> list = menuRepository.findAll();
        Map<String, Integer> wordFrequency = new HashMap<>();

        for ( Menu item : list )
        {
            String cleanText = item.getMenuName().replaceAll("[^가-힣]", "");
            String[] words = cleanText.split("");

            for (String word : words) {
                // 단어가 이미 등록되어 있으면 빈도수 증가, 없으면 새로 등록
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }


        Map.Entry<String, Integer>[] entryArray = wordFrequency.entrySet().toArray(new Map.Entry[0]);
        class _TempDto {
            public String menuName;
            public String frequency = "";
            public int mapIndex = -1;
        }
        List< _TempDto > freqList = new ArrayList<>();
        for ( Menu item : list )
        {
            for (char c : item.getMenuName().toCharArray()) {
                String character = String.valueOf(c);
//                int frequency = wordFrequency.getOrDefault(character, 0);
                int frequency = 0;
                int index = -1;
                for (int i = 0; i < entryArray.length; i++) {
                    if (entryArray[i].getKey().equals(character)) {
                        frequency = entryArray[i].getValue();
                        index = i;
                        break; // 찾았으면 루프 종료
                    }
                }


                if (0 < frequency && 0 <= index) {
                    _TempDto itemData = new _TempDto();
                    itemData.menuName = item.getMenuName();
                    itemData.frequency += character + ":" + frequency + " ";
                    itemData.mapIndex = index;
                    freqList.add( itemData );
                }
            }
        }

        Map<String, List<String>> menuMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {

            List<String> elem = new ArrayList<>();

            for ( Menu item : list )
            {
                for (char c : item.getMenuName().toCharArray()) {
                    String character = String.valueOf(c);
                    if ( character.equals( entry.getKey() ))
                    {
                        elem.add( item.getMenuName() );
                        break;
                    }
                }
            }

            if ( !elem.isEmpty() )
                menuMap.put( entry.getKey(), elem );
        }






        model.addAttribute( "list", wordFrequency );
        model.addAttribute( "freq", freqList );
        model.addAttribute( "menuMap", menuMap );



        return "/miningTest";
    }
}
