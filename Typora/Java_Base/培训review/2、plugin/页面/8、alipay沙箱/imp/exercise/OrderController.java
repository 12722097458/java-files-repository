package com.igeek.shop.controller.order;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.igeek.shop.alipay_config.AlipayConfig;
import com.igeek.shop.entity.*;
import com.igeek.shop.service.OrdersService;
import com.igeek.shop.utils.PageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

@Controller("orderOrderController")
@RequestMapping(value = "/order")
public class OrderController {

    @Resource(name = "ordersService")
    private OrdersService ordersService;

    @RequestMapping(value = "/toOrderInfo")
    public String toOrderInfo(){
        return "order/order_info";
    }

    @RequestMapping(value = "/toOrderPay")
    public String toOrderPay(String oid, Model model){

        Orders orders = ordersService.searchOrdersByOid(oid);
        model.addAttribute("orders", orders);

        return "order/order_pay";
    }

    @RequestMapping(value = "/doOrderPay")
    public void doOrderPay(String oid, HttpServletResponse response) throws IOException, AlipayApiException {

        response.setContentType("text/html;charset=UTf-8");
        PrintWriter out = response.getWriter();
        Orders orders = ordersService.searchOrdersByOid(oid);

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = orders.getOid();
        //付款金额，必填
        String total_amount = "1";
        //订单名称，必填
        String subject = orders.getUid();
        //商品描述，可空
        String body = orders.getName();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //输出
        out.println(result);
    }

    @RequestMapping(value = "/notify_url")
    public void notify_url(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException, SQLException {
        //获取支付宝POST过来反馈信息

        response.setContentType("text/html;charset=UTf-8");
        PrintWriter out = response.getWriter();

        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

        /* 实际验证过程建议商户务必添加以下校验：
        1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
        4、验证app_id是否为该商户本身。
        */
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            out.println("success");

        }else {//验证失败
            out.println("fail");

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
    }

    @RequestMapping(value = "/return_url")
    public String return_url(HttpServletRequest request) throws IOException, AlipayApiException, SQLException {
        //获取支付宝POST过来反馈信息
        /*
            *************************页面功能说明*************************
            * 该页面仅做页面展示，业务逻辑处理请勿在该页面执行
            */

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);

            Orders orders = new Orders();
            orders.setOid(out_trade_no);
            orders.setTrade_no(trade_no);
            ordersService.updateOrders(orders);
            return "redirect:/order/to_return_url?oid="+orders.getOid()+"&trade_no="+orders.getTrade_no()+"&total="+total_amount;

        }else {
            System.out.println("验签失败");
            return "order/order_falied";
        }
    }

    @RequestMapping(value = "/to_return_url")
    public String to_return_url() {
        return "order/order_success";
    }


    @RequestMapping(value = "/doSubmitOrdersAjax")
    @ResponseBody
    public Map<String, Object> doSubmitOrdersAjax(Orders orders, HttpSession session) {
        //确认订单已经将商品放入了数据库中,把orders对象填充完整。。。

        String oid = UUID.randomUUID().toString().replace("-", "");
        Date ordertime = new Date();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User)session.getAttribute("user");
        //生成订单，购物车不可能为空
        orders.setOid(oid);
        orders.setOrdertime(ordertime);
        orders.setTotal(cart.getAllMoney());
        orders.setState(0);
        orders.setUid(user.getUid());

        List<CartItem> cartItems = cart.getCartItems();   //product + count
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (int i = 0; i < cartItems.size(); i++) {
            Product product = cartItems.get(i).getProduct();
            int count = cartItems.get(i).getCount();
            String itemid = UUID.randomUUID().toString().replace("-", "");
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(count);
            orderItem.setItemid(itemid);
            orderItem.setOid(oid);
            orderItem.setPid(product.getPid());
            orderItem.setPrice(product.getMarket_price());
            orderItem.setPimage(product.getPimage());
            orderItems.add(orderItem);
        }
        orders.setOrderItems(orderItems);
        Map<String,Object> map = new HashMap<>();

        int result = ordersService.insertOrders(orders);
        if (result == 1) {
            session.removeAttribute("cart");
            map.put("result", oid);
            return map;
        }
        map.put("result", -1);
        return map;
    }

    @RequestMapping(value = "/doSearchOrders")
    public String doSearchOrders(HttpSession session,Model model,
                                 @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){

        User user = (User)session.getAttribute("user");
        PageUtils<Orders> pageUtils = new PageUtils<Orders>();
        Orders condition = new Orders();
        //能够查看订单，一定是登录状态，不会空指针异常
        condition.setUid(user.getUid());
        int pageSize = 3;

        pageUtils.setPageSize(pageSize);
        pageUtils.setPageNo(pageNo);
        ordersService.searchByCondition(condition, pageUtils);
        model.addAttribute("pageUtils", pageUtils);
        return "order/order_list";
    }

}
