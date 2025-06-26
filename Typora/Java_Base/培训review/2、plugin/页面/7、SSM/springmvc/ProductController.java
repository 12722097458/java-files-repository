package com.igeek.shop.controller.admin;

import com.igeek.shop.entity.Category;
import com.igeek.shop.entity.EasyUIResult;
import com.igeek.shop.entity.Product;
import com.igeek.shop.entity.ProductCondition;
import com.igeek.shop.service.CategoryService;
import com.igeek.shop.service.ProductService;
import com.igeek.shop.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller("adminProductController")
@RequestMapping(value = "/admin")
public class ProductController {

    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "categoryService")
    private CategoryService categoryService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/toInfo")
    public String toInfo(String pid, Model model) {

        Product product = productService.selectById(pid);
        model.addAttribute("product", product);
        return "administrator/product/info";
    }

    @RequestMapping(value = "/toList")
    public String toList() {
        return "administrator/product/list_easyui";
    }

    @RequestMapping(value = "/doListAjaxEasyUI")
    @ResponseBody
    public EasyUIResult<Product> doListAjaxEasyUI(ProductCondition productCondition,
                                                  @RequestParam(name = "page",defaultValue = "1") Integer page,
                                                  @RequestParam(name = "rows",defaultValue = "3") Integer pageSize) {

        System.out.println("查询条件："+productCondition);
        PageUtils<Product> pageUtils = new PageUtils<>();

        pageUtils.setPageNo(page);
        pageUtils.setPageSize(pageSize);

        productService.selectByCondition(productCondition, pageUtils);

        EasyUIResult<Product> easyUIResult = new EasyUIResult<>();
        easyUIResult.setTotal(pageUtils.getTotalCount());
        easyUIResult.setRows(pageUtils.getData());
        return easyUIResult;
    }

    @RequestMapping(value = "/doAddCategoriesAjax")
    @ResponseBody
    public List<Category> doAddCategoriesAjax() {

        List<Category> categories = categoryService.selectAll();
        return categories;
    }

    @RequestMapping(value = "/doDeleteProductAjax")
    @ResponseBody
    public Map<String, Integer> doDeleteProductAjax(String pid) {

        Integer result = productService.deleteByPid(pid);
        Map<String,Integer> map = new HashMap<>();
        map.put("result", result);
        return map;
    }

    @RequestMapping(value = "/toAdd")
    public String toAdd() {
        return "administrator/product/add";
    }

    @RequestMapping(value = "/doAddProductAjax")
    @ResponseBody
    public Map<String, Integer> doAddProductAjax(Product product,
                                                 @RequestParam(name= "upload") MultipartFile partFile) {

        // 存储路径
        // 获取服务器存放文件路径
        String imgPath = "products/1";
        String path = servletContext.getRealPath(imgPath);
        System.out.println("path:"+path);
        File uploadFile = new File(path);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }

        // 根据名字，获取文件的那个part对象
        //Part part = request.getPart("onload");

        // 循环处理上传的文件
        // 获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
        //String header = part.getHeader("content-disposition");

        // 获取文件名
        String fileName = partFile.getOriginalFilename();

        //重命名
        String newFileName = UUID.randomUUID().toString();

        int lastIndexOfDot = fileName.lastIndexOf(".");
        if (lastIndexOfDot != -1) {
            // 有后缀,则要得到后缀
            String subfix = fileName.substring(lastIndexOfDot);
            newFileName = newFileName + subfix;
        }
        Map<String,Integer> map = new HashMap<>();
        try {
            // 把文件写到指定路径
            //part.write(uploadFile + File.separator + newFileName);
            File file = new File(uploadFile + File.separator + newFileName);
            partFile.transferTo(file);

            product.setPimage(imgPath+"/"+newFileName);
            product.setPid(UUID.randomUUID().toString().replace("-", ""));
            product.setPdate(new Date());

            //product 对象已经封装完毕，放入数据库
            int result = productService.insertProduct(product);
            map.put("result", result);
            return map;
        } catch (IOException e) {
            e.getStackTrace();
            map.put("result", -1);
            return map;
        }
    }


    @RequestMapping(value = "/toEdit")
    public String toEdit(String pid, Model model) {

        Product product = productService.selectById(pid);
        model.addAttribute("product", product);
        return "administrator/product/edit";
    }

    @RequestMapping(value = "/doEditAjax")
    @ResponseBody
    public Map<String, Object> doEditAjax(Product product,
                                          @RequestParam("upload") MultipartFile partFile) {

        // 存储路径
        // 获取服务器存放文件路径
        String imgPath = "products/1";
        String path = servletContext.getRealPath(imgPath);
        System.out.println("path:"+path);
        File uploadFile = new File(path);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }

        Map<String,Object> map = new HashMap<>();

        if (partFile.getSize() != 0) {
            //需要重新上传文件
            // 获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
            //String header = partFile.getHeader("content-disposition");

            // 获取文件名
            //String fileName = getFileName(header);
            String fileName = partFile.getOriginalFilename();

            //重命名
            String newFileName = UUID.randomUUID().toString();

            int lastIndexOfDot = fileName.lastIndexOf(".");
            if (lastIndexOfDot != -1) {
                // 有后缀,则要得到后缀
                String subfix = fileName.substring(lastIndexOfDot);
                newFileName = newFileName + subfix;
            }
            try {
                // 把文件写到指定路径
                //part.write(uploadFile + File.separator + newFileName);
                File file = new File(uploadFile + File.separator + newFileName);
                partFile.transferTo(file);
                product.setPimage(imgPath+"/"+newFileName);
            } catch (IOException e) {
                e.getStackTrace();
                map.put("result", -1);
                return map;
            }
        }

        //product 对象已经封装完毕，放入数据库
        int result = productService.updateProduct(product);
        if (result == 1) {
            map.put("result", product.getPid());
            return map;
        }
        map.put("result", 0);
        return map;
    }
}
