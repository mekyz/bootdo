package com.bootdo.business.controller;

import com.bootdo.business.domain.BusinessDO;
import com.bootdo.business.domain.Location;
import com.bootdo.business.service.BusinessService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.utils.Utils;
import com.spatial4j.core.io.GeohashUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @date 2018-03-11 12:15:28
 */

@Controller
@RequestMapping("/business/business")
public class BusinessController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(BusinessController.class);
    @Autowired
    private BusinessService businessService;

    @GetMapping()
    @RequiresPermissions("business:business:business")
    String Business() {
        return "business/business/business";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("business:business:business")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<BusinessDO> businessList = businessService.list(query);
        int total = businessService.count(query);
        PageUtils pageUtils = new PageUtils(businessList, total);
        return pageUtils;
    }

    @GetMapping("/add")
//	@RequiresPermissions("business:business:add")
    String add() {
        return "business/business/add";
    }

    @GetMapping("/edit/{tbId}")
    @RequiresPermissions("business:business:edit")
    String edit(@PathVariable("tbId") Integer tbId, Model model) {
        BusinessDO business = businessService.get(tbId);
        model.addAttribute("business", business);
        return "business/business/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
//	@RequiresPermissions("business:business:add")
    public R save(BusinessDO business, @RequestParam("file") MultipartFile file) {
        //判断tb_business表中是否有对应的数据
        business.setTbUserId(Integer.parseInt(getUserId() + ""));

        if (business.getTbStartingPrice() == null) {
            business.setTbStartingPrice(0);
        }
        R r = R.ok();
        if (!StringUtils.isNotEmpty(business.getTbStoreName())) {
            r.put("code", 1);
            r.put("msg", "请输入名字！");
            return r;
        }
        if (!StringUtils.isNotEmpty(business.getTbAddress())) {
            r.put("code", 1);
            r.put("msg", "请输入地址！");
            return r;
        }
        if (!StringUtils.isNotEmpty(business.getTbPhone())) {
            r.put("code", 1);
            r.put("msg", "请输入联系电话！");
            return r;
        }
        //有图片,直接上传
        if (StringUtils.isNotEmpty(file.getOriginalFilename())) {
            try {
                String url = Utils.qiNiuUpload(file.getInputStream());
                business.setTbPhoto(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //没有图片，提示
            if (!StringUtils.isNotEmpty(business.getTbPhoto())) {
                r.put("code", 1);
                r.put("msg", "请选择图片！");
                return r;
            }
        }
        try {
            //将地址解析为经纬度
            Location location = Utils.getLatLng(business.getTbAddress());
            double lat = location.getResult().getLocation().getLat();
            double lng = location.getResult().getLocation().getLng();
            business.setTbLatitude(String.valueOf(lat));
            business.setTbLongitude(String.valueOf(lng));
            //geo_code
            String geoCode = GeohashUtils.encodeLatLon(lat, lng, 5);
            business.setTbGeoCode(geoCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BusinessDO dao = queryBusiness(business.getTbUserId());
        if (dao == null) {
            if (businessService.save(business) > 0) {
                r.put("url", business.getTbPhoto());
                return r;
            }
            return r;
        } else {
            //修改
            business.setTbId(dao.getTbId());
            R r1 = update(business);
            r1.put("url", business.getTbPhoto());
            return r1;
        }
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("business:business:edit")
    public R update(BusinessDO business) {
        businessService.update(business);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("business:business:remove")
    public R remove(Integer tbId) {
        if (businessService.remove(tbId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("business:business:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] tbIds) {
        businessService.batchRemove(tbIds);
        return R.ok();
    }

    /**
     * 判断tb_business表中是否有对应的数据
     *
     * @param tbUserId
     * @return
     */
    public BusinessDO queryBusiness(Integer tbUserId) {
        return businessService.getByUserId(tbUserId);
    }

    @ResponseBody
    @RequestMapping("/detail")
    public R getDetail() {
        int id = Integer.parseInt(getUserId() + "");
        BusinessDO businessDO = queryBusiness(id);
        R r = R.ok();
        if (businessDO != null) {
            r.put("business", businessDO);
        } else {
            r.put("code", 1);
            r.put("msg", "未找到详细信息");
        }
        return r;
    }
}
