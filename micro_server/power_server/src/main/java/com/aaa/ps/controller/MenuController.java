package com.aaa.ps.controller;

import com.aaa.common.entity.Menu;
import com.aaa.ps.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author
 * @ClassName MenuController
 * @Description TODO
 * @date 2020/8/25 16:32
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 根据Id删除
     * @param menuId
     * @return
     */
    @DeleteMapping("deleteByPrimaryKey")
    public int deleteByPrimaryKey(Integer menuId){
        return menuService.deleteByPrimaryKey(menuId);
    }

    /**
     * 添加
     * @param record
     * @return
     */
    @PostMapping("insertSelective")
    public int insertSelective(Menu record){
        return menuService.insertSelective(record);
    }

    /**
     * 根据ID查询
     * @param menuId
     * @return
     */
    @GetMapping("selectByPrimaryKey")
    public Menu selectByPrimaryKey(Integer menuId){
        return menuService.selectByPrimaryKey(menuId);
    }

    /**
     * 更新
     * @param record
     * @return
     */
    @PutMapping("updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(Menu record){
        return menuService.updateByPrimaryKeySelective(record);
    }

    /**
     * 列表查询
     * @return
     */
    @GetMapping("selectAll")
    public List<Menu> selectAll(){
        return menuService.selectAll();
    }

}
