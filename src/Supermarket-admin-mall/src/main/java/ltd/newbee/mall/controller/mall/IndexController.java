package ltd.newbee.mall.controller.mall;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.IndexConfigTypeEnum;
import ltd.newbee.mall.common.NewBeeMallCategoryLevelEnum;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexCarouselVO;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexCategoryVO;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexConfigGoodsVO;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.service.NewBeeMallCarouselService;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.NewBeeMallIndexConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Resource
    private NewBeeMallCarouselService newBeeMallCarouselService;

    @Resource
    private NewBeeMallIndexConfigService newBeeMallIndexConfigService;

    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;

    @GetMapping({"/index", "/", "/index.html"})
    public String indexPage(HttpServletRequest request) {
        List<NewBeeMallIndexCategoryVO> categories = newBeeMallCategoryService.getCategoriesForIndex();
        if (CollectionUtils.isEmpty(categories)) {
            return "error/error_5xx";
        }
        List<NewBeeMallIndexCarouselVO> carousels = newBeeMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER, Constants.INDEX_CAROUSEL_TYPE_UNFIXED);
        List<NewBeeMallIndexConfigGoodsVO> hotGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_GOODS_HOT_NUMBER);
        List<NewBeeMallIndexConfigGoodsVO> newGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(), Constants.INDEX_GOODS_NEW_NUMBER);
        List<NewBeeMallIndexConfigGoodsVO> recommendGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(), Constants.INDEX_GOODS_RECOMMOND_NUMBER);
        request.setAttribute("categories", categories);//分类数据
        request.setAttribute("carousels", carousels);//轮播图
        request.setAttribute("hotGoodses", hotGoodses);//热销商品
        request.setAttribute("newGoodses", newGoodses);//新品
        request.setAttribute("recommendGoodses", recommendGoodses);//推荐商品
        return "mall/index";
    }

    @GetMapping({"/indexGetFirstCategories"})
    @ResponseBody
    public List<GoodsCategory> indexGetFirstCategories(HttpServletRequest request) {

        List<GoodsCategory> categories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());

        return categories;
    }

    @GetMapping({"/indexGetSecondCategories"})
    @ResponseBody
    public List<GoodsCategory> indexGetSecondCategories(@RequestParam Map<String, Object> params, HttpServletRequest request) {

        Long parentCategoryId = 0L;
        if (params.containsKey("parentCategoryId") && !StringUtils.isEmpty(params.get("parentCategoryId") + "")) {
            parentCategoryId = Long.valueOf(params.get("parentCategoryId") + "");
        }else {
            List<GoodsCategory> categories = newBeeMallCategoryService.selectByLevelAndParentIdsLimitOne(Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());
            parentCategoryId = categories.get(0).getCategoryId();
        }

        List<GoodsCategory> categories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(parentCategoryId), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());

        return categories;
    }

    @GetMapping({"/indexGetCarousels"})
    @ResponseBody
    public List<NewBeeMallIndexCarouselVO> indexGetCarousels(HttpServletRequest request) {

        List<NewBeeMallIndexCarouselVO> carousels = newBeeMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER, Constants.INDEX_CAROUSEL_TYPE_UNFIXED);

        return carousels;
    }

    @GetMapping({"/indexGetFixedImages"})
    @ResponseBody
    public List<NewBeeMallIndexCarouselVO> indexGetFixedImages(HttpServletRequest request) {

        List<NewBeeMallIndexCarouselVO> carousels = newBeeMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER, Constants.INDEX_CAROUSEL_TYPE_FIXED);

        return carousels;
    }

    @GetMapping({"/indexGetHotGoods"})
    @ResponseBody
    public List<NewBeeMallIndexConfigGoodsVO> indexGetHotGoods(HttpServletRequest request) {

        List<NewBeeMallIndexConfigGoodsVO> hotGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_HOT.getType(), Constants.INDEX_GOODS_HOT_NUMBER);
        return hotGoodses;
    }

    @GetMapping({"/indexGetNewGoods"})
    @ResponseBody
    public List<NewBeeMallIndexConfigGoodsVO> indexGetNewGoods(HttpServletRequest request) {

        List<NewBeeMallIndexConfigGoodsVO> newGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_NEW.getType(), Constants.INDEX_GOODS_NEW_NUMBER);
        return newGoodses;
    }

    @GetMapping({"/indexGetRecommendGoodses"})
    @ResponseBody
    public List<NewBeeMallIndexConfigGoodsVO> indexGetRecommendGoodses(HttpServletRequest request) {

        List<NewBeeMallIndexConfigGoodsVO> recommendGoodses = newBeeMallIndexConfigService.getConfigGoodsesForIndex(IndexConfigTypeEnum.INDEX_GOODS_RECOMMOND.getType(), Constants.INDEX_GOODS_RECOMMOND_NUMBER);
        return recommendGoodses;
    }
}
