package com.chunjae.test06th.ctrl;

import com.chunjae.test06th.biz.FoodService;
import com.chunjae.test06th.entity.School;
import com.chunjae.test06th.utils.Week;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/food/*")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("foodList")		// board/list.do
    public String boardList(Model model) throws Exception {
        return "food/foodList";
    }

    @PostMapping("foodList")		// board/list.do
    public String getBoardList(HttpServletRequest request, Model model) {
        try {
            String sc_name = request.getParameter("name");
            System.out.println(sc_name);
            School dto = foodService.getSchool(sc_name);
            System.out.println(dto.toString());

            List<String> ddishList; // 식단
            List<String> mlsvList; // 날짜
            List<String> orplcList; // 원산지
            List<String> calList; // 칼로리
            List<String> ntrList; // 영양

            Week week = new Week();

            List<String> date = week.getDate();

            String codeS = dto.getSc_code();
            String codeK = dto.getEo_code();
            String schoolName = dto.getSc_name();

            int minValue = 1;
            int maxValue = 5;

            foodService.menuServiceSet(codeS, codeK, date, minValue, maxValue);
            ddishList = foodService.getDdishList();
            mlsvList = foodService.getMlsvList();
            orplcList = foodService.getOrplcList();
            calList = foodService.getCalList();
            ntrList = foodService.getNtrList();

            model.addAttribute("schoolName", schoolName);
            model.addAttribute("ddishList", ddishList);
            model.addAttribute("mlsvList", mlsvList);
            model.addAttribute("orplcList", orplcList);
            model.addAttribute("calList", calList);
            model.addAttribute("ntrList", ntrList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return "food/foodList";
        }
    }
}
