package com.bootdo.order.controller;

import com.bootdo.business.domain.BusinessDO;
import com.bootdo.business.service.BusinessService;
import com.bootdo.commodity.domain.CommodityDO;
import com.bootdo.commodity.service.CommodityService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.order.domain.OrderDO;
import com.bootdo.order.service.OrderService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author 阿钟
 * @email 1992lcg@163.com
 * @date 2018-03-19 22:36:31
 */

@Controller
@RequestMapping("/order/order")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private CommodityService commodityService;

    @GetMapping()
    @RequiresPermissions("order:order:order")
    String Order() {
        return "order/order/order";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("order:order:order")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        BusinessDO businessDO = businessService.getByUserId(getUserId().intValue());
        params.put("toBussinessId", businessDO.getTbId());
        Query query = new Query(params);
        List<OrderDO> orderList = orderService.list(query);
        //处理数据
        for (OrderDO orderDO : orderList) {
            String status = getStatus(orderDO.getToStatus());
            orderDO.setStatus(status);
            //商品信息
            JSONArray array = new JSONArray(orderDO.getToTcidNum());
            //默认取订单里面商品的第一个商品名字
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int tcId = object.optInt("tcId");
                int count = object.optInt("count");
                CommodityDO commodityDO = commodityService.get(tcId);
                sb.append(commodityDO.getTcName()).append("　x").append(count);
                sb.append("</br>");
            }
            orderDO.setShoppingInfo(sb.toString());
        }
        int total = orderService.count(query);
        PageUtils pageUtils = new PageUtils(orderList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("order:order:add")
    String add() {
        return "order/order/add";
    }

    /**
     * @param type type 1,接单 2,拒绝接单
     * @return
     */
    @GetMapping("/reorder")
    @ResponseBody
    public R reOrder(Integer toId, Integer type) {
        R r = R.ok();
        OrderDO orderDO = new OrderDO();
        orderDO.setToId(toId);
        if (type == 1) {
            orderDO.setToStatus(3);
        } else if (type == 2) {
            orderDO.setToStatus(8);
        }
        int update = orderService.update(orderDO);
        if (update > 0) {
            return r;
        }
        return R.error();
    }

    private String getStatus(int toStatus) {
        String[] status = {"删除", "待支付", "待接单", "待骑手取货"};
        return status[toStatus];
    }

}
