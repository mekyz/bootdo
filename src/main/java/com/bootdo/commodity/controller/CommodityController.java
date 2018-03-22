package com.bootdo.commodity.controller;

import com.bootdo.commodity.domain.CommodityDO;
import com.bootdo.commodity.service.CommodityService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.utils.Utils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-12 16:37:04
 */

@Controller
@RequestMapping("/commodity/commodity")
public class CommodityController extends BaseController {
    @Autowired
    private CommodityService commodityService;

    @GetMapping()
    @RequiresPermissions("commodity:commodity:commodity")
    String Commodity() {
        return "commodity/commodity/commodity";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("commodity:commodity:commodity")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        params.put("tbId", getUserId());
        Query query = new Query(params);
        List<CommodityDO> commodityList = commodityService.list(query);
        int total = commodityService.count(query);
        PageUtils pageUtils = new PageUtils(commodityList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("commodity:commodity:add")
    String add() {
        return "commodity/commodity/add";
    }

    @GetMapping("/edit/{tcId}")
    @RequiresPermissions("commodity:commodity:edit")
    String edit(@PathVariable("tcId") Integer tcId, Model model) {
        CommodityDO commodity = commodityService.get(tcId);
        model.addAttribute("commodity", commodity);
        return "commodity/commodity/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("commodity:commodity:add")
    public R save(CommodityDO commodity, @RequestParam("file") MultipartFile file) {
        commodity.setTbId(Integer.parseInt(getUserId() + ""));
        R r = R.ok();
        if (!StringUtils.isNotEmpty(commodity.getTcName())) {
            r.put("code", 1);
            r.put("msg", "请输入商品名字！");
            return r;
        }
        //有图片,直接上传
        if (StringUtils.isNotEmpty(file.getOriginalFilename())) {
            try {
                String url = Utils.qiNiuUpload(file.getInputStream());
                commodity.setTcPhoto(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            r.put("code", 1);
            r.put("msg", "请选择商品图片！");
            return r;
        }
        if (commodity.getTcPrice() == null) {
            r.put("code", 1);
            r.put("msg", "请输入商品价格！");
            return r;
        }
        if (!StringUtils.isNotEmpty(commodity.getTcDescription())) {
            r.put("code", 1);
            r.put("msg", "请输入商品描述！");
            return r;
        }
        if (commodityService.save(commodity) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("commodity:commodity:edit")
    public R update(CommodityDO commodity, @RequestParam("file") MultipartFile file) {
        //有图片,直接上传
        if (StringUtils.isNotEmpty(file.getOriginalFilename())) {
            try {
                String url = Utils.qiNiuUpload(file.getInputStream());
                commodity.setTcPhoto(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        commodityService.update(commodity);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("commodity:commodity:remove")
    public R remove(Integer tcId) {
        if (commodityService.remove(tcId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("commodity:commodity:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] tcIds) {
        commodityService.batchRemove(tcIds);
        return R.ok();
    }

}
