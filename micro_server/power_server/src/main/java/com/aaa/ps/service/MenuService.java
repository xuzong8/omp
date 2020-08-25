package com.aaa.ps.service;

import com.aaa.common.entity.Menu;

import java.util.List;

/**
 * @author
 * @Description TODO
 * @date 2020/8/25 16:26
 */
public interface MenuService {
    /**
     * 根据Id删除
     * @param menuId
     * @return
     */
    int deleteByPrimaryKey(Integer menuId);

    /**
     * 添加
     * @param record
     * @return
     */
    int insertSelective(Menu record);

    /**
     * 根据ID查询
     * @param menuId
     * @return
     */
    Menu selectByPrimaryKey(Integer menuId);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * 列表查询
     * @return
     */
    List<Menu> selectAll();
}
