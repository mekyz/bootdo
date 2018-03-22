package com.bootdo.api.controller;

import com.bootdo.business.domain.BusinessDO;
import com.bootdo.business.service.BusinessService;
import com.bootdo.commodity.domain.CommodityDO;
import com.bootdo.commodity.service.CommodityService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.member.domain.MemberDO;
import com.bootdo.member.service.MemberService;
import com.bootdo.order.domain.OrderDO;
import com.bootdo.order.service.OrderService;
import com.bootdo.utils.Utils;
import com.spatial4j.core.io.GeohashUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ch.hsr.geohash.GeoHash;

/**
 * @author 阿钟
 * 提供给app端调用的接口
 */

@Controller
@RequestMapping("/api")
public class ApiController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ApiController.class);
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;

    /**
     * 商家列表
     *
     * @param lat      纬度
     * @param lng      经度
     * @param page     页码
     * @param pageSize 没页条数
     *                 <p>
     *                 根据经纬度获取周边的商家
     *                 </p>
     * @return
     */
    @ResponseBody
    @PostMapping("/stores")
    public R takeaway(String lat, String lng, int page, int pageSize) {
        R r = R.ok();
        if (!StringUtils.isNotEmpty(lng)) {
            r.put("code", 1);
            r.put("msg", "经度不能为空！");
            return r;
        }
        if (!StringUtils.isNotEmpty(lat)) {
            r.put("code", 1);
            r.put("msg", "纬度不能为空！");
            return r;
        }
        if (page <= 0) {
            r.put("code", 1);
            r.put("msg", "请输入页码！");
            return r;
        }
        if (pageSize <= 0) {
            r.put("code", 1);
            r.put("msg", "请输入每页查询条数！");
            return r;
        }
        /**
         * http://blog.csdn.net/ghsau/article/details/50591932
         * 查询5KM范围的geoCode（商家店铺）
         */
        double latd = Double.parseDouble(lat);
        double lngd = Double.parseDouble(lng);
        String geoCode = GeohashUtils.encodeLatLon(latd, lngd, 5);
        HashMap<String, Object> map = new HashMap<>();
        // 获取附近的其他8块区域
        GeoHash[] geoHash = getPoiGeoHash(latd, lngd);
        for (int i = 1; i <= geoHash.length; i++) {
            map.put("geoCode" + i, geoHash[i - 1].toBase32());
        }
        //分页
        int start = (page - 1) * pageSize;
        map.put("geoCode", geoCode);
        map.put("start", start);
        map.put("end", pageSize);
        List<BusinessDO> list = businessService.getBusinessByLocation(map);
        Iterator<BusinessDO> it = list.iterator();
        while (it.hasNext()) {
            BusinessDO bDo = it.next();
            double distance = Utils.getDistance(lngd, latd, Double.valueOf(bDo.getTbLongitude()),
                    Double.valueOf(bDo.getTbLatitude()));
            bDo.setDistance(distance);
            if (distance > 5000) {
                it.remove();
            }
        }
        Collections.sort(list, new Comparator<BusinessDO>() {
            @Override
            public int compare(BusinessDO o1, BusinessDO o2) {
                if (o1.getDistance() > o2.getDistance()) {
                    return 1;
                }
                if (o1.getDistance() == o2.getDistance()) {
                    return 0;
                }
                return -1;
            }
        });
        r.put("data", list);
        return r;
    }

    private GeoHash[] getPoiGeoHash(double lat, double lng) {
        GeoHash geoHash = GeoHash.withCharacterPrecision(lat, lng, 5);
        // 当前
        System.out.println(geoHash.toBase32());
        // N, NE, E, SE, S, SW, W, NW
        return geoHash.getAdjacent();
    }

    /**
     * 商品列表
     *
     * @param tbId
     * @return
     */
    @ResponseBody
    @PostMapping("/shopping")
    public R businessShopping(String tbId) {
        R r = R.ok();
        if (!StringUtils.isNotEmpty(tbId)) {
            r.put("code", 1);
            r.put("msg", "商家id不能为空！");
            return r;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("tbId", tbId);
        List<CommodityDO> list = commodityService.list(map);
        r.put("data", list);
        return r;
    }

    /**
     * 获取验证码
     *
     * @param phone 手机号
     * @return
     */
    @ResponseBody
    @PostMapping("/vcode")
    public R getVcode(String phone) {
        R r = R.ok();
        if (!StringUtils.isNotEmpty(phone)) {
            r.put("code", 1);
            r.put("msg", "手机号不能为空！");
            return r;
        }
        String vCode = Utils.getVCode(phone);
        if (!StringUtils.isNotEmpty(vCode)) {
            r.put("code", 1);
            r.put("msg", "验证码发送失败！");
            return r;
        }
        r.put("vCode", vCode);
        return r;
    }

    /**
     * 注册 登录接口
     *
     * @param phone 手机号
     * @param type  1.用户、2.骑手
     * @return
     */
    @ResponseBody
    @PostMapping("/reglogin")
    public R register(String phone, String type) {
        R r = R.ok();
        if (!StringUtils.isNotEmpty(phone)) {
            r.put("code", 1);
            r.put("msg", "手机号不能为空！");
            return r;
        }
        if (!StringUtils.isNotEmpty(type)) {
            r.put("code", 1);
            r.put("msg", "用户类型不能为空！");
            return r;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("tmAccount", phone);
        List<MemberDO> list = memberService.list(map);
        if (list.size() > 0) {
            if (list.get(0).getTmType() == Integer.parseInt(type)) {
                //登录
                r.put("msg", "登录成功");
                r.put("data", list.get(0));
            } else {
                //登录
                r.put("code", 1);
                r.put("msg", "登录失败！该账号不属于此客户端");
            }
            return r;
        } else {
            //注册
            MemberDO member = new MemberDO();
            member.setTmAccount(phone);
            member.setTmType(Integer.parseInt(type));
            member.setTmName("elm" + phone);
            member.setTmPhone(phone);
            int save = memberService.save(member);
            if (save > 0) {
                r.put("msg", "注册成功");
                r.put("data", member);
            }
            return r;
        }
    }

    /**
     * 订单提交接口
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/add/order")
    public R addOrder(String tmId, String reName, String rePhone, String reAddress, String tcidNum, String bussinessId,
                      String price) {
        R r = R.error();
        if (!StringUtils.isNotEmpty(tmId)) {
            r.put("code", 1);
            r.put("msg", "用户id不能为空！");
            return r;
        }
        if (!StringUtils.isNotEmpty(reName)) {
            r.put("code", 1);
            r.put("msg", "收货人姓名不能为空！");
            return r;
        }
        if (!StringUtils.isNotEmpty(rePhone)) {
            r.put("code", 1);
            r.put("msg", "收货人电话不能为空！");
            return r;
        }
        if (!StringUtils.isNotEmpty(reAddress)) {
            r.put("code", 1);
            r.put("msg", "收货人地址不能为空！");
            return r;
        }
        if (!StringUtils.isNotEmpty(tcidNum)) {
            r.put("code", 1);
            r.put("msg", "商品信息不能为空！");
            return r;
        }
        if (!StringUtils.isNotEmpty(bussinessId)) {
            r.put("code", 1);
            r.put("msg", "商家id不能为空！");
            return r;
        }
        if (!StringUtils.isNotEmpty(price)) {
            r.put("code", 1);
            r.put("msg", "订单价格不能为空！");
            return r;
        }
        OrderDO orderDO = new OrderDO();
        orderDO.setToMemberId(Integer.parseInt(tmId));
        orderDO.setToNumber("ELM" + Utils.yyyyMMddHHmm());
        orderDO.setToStatus(1);
        orderDO.setToReName(reName);
        orderDO.setToRePhone(rePhone);
        orderDO.setToReAddress(reAddress);
        orderDO.setToBussinessId(Integer.parseInt(bussinessId));
        orderDO.setToCreateTime(new Date(System.currentTimeMillis()));
        orderDO.setToTcidNum(tcidNum);
        orderDO.setToPrice(new BigDecimal(Double.valueOf(price)));
        int save = orderService.save(orderDO);
        if (save > 0) {
            return payOrder(orderDO.getToId());
        }
        return r;
    }

    /**
     * 订单支付
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/pay/order")
    public R payOrder(int toId) {
        R r = R.ok();
        OrderDO orderDO = new OrderDO();
        orderDO.setToId(toId);
        orderDO.setToStatus(2);
        int update = orderService.update(orderDO);
        if (update > 0) {
            return r;
        }
        r = R.error();
        return r;
    }

    /**
     * 订单列表
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/orderlist")
    public R orderList(int tmId, int page, int pageSize) {
        R r = R.ok();
        int offset = (page - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        map.put("toMemberId", tmId);
        map.put("offset", offset);
        map.put("limit", pageSize);
        List<OrderDO> list = orderService.list(map);
        for (OrderDO orderDO : list) {
            //商家信息
            BusinessDO businessDO = businessService.get(orderDO.getToBussinessId());
            orderDO.setBusinessDO(businessDO);
            //商品信息
            JSONArray array = new JSONArray(orderDO.getToTcidNum());
            //默认取订单里面商品的第一个商品名字
            String tcName = "";
            int count = 0;
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                int tcId = object.optInt("tcId");
                count = object.optInt("count") + count;
                if (i == 0) {
                    CommodityDO commodityDO = commodityService.get(tcId);
                    tcName = commodityDO.getTcName();
                }
            }
            if (count > 1) {
                orderDO.setShoppingInfo(tcName + " 等" + count + "件商品");
            } else if (count == 1) {
                orderDO.setShoppingInfo(tcName);
            }
        }
        r.put("data", list);
        return r;
    }
}
